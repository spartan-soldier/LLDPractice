import customException.InvalidInputException;
import model.User;
import util.GameUtil;

import java.util.*;

public class Driver {
    public static void main(String[] args) {
        Board board = new Board();
        Scanner stdin = new Scanner(System.in);
        List<String> input = new ArrayList<>();
        while (stdin.hasNext()) {
            String val = stdin.nextLine();
            input.add(val);
            if(val.equals("Exit"))
                break;
        }
        Map<User,String> hm = new TreeMap<>((user1, user2)->user1.getName().compareTo(user2.getName()));

        int noOfPlayers = 2;
        try {
            findAndInitializeUsersWithPieces(hm,input,noOfPlayers);
            if(noOfPlayers<2){
                throw new InvalidInputException("Atleast 2 players are required!");
            }
            if(hm.size()<noOfPlayers) {
                throw new InvalidInputException("As per the first parameter provided, "+ noOfPlayers+" players are required!");
            }
            GameUtil.sanityForOtherInput(board.grid,input,noOfPlayers);
        } catch (InvalidInputException e) {
            e.printStackTrace();
            System.exit(1);
        }
        List<User> players = new ArrayList<>(hm.keySet());
        int k = hm.size();
        int turn = 0;
        while (k < input.size()) {
            String currPlayer = players.get(turn).getName();
            String currPiece = hm.get(players.get(turn));
            System.out.println("Curr Player: "+currPlayer+", Piece: "+currPiece);

            String[] gridIndexOrExit = input.get(k).split(" ");
            if (gridIndexOrExit[0].equals("Exit")) {
                System.out.println("Game Over!");
                break;
            } else {
                int row = Integer.valueOf(gridIndexOrExit[0]);
                int col = Integer.valueOf(gridIndexOrExit[1]);
                if(board.setGridItem(row,col,currPiece)) {
                    Board.printBoard();
                    if (checkWinning(board.grid, row, col, currPiece)) {
                        System.out.println(currPlayer + " won!");
                        break;
                    }
                    turn = (turn+1)%players.size();
                }
                k++;
            }

        }
    }

    private static boolean checkWinning(String[][] board, int row, int col, String currPiece) {

        boolean rowLeft = checkWinningUtil(board,  row,  col,  currPiece,  "", "DEC");
        boolean rowRight = checkWinningUtil(board,  row,  col,  currPiece,  "", "INC");
        if(rowLeft && rowRight) return true;

        boolean colUp = checkWinningUtil(board,  row,  col,  currPiece,  "DEC", "");
        boolean colDown = checkWinningUtil(board,  row,  col,  currPiece,  "INC", "");
        if(colUp && colDown) return true;

        boolean lDiagUp = checkWinningUtil(board,  row,  col,  currPiece,  "DEC", "DEC");
        boolean lDiagDown = checkWinningUtil(board,  row,  col,  currPiece,  "INC", "INC");
        if(!(row==board.length-1 && col == 0) && !(row==0 && col == board[0].length -1)) {
            if (lDiagUp && lDiagDown) return true;
        }
        boolean rDiagUp = checkWinningUtil(board,  row,  col,  currPiece,  "DEC", "INC");
        boolean rDiagDown = checkWinningUtil(board,  row,  col,  currPiece,  "INC", "DEC");
        if(!(row==0 && col == 0) && !(row== board.length -1 && col == board[0].length -1)) {
            if(rDiagUp && rDiagDown) return true;
        }

        return false;
    }

    private static boolean checkWinningUtil(String[][] board, int row, int col, String currPiece, String rOp,String cOp){
        int r = row;
        int c = col;

        while(GameUtil.isValid(r,c, board)){
            if(!board[r][c].equals(currPiece)){
               return false;
            }else{
                switch (rOp){
                    case "INC": r++;
                    break;
                    case "DEC": r--;
                    break;
                }
                switch (cOp){
                    case "INC": c++;
                        break;
                    case "DEC": c--;
                        break;
                }
            }
        }
        return true;
    }




    private static Map<User, String> findAndInitializeUsersWithPieces(Map<User, String> hm, List<String> input, int noOfPlayers) throws InvalidInputException {
        for(int i=0; i< noOfPlayers; i++){
            String[] arr = input.get(i).split(" ");
            if(arr.length!=2)
                throw new InvalidInputException("Please Enter a Valid Input!!!");
            if(arr[0].length()>0){
                User user = new User(arr[0]);
                hm.put(user,arr[1]);
            }
        }
        return hm;
    }

}
