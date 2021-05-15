import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Board {
    Logger logger = Logger.getLogger("Board.class");
    static int numRows = 3;
    static int numCols = 3;
    static String[][] grid;
    public static void initializeBoard() {
        for(int i = 0 ; i< numRows; i++){
            for(int j = 0 ; j< numCols; j++){
                grid[i][j] = "-";
            }
        }
    }

    public boolean setGridItem(int row,int col, String item) {
        if(grid[row][col].equals("-")) {
            grid[row][col] = item;
            return true;
        }
        else{
            System.out.println("Invalid Move! Cannot fill an already filled Cell: "+row+", "+col);
            return  false;
        }
    }

    public static void printBoard(){
        for(String[] row:grid){
            for(String val: row){
                System.out.print(val+" ");
            }
            System.out.println();
        }
    }

    public Board(){
        grid = new String[numRows][numCols];
        initializeBoard();
    }
     public Board(int rows,int cols){
        numRows = rows;
        numCols =cols;
        grid = new String[numRows][numCols];
        initializeBoard();
     }


}

