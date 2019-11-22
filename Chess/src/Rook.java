

import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(Position p, boolean isWhite, PieceImages images, Board bd) {
        this.setFitWidth(this.width);
        this.setFitHeight(this.width);
        this.pos = p;
        this.isWhite = isWhite;
        this.img = this.isWhite ?
                images.whiteRook :
                images.blackRook;
        this.setImage(img);
        this.board = bd;
        
    }

        public ArrayList<Position> getLegalMoves() {
        ArrayList<Position> moves = new ArrayList<>();
            moves = addUp(moves);
            moves = addDown(moves);
            moves = addRight(moves);
            moves = addLeft(moves);
        return moves;
    }
    public ArrayList<Position> addUp(ArrayList<Position> moves){
        //TODO add castleing @Josh
        boolean done = false;
        int row = this.pos.row-1;
        while (!done){
            if (row >= 0){
                if (this.board.tiles[this.pos.col][row].hasPiece){
                    if (this.board.tiles[this.pos.col][row].piece.isWhite ^ this.isWhite){
                        moves.add(new Position(this.pos.col, row));
                        break;
                    }else break;
                }
                moves.add(new Position(this.pos.col, row));
                row--;
            }
            else done = true;
        }
        return moves;
    }


    public ArrayList<Position> addDown(ArrayList<Position> moves){
        boolean done = false;
        int row = this.pos.row+1;
        while (!done){
            if (row <= 7){
                if (this.board.tiles[this.pos.col][row].hasPiece){
                    if (this.board.tiles[this.pos.col][row].piece.isWhite ^ this.isWhite){
                        moves.add(new Position(this.pos.col, row));
                        break;
                    }else break;
                }
                moves.add(new Position(this.pos.col, row));
                row++;
            }
            else done = true;
        }
        return moves;
    }

    public ArrayList<Position> addRight(ArrayList<Position> moves){
        boolean done = false;
        int col = this.pos.col+1;
        while (!done){
            if (col <= 7){
                if (this.board.tiles[col][this.pos.row].hasPiece){
                    if (this.board.tiles[col][this.pos.row].piece.isWhite ^ this.isWhite){
                        moves.add(new Position(col, this.pos.row));
                        break;
                    }else break;
                }
                moves.add(new Position(col, this.pos.row));
                col++; }
            else done = true; }
        return moves;
    }

    public ArrayList<Position> addLeft(ArrayList<Position> moves){
        boolean done = false;
        int col = this.pos.col-1;
        while (!done){
            if (col >= 0){
                if (this.board.tiles[col][this.pos.row].hasPiece){
                    if (this.board.tiles[col][this.pos.row].piece.isWhite ^ this.isWhite){
                        moves.add(new Position(col, this.pos.row));
                        break;
                    }else break;
                }
                moves.add(new Position(col, this.pos.row));
                col--;
            }
            else done = true;
        }
        return moves;

    }




    public String toString() {
        return "Rook at " + this.pos.col + "x" + this.pos.row;
    }
}
