import javafx.scene.image.Image;

import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(Position p, boolean isWhite, PieceImages images, Board bd) {
        this.setFitWidth(this.width);
        this.setFitHeight(this.width);
        this.pos = p;
        this.isWhite = isWhite;
        this.img = this.isWhite ?
                images.whiteQueen :
                images.blackQueen;
        this.setImage(img);
        this.board = bd;
    }

    public ArrayList<Position> getLegalMoves() {
        ArrayList<Position> moves = new ArrayList<>();
        moves = addDownLeft(moves);
        moves = addDownRight(moves);
        moves = addUpLeft(moves);
        moves = addUpRight(moves);
        moves = addUp(moves);
        moves = addLeft(moves);
        moves = addDown(moves);
        moves = addRight(moves);


        return moves;
    }
    public ArrayList<Position> addUp(ArrayList<Position> moves){
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
        return "Queen at " + this.pos.col + "x" + this.pos.row;
    }
}
