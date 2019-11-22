import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Board extends GridPane implements Serializable {
    public Tile[][] tiles;
    public Tile activeTile = null;
    public GraveyardPane graveyard;
    public StackPane topPane;
    public boolean whitePlayer;
    public SimpleBooleanProperty isWhiteTurn = new SimpleBooleanProperty(true);
    public ArrayList<Piece> capturedPieces;
    public ChatBox chatBox;

    public Board(GraveyardPane graveyard, StackPane topPane, boolean white, ChatBox chatBox) {
        this.chatBox = chatBox;
        this.whitePlayer = white;
        PieceImages pi = new PieceImages();
        this.topPane = topPane; //TODO add functionality to topPane (signaling check, when pawn gets to end it can change to another piece) etc.
        this.graveyard = graveyard;
        this.tiles = new Tile[8][8];
        putTilesOnBoard();
        addPieces(pi);
        this.isWhiteTurn.addListener((o,b,b1) -> {
            this.getMessages();
        });
        if (!this.whitePlayer) {
            System.out.println("Waiting for white to go first!");
            this.getMessages();
        }
    }

    /**
     * populates board with appropriate tiles and pieces
     */
    private void putTilesOnBoard() {
        boolean isWhite = true;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Tile t = new Tile(isWhite, new Position(x, y));
                t.setOnMouseClicked(e -> {
                    if (t.piece != null && this.activeTile == null && this.whitePlayer == t.piece.isWhite) {
                        System.out.println(t.piece.toString());
                        this.activeTile = t;
                        ArrayList<Position> moves = t.piece.getLegalMoves();
                        this.highlightAvailableMoves(moves, t.isWhite);
                    } else if (t.isHighlighted.getValue() && this.activeTile.piece != null ) {
                        movePieces(t);
                    } else {
                        this.activeTile = null;
                        this.clearHighlightedTiles();
                    }
                    this.updatePieceBoards();
                });
                t.isHighlighted.addListener((o, b, b1) -> {
                    if (o.getValue() == true) {
                        t.startHighlight();
                    } else {
                        t.clearHighlight();
                    }
                });
                this.tiles[x][y] = t;
                this.add(this.tiles[x][y], x, y);
                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }

    }

    public void getMessages() {
        while(true){
            try {
                GameMessage received = (GameMessage)Chess.in.readObject();
                if (received != null && received.type == MessageType.MOVE) {
                    this.onMessageReceived(received);
                    break;
                }
                else if (received != null && received.type == MessageType.CHAT){
                    chatBox.onMessageReceived(received);
                    break;
                }
            } catch (Exception e) {e.printStackTrace();}
        }
    }

    public GameMessage createMoveMessage(Position from, Position to) {
        Position[] moves = {from ,to};
        return new GameMessage(MessageType.MOVE, moves, null);
    }

    public void sendMessage(GameMessage m) {
        try {
            Chess.out.writeObject(m);
        } catch (Exception e) {e.printStackTrace();}

    }


    public void onMessageReceived(GameMessage m) {
        Position from = m.movePositions[0];
        Position to = m.movePositions[1];
        System.out.println(from.row + "x" + from.col + " to " + to.row + "x" + to.col);
        Tile fromTile = this.tiles[from.col][from.row];
        Tile toTile = this.tiles[to.col][to.row];

        try {
            toTile.getChildren().remove(1);
        } catch (Exception e) {}
        if (toTile.hasPiece) graveyard.addPiece(toTile.piece); //adds the piece to the captured pieces arrayList
        toTile.setPiece(fromTile.piece);
        fromTile.setPiece(null);
        fromTile.hasPiece = false;
        this.clearHighlightedTiles();
        checks();
        updatePieceBoards();
    }

    private void updatePieceBoards() {
        for (Tile[] row : this.tiles) {
            for (Tile t : row) {
                if (t.piece != null) {
                    t.piece.updateBoard(this);
                }
            }
        }
    }

    private void clearHighlightedTiles() {
        for (Tile[] row : this.tiles) {
            for (Tile t : row) {
                if (t.isHighlighted.getValue()) {
                    t.isHighlighted.set(false);
                }
            }
        }
    }


    private void addPieces(PieceImages pi) {


        for (Tile[] row : this.tiles) {
            for (Tile t : row) {
                if (t.position.row == 1) {
                    t.setPiece(new Pawn(t.position, true, pi, this));
                    t.piece.isFirstMove = true; //setPiece sets this to false, this is where we will make it true just once
                }
                else if (t.position.row == 6) {
                    t.setPiece(new Pawn(t.position, false, pi, this));
                    t.piece.isFirstMove = true; //setPiece sets this to false, this is where we will make it true just once

                }
                if (t.position.row == 0) {
                    switch (t.position.col) {
                        case 0:
                        case 7:
                            t.setPiece(new Rook(t.position, true, pi, this));
                            break;
                        case 1:
                        case 6:
                            t.setPiece(new Knight(t.position, true, pi, this));
                            break;
                        case 2:
                        case 5:
                            t.setPiece(new Bishop(t.position, true, pi, this));
                            break;
                        case 3:
                            t.setPiece(new Queen(t.position, true, pi, this));
                            break;
                        case 4:
                            t.setPiece(new King(t.position, true, pi, this));
                            break;
                        default:
                            break;
                    }
                } else if (t.position.row == 7) {
                    switch (t.position.col) {
                        case 0:
                        case 7:
                            t.setPiece(new Rook(t.position, false, pi, this));
                            break;
                        case 1:
                        case 6:
                            t.setPiece(new Knight(t.position, false, pi, this));
                            break;
                        case 2:
                        case 5:
                            t.setPiece(new Bishop(t.position, false, pi, this));
                            break;
                        case 3:
                            t.setPiece(new Queen(t.position, false, pi, this));
                            break;
                        case 4:
                            t.setPiece(new King(t.position, false, pi, this));
                            break;
                        default:
                            break;
                    }
                }

            }
        }
    }

    /**
     * run checks on both kings to see if one is in checkmate or check
     */
    private void checks(){
        checks(true);
        checks(false);
    }

    /**
     * run checks on one king to see if it is in checkmate or check
     * @param isWhite
     */
    private void checks(boolean isWhite) {
        String player = isWhite ? "White" : "Black";
        if (check(isWhite)) {
            if (checkMate(isWhite)) System.out.println("Game over! " + player + " is in checkmate!");
            else System.out.println(player + " is in check!"); }
    }

    /**
     * check to see if a king is in check
     * @param isWhite
     * @return
     */
    private boolean check(boolean isWhite) {
        Tile kingTile = null;
        ArrayList<Position> opponentMoves = new ArrayList<>();
        // find king's tile and populate opponent moves
        for (Tile[] t : this.tiles) {
            for (Tile tile : t) {
                if (tile.piece instanceof King && tile.piece.isWhite == isWhite) {
                    kingTile = tile;
                }
                if (tile.hasPiece && tile.piece.isWhite != isWhite) {
                    opponentMoves.addAll(tile.piece.getLegalMoves());
                }
            }
        }
        // compare every position with king's position
        for (Position opponentMove : opponentMoves) {
            if (opponentMove.equals(kingTile.position)) {
                return true;
            }
        }
        return false;
    }

    /**
     * check to see if a king is in checkmate
     * @param isWhite
     * @return
     */
    private boolean checkMate(boolean isWhite){
        ArrayList<Position> kingMoves = new ArrayList<>();
        ArrayList<Position> opponentMoves = new ArrayList<>();
        // find king's tile and populate opponent moves
        for (Tile[] t : this.tiles) {
            for (Tile tile : t) {
                if (tile.piece instanceof King && tile.piece.isWhite == isWhite) {
                    kingMoves = tile.piece.getLegalMoves();
                }
                if (tile.hasPiece && tile.piece.isWhite != isWhite) {
                    opponentMoves.addAll(tile.piece.getLegalMoves());
                }
            }
        }
        // check every possible move of the opponent against every possible move of the king, only return true if ALL
        // of the king's moves equal the opponent's
        int matchingTiles = 0;
        for (int i=0; i<kingMoves.size(); i++){
        for (Position opponentMove : opponentMoves) {
            if (opponentMove.equals(kingMoves.get(i))) {
                matchingTiles++;
                break;
            }
            }
        }
        return matchingTiles >= kingMoves.size();
    }

    private void highlightAvailableMoves(ArrayList<Position> moves, boolean isWhite) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position p = this.tiles[i][j].position;
                for (Position pos : moves) {
                    if (pos.col == p.col && pos.row == p.row) {
                        this.tiles[i][j].isHighlighted.set(true);
                    }
                }
            }
        }
    }
    private void movePieces(Tile t){
        Position from = this.activeTile.position;
        Position to = t.position;
        t.getChildren().remove(1);
        if (t.hasPiece) graveyard.addPiece(t.piece); //adds the piece to the captured pieces arrayList
        t.setPiece(activeTile.piece);
        this.activeTile.setPiece(null);
        activeTile.hasPiece = false;
        this.activeTile = null;
        this.clearHighlightedTiles();
        checks();
        GameMessage toSend = this.createMoveMessage(from, to);
        this.updatePieceBoards();
        this.sendMessage(toSend);
        this.isWhiteTurn.set(!isWhiteTurn.getValue());
    }
}
