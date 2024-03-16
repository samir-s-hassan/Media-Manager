/***
 * Class to model the entity Movie
 * 
 * @author Samir Hassan
 * @version 0.1
 *          Date of creation: January 31, 2022
 *          Last Date Modified: February 10, 2022
 */
public class Movie extends Media {
	// Data members
	private int duration;

	/***
	 * Default constructor
	 * No parameters
	 * Initializes title, genre, rating, duration
	 */
	public Movie() {
		super();
		duration = 0;
	}

	/***
	 * Constructor with four parameters
	 * 
	 * @param title    for the title of the movie
	 * @param genre    for the genre of the movie
	 * @param rating   for the rating of the movie
	 * @param duration for the duration of the movie
	 */
	public Movie(String title, String genre,
			String rating, int duration) {
		super(title, genre, rating);
		this.duration = duration;
	}

	/***
	 * Getter for the duration of the movie
	 * 
	 * @param no parameters
	 * @return the value of the data member duration
	 */
	public int getDuration() {
		return duration;
	}

	/***
	 * Setter for the duration of the movie
	 * 
	 * @param d to set the data member duration
	 *          no return value
	 */
	public void setDuration(int d) {
		duration = d;
	}

	/***
	 * Method to get the movie information
	 * no parameters
	 * 
	 * @return formatted string containing the value of the data members
	 */
	public String toString() {
		String out;
		out = super.toString() + String.format("\t%-5s",
				duration);
		return out;
	}

	/***
	 * Method to get the movie information in a formatted manner similar to text
	 * file
	 * no parameters
	 * 
	 * @return formatted string containing the value of the data members
	 */
	public String simpleString() {
		String out;
		out = ("Movie|") + super.simpleString() + String.format("%s", duration);
		return out;
	}
}
