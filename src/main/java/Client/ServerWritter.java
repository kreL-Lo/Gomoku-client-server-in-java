package Client;

import org.json.simple.JSONObject;

import java.io.DataOutputStream;
import java.net.Socket;

public class ServerWritter implements  Runnable{
    Socket s;
    JSONObject data= null;
    DataOutputStream out ;
    boolean isData = false;
    public ServerWritter(Socket s) {
        try {
            out = new DataOutputStream(s.getOutputStream());
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    @Override
    public void run() {
        System.out.println("Writting");
        while(true){
            //wait for updating data to be set

            while (isData==false){
                try {
                    Thread.sleep(1);
                }
                catch (Exception e){
                    System.out.println(e);
                }
            }
            isData= false;
            byte [] bytes = data.toString().getBytes();
            try {
                out.write(bytes);
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public void setData(JSONObject data) {
        this.data = data;
        System.out.println(data);
        this.isData= true;
    }
}
