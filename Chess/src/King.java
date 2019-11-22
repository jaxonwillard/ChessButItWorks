
import java.util.ArrayList;

public class King extends Piece {
    public King(Position p, boolean isWhite, PieceImages images, Board bd) {
        this.setFitWidth(this.width);
        this.setFitHeight(this.width);
        this.pos = p;
        this.isWhite = isWhite;
        this.img = this.isWhite ? images.whiteKing : images.blackKing;
        this.setImage(img);
        this.board = bd;
    }

        public ArrayList<Position> getLegalMoves() {
        //TODO add castleing @Josh
        ArrayList<Position> moves = new ArrayList<>();
        for (int rows = this.pos.row - 1; rows <= this.pos.row + 1; rows++) {
            for (int cols = this.pos.col - 1; cols <= this.pos.col + 1; cols++) {
                if (rows >= 0 && rows <= 7 && cols >= 0 && cols <= 7) {
                    if (this.board.tiles[cols][rows].hasPiece){
                        if (this.board.tiles[cols][rows].piece.isWhite ^ this.isWhite){
                            moves.add(new Position(cols, rows));}
                        } else{
                            moves.add(new Position(cols, rows));}
                }
            }
        }

        return moves;
    }

    public String toString() {
        return "King at " + this.pos.col + "x" + this.pos.row;
    }
}
