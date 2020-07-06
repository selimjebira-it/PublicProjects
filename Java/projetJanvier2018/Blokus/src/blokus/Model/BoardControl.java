package blokus.Model;

import java.util.ArrayList;

/**
 *
 * @author selim
 */
public final class BoardControl extends Board {

    private final ArrayList<Piece> piecePlayed;

    /**
     * constructor of the board controler
     * @param width the width of the board
     * @param height the height of the board
     */
    public BoardControl(int width, int height) {

        super(width, height);
        piecePlayed = new ArrayList<>();
    }

    /**
     * method to add a piece to the board
     * @param piece the piece to add
     * @param pos the first position of the piece
     * @param positionsOfPiece positions of the piece
     * @param color color of the piece
     */
    public void addPiece(Piece piece, Position pos, ArrayList<Position> positionsOfPiece, String color) {

        piece.setFirst(pos);
        getBox(positionsOfPiece).forEach(box -> {
            
            box.setColor(color);
        });

        piecePlayed.add(piece);

    }

    /**
     * method to check if the player can insert the piece at the specified position
     * @param player player who is insering the piece
     * @param piece the piece to insert
     * @param pos the position where he wants to put the piece
     * @return 
     */
    public boolean canBeInsert(Player player, Piece piece, Position pos) {
        ArrayList<Position> positions = piece.getPositionsWithFirst(pos);
        Position firstMove = new Position(player.getFirstMove().getX() * (getWidth() - 1), player.getFirstMove().getY() * (getHeight() - 1));
        if (player.getActivePieces().isEmpty()) {
            
            return canBeInsertFirst(positions,firstMove );
        } else {
            
            return canBeInsert(positions, piece.getColor());
        }
    }

    /**
     * method to insert a piece at a specified position
     * @param player the player whose insering
     * @param piece the piece to insert
     * @param pos the first position
     * @throws BlokusException 
     */
    public void insert(Player player, Piece piece, Position pos) throws BlokusException {

        
        String color = piece.getColor();
        ArrayList<Position> positionsOfPiece = piece.getPositionsWithFirst(pos);
        Position firstMove = new Position(player.getFirstMove().getX() * (getWidth() - 1), player.getFirstMove().getY() * (getHeight() - 1));
        

        if (player.getActivePieces().isEmpty()) {

            if (canBeInsertFirst(positionsOfPiece, firstMove)) {

                addPiece(piece, pos, positionsOfPiece, color);
                player.updateScore();
            } else {
                throw new BlokusException("Piece : " + positionsOfPiece
                        + " can't be insert because firstMove of player : " + player.getName()
                        + " should be : " + firstMove);
            }
        } else {
            System.out.println("player bag is not empty");
            if (canBeInsert(positionsOfPiece, color)) {

                addPiece(piece, pos, positionsOfPiece, color);
            } else {
                throw new BlokusException(piece.toString() + " can't be insert at : " + pos.toString() + " of player : " + player.getName());
            }
        }
    }

    /**
     * method to reset the board
     */
    public void reset() {

        grid.stream().flatMap(list -> list.stream()).forEach(Box::reset);
        piecePlayed.clear();
    }

    /**
     * method to check if a player can play 
     * @param player the player we want to check 
     * @return true if the player can make a move
     */
    public boolean canPlay(Player player) {

        ArrayList<Piece> piecesOnBoard = player.getActivePieces();
        ArrayList<Piece> piecesLeft = player.getInactivePieces();
        if (piecesOnBoard.isEmpty()) {
            return true;
        }
        if (piecesLeft.isEmpty()) {
            return false;
        }

        ArrayList<Box> allCornersFree = allCornersFree(piecesOnBoard);

        return piecesLeft.stream().anyMatch((Piece piece) -> {
            return allCornersFree.stream().anyMatch((Box box) -> {
                return canBeInsert(piece, box);
            });
        });
    }

}
