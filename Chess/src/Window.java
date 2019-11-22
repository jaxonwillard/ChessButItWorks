import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Window extends Application {
    public void start(Stage stage){
        ChatBox chatBox = new ChatBox();
        stage.setTitle("Chat away");
        stage.setHeight(175);
        Scene sc = new Scene(chatBox);
        stage.setScene(sc);
        stage.show();



    }
}
