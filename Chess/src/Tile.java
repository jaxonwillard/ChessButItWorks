import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Tile extends StackPane {
    boolean isWhite;
    public Piece piece;
    public Position position;
    public boolean hasPiece;
    public SimpleBooleanProperty isHighlighted = new SimpleBooleanProperty(false);
    public Rectangle highlight;


    public Tile(boolean isWhite, Position position){
        this.position = position;
        this.isWhite = isWhite;
        Color c = isWhite ? Color.BURLYWOOD : Color.GREEN;
        Background b = new Background(new BackgroundFill(c, null, null));
        this.setBackground(b);
        this.getChildren().add(new Rectangle(50, 50, Color.TRANSPARENT));
    }

    public void setPiece(Piece p) {
        this.piece = p;
        if (p != null) {
            this.getChildren().add(1, this.piece);
            this.hasPiece = true;
            this.piece.pos = this.position;
            this.piece.isFirstMove = false;
        }
    }

    public void startHighlight() {
        this.highlight = new Rectangle(this.getWidth(), this.getHeight(), Color.YELLOW);
        FadeTransition ft = new FadeTransition();
        ft.setNode(this.highlight);
        ft.setFromValue(0.01);
        ft.setToValue(0.8);
        ft.setDuration(Duration.millis(1000));
        ft.setCycleCount(Animation.INDEFINITE);
        ft.setAutoReverse(true);
        this.getChildren().add(this.highlight);
        ft.play();
    }

    public void clearHighlight() {
        this.getChildren().remove(this.highlight);
        this.highlight = null;
    }


}
