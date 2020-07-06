package blokus.ViewFx;

import blokus.Controler.Controler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author selim
 */
public class Gui extends Application {

    private static final int SPACING = 10;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 750;
    private static final Image ICON = new Image(Gui.class.getResourceAsStream("blokusIcon.PNG"));
    private static final String TITLE = "BLOKUS";
    private static final VBox ROOT = new VBox();
    private MenuBar header;
    private HBox content;
    private HBox Buttons;

    @Override
    public void start(Stage primaryStage) throws Exception {

        init(primaryStage);
        Scene scene = new Scene(ROOT, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle(TITLE);
        primaryStage.getIcons().add(ICON);
        primaryStage.show();
    }

    public static void main(String[] args) {
        
        launch(args);
    }

    private void init(Stage primaryStage) {

        Controler controler = new Controler();
        controler.newGame();
        initMenuBar(primaryStage, controler);
        initButtons(controler);
        GameFx gameFx = new GameFx(controler.getGame());
        this.content = gameFx.getRoot();
        initScene();
    }

    private void initButtons(Controler controler) {

        ButtonsFx buttons = new ButtonsFx();
        buttons.getRestart().setOnAction((event) -> {
            controler.resetGame();
        });
        buttons.getSkip().setOnAction((event) -> {
            controler.skip();
        });
        buttons.getLeave().setOnAction((event) -> {
            controler.leave();
        });

        this.Buttons = buttons;

    }

    private void initMenuBar(Stage primaryStage, Controler controler) {

        MenuFx menuFx = new MenuFx();
        menuFx.getNewGame().setOnAction((event) -> {
            
            controler.newGame();
        });
        menuFx.getExit().setOnAction((event) -> {
            primaryStage.close();
        });
        menuFx.getHistory().setOnAction((event) -> {
                        
            initHistory(primaryStage, controler.getHistory());

        });
        this.header = menuFx.getMenuBar();
    }

    private void initHistory(Stage primaryStage, String textToInsert) {

        TextArea text = new TextArea();
        text.setText(textToInsert);
        text.setEditable(false);
        BorderPane secondWindow = new BorderPane(text);
        Scene secondScene = new Scene(secondWindow, 400, 300);
        Stage historyWindow = new Stage();
        historyWindow.setTitle("History");
        historyWindow.setScene(secondScene);
        historyWindow.setX(primaryStage.getX() + 200);
        historyWindow.setY(primaryStage.getY() + 100);
        
        historyWindow.show();

    }

    private void initScene() {

        this.ROOT.getChildren().clear();
        this.ROOT.getChildren().addAll(this.header, this.content, this.Buttons);

    }

}
