import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class is responsible for dealing with Player's data, creating and
 * loading text files and updating the necessary player data.
 * <p>
 * File name: PlayerData.java
 *
 * @author Patel and Elliot
 * @version 2.0
 */

public class PlayerData {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;

    // Attributes for the Name of the Player and max Level unlocked by them
    private String playerName;
    private int maxLevel;

    /***
     * Constructor either reads Player data or initialises it by calling appropriate
     * method
     *
     * @param playerFile Text file which has Player's saved game data
     */
    public PlayerData(File playerFile) {
        if (playerFile.length() != ZERO) {
            Scanner prof = null;
            try {
                prof = new Scanner(playerFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            assert prof != null;
            readPlayerData(prof);
        } else {
            initialiseData(playerFile);
        }
    }

    /***
     * Method reads the player's data using Scanner
     *
     * @param scan Scanner
     */
    private void readPlayerData(Scanner scan) {
        playerName = scan.next();
        maxLevel = scan.nextInt();
        scan.close();
    }

    /***
     * Method initialises the data using file
     *
     * @param playerFile File containing Player's data
     */
    private void initialiseData(File playerFile) {
        String playerName = playerFile.getName();
        this.playerName = playerName.substring(ZERO, playerName.length() - FOUR);
        this.maxLevel = ONE;
        updatePlayerFile();
    }

    /***
     * Method creates a text file and writes data in it.
     *
     */
    public void updatePlayerFile() {
        File player = new File("src/profiles/" + getPlayerName() + ".txt");
        try {
            FileWriter playerFile = new FileWriter(player);
            playerFile.write(getPlayerName() + "\n" + getMaxLevel());
            playerFile.close();

        } catch (IOException e) {
            System.out.println("\nAn error occurred while updating player profile.");
            e.printStackTrace();
        }
    }

    /***
     * Method updates max level of the player if the previous level is smaller than
     * the new one
     *
     * @param level Number of the Level
     */
    public void updateMaxLevel(int level) {
        switch (level) {
            case ONE:
                if (maxLevel < TWO) {
                    maxLevel = TWO;
                }
                break;
            case TWO:
                if (maxLevel < THREE) {
                    maxLevel = THREE;
                }
                break;
            case THREE:
                if (maxLevel < FOUR) {
                    maxLevel = FOUR;
                }
                break;
            default:
                break;
        }
    }

    /***
     * Get method for getting player's name
     *
     * @return playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /***
     * Get method for getting player's max level unlocked
     *
     * @return maxLevel
     */
    public int getMaxLevel() {
        return maxLevel;
    }
}
