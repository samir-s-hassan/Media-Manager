/***
 * Class to test the entities
 * @author Samir Hassan
 * @version 0.1
 * Date of creation: February 10, 2022
 * Last Date Modified: February 10, 2022
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.PrintWriter;

public class Test2 {
    public static void main(String[] args) {
        Media[] mediaList = new Media[50]; // creates the array media 

        String fileNum;
        int count = 0; //global variable for media elements
        Scanner scnr = new Scanner(System.in); //Scanner object

        try {
            FileInputStream fileByteStream = new FileInputStream("media.txt");
            Scanner inFS = new Scanner(fileByteStream);

            while (inFS.hasNextLine()) {
                fileNum = inFS.nextLine();
                String[] splitText = fileNum.split("\\|"); // split the text with the delimiter

                //add the elements to the media array accordingly
                for (int i = 0; i < splitText.length; i += 5) {
                    if (splitText[i].equalsIgnoreCase("movie")) {
                        Media movie = new Movie(splitText[i + 1], splitText[i + 2],
                                splitText[i + 3], Integer.valueOf(splitText[i + 4]));
                        mediaList[count] = movie;
                        count++;
                    } else {
                        Media show = new Show(splitText[i + 1], splitText[i + 2], splitText[i + 3],
                                Integer.valueOf(splitText[i + 4]));
                        mediaList[count] = show;
                        count++;

                    }
                }

            }

            fileByteStream.close(); //close the text file we were reading

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println(""); //why is this needed?
        }

        // create a menu that the user can use to carry out different operations within
        boolean correct = false;
        do {
            System.out.println("Select an operation");
            System.out.println("1: View Media List");
            System.out.println("2: Search media by title");
            System.out.println("3: Add a new media");
            System.out.println("4: Remove an existing media");
            System.out.println("5: Sort media by genre");
            System.out.println("6. Exit");
            int userInt = getInt(scnr);
            System.out.println();

            if (userInt == 1) {//prints the media
                printMedia(mediaList, count);
                System.out.println();
            }
            if (userInt == 2) {//searches the list of media for a user entered input
                System.out.println("Enter media title: ");
                String title = scnr.nextLine();
                int index = findMedia(mediaList, count, title);
                if (index != -1) {
                    System.out.println("Media found: " + mediaList[index].toString());
                } else {
                    System.out.println("Media not found");
                }
                System.out.println();
            }
            try {
                if (userInt == 3) { //lets the user add a form of media
                    System.out.println("Enter media title");
                    String title1 = scnr.nextLine();
                    String type1 = "";
                    boolean typeChecker = false;
                    while (typeChecker == false) {//checks for the option entered by the user for type of media. limits them to either movie or show.
                        System.out.println("Enter the type (movie/show): ");
                        type1 = scnr.nextLine();
                        if (type1.equalsIgnoreCase("movie")) {
                            typeChecker = true;
                        } else if (type1.equalsIgnoreCase("show")) {
                            typeChecker = true;
                        } else {
                            System.out.println("\nThis is not a type of media. Please try again");
                            typeChecker = false;
                        }
                    }
                    System.out.println("Enter the genre (Drama/Comedy/Animation/Anime/Documentary etc.): "); //lets the user input the data associated with media
                    String genre1 = scnr.nextLine();
                    int duration1 = 0;
                    String rating1 = "";
                    if (type1.equalsIgnoreCase("movie")) {
                        System.out.println("Enter the rating (G/PG/PG-13/G/R/NC-17): ");
                        rating1 = scnr.nextLine();
                        checkRating(rating1, type1);
                        System.out.println("Enter the duration in minutes: ");
                        duration1 = scnr.nextInt();
                    }
                    int seasons1 = 0;
                    if (type1.equalsIgnoreCase("show")) {
                        System.out.println("Enter the rating (TV-Y/TV-Y7/TV-G/TV-PG/TV-14/TV-MA): ");
                        rating1 = scnr.nextLine();
                        checkRating(rating1, type1);
                        System.out.println("Enter the number of seasons: ");
                        seasons1 = scnr.nextInt();
                    }
                    boolean canAddMovie = true; //checks if the media the user enters isn't already present by checking for the name
                    for (int i = 0; i < count; i++) {
                        if (mediaList[i].getTitle().equalsIgnoreCase(title1)) {
                            System.out.println("Media already exists");
                            canAddMovie = false;
                            break;
                        }
                    }
                    if (canAddMovie) { //if no media with the same title is present, we can then add the user's request to our listing
                        if (type1.equalsIgnoreCase("movie")) {
                            Media movie = new Movie(title1, genre1, rating1, duration1);
                            mediaList[count] = movie;
                            count++;
                        } else {
                            Media show = new Show(title1, genre1, rating1, seasons1);
                            mediaList[count] = show;
                            count++;
                        }
                    }

                }
                System.out.println();

            } catch (InvalidRatingException e) {
                System.out.println(e.getMessage());
            }
            if (userInt == 4) {//removes a media from the list
                System.out.println("Enter media title: ");
                String title = scnr.nextLine();
                int k = findMedia(mediaList, count, title);
                if (k != -1) {
                    for (k = k; k < count - 1; k++) { //shifts each element down to account for the removed media element
                        mediaList[k] = mediaList[k + 1];
                    }
                    count--;
                    System.out.println("Media removed successfully");
                } else {
                    System.out.println("Media not found");
                }
                System.out.println();
            }
            if (userInt == 5) {//sorts the media according to its genre and shows the user
                sortMedia(mediaList, count);
                printMedia(mediaList, count);
                System.out.println();
            }
            if (userInt == 6) {//saves the new media array onto the text file
                try {
                    FileOutputStream fileStream = new FileOutputStream("media.txt");
                    PrintWriter outFS = new PrintWriter(fileStream);
                    for (int i = 0; i < count; i++) {
                        outFS.println(mediaList[i].simpleString());
                        if (i % 5 == 0){
                            System.out.println();
                        }
                    }
                    outFS.close();
                } catch (FileNotFoundException e) {
                    System.out.println("File not found.");
                } 
                System.out.println("Thank you for using my program. Bye!");
                correct = true;
            }
        } while (!correct);
    }

    /***
     * Prints the elements of the media array
     * 
     * @param list  is the array of media entities
     * @param count is the amount of actual media entities in the list (no null
     *              elements)
     * @return no return value
     */
    public static void printMedia(Media[] list, int count) {
        System.out.printf("%-30s\t%-10s\t%-5s\t%s\n",
        "Title", "Genre", "Rating", "Duration/Seasons");
        for (int i = 0; i < count; i++) {
            System.out.println(list[i]);
        }
    }

    /***
     * Finds the media corresponding to the title entered
     * 
     * @param list  is the array of media entities
     * @param count is the amount of actual media entities in the list (no null
     *              elements)
     * @param title is the title of the media we are looking for
     * @return an integer corresponding to the index of the media element in the
     *         list
     */
    public static int findMedia(Media[] list, int count, String title) {
        int counter = 0;
        for (int i = 0; i < count; i++) {
            if (list[i].getTitle().equals(title)) {
                break;
            }
            counter++;
        }
        if (counter == count) {
            counter = -1;
        }
        return counter;
    }

    /***
     * Sorts the media array based on alphabetical order of the genre
     * 
     * @param list  is the array of media entities
     * @param count is the amount of actual media entities in the list (no null
     *              elements)
     * @return no return value
     */
    public static void sortMedia(Media[] list, int count) {
        for (int i = 1; i < count; i++) {
            // Insert element i in the sorted sub-list
            Media currentVal = list[i];
            int j = i;
            while (j > 0 && (currentVal.getGenre().compareTo(list[j - 1].getGenre()) < 0)) {
                // Shift element (j-1) into element (j)
                list[j] = list[j - 1];
                j--;
            }
            // Insert currentVal at position j
            list[j] = currentVal;
        }
    }

    /***
     * Checks the rating to see if the rating is valid for the type of media
     * 
     * @param rating is the rating of the media entity
     * @param type   is the type of media we are looking at
     * @return a truth (boolean) value corresponding to the rating
     */
    public static boolean checkRating(String rating, String type) throws InvalidRatingException {
        if (type.matches("movie") | type.matches("Movie")) {
            if (rating.matches("G") | rating.matches("PG-13") | rating.matches("PG") | rating.matches("R")
                    | rating.matches("NC-17")) {
                return true;
            } else {
                throw new InvalidRatingException(
                        "Invalid rating: " + rating + ". Must be one of the following: G, PG, PG-13, R, or NC-17.");
            }
        } else if (type.matches("show") | type.matches("Show")) {
            if (rating.matches("TV-Y") | rating.matches("TV-Y7") | rating.matches("TV-G")
                    | rating.matches("TV-PG") | rating.matches("TV-14") | rating.matches("TV-MA")) {
                return true;
            } else {
                throw new InvalidRatingException("Invalid rating: " + rating
                        + ". Must be one of the following: TV-Y, TV-Y7, TV-G, TV-PG, TV-14, TV-MA.");
            }
        } else {
            throw new InvalidRatingException("Invalid media type");
        }

    }

    /**
     * gets the integer value
     * 
     * @param scnr the user input for tax status
     * @return integer corresponding to user menu option
     *         THIS CODE IS FROM MY CALCTAXES
     */
    public static int getInt(Scanner scnr) {
        boolean correct;
        int taxStatus = 0;
        do { // do-while loop to get the correct input
            correct = scnr.hasNextInt();
            if (correct == false) {
                System.out.println("Invalid operation. Try again.");
                scnr.nextLine();
            } else {
                taxStatus = scnr.nextInt();
                if (taxStatus < 1 || taxStatus > 6) { // gets the correct range
                    System.out.println("\nInvalid operation. Try again.");
                    correct = false;
                }
                scnr.nextLine();
            }
        } while (!correct);
        return taxStatus;
    }

}