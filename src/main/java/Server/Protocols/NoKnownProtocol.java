package Server.Protocols;

import org.json.simple.JSONObject;

public class NoKnownProtocol {
    public static JSONObject function(){
        JSONObject obj =new JSONObject();
        obj.put("PROTOCOL","NO_KNOWN_PROTOCOL");
        obj.put("SUCCES",0);
        return obj;
    }
}
