import java.io.Serializable;
enum MessageType {
    CHAT,
    MOVE,
}
public class GameMessage implements Serializable {
    public MessageType type;
    public String chatMessage;
    public Position[] movePositions;

    GameMessage(MessageType type, Position[] move, String chatMessage) {
        this.type = type;
        this.movePositions = move;
        this.chatMessage = chatMessage;
    }
    GameMessage(MessageType type, String chatMessage){
        this.type = type;
        this.chatMessage = chatMessage;
    }
}
