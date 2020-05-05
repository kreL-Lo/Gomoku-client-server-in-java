package Client.toServerProtocols;

import org.json.simple.JSONObject;

public class JoinLobby {
    public static JSONObject function(String name,String roomId){
            JSONObject data = new JSONObject();
            data.put("PROTOCOL", "JOIN_LOBBY");
            data.put("name", name);
            data.put("roomId", roomId);
            return data;
    }
}
