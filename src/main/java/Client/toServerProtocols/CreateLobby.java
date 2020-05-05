package Client.toServerProtocols;

import org.json.simple.JSONObject;

public class CreateLobby {
    public static JSONObject function(String name){
        JSONObject data = new JSONObject();
        data.put("PROTOCOL","CREATE_LOBBY");
                data.put("name",name);
                return data;
    }
}
