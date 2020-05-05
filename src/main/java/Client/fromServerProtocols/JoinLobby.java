package Client.fromServerProtocols;

import Client.extra.ParseColor;
import Client.graphics.Game.Canvas;
import Client.graphics.Game.GamePanel;
import Client.graphics.LobbyPanel;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.*;

public class JoinLobby {
    public static void function (JSONObject object , LobbyPanel lobbyPanel )
    {
        if(object.get("SUCCESS").toString().compareTo("1")==0) {
            lobbyPanel.cleanPanel();
            String id =object.get("roomId").toString();

            //parse color
            String col = object.get("piece_color").toString();
            Color color = ParseColor.parse(col);
            String playerTurn = object.get("turn").toString();
            GamePanel gamePanel = new GamePanel(id,color,playerTurn,lobbyPanel.getServerWritter());
            lobbyPanel.setGamePanel(gamePanel);
            lobbyPanel.add(gamePanel);
            lobbyPanel.revalidate();
            lobbyPanel.repaint();

        }

    }
}
