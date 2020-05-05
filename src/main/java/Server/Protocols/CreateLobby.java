package Server.Protocols;

import Server.LobbyHandler;
import Server.ServerData.Board;
import Server.ServerData.Lobby;
import org.json.simple.JSONObject;

import java.util.List;

public class CreateLobby {
    public static JSONObject function(JSONObject object, LobbyHandler lobbyHandler){
        JSONObject json = new JSONObject();
        String str = object.get("name").toString();
        boolean available = true;
        for(Lobby l : lobbyHandler.getLobbyList())
        {
            System.out.println(l.getId());
            if(l.getId().compareTo(str)==0){
                available = false;
                break;
            }
        }
        if(available==false){
            return noAvailable();
        }
        Lobby lobby = new Lobby();
        Board board = new Board();
        lobby.setBord(board);
        lobby.setId(str);
        lobbyHandler.addLobby(lobby);
        json.put("PROTOCOL","CREATE_LOBBY");
        json.put("SUCCESS",1);
        json.put("ANSWER","SUCCESSFULLY CREATED");
        return json;
    }
    //two cases already created
    static JSONObject noAvailable(){
        JSONObject obj = new JSONObject();
        obj.put("PROTOCOL","CREATE_LOBBY");
        obj.put("SUCCESS",0);
        obj.put("ANSWER","LOBBY ALREADY CREATED");
        return obj;
    }
}
