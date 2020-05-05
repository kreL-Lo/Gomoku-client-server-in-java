package Client.graphics;

import Client.ServerReader;
import Client.ServerWritter;
import Client.graphics.Game.Canvas;
import Client.graphics.Game.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame implements ActionListener {
    ServerReader serverReader;
    ServerWritter serverWritter;
    JPanel mainPanel;
    JButton play = new JButton("Play");
    JButton exit = new JButton("Exit");
    JPanel subPanel;
    JPanel lobbyPanel ;
    JPanel test;
    public MainFrame(ServerReader serverReader,ServerWritter serverWritter){
        this.serverReader= serverReader;
        this.serverWritter= serverWritter;
        setSize(600,600);
        init();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }


    void init()
    {
        mainPanel  = new JPanel(new BorderLayout());
        play.setPreferredSize(new Dimension(100,100));
        subPanel = new JPanel(new GridLayout(2,0));
        exit.addActionListener(this);
        initPlay();
        subPanel.add(play);
        subPanel.add(exit);
        test = new JPanel();
        test.add(subPanel);
        mainPanel.add(test,BorderLayout.CENTER);
        add(mainPanel);
    }
    void initPlay(){

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("here");
                mainPanel.remove(test);
                lobbyPanel = new LobbyPanel(serverReader,serverWritter);
                mainPanel.add(lobbyPanel);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
