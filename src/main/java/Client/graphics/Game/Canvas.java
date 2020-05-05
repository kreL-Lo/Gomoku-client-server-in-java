package Client.graphics.Game;

import Client.ServerWritter;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class
Canvas extends java.awt.Canvas  {
    int rows  = 15;
    int cols  =15;
    int len = 30;
    int distance = 450;
    String roomId;
    Color color ;

    public Canvas(String roomId, Color color, ServerWritter serverWritter){
        this.roomId = roomId;
        this.color = color;
        addMouseListener( new Mouse(this,roomId,serverWritter));
    }
    public void paint(Graphics g) {
        super.paint(g);
        initTable(g,30,30);
    }

    void initTable(Graphics g,int x ,int y){
        for(int i=0;i<rows+1 ; i++ ){
            for(int j =0 ;j<cols+1;j++){
                g.drawLine(x+i*len,y,x+i*len,y+distance);
                g.drawLine(x,y+j*len,x+distance,y+j*len);
            }
        }
    }
    public void drawPiece(Graphics g, int x, int y,Color color){
        g.setColor(color);
        g.fillOval(30+len*x,30+len*y,30,30);
    }
}
