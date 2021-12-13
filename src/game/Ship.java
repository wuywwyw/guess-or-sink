package game;

//Import maxHitsAllowed in class GuessOrSink
import static game.GuessOrSink.maxHitsAllowed;

/**
 * One object of class Ship which provides functionality for keeping track of the ship state.
 *
 * @author Foothill College, Yuwei Wu
 */
public class Ship
{
    /** Number of incorrect guesses equivalent to the number of lines of the ship to sink */
    private int numOfHits;
    private boolean isAlive;
    /** Used in for loop */
    private int i;
    private String[] shipAndSails;
    private int numOfSailRows = maxHitsAllowed - 3;

    //Add static final String variables to stores different elements of the ship
    static final String mast = "                        |       |            \n";
    static final String sails = "                     3--|--3 3--|--3         \n";
    static final String deck = "                  \\-----|-------|-----//     \n";
    static final String shipSide = "                   \\__YW_____________//      \n";
    static final String water1 = "----------------------------------------------------------\n";
    static final String water2 = " ^^^^^          ^^^^^          ^^^^^          ^^^^^       \n";
    static final String water3 = "         ^^^^^         ^^^^^          ^^^^^          ^^^^^\n";
    static final String water = (water1 + water2 + water3);

    /**
     * Constructs a Ship object with the specified maxHitsAllowed.
     * @param maxHitsAllowed maximum allowable hits
     */
    public Ship(int maxHitsAllowed)
    {
        GuessOrSink.maxHitsAllowed = maxHitsAllowed;

        //Builds the array of the ship for the specific word
        shipAndSails = new String[GuessOrSink.maxHitsAllowed];
        shipAndSails[0] = mast;
        for (i = 1; i <= numOfSailRows; i++)
        {
            shipAndSails[i] = sails;
        }
        shipAndSails[GuessOrSink.maxHitsAllowed - 2] = deck;
        shipAndSails[GuessOrSink.maxHitsAllowed - 1] = shipSide;

        //build the full ship
        StringBuilder fullShip = new StringBuilder();
        for (i = 0; i < shipAndSails.length; i++)
        {
            fullShip.append(shipAndSails[i]);
        }
        fullShip.append(water);
    }


    /**
     * Accessor method returns how many hits the ship has taken so far.
     * @return numOfHits    number of incorrect guesses
     */
    public int getNumOfHits()
    {
        return numOfHits;
    }


    /** A method increases the number of hits by one */
    public void addHits()
    {
        numOfHits++;
    }


    /**
     * Accessor method returns if the ship is alive.
     * @return isAlive    the status of the ship
     */
    public boolean getIsAlive()
    {
        if (numOfHits >= maxHitsAllowed)
            isAlive= false;
        return isAlive;
    }

    /** String representation of the ASCII ship in its current state */
    public String toString()
    {
        int numOfSinkingShipLine = maxHitsAllowed - numOfHits;
        StringBuilder drawShip = new StringBuilder();
        for (int i = 0; i < numOfSinkingShipLine; i++)
        {
            drawShip.append(shipAndSails[i]);
        }
        drawShip.append(water);
        return drawShip.toString();
    }
}

