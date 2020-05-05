package Client.fromServerProtocols;

import Client.graphics.CreateLobbyPanel;
import Client.graphics.LobbyPanel;
import org.json.simple.JSONObject;

public class CreateLobby {
    public static void function (JSONObject object , CreateLobbyPanel createLobbyPanel)
    {
        createLobbyPanel.getServerOut().setText(object.get("ANSWER").toString());
        createLobbyPanel.revalidate();
        createLobbyPanel.repaint();
    }
}
