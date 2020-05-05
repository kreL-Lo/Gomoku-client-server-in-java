package Client.toServerProtocols;

import org.json.simple.JSONObject;

public class SendMove {
    public static JSONObject function(String roomId,int x, int y){
        JSONObject data = new JSONObject();
        data.put("PROTOCOL", "SEND_MOVE");
        data.put("move_X",Integer.toString(x));//[0-14]
        data.put("move_Y",Integer.toString(y));//[0-14]
        data.put("roomId", roomId);//
        return data;
    }
}
