package blokus.ViewFx;

import blokus.Interface.Observer;
import blokus.Model.BlokusException;
import blokus.Model.Game;
import blokus.Model.Piece;
import blokus.Model.Player;
import blokus.Model.Position;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toCollection;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author selim
 */
public class GameFx implements Observer {

    private static final int SPACING = 10;

    private final Game game;
    private final HBox root;
    private final VBox playerFxCont;
    private final VBox canvas;

    private final BoardFx boardFx;
    private final ArrayList<PlayerFx> players;

    public GameFx(Game game) {

        this.game = game;
        canvas = new VBox();
        playerFxCont = new VBox();
        players = initPLayerFx();
        boardFx = initBoard();
        initFunction();
        game.addObs(this);
        root = new HBox(SPACING, playerFxCont, canvas);
    }

    private BoardFx initBoard() {

        BoardFx res = new BoardFx(game.getBoard());
        canvas.getChildren().add(res.getGrid());
        canvas.setAlignment(Pos.CENTER);
        return res;

    }

    private ArrayList<PlayerFx> initPLayerFx() {

        ArrayList<PlayerFx> playersFx = game.getPlayers().stream().map(player -> {
            return new PlayerFx(player);
        }).collect(toCollection(ArrayList::new));
        playersFx.forEach(player -> {

            playerFxCont.getChildren().add(player.getRoot());

        });
        return playersFx;
    }

    private void initFunction() {

        boardFx.getBoxes().stream().flatMap(line -> {
            return line.stream();
        }).forEach(boxFx -> {
            setDragAndDropGestureOnBoard(boxFx);
        });

    }

    private void setDragAndDropGestureOnBoard(BoxFx boxFx) {

        setOnDragMove(boxFx);
        setOnDragEnterAndOut(boxFx);

    }

    private void setOnDragMove(BoxFx boxFx) {

        Rectangle target = boxFx.getRectangle();

        target.setOnDragOver((event) -> {

            Position pos = boxFx.getPosition();
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            Dragboard db = event.getDragboard();
            File file = db.getFiles().get(0);
            try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
                Piece piece = (Piece) in.readObject();

                if (boardFx.getBoard().canBeInsert(game.getCurrentPlayer(), piece, pos)) {
                    if (target.getFill().equals(Paint.valueOf("GREY"))) {
                        target.setFill(Paint.valueOf("Orange"));
                        setOnDragDone(target, game.getCurrentPlayer(), piece, pos);
                    }

                } else {
                    target.setOnDragDropped((eventDropped) -> {
                        //nothing
                    });
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(BoardFx.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(BoardFx.class.getName()).log(Level.SEVERE, null, ex);
            }
            event.consume();

        });
    }

    private void setOnDragEnterAndOut(BoxFx boxFx) {

        Rectangle rectangle = boxFx.getRectangle();
        rectangle.setOnDragEntered((event) -> {
            event.acceptTransferModes(TransferMode.COPY);
            if (rectangle.getFill().equals(Paint.valueOf("White"))) {
                rectangle.setFill(Paint.valueOf("Grey"));
            }
        });
        rectangle.setOnDragExited((event) -> {
            event.acceptTransferModes(TransferMode.COPY);
            if (rectangle.getFill().equals(Paint.valueOf("Orange")) || rectangle.getFill().equals(Paint.valueOf("Grey"))) {
                rectangle.setFill(Paint.valueOf("white"));
            }
        });

    }

    private void setOnDragDone(Rectangle target, Player player, Piece piece, Position pos) {

        target.setOnDragDropped((event) -> {
            System.out.println("dropped");
            try {
                Piece piecePlayer = player.getPiece(player.getBag().indexOf(piece));
                game.play(player, piecePlayer, pos);
                event.setDropCompleted(true);
            } catch (BlokusException ex) {
                System.out.println(ex.getMessage());
            }
        });

    }

    @Override
    public void update() {

        if (game.isOver()) {
            Alert alert = new Alert(Alert.AlertType.NONE, "Game is Over", new ButtonType("ok"));
            alert.show();
        }
    }

    public HBox getRoot() {
        return root;
    }

    public BoardFx getBoardFx() {
        return boardFx;
    }

    public ArrayList<PlayerFx> getPlayers() {
        return players;
    }

}
