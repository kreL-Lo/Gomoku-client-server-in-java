package Client.fromServerProtocols;

import Client.ServerWritter;
import Client.graphics.LobbyPanel;
import Client.graphics.extra.LobbyBar;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;

public class ShowLobbies {

    public static void function (JSONObject object , LobbyPanel lobbyPanel)
    {
        System.out.println("here");
        lobbyPanel.remove(lobbyPanel.getLobbiesPanel());
        lobbyPanel.setLobbiesPanel(new JPanel(new GridLayout(5,0)));
        JPanel panel =lobbyPanel.getLobbiesPanel();
        JSONArray lobbies = (JSONArray) object.get("lobbies");
        for(int i =0 ;i<lobbies.size();i++){
            JSONObject obj = (JSONObject) lobbies.get(i);
            String name = obj.get("name").toString();
            String nrPlayers = obj.get("nr_players").toString();
            panel.add(new LobbyBar(name,nrPlayers,lobbyPanel));
        }
        lobbyPanel.add(panel);
        lobbyPanel.revalidate();
        lobbyPanel.repaint();

    }
}
