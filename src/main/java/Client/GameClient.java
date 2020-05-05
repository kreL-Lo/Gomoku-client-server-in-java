package Client;
import Client.graphics.MainFrame;


import java.net.Socket;
import java.util.regex.*;
public class GameClient {
    Socket socket;

    public static void main (String args[]){

        try {
            Thread.sleep(1000);
        }
        catch (Exception e){

        }
        System.out.println("192.168.0.2" + " " + 8000);
        new GameClient("192.168.0.2" ,8000);
        //89.38.74.214
        System.out.println("ENDED");


    }

    public GameClient(String adress, int port){
        try{
            System.out.println("Connected");
            socket  = new Socket(adress,port);
            System.out.println(socket);

        ServerReader serverReader = new ServerReader(socket);
        ServerWritter serverWritter = new ServerWritter(socket);
        Thread t1 = new Thread(serverReader);
        t1.start();
        Thread t2 = new Thread(serverWritter);
        t2.start();
        MainFrame mainFrame = new MainFrame(serverReader,serverWritter);
    }
        catch (Exception e){

    }
    }
}
