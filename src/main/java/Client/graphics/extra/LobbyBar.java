package Client.graphics.extra;

import Client.ServerWritter;
import Client.graphics.LobbyPanel;
import Client.toServerProtocols.CreateLobby;
import Client.toServerProtocols.JoinLobby;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LobbyBar extends JPanel {
    JButton joinButton = new JButton("Join");
    JLabel [] setName;
    JLabel [] setNrPlayers;
    ServerWritter serverWritter ;
    LobbyPanel lobbyPanel ;
    public LobbyBar(String name, String nrPlayers, LobbyPanel lobbyPanel){
        this.lobbyPanel= lobbyPanel;
        this.serverWritter = lobbyPanel.getServerWritter();
        setName = new JLabel[2];
        setNrPlayers = new JLabel[2];
        for(int i= 0 ;i<2;i++){
            setName[i] = new JLabel();
            setNrPlayers[i] = new JLabel();
        }
        setName[0].setText("name");
        setName[1].setText(name);
        setNrPlayers[0].setText("nrPlayers");
        setNrPlayers[1].setText(nrPlayers + " out of 2");
        setLayout(new GridLayout(2,3));
        initJoinButton();
        add(setName[0]);
        add(setName[1]);
        add(joinButton);
        add(setNrPlayers[0]);
        add(setNrPlayers[1]);
    }
    void initJoinButton(){
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = setName[1].getText();
                String playerName = lobbyPanel.getPlayerName();
                serverWritter.setData(JoinLobby.function(playerName,id));
            }
        });
    }
}
