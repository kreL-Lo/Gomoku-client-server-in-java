package Client.extra;

import java.awt.*;

public class ParseColor {
    public static Color parse(String color){

        if(color.compareTo("Red")==0){
            return new Color(255,0,0);
        }else if(color.compareTo("Blue")==0){
            return new Color(0,0,255);
        }else if(color.compareTo("Yellow")==0){
            return new Color(255,255,0);
        }else if(color.compareTo("Green")==0){
            return new Color(0,255,0);
        }else if(color.compareTo("Black")==0){
            return new Color(0,0,0);
        }
        return new Color(100,100,30);
    }
}
