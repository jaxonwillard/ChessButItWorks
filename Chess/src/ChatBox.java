import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class ChatBox extends VBox {
    private Integer cfIndex=0;
    private int cfLimit = 15;
    private Queue<String> messages = new LinkedList<>();
    private Text[] chatText = new Text[cfLimit];


    public ChatBox(){
        VBox chatField = new VBox();
        TextField textField = new TextField("Type Here");
        for ( int i=0; i < chatText.length; i++ ){
            chatText[i] = new Text(" ");
            chatField.getChildren().add(chatText[i]);
        }
        this.getChildren().addAll(chatField, textField);
//        getMessages();


        textField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER){
                receiveText("You>" + textField.getText());
                sendChatMessage(createChatMessage(textField.getText()));
                textField.setText("");
            }
        });
    }
    public void receiveText(String text){
        if (cfIndex<cfLimit){
            chatText[cfIndex].setText(text);
            cfIndex++;
        } else{
            for (int i=0; i<chatText.length-1; i++){
                chatText[i].setText(chatText[i+1].getText()); }
            chatText[cfIndex-1].setText(text); }
    }
    public GameMessage createChatMessage(String message){
        return new GameMessage(MessageType.CHAT, message);
    }

    public void sendChatMessage(GameMessage m){
        try{
            Chess.out.writeObject(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void onMessageReceived(GameMessage m){
        receiveText("Them>" + m.chatMessage);
    }

    public void getMessages(){
        while(true){
            try{
                if ((GameMessage)Chess.in.readObject() != null){
                    GameMessage received = (GameMessage)Chess.in.readObject();
                    this.onMessageReceived(received);
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }







}
