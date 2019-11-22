import javafx.scene.image.Image;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(Position p, boolean isWhite, PieceImages images, Board bd) {
        this.setFitWidth(this.width);
        this.setFitHeight(this.width);
        this.pos = p;
        this.isWhite = isWhite;
        this.img = this.isWhite ? images.whiteBishop: images.blackBishop;
        this.setImage(img);
        this.board = bd;
    }

    public ArrayList<Position> getLegalMoves() {
        ArrayList<Position> moves = new ArrayList<>();
        moves = addDownLeft(moves);
        moves = addUpLeft(moves);
        moves = addUpRight(moves);
        moves = addDownRight(moves);
        return moves;
    }
    public ArrayList<Position> addDownLeft(ArrayList<Position> moves){
        int row = this.pos.row+1;
        int col = this.pos.col-1;
        while(row <= 7 && col >= 0) {
                if (this.board.tiles[col][row].hasPiece) {
                    if (this.board.tiles[col][row].piece.isWhite ^ this.isWhite) {
                        moves.add(new Position(col, row));
                        break;
                    } else break;
                }
                moves.add(new Position(col, row));
                row++;
                col--;
        }
        return moves;
    }

    public ArrayList<Position> addUpLeft(ArrayList<Position> moves){
        int row = this.pos.row-1;
        int col = this.pos.col-1;
        while(row >= 0 && col >= 0){
            if (this.board.tiles[col][row].hasPiece){
                if (this.board.tiles[col][row].piece.isWhite ^ this.isWhite){
                    moves.add(new Position(col, row));
                    break;
                }
                else break;
            }
            moves.add(new Position(col, row));
            row--;
            col--;
}
        return moves;
    }

    public ArrayList<Position> addDownRight(ArrayList<Position> moves){
        int row = this.pos.row+1;
        int col = this.pos.col+1;
        while(row <= 7 && col <= 7){
                if (this.board.tiles[col][row].hasPiece){
                    if (this.board.tiles[col][row].piece.isWhite ^ this.isWhite){
                        moves.add(new Position(col, row));
                        break;
                    }
                    else break;
                }
                moves.add(new Position(col, row));
                row++;
                col++;
        }
        return moves;
    }

    public ArrayList<Position> addUpRight(ArrayList<Position> moves){
        int row = this.pos.row-1;
        int col = this.pos.col+1;
        while(row >= 0 && col <= 7){
            if (this.board.tiles[col][row].hasPiece){
                if (this.board.tiles[col][row].piece.isWhite ^ this.isWhite){
                    moves.add(new Position(col, row));
                    break;
                }
                else break;
            }
            moves.add(new Position(col, row));
            row--;
            col++;
        }
        return moves;
    }

    public String toString() {
        return "Bishop at " + this.pos.col + "x" + this.pos.row;
    }
}
