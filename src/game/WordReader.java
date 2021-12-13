package game;

//Import File class
import java.io.File;
//Import the FileNotFoundException class to throw the exception if the file is not found
import java.io.FileNotFoundException;
//Import the Scanner class to use for reading from inFile
import java.util.Scanner;

/**
 * Read in a file and populate an array of words to be used by the GuessOrSink class
 *
 * @author Foothill College, Yuwei Wu
 */
public class WordReader
{
    String[] words;

    /**
     * Constructor of WordReader
     * @param fileName  the file to parse
     * @throws FileNotFoundException throw the exception if the file is not found
     */
    public WordReader(String fileName) throws FileNotFoundException
    {
        File inFile = new File(fileName);
        Scanner input = new Scanner(inFile);

        String line = input.nextLine();
        String[] tokens = line.split(",");
        int numWords = Integer.parseInt(tokens[1]);
        words = new String[numWords];

        for (int j = 0; j < words.length; j++)
        {
            line = input.next();
            words[j] = line;
        }
    }

    /**
     * Returns array of words
     * @return array of words
     */
    public String[] getWords()
    {
        return words;
    }

}
