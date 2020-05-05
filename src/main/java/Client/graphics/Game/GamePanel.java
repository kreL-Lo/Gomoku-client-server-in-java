package Client.graphics.Game;

import Client.ServerWritter;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    JLabel turnPlacement = new JLabel("Player Turn");
    JLabel turn =new JLabel("");
    JLabel winner = new JLabel("");
    JPanel subPanel;
    Canvas canvas;
    public GamePanel(String roomId, Color color, String playerTurn, ServerWritter serverWritter){
        setLayout(new BorderLayout());
        canvas  = new Canvas(roomId,color,serverWritter);
        subPanel = new JPanel(new    GridLayout(0,3));
        turn.setText(playerTurn);
        subPanel.add(turnPlacement);
        subPanel.add(turn);
        subPanel.add(winner);
        add(canvas,BorderLayout.CENTER);
        add(subPanel,BorderLayout.PAGE_END);
        revalidate();
        repaint();
    }

    public JLabel getWinner() {
        return winner;
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public JLabel getTurn() {
        return turn;
    }

    public void setTurn(JLabel turn) {
        this.turn = turn;
    }
}
