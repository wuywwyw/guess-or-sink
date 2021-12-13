package game;

//Import the FileNotFoundException class to throw the exception if the file is not found
import java.io.FileNotFoundException;
//Import the Random class to use for generate random index number
import java.util.Random;
//Import the Scanner class to use for reading from System.in
import java.util.Scanner;

import static game.Ship.water;

/**
 * Enables the user to play a console game
 * where they have to guess the letters in a randomly chosen word.
 *
 * @author Foothill College, Yuwei Wu
 */
public class GuessOrSink
{
    /** An array of boolean objects */
    static boolean[] wordsUsed;
    /** Initialize the number of words played to 0 at the beginning the game */
    static int numOfWordsPlayed = 0;
    /** Number of total guesses */
    static int maxHitsAllowed;
    /** Allow user to have an extra guess */
    public static final int extraGuesses = 1;
    /** Store the word user should guess */
    private String wordInPlay;
    /** Initialize the number of times user guesses to 1 */
    private int guessNumber = 1;
    /** Number of correct guesses */
    private int correctGuesses;
    /** Used in for loop */
    private int i;
    /** Each position of the array represents a letter in wordInPlay */
    private char[] lettersGuessed;
    /** Hold the letters guessed for the presentation of the word in the console */
    private StringBuilder presentedWord;
    /** Keep track of the ship state */
    private Ship theShip;
    /** Check if the user wins the current instance of the game */
    boolean winStatus;
    /** Check if the game has completed */
    boolean gameInProgress;
    /** If the user guess the word correct */
    boolean guessedWord;
    private static String[] wordsToGuess;

    /**
     * Constructs a GuessOrSink object which initializes the instance variables
     * @param inputFileName input the file name
     * @throws FileNotFoundException throws the exception if the file is not found
     */
    public GuessOrSink(String inputFileName) throws FileNotFoundException
    {
        WordReader getFile = new WordReader(inputFileName);
        wordsToGuess = getFile.getWords();

        Random rnd = new Random();
        int randomIndex = rnd.nextInt(wordsToGuess.length);
        wordInPlay = wordsToGuess[randomIndex];

        //Create an array to determine if a word has been selected or not
        wordsUsed = new boolean[wordsToGuess.length];
        for (int i = 0; i < wordsToGuess.length; i++)
        {
            wordsUsed[i] = false;
        }

        //Create a loop to generate a random number without duplicating it
        do
        {
            if (!wordsUsed[randomIndex])
            {
                wordInPlay = wordsToGuess[randomIndex];
            }
        }
        while (wordsUsed[randomIndex]);
        wordsUsed[randomIndex] = true;
        numOfWordsPlayed++;

        maxHitsAllowed = wordInPlay.length() + extraGuesses;

        //Creates the array that will hold the letters guessed and initializes it to underscores
        presentedWord = new StringBuilder();
        lettersGuessed = new char[wordInPlay.length()];
        for (i = 0; i < wordInPlay.length(); i++)
        {
            lettersGuessed[i] = '_';
            if (i > 0)
            {
                presentedWord.append(" ");
            }
            presentedWord.append(lettersGuessed[i]);
        }
        presentedWord.toString();
        theShip = new Ship(maxHitsAllowed);
    }


    /**
     *  A public method which asks the Ship instance for a String representation
     *  and displays the ship and a message introducing the current game.
     */
    public void displayIntroduction()
    {
        System.out.println("Let's play Guess or Sink!");
        System.out.println(theShip.toString());
        if (gameInProgress = true)
        {

            System.out.println("To save the ship from sinking, your job is to guess the " +
                    wordInPlay.length() + " letter word: " + presentedWord);
        }
        else
        {
            displayEnding();
        }

    }


    /**
     * A public method which asks the user to guess a letter.
     * Then, draws the updated ship if the user makes an incorrect guess.
     */
    public void play()
    {
        correctGuesses = 0;
        char guess;
        guessedWord = false;
        Scanner keyboard = new Scanner(System.in);

        while (guessNumber <= maxHitsAllowed && !guessedWord)
        {
            System.out.println("Enter your guess #" + guessNumber + ":");
            String input = keyboard.nextLine();
            guess = Character.toLowerCase(input.charAt(0));

            if (wordInPlay.toLowerCase().indexOf(guess) >= 0)
            {
                System.out.println("Correct!\n");
                for (i = 0; i < wordInPlay.length(); i++)
                {
                    if (wordInPlay.toLowerCase().charAt(i) == guess)
                    {
                        correctGuesses++;
                        lettersGuessed[i] = guess;
                    }
                }
                System.out.println(String.valueOf(lettersGuessed).replaceAll(".", "$0 "));

                guessedWord = true;
                for (i = 0; i < lettersGuessed.length; i++)
                {
                    if (lettersGuessed[i] == '_')
                    {
                        guessedWord = false;
                        break;
                    }
                }

            }
            else
            {
                System.out.println("Incorrect guess!");
                theShip.addHits();
                System.out.println(theShip.toString());
            }
            guessNumber++;
            gameInProgress = true;
        }
        gameInProgress = false;
    }


    /**
     * A public method which returns:
     * - true, if the user asked guessed all the letters.
     * - false, if the user has no remaining guesses, returns false.
     * @return winStatus
     */
    public boolean getWinStatus()
    {
        if(correctGuesses == wordInPlay.length())
        {
            winStatus = true;
        }
        else
        {
            winStatus = false;
        }
        return winStatus;
    }


    /**  A public method which shows the wordInPlay and whether the user has won the game */
    public void displayEnding()
    {
        if(guessedWord)
        {
            System.out.println("You saved the day! The word is: " + wordInPlay);
            System.out.println(theShip.toString());
        }
        else
        {
            System.out.println("The ship has sunk! The word is: " + wordInPlay + "\n" + water);
        }
        System.out.println("Game Over");
    }
}

