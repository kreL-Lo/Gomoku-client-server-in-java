package Server;

import Server.ServerData.Lobby;

import java.util.ArrayList;
import java.util.List;

public class LobbyHandler {
    List<Lobby> lobbyList= new ArrayList<>();
    public void addLobby(Lobby l ){
        lobbyList.add(l);
    }
    public Lobby getLobby(String id ){
        for(Lobby l: lobbyList){
            if(l.getId().compareTo(id)==0){
                return l;
            }
        }
        return null;
    }

    public List<Lobby> getLobbyList() {
        return lobbyList;
    }
}
