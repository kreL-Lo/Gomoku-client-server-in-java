package Client;


import Client.fromServerProtocols.*;
import Client.graphics.CreateLobbyPanel;
import Client.graphics.LobbyPanel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.DataInputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
public class ServerReader implements Runnable {
    Socket s;
    CreateLobbyPanel createLobbyPanel;
    LobbyPanel lobbyPanel;

    DataInputStream in ;
    JSONObject data = null;
    String payload;
    boolean available;
    byte [] buffer  =new byte[1024];

    public ServerReader(Socket s) {
        try {
            in = new DataInputStream(s.getInputStream());
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    @Override
    public void run() {
        System.out.println("Reading");
        while(true){
            try {
                int cnt = -1;
                try {
                    cnt = in.read(buffer);
                } catch (Exception e) {
                    System.out.println(e);
                }
                byte[] raw = new byte[cnt];
                for (int i = 0; i < cnt; i++) {
                    raw[i] = buffer[i];
                }

                try {
                    payload = new String(raw, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    data = (JSONObject) new JSONParser().parse(payload);
                    System.out.println(data);
                    handler(data);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
            catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public void setLobbyPanel(LobbyPanel lobbyPanel) {
        this.lobbyPanel = lobbyPanel;
    }

    public void setCreateLobbyPanel(CreateLobbyPanel createLobbyPanel) {
        this.createLobbyPanel = createLobbyPanel;
    }


    /*
    {
    "PROTOCOL" : "some_name",
    "info":info



    }
    */
    int parse(JSONObject object){
        String protocol = object.get("PROTOCOL").toString();
        System.out.println(protocol);
        if(protocol.compareTo("SHOW_LOBBIES")==0){
            return 1;
        }
        else if(protocol.compareTo("CREATE_LOBBY")==0){
            return 2;
        }
        else if(protocol.compareTo("JOIN_LOBBY")==0){
            return 3;
        }
        else if(protocol.compareTo("SEND_MOVE")==0){
            return 4;
        }
        else if(protocol.compareTo("END_GAME")==0){
            return 5;
        }
        return 0;
    }
    void handler(JSONObject object){
        int cnt = parse(object);

        if(cnt ==1){
            ShowLobbies.function(object,lobbyPanel);
        }
        else if(cnt==2){
            CreateLobby.function(object,createLobbyPanel);
        }
        else if(cnt==3){
            JoinLobby.function(object,lobbyPanel);
        }
        else if(cnt==4){
            SendMove.function(object,lobbyPanel.getGamePanel());
        }
        else if(cnt==5){
            EndGame.function(object,lobbyPanel.getGamePanel());
            try{
                s.close();}
            catch (Exception e){

            }
        }

    }
}
