package Client.toServerProtocols;

import org.json.simple.JSONObject;

public class ShowLobbies {
    public static JSONObject function(){
        JSONObject data = new JSONObject();
        data.put("PROTOCOL","SHOW_LOBBIES");
        return data;
    }
}
