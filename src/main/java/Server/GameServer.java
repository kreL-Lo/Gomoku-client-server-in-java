package Server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.simple.parser.JSONParser;

public class GameServer {
    public static void main (String args[]){
        new GameServer(8000);

    }
    private Socket socket= null;
    private ServerSocket ss = null;



    boolean open=true;
    int port;

    public GameServer  (int port){
        //INIT VARIABLES
        this.port = port;
        try {
            ss = new ServerSocket(port);
            System.out.println("Listening on "+ InetAddress.getLocalHost().getHostAddress().trim() + ":" +port);
            Thread serverThread= new Thread(new Runnable() {
                //  rcv/snd data
                org.json.simple.JSONObject payLoad; // payload
                ProtocolHandler protocolHandler = new ProtocolHandler();
                byte [] data = new byte[1024];// payload
                @Override
                public void run() {
                    while(open){
                        try {
                            Socket p= ss.accept();
                            System.out.println("Accepted a client");
                            Thread clientThread = new Thread(new Runnable() {
                                    Socket s =p;
                                @Override
                                public void run() {
                                    try {
                                        DataInputStream in  = new DataInputStream(s.getInputStream());
                                        DataOutputStream out = new DataOutputStream(s.getOutputStream());
                                        while(open) {
                                            //reading byte
                                            //cnt stores the nr of bytes of the incoming data
                                            int cnt = in.read(data);
                                            //each charactes has a byte representation
                                            //so the strData must be a string equal to the cnt value, no more ,no less
                                            //cuz the Json parsing does exceptions otherwise starts reading nonsense
                                            byte[] actualData = new byte[cnt];
                                            for(int i =0;i<cnt;i++){
                                                actualData[i] = data[i];
                                            }
                                            System.out.println(s);
                                            //converting to string
                                            String strData = new String(actualData, "UTF-8");
                                            try {
                                                payLoad = (org.json.simple.JSONObject) new  JSONParser().parse(strData);
                                                System.out.println("");
                                            } catch (Exception e) {

                                                //out.write(toSend);
                                                e.printStackTrace();
                                            }
                                            payLoad  = protocolHandler.response(payLoad,s);
                                            byte [] toSend = payLoad.toString().getBytes();
                                            out.write(toSend);
                                            out.flush();
                                        }
                                        s.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            clientThread.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            })  ;
            serverThread.start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
