package Server.Protocols;

import Server.LobbyHandler;
import Server.ServerData.Lobby;
import Server.ServerData.Player;
import org.json.simple.JSONObject;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class JoinLobby {
    static List<String > pieseColors = new ArrayList<String>();
    static void initPieceColors(){
        pieseColors.clear();
        pieseColors.add("Red");
        pieseColors.add("Blue");
        pieseColors.add("Yellow");
        pieseColors.add("Green");
        pieseColors.add("Black");
    }
    public static JSONObject function(JSONObject object, LobbyHandler lobbyHandler, Socket s){
        String name;
        String id;
        try {
            name = object.get("name").toString();
            id = object.get("roomId").toString();
        }
        catch ( Exception e){
            return  noAvailable();
        }
        Lobby l = lobbyHandler.getLobby(id);
        if(l==null){
            //not available
            return noAvailable();
        }
        else if(l.getPlayerList().size()>1){
            return noAvailable();
        }
        initPieceColors();
        Player p = new Player();
        p.setName(name);
        p.setSocket(s);
        String setColor =null;
        if(l.getPlayerList().size()==1){
            List<Player> playerList = l.getPlayerList();
            List<String > colors = new ArrayList<>();
            for(Player player : playerList){
                colors.add(player.getPieceColor());
            }
            for(String col : colors){
                pieseColors.remove(col);
            }

            setColor = pieseColors.get((int)Math.random()*pieseColors.size());
            p.setTurn(2);
            p.setPieceColor(setColor);
            l.addPlayer(p);
            String playerTurn = null;
            for(Player player : l.getPlayerList())
            {
                if(player.getTurn()==l.getBord().currentTurn)
                {
                    playerTurn = p.getName();
                    break;
                }
            }
            return twoPlayers(p,playerTurn,id);
        }
        else{
            setColor = pieseColors.get((int)Math.random()*pieseColors.size());
            p.setTurn(1);
            p.setPieceColor(setColor);
            l.addPlayer(p);
            return onePlayer(p,id);
        }
    }
    public  static JSONObject noAvailable(){
        JSONObject json = new JSONObject();
        json.put("PROTOCOL","JOIN_LOBBY");
        json.put("SUCCESS",0);
        return json;
    }

    public static JSONObject onePlayer(Player p,String roomId){
        JSONObject json = new JSONObject();
        json.put("PROTOCOL","JOIN_LOBBY");
        json.put("SUCCESS",1);
        json.put("turn",p.getName());
        json.put("piece_color",p.getPieceColor());
        json.put("roomId",roomId);
        return  json;
    }
    public static  JSONObject twoPlayers(Player p,String playerTurn,String roomId){
        JSONObject json = new JSONObject();
        json.put("PROTOCOL","JOIN_LOBBY");
        json.put("SUCCESS",1);
        json.put("turn",playerTurn);
        json.put("piece_color",p.getPieceColor());
        json.put("roomId",roomId);
        return  json;
    }
}
