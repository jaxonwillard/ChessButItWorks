import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static Hashtable<Boolean, ObjectOutputStream> writers = new Hashtable<>();

    public static void main(String[] args) throws Exception {
        System.out.println("Chess Server is Running...");
        ExecutorService pool = Executors.newFixedThreadPool(2);
        try (ServerSocket listener = new ServerSocket(58901)) {
            pool.execute(new Handler(listener.accept(), true));
            pool.execute(new Handler(listener.accept(), false));
        }
    }

    private static class Handler implements Runnable{
        public Socket socket;
        public boolean isWhite;
        public ObjectOutputStream output;
        public ObjectInputStream input;

        Handler(Socket s, boolean white) {
            this.socket = s;
            this.isWhite = white;
        }

        @Override
        public void run() {
            try {
                System.out.println("In handler " + (this.isWhite ? "white" : "black"));
                this.output = new ObjectOutputStream(socket.getOutputStream());
                this.input = new ObjectInputStream(socket.getInputStream());
                writers.put(this.isWhite, output);

                System.out.println();
                while (true) {
                    try {
                        System.out.println("listening...");
                        GameMessage m  = (GameMessage) this.input.readObject();
                        writers.get(!this.isWhite).writeObject(m);
                        System.out.println("writing!...");
                        writers.get(!this.isWhite).flush();
                    } catch (Exception e) {e.printStackTrace();}
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                if (output != null) {
                    writers.remove(this.isWhite);
                }
                try {socket.close(); } catch (IOException e) {}
            }
        }
    }

}