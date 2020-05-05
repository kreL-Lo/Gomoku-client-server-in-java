package Client.fromServerProtocols;

import Client.extra.ParseColor;
import Client.graphics.Game.GamePanel;
import org.json.simple.JSONObject;

import java.awt.*;

public class EndGame {
    public static void function (JSONObject object , GamePanel gamePanel)
    {
            String winer = object.get("winner").toString();
            gamePanel.getWinner().setText(winer);
            int x = Integer.valueOf(object.get("move_X").toString());
            int y=  Integer.valueOf(object.get("move_Y").toString());
            String col= object.get("color").toString();
            Color color = ParseColor.parse(col);
            gamePanel.getCanvas().drawPiece(
                    gamePanel.getCanvas().getGraphics(),x,y,color
            );
            System.out.println(x + " " + y );
            gamePanel.revalidate();
            gamePanel.repaint();

    }
}
