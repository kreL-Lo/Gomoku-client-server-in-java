package Server;

import Server.Protocols.*;
import org.json.simple.JSONObject;

import java.net.Socket;

public class ProtocolHandler {
    LobbyHandler lobbyHandler ;
    public ProtocolHandler (){
        lobbyHandler = new LobbyHandler();
    }

    int parser(JSONObject object){
        String protocol =object.get("PROTOCOL").toString();
        if(protocol.compareTo("SHOW_LOBBIES")==0){
            return 1;
        }
        else if(protocol.compareTo("CREATE_LOBBY")==0){
            return 2;
        }else if(protocol.compareTo("JOIN_LOBBY")==0){
            return 3;
        }
        else if(protocol.compareTo("SEND_MOVE")==0){
            return 4;
        }
        return 0;
    }
    public JSONObject response(JSONObject object, Socket s) {
        System.out.println(object);
        int cnt = parser(object);
        if(cnt==1){
            return ShowLobbies.function(object,lobbyHandler);
        }
        else if(cnt==2){
            return CreateLobby.function(object,lobbyHandler);
        }
        else if(cnt==3){
            return JoinLobby.function(object,lobbyHandler,s);
        }
        if(cnt==4){
            return SendMove.function(object,s,lobbyHandler);
        }
        return NoKnownProtocol.function();
    }
}
