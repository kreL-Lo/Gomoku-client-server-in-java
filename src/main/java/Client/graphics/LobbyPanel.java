package Client.graphics;

import Client.ServerReader;
import Client.ServerWritter;
import Client.graphics.Game.GamePanel;
import Client.toServerProtocols.ShowLobbies;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LobbyPanel extends JPanel {
    String playerName;
    ServerReader serverReader;
    ServerWritter serverWritter;
    GamePanel gamePanel = null;
    JButton setNameButton = new JButton("setName");
    JTextArea setNameArea = new JTextArea();
    JButton showLobbies = new JButton("ListLobbies");
    JButton createLobby = new JButton("CreateLobby");
    JPanel bottomPanel  = new JPanel(new GridLayout(2,2));
    JPanel lobbiesPanel = new JPanel(new GridLayout(5,0));



    public JPanel getLobbiesPanel() { return lobbiesPanel; }
    public LobbyPanel(ServerReader serverReader, ServerWritter serverWritter){
        this.serverReader= serverReader;
        this.serverWritter= serverWritter;
        serverReader.setLobbyPanel(this);
        setLayout(new BorderLayout());
        init();
    }

    void init(){
        initCreateLobbyButton();
        initSetNameButton();
        initSetShowLobbiesButton();
        System.out.println("loby");
        bottomPanel.add(setNameButton);
        bottomPanel.add(setNameArea);
        bottomPanel.add(showLobbies);
        bottomPanel.add(createLobby);

        add(bottomPanel,BorderLayout.PAGE_END);
    }

    void initCreateLobbyButton(){
        System.out.println("here");
        createLobby.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(bottomPanel);
                remove(lobbiesPanel);
                System.out.println("here");
                add(new CreateLobbyPanel(serverReader,serverWritter));
                revalidate();
                repaint();

            }
        });
    }

    void initSetShowLobbiesButton(){
        showLobbies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverWritter.setData(ShowLobbies.function());
            }
        });
    }
    void initSetNameButton(){
        setNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerName = setNameArea.getText();
            }
        });
    }

    public ServerWritter getServerWritter() {
        return serverWritter;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setLobbiesPanel(JPanel jPanel) {
        lobbiesPanel = jPanel;
    }
    public void cleanPanel(){
        remove(bottomPanel);
        remove(lobbiesPanel);
        repaint();
        revalidate();
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
