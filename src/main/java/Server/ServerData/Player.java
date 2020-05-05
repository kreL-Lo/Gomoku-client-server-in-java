package Server.ServerData;

import java.net.Socket;

public class Player {
    String name;//pentru identificare
    Socket socket;
    String pieceColor=null;
    int turn =0;

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getTurn() {
        return turn;
    }

    public void setPieceColor(String pieceColor) {
        this.pieceColor = pieceColor;
    }

    public String getPieceColor() {
        return pieceColor;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getName() {
        return name;
    }
}
