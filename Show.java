/***
 * Class to model the entity Show
 * 
 * @author Samir Hassan
 * @version 0.1
 *          Date of creation: January 31, 2022
 *          Last Date Modified: February 10, 2022
 */
public class Show extends Media {
	// Data members
	private int seasons;

	/***
	 * Default constructor
	 * No parameters
	 * Initializes title, genre, rating, seasons
	 */
	public Show() {
		super();
		seasons = 0;
	}

	/***
	 * Constructor with four parameters
	 * 
	 * @param title   for the title of the show
	 * @param genre   for the genre of the show
	 * @param rating  for the rating of the show
	 * @param seasons for the seasons of the show
	 */
	public Show(String title, String genre,
			String rating, int seasons) {
		super(title, genre, rating);
		this.seasons = seasons;
	}

	/***
	 * Getter for the seasons of the show
	 * 
	 * @param no parameters
	 * @return the value of the data member seasons
	 */
	public int getSeasons() {
		return seasons;
	}

	/***
	 * Setter for the seasons of the show
	 * 
	 * @param s to set the data member seasons
	 *          no return value
	 */
	public void setSeasons(int s) {
		seasons = s;
	}

	/***
	 * Method to get the show information
	 * no parameters
	 * 
	 * @return formatted string containing the value of the data members
	 */
	public String toString() {
		String out;
		out = super.toString() + String.format("\t%-5s",
				seasons);
		return out;
	}

	/***
	 * Method to get the show information in a formatted manner similar to text file
	 * no parameters
	 * 
	 * @return formatted string containing the value of the data members
	 */
	public String simpleString() {
		String out;
		out = ("Show|") + super.simpleString() + String.format("%s", seasons);
		return out;
	}
}
