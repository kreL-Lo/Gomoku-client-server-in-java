package Client.graphics;

import Client.ServerReader;
import Client.ServerWritter;
import Client.toServerProtocols.CreateLobby;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateLobbyPanel extends JPanel {
    ServerReader serverReader;
    ServerWritter serverWritter;
    JTextArea serverOut = new JTextArea("SERVER-OUTPUT");
    JButton create= new JButton("Create");
    JButton back= new JButton("Back");
    JPanel subPanel = new JPanel(new GridLayout(0,2));
    JPanel dataPanel = new JPanel(new GridLayout(0,2));
    JPanel test = new JPanel();
    JTextField [] textFields;
    JTextArea [] textAreas;
    public CreateLobbyPanel(ServerReader serverReader,ServerWritter serverWritter){
        this.serverReader =serverReader;
        serverReader.setCreateLobbyPanel(this);
        this.serverWritter = serverWritter;
        setLayout(new BorderLayout());
        init();
    }
    void init(){
        initText();
        initCreateLobbyButton();
        initBackButton();
        subPanel.add(create);
        subPanel.add(back);
        add(subPanel,BorderLayout.PAGE_END);
        add(serverOut,BorderLayout.PAGE_START);
        test.add(dataPanel);
        add(test,BorderLayout.CENTER);
    }

    void initText(){
        textAreas = new JTextArea[1];
        textFields = new JTextField[1];
        textFields[0] = new JTextField("Lobby Name");
        textAreas[0] = new JTextArea();
        for(int i= 0 ;i<1;i++){
            dataPanel.add(textFields[i]);
            dataPanel.add(textAreas[i]);
        }

    }

    void initBackButton(){
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(subPanel);
                remove(test);
                remove(serverOut);
                add(new LobbyPanel(serverReader,serverWritter));
                revalidate();
                revalidate();
            }
        });
    }

    void initCreateLobbyButton(){
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textAreas[0].getText();
                serverWritter.setData(CreateLobby.function(name));
            }
        });
    }

    public JTextArea getServerOut() {
        return serverOut;
    }
}
