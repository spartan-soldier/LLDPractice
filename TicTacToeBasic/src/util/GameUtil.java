package util;

import customException.InvalidInputException;

import java.util.List;

public class GameUtil {

    public static boolean isValid(int r, int c, String[][] board) {
        return r>=0 && c>=0 && r<board.length && c < board.length;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return strNum.matches("-?\\d+");
    }

    public static void sanityForOtherInput(String[][] board, List<String> inputList, int noOfPlayers) throws InvalidInputException {

        for(int i = noOfPlayers;i< inputList.size();i++){
            String input= inputList.get(i);
            String[] temp = input.split(" ");
            if(temp.length== 1 || temp.length== 2  ){
                if(temp.length == 1) {
                    if(!temp[0].equals("Exit"))
                        throw new InvalidInputException("Invalid Input supplied: " + temp);
                }
                else{
                    if(isNumeric(temp[0]) && isNumeric(temp[1])){
                        int row = Integer.valueOf(temp[0]);
                        int col = Integer.valueOf(temp[1]);
                        if(row<0|| col<0 || row>=board.length || col >= board.length)
                            throw new InvalidInputException("ERROR: Input index for array is out of Bound. Please give a valid input : "+temp);
                    }else{
                        throw new InvalidInputException("ERROR: Gird rows and columns cannot be non Integer : "+temp);
                    }
                }
            }else{
                throw new InvalidInputException("Invalid Input supplied: "+temp);
            }
        }
    }
}
