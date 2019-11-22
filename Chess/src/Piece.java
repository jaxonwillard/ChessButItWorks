import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class Piece extends ImageView {
//    public String imgUrl;
    public Image img;
    public Position pos;
    public boolean isWhite;
    public boolean isCaptured;
    public int width = 40;
    public Board board;
    public abstract ArrayList<Position> getLegalMoves();
    public abstract String toString();
    public boolean isFirstMove = true;
    public void updateBoard(Board b) {
        this.board = b;
    }

}
