package Client.graphics.Game;

import Client.ServerWritter;
import Client.toServerProtocols.SendMove;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {
    ServerWritter serverWritter;
    Canvas canvas;
    String roomId;
    int len = 30;
    int x0 = 30;
    int y0= 30;
    int x1 = 450+30;
    int y1 = 450+30;

    public Mouse(Canvas canvas,String roomId,ServerWritter serverWritter){
        this.canvas = canvas;
        this.roomId = roomId;

        this.serverWritter = serverWritter;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e);
        if(e.getButton()==1){
            int x =e.getX();
            int y= e.getY();
            if(x>=x0 && y>=y0&& x<=x1 && y<=y1){
                x = (x-len)/len;
                y = (y-len)/len;
                serverWritter.setData(SendMove.function(roomId,x,y));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
