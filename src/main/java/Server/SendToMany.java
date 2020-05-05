package Server;

import org.json.simple.JSONObject;

import java.io.DataOutputStream;
import java.net.Socket;

public class SendToMany {

    public static void send(Socket s, JSONObject payLoad){
        DataOutputStream out=null;
        try {
            out = new DataOutputStream(s.getOutputStream());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        byte [] toSend = payLoad.toString().getBytes();
        try {
            out.write(toSend);
            out.flush();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
