package Server.Protocols;

import Server.LobbyHandler;
import Server.ServerData.Lobby;
import Server.ServerData.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class ShowLobbies {
    public static JSONObject function(JSONObject obj, LobbyHandler lobbyHandler){
        JSONObject json = new JSONObject();
        json.put("PROTOCOL","SHOW_LOBBIES");
        json.put("SUCCES",1);

        List<Lobby>lobbies = lobbyHandler.getLobbyList();
        JSONArray arr =new JSONArray();
        for(Lobby lobby : lobbies){
            JSONObject l = new JSONObject();
            l.put("name",lobby.getId());
            l.put("nr_players",lobby.getPlayerList().size());
            arr.add(l);
        }
        json.put("lobbies",arr);
        return json;
    }

}
