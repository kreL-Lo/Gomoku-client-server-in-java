package Server.ServerData;

public class Board {
    public int [][] board = new int[15][15];
    public int currentTurn =1;
    public Board(){
        initBoard();
    }
    public void setPiece(int piece, int x, int y){
        board[x][y]= piece;
    }
    public void initBoard()
    {
        for(int i= 0 ;i<15;i++){
            for(int j= 0 ;j<15;j++){
                board[i][j]=0;
            }
        }
    }
}
