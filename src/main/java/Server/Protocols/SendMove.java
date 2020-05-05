package Server.Protocols;

import Server.LobbyHandler;
import Server.SendToMany;
import Server.ServerData.Board;
import Server.ServerData.Lobby;
import Server.ServerData.Player;
import org.json.simple.JSONObject;

import java.net.Socket;

public class SendMove {




    public static JSONObject function(JSONObject object, Socket s , LobbyHandler lobbyHandler){
        JSONObject json = new JSONObject();
        String moveX,id;
        String moveY;
        try {
            moveX = object.get("move_X").toString();// 15 pe 15 : 0 - 14
            moveY = object.get("move_Y").toString();

            id = object.get("roomId").toString();
        }
        catch (Exception e){
            return noAvailable();
        }
        Lobby lobby = lobbyHandler.getLobby(id);
        if(lobby.getPlayerList().size()==1){
            return  noAvailable();
        }
        if(lobby==null)
            return noAvailable();
        Board board = lobby.getBord();
        if(board == null){
            return  noAvailable();
        }

        Player player = lobby.findBySocket(s);
        if(player.getTurn()==board.currentTurn)
        {
            //move validity
            boolean validMove = false;
            int x= Integer.valueOf(moveX);
            int y= Integer.valueOf(moveY);
            if(x>=0 && x<15 && y>=0&&y<15){
                if(board.board[x][y]==0){
                    validMove=true;
                    board.board[x][y]=player.getTurn();
                    if(EndGame.checkEnd(board)){

                        for(Player p : lobby.getPlayerList()){
                            if(p.getSocket()!=s){
                                SendToMany.send(p.getSocket(),EndGame.endGame(player.getName(),moveX,moveY,player.getPieceColor()));
                            }
                        }
                        return EndGame.endGame(player.getName(),moveX,moveY,player.getPieceColor());
                    }
                }
                else{
                    return noAvailable();
                }
            }
            else return noAvailable();

            if(validMove == true) {
                if (board.currentTurn == 1)
                    board.currentTurn = 2;
                else
                    board.currentTurn = 1;
            }
            String playerTurn = null;
            for(Player player1: lobby.getPlayerList())
            {
                System.out.println(player1.getTurn());
                if(player1.getTurn()==lobby.getBord().currentTurn)
                {
                    playerTurn = player1.getName();
                    break;
                }
            }
            for(Player p : lobby.getPlayerList()){
                if(p.getSocket()!=s){
                    SendToMany.send(p.getSocket(),succes(x,y,player,playerTurn));
                }
            }
            return succes(x,y,player,playerTurn);
        }
        return noAvailable();
    }
    static JSONObject succes(int x, int y,Player p,String playerTurn){
        JSONObject json = new JSONObject();
        json.put("player_move",p.getName());
        json.put("turn",playerTurn);
        json.put("move_X",x);
        json.put("move_Y",y);
        json.put("color",p.getPieceColor());
        json.put("PROTOCOL","SEND_MOVE");
        json.put("SUCCESS",1);
        return json;
    }
    static JSONObject noAvailable(){
        JSONObject json = new JSONObject();
        json.put("PROTOCOL","SEND_MOVE");
        json.put("SUCCESS",0);
        return json;
    }

}
