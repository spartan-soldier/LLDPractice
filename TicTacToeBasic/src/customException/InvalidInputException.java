package customException;

public class InvalidInputException extends Exception {
    public InvalidInputException(String msg) {
        System.out.println(msg);
    }
}
