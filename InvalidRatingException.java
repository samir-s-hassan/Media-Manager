/***
 * Class to model the entity InvalidRatingException
 * @author Samir Hassan
 * @version 0.1
 * Date of creation: February 10, 2022
 * Last Date Modified: February 10, 2022
 */

import java.util.InputMismatchException;

public class InvalidRatingException extends InputMismatchException {

    /***
     * Default constructor
     * No parameters
     */
    public InvalidRatingException() {
        super("Invalid Rating");
    }

    /***
     * One argument constructor
     * 
     * @param message is a message that should be output upon the occurence
     */
    public InvalidRatingException(String message) {
        super(message);
    }

}