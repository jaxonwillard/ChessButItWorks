import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Chess extends Application{
    public static Board board;
    public static Socket socket = null;
    public static ObjectOutputStream out = null;
    public static ObjectInputStream in = null;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter ip address or localhost: ");
        String ip = input.nextLine();
        connectToServer(ip, 58901);
        System.out.println("Connected... Let's Play!");
        BorderPane bp = new BorderPane();
        GraveyardPane graveyard = new GraveyardPane();
        StackPane topPane = new StackPane();
        ChatBox cb = new ChatBox();
        Board board = new Board(graveyard, topPane, ip.equals("localhost"), cb);
//        TODO add in the chatbox pane
        bp.setRight(cb);
        bp.setCenter(board);
        bp.setLeft(graveyard);
        bp.setTop(topPane);
        Scene sc = new Scene(bp);
        stage.setScene(sc);
        stage.show();
    }

    private static void connectToServer(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            System.out.println("Connected to Server");
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch(IOException e) {e.printStackTrace();}
    }
}