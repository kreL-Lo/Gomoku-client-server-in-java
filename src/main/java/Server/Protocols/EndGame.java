
package Server.Protocols;
import java.util.Map;
import java.util.regex.*;
import Server.ServerData.Board;
import org.json.simple.JSONObject;

public class EndGame {

public static JSONObject endGame(String playerName,String moveX, String moveY, String color){
    JSONObject json= new JSONObject();
    json.put("PROTOCOL","END_GAME");
    json.put("winner","Winner: "+playerName);
    json.put("move_X",moveX);
    json.put("move_Y",moveY);
    json.put("piece_color",color);
    return json;
}

static int [] shift_array(int [] array,int times){
    int []arr = new int[15];
    for(int i =0 ;i<times;i++){

        for(int j =14;j>0;j--){
            arr[j]= array[j-1];
        }
        arr[0]= array[14];
        for(int j =0 ;j<15;j++){
            array[j]= arr[j];
        }
    }
    return  arr;
}

    static int [] shift_array_left(int [] array,int times){
        int []arr = new int[15];
        for(int i =0 ;i<times;i++){

            for(int j =0;j<14;j++){
                arr[j]= array[j+1];
            }
            arr[14]= array[0];
            for(int j =0 ;j<15;j++){
                array[j]= arr[j];
            }
        }
        return  arr;
    }
public static boolean checkEnd(Board b){
        int board[][] = b.board;
        //horizontal
        if(checkBoard(board))
            return true;
        int transposed[][] = new int [15][15];
        for(int i =0 ;i<15;i++){
            for(int j =0;j<15;j++){
                transposed[i][j]= board[j][i];
            }
        }
        //vertical
        if(checkBoard(transposed))
            return true;
        //diagonal
        for(int i= 0 ;i<15;i++){
            for(int j =0;j<15;j++)
            { transposed[i][j] = board[i][j];}
        }

        for(int i =0 ;i<15;i++){
            transposed[i]= shift_array(transposed[i],i);
        }
        int [][ ] another = new int [15][15];
        for(int i =0 ;i<15;i++){
            for(int j =0;j<15;j++){
                another[i][j]= transposed[j][i];
            }
        }
        if(checkBoard(another)){
            return true;
        }
        for(int i =0 ;i<15;i++){
            System.out.println();
            for(int j=0;j<15;j++){
                System. out . print (another[i][j] + " ");
            }
        }
    for(int i= 0 ;i<15;i++){
        for(int j =0;j<15;j++)
        { transposed[i][j] = board[i][j];}
    }

    for(int i =0 ;i<15;i++){
        transposed[i]= shift_array_left(transposed[i],i);
    }
    for(int i =0 ;i<15;i++){
        for(int j =0;j<15;j++){
            another[i][j]= transposed[j][i];
        }
    }
    if(checkBoard(another)){
        return true;
    }
    for(int i =0 ;i<15;i++){
        System.out.println();
        for(int j=0;j<15;j++){
            System. out . print (another[i][j] + " ");
        }
    }
    System.out.println();
        System.out.println();
        return  false;
    }


    static boolean checkBoard(int[][] board){
        String str= "";
        for(int i= 0 ;i<15;i++){
            for(int j= 0;j<15;j++){
                str+=board[i][j];
            }
        }
        String pattern = "(1{5,5})|(2{5,5})";
        Pattern r = Pattern.compile(pattern);
        Matcher m =r.matcher(str);
        if(m.find()){
            return true;
        }
        return false;

    }


}
