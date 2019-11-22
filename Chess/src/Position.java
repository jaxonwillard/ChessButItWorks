import java.io.Serializable;

public class Position implements Serializable {
    public int col;
    public int row;
    public boolean canCapture;

    public Position(int x, int y) {
        this.col = x;
        this.row = y;
        this.canCapture = true;
    }



    public boolean equals(Position p) {
        return this.row == p.row && this.col == p.col;
    }
}