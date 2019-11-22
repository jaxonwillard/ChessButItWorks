import javafx.scene.image.Image;

import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(Position p, boolean isWhite, PieceImages images, Board bd) {
        this.setFitWidth(this.width);
        this.setFitHeight(this.width);
        this.pos = p;
        this.isWhite = isWhite;
        this.img = this.isWhite ? images.whiteKnight : images.blackKnight;
        this.setImage(img);
        this.board = bd;
    }

    public ArrayList<Position> getLegalMoves() {
        ArrayList<Position> moves = new ArrayList<>();
        // left two up one
        if (this.pos.col - 2 >= 0 && this.pos.row-1 >= 0){
            if (this.board.tiles[this.pos.col-2][this.pos.row-1].hasPiece){
                if (this.board.tiles[this.pos.col-2][this.pos.row-1].piece.isWhite ^ this.isWhite){
                    moves.add(new Position(this.pos.col-2, this.pos.row-1));
                }
            }
            else moves.add(new Position(this.pos.col-2, this.pos.row-1));}

        // right two up one
        if (this.pos.col + 2 <= 7 && this.pos.row-1 >= 0){
            if (this.board.tiles[this.pos.col+2][this.pos.row-1].hasPiece){
                if (this.board.tiles[this.pos.col+2][this.pos.row-1].piece.isWhite ^ this.isWhite){
                    moves.add(new Position(this.pos.col+2, this.pos.row-1));
                }
            }
            else moves.add(new Position(this.pos.col+2, this.pos.row-1));}

        // right two down one
        if (this.pos.col + 2 <= 7 && this.pos.row+1 <= 7){
            if (this.board.tiles[this.pos.col+2][this.pos.row+1].hasPiece){
                if (this.board.tiles[this.pos.col+2][this.pos.row+1].piece.isWhite ^ this.isWhite){
                    moves.add(new Position(this.pos.col+2, this.pos.row+1));
                }
            }
            else moves.add(new Position(this.pos.col+2, this.pos.row+1));}

        // left one up two
        if (this.pos.col - 1 >= 0 && this.pos.row-2 >= 0){
            if (this.board.tiles[this.pos.col-1][this.pos.row-2].hasPiece){
                if (this.board.tiles[this.pos.col-1][this.pos.row-2].piece.isWhite ^ this.isWhite){
                    moves.add(new Position(this.pos.col-1, this.pos.row-2));
                }
            }
            else moves.add(new Position(this.pos.col-1, this.pos.row-2));}

        // right one up two
        if (this.pos.col + 1 <= 7 && this.pos.row-2 >= 0){
            if (this.board.tiles[this.pos.col+1][this.pos.row-2].hasPiece){
                if (this.board.tiles[this.pos.col+1][this.pos.row-2].piece.isWhite ^ this.isWhite){
                    moves.add(new Position(this.pos.col+1, this.pos.row-2));
                }
            }
            else moves.add(new Position(this.pos.col+1, this.pos.row-2));}

        // left one down two
        if (this.pos.col - 1 >= 0 && this.pos.row+2 <= 7){
            if (this.board.tiles[this.pos.col-1][this.pos.row+2].hasPiece){
                if (this.board.tiles[this.pos.col-1][this.pos.row+2].piece.isWhite ^ this.isWhite){
                    moves.add(new Position(this.pos.col-1, this.pos.row+2));
                }
            }
            else moves.add(new Position(this.pos.col-1, this.pos.row+2));}

        // right one down two
        if (this.pos.col + 1 <= 7 && this.pos.row+2 <= 7){
            if (this.board.tiles[this.pos.col+1][this.pos.row+2].hasPiece){
                if (this.board.tiles[this.pos.col+1][this.pos.row+2].piece.isWhite ^ this.isWhite){
                    moves.add(new Position(this.pos.col+1, this.pos.row+2));
                }
            }
            else moves.add(new Position(this.pos.col+1, this.pos.row+2));}


        // left two up one
        if (this.pos.col - 2 >= 0 && this.pos.row+1 <= 7){
            if (this.board.tiles[this.pos.col-2][this.pos.row+1].hasPiece){
                if (this.board.tiles[this.pos.col-2][this.pos.row+1].piece.isWhite ^ this.isWhite){
                    moves.add(new Position(this.pos.col-2, this.pos.row+1));
                }
            }
            else moves.add(new Position(this.pos.col-2, this.pos.row+1));}


        return moves;
    }
    public String toString() {
        return "Knight at " + this.pos.col + "x" + this.pos.row;
    }
}
