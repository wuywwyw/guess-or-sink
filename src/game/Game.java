package game;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Enables the user to play a console game. Displays the number of wins.
 *
 * @author Foothill College, Yuwei Wu
 */
public class Game 
{
	/** Define static variables of type String and set them to the path of the file */
	private static final String HEROS_FILENAME = "resources/superheroes.txt";
	private static final String TOPICS_FILENAME = "resources/topics.txt";
	private static final String COLORS_FILENAME = "resources/colors.txt";

	/** Displays the menu prompting the user to check if the user wants to play */
	public static void printMenu() 
	{
		System.out.println("What would you like to do?");
		System.out.println("\t1. Play Guess or Sink with Programming Topics category");
		System.out.println("\t2. Play Guess or Sink with Super Heros Category");
		System.out.println("\t3. Play Guess or Sink with Colors category");
		System.out.println("\tq. Quit");
		System.out.println("Enter your selection: ");
	}

	/**
	 * Displays a menu to the user where the user can choose to play a 
	 * console game. 
	 * @param args	not used
	 */
	public static void main(String[] args) 
	{
		System.out.println("Welcome to CS1A console game!");

		// Stores user input menu selection.
		char selection;	

		// Reads from standard in.
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);

		// Keeps track of the number of games played.
		int numGamesPlayed = 0;

		// Keeps track of how many games the user has won.
		int score = 0;

		do
		{
			//Define a static method for the Game class which displays a menu.
			printMenu();

			// Parses the user input.
			String tmpInput = keyboard.next();
			selection = tmpInput.charAt(0);

			// Enable three selections
			if (selection == '1' || selection == '2' || selection == '3')
			{
				numGamesPlayed++;

				// Enables the user to play a console game where they have to guess the letters in a randomly chosen word
				GuessOrSink shipGame;

				String inputFileName = TOPICS_FILENAME;
				if (selection == '2')
					inputFileName = HEROS_FILENAME;
				if (selection == '3')
					inputFileName = COLORS_FILENAME;

				try
				{
					// Update the constructor to receive an argument of inputFileName
					shipGame = new GuessOrSink(inputFileName);

					// Display introduction
					shipGame.displayIntroduction();

					// Call a method for user to play the game
					shipGame.play();

					// Call an accessor method which returns true or false depending on the user guesses
					boolean win = shipGame.getWinStatus();

					// Display ending
					shipGame.displayEnding();

					// Uses a ternary operator to update the score
					score = win ? ++score: score;

					// Prints out the current score.
					System.out.println("Your current score is " + score);
				}
				catch (FileNotFoundException e)
				{
					System.out.println("\nInvalid filename. Please choose different topic.\n");
				}
			}
		} while (selection != 'q');

		System.out.println("Your score is " + score + " out of " + numGamesPlayed);
		System.out.println("Goodbye.");
	} // FUNCTION : main
}
