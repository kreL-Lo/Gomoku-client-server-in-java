package Server.ServerData;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Lobby {
    String id ;
    Board bord=null;
    List<Player> playerList = new ArrayList<>();

    public void setId(String id) {
        this.id = id;
    }
    public void addPlayer(Player p) {
        playerList.add(p);
    }

    public void setBord(Board bord) {
        this.bord = bord;
    }

    public Board getBord() {
        return bord;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }
    public Player findBySocket(Socket s){
        for(Player p : playerList){
            if(p.getSocket()==s)
            {
                return p;
            }
        }
        return null;

    }

    public String getId() {
        return id;
    }
}
