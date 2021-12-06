import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/***
 * The class is responsible for dealing with LeaderBoard and it's functionality.
 *
 * FileName : Leaderboard.java
 *
 * @author Patel, Elliot, Joe, Shivraj.
 * @version 2.0
 */

public class Leaderboard {

	// Array size of the different levels
	private static final int LEVEL_DATA_SIZE = 10;

	private static final int ZERO = 0;
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int FIVE = 5;
	private static final int SIX = 6;
	private static final int SEVEN = 7;
	private static final int EIGHT = 8;
	private static final int NINE = 9;
	private static final int TEN = 10;

	// Arrays for storing scores and player's names for Level 1
	private static int[] firstScores = new int[LEVEL_DATA_SIZE];
	private static String[] firstNames = new String[LEVEL_DATA_SIZE];

	// Arrays for storing scores and player's names for Level 2
	private static int[] secondScores = new int[LEVEL_DATA_SIZE];
	private static String[] secondNames = new String[LEVEL_DATA_SIZE];

	// Arrays for storing scores and player's names for Level 3
	private static int[] thirdScores = new int[LEVEL_DATA_SIZE];
	private static String[] thirdNames = new String[LEVEL_DATA_SIZE];

	// Arrays for storing scores and player's names for Level 4
	private static int[] fourthScores = new int[LEVEL_DATA_SIZE];
	private static String[] fourthNames = new String[LEVEL_DATA_SIZE];

	/***
	 * Constructor Leaderboard tries to call readLeaderboards method and catches
	 * exception if any.
	 */
	public Leaderboard() {
		try {
			// Reads the text files of LeaderBoards
			readLeaderboards();
		} catch (FileNotFoundException e) {
			System.out.println("Leaderboards not found.");
		}
	}

	/***
	 * Method passes the leaderboards of each levels from the mentioned text files,
	 * using scanner to readLeaderboard method.
	 *
	 * @throws FileNotFoundException If the method fails to locate the required file
	 */
	public void readLeaderboards() throws FileNotFoundException {

		// Reads level 1 data using scanner
		File first = new File("src/leaderboards/Leaderboard1.txt");
		Scanner level1;
		level1 = new Scanner(first);
		readLeaderboard(level1, ONE);

		// Reads level 2 data using scanner
		File second = new File("src/leaderboards/Leaderboard2.txt");
		Scanner level2;
		level2 = new Scanner(second);
		readLeaderboard(level2, TWO);

		// Reads level 3 data using scanner
		File third = new File("src/leaderboards/Leaderboard3.txt");
		Scanner level3;
		level3 = new Scanner(third);
		readLeaderboard(level3, THREE);

		// Reads level 4 data using scanner
		File fourth = new File("src/leaderboards/Leaderboard4.txt");
		Scanner level4;
		level4 = new Scanner(fourth);
		readLeaderboard(level4, FOUR);
	}

	/***
	 * Method Reads all the data of the leaderboard and stores them in the
	 * appropriate level array
	 *
	 * @param board   Leader board
	 * @param levelNo Number of the Level
	 */
	public void readLeaderboard(Scanner board, int levelNo) {

		String[] tempNames = new String[LEVEL_DATA_SIZE];
		int[] tempScores = new int[LEVEL_DATA_SIZE];

		for (int i = ZERO; i < LEVEL_DATA_SIZE; i++) {
			tempNames[i] = board.next();
			tempScores[i] = board.nextInt();
		}

		if (levelNo == ONE) {
			firstNames = tempNames;
			firstScores = tempScores;
		} else if (levelNo == TWO) {
			secondNames = tempNames;
			secondScores = tempScores;
		} else if (levelNo == THREE) {
			thirdNames = tempNames;
			thirdScores = tempScores;
		} else {
			fourthNames = tempNames;
			fourthScores = tempScores;
		}

	}

	/***
	 * Method to write the leaderboards data in the appropriate text files and catch
	 * IOExceptions if any.
	 *
	 */
	public static void writeLeaderboards() {

		// Writes level 1 leader board data in the text file
		File leaderboard1 = new File("src/leaderboards/Leaderboard1.txt");
		try {
			FileWriter lvl1 = new FileWriter(leaderboard1);
			lvl1.write(getLeaderboardData(ONE));
			lvl1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Writes level 2 leader board data in the text file
		File leaderboard2 = new File("src/leaderboards/Leaderboard2.txt");
		try {
			FileWriter lvl2 = new FileWriter(leaderboard2);
			lvl2.write(getLeaderboardData(TWO));
			lvl2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Writes level 3 leader board data in the text file
		File leaderboard3 = new File("src/leaderboards/Leaderboard3.txt");
		try {
			FileWriter lvl3 = new FileWriter(leaderboard3);
			lvl3.write(getLeaderboardData(THREE));
			lvl3.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Writes level 4 leader board data in the text file
		File leaderboard4 = new File("src/leaderboards/Leaderboard4.txt");
		try {
			FileWriter lvl4 = new FileWriter(leaderboard4);
			lvl4.write(getLeaderboardData(FOUR));
			lvl4.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/***
	 * Method calls updatePosition method while checking particular conditions and
	 * passes the parameter to it.
	 *
	 * @param level      Number of the Level
	 * @param playerName Name of the Player
	 * @param score      Score of the Player
	 */
	public static void inputPlayerScore(int level, String playerName, int score) {
		if (level == ONE) {
			updatePosition(firstScores, firstNames, playerName, score, ONE);
		} else if (level == TWO) {
			updatePosition(secondScores, secondNames, playerName, score, TWO);
		} else if (level == THREE) {
			updatePosition(thirdScores, thirdNames, playerName, score, THREE);
		} else {
			updatePosition(fourthScores, fourthNames, playerName, score, FOUR);
		}
	}

	/***
	 * Method checks for the ranking of the player on the basis of their scores and
	 * updates by passing them to updateLeaderboard method.
	 *
	 * @param levelScores Array of player's scores on the leader board
	 * @param levelNames  Array of player's names on the leader board
	 * @param playerID    Player's name
	 * @param score       Player's score
	 * @param levelNo     Number of the Level
	 */
	public static void updatePosition(int[] levelScores, String[] levelNames, String playerID, int score, int levelNo) {

		int loc = ZERO;

		// Checks for the location where player stands on.
		if(levelScores[0] == 0) {
			levelScores[0] = score;
		}else {
			if (levelScores[ZERO] > score) {
				if (levelScores[ONE] > score) {
					if (levelScores[TWO] > score) {
						if (levelScores[THREE] > score) {
							if (levelScores[FOUR] > score) {
								if (levelScores[FIVE] > score) {
									if (levelScores[SIX] > score) {
										if (levelScores[SEVEN] > score) {
											if (levelScores[EIGHT] > score) {
												if (levelScores[NINE] > score) {
													loc = TEN;
												} else {
													loc = NINE;
												}
											} else {
												loc = EIGHT;
											}
										} else {
											loc = SEVEN;
										}
									} else {
										loc = SIX;
									}
								} else {
									loc = FIVE;
								}
							} else {
								loc = FOUR;
							}
						} else {
							loc = THREE;
						}
					} else {
						loc = TWO;
					}
				} else {
					loc = ONE;
				}
			}
		}

		updateLeaderboard(levelScores, levelNames, loc, playerID, score, levelNo);
	}

	/***
	 * Method updates the Leader board of the given level using the passed
	 * parameters.
	 *
	 * @param scores   Array of player's scores on the leader board
	 * @param names    Array of player's names on the leader board
	 * @param loc      Location where the Recent winner's player is going to be
	 *                 added
	 * @param playerID Player's name
	 * @param score    Player's score
	 * @param levelNo  Number of the Level
	 */
	private static void updateLeaderboard(int[] scores, String[] names, int loc, String playerID, int score,
			int levelNo) {

		for (int i = LEVEL_DATA_SIZE - ONE; i > loc; i--) {
			scores[i] = scores[i - ONE];
			names[i] = names[i - ONE];
		}
		scores[loc] = score;
		names[loc] = playerID;

		if (levelNo == ONE) {
			firstScores = scores;
			firstNames = names;
		} else if (levelNo == TWO) {
			secondScores = scores;
			secondNames = names;
		} else if (levelNo == THREE) {
			thirdScores = scores;
			thirdNames = names;
		} else {
			fourthScores = scores;
			fourthNames = names;
		}
	}

	/***
	 * Get method for storing Leader board data in a specific format, in the text
	 * file.
	 *
	 * @param levelNo Number of the Level
	 * @return line Output line for text file
	 */
	public static String getLeaderboardData(int levelNo) {

		String line = "";

		if (levelNo == ONE) {
			for (int i = 0; i < LEVEL_DATA_SIZE; i++) {
				line += firstNames[i] + " " + firstScores[i] + "\n";
			}
		} else if (levelNo == TWO) {
			for (int i = 0; i < LEVEL_DATA_SIZE; i++) {
				line += secondNames[i] + " " + secondScores[i] + "\n";
			}
		} else if (levelNo == THREE) {
			for (int i = 0; i < LEVEL_DATA_SIZE; i++) {
				line += thirdNames[i] + " " + thirdScores[i] + "\n";
			}
		} else {
			for (int i = 0; i < LEVEL_DATA_SIZE; i++) {
				line += fourthNames[i] + " " + fourthScores[i] + "\n";
			}
		}

		return line;
	}

	/***
	 * Get method for displaying Leader board data in a specific format, in the text
	 * file.
	 *
	 * @param levelNo Number of the Level
	 * @return line Output line for menus
	 */
	public static String formatLeaderboardData(int levelNo) {

		String line = "";

		if (levelNo == ONE) {
			for (int i = 0; i < LEVEL_DATA_SIZE; i++) {
				line += (i + 1) + ".\t" + firstNames[i] + " " + firstScores[i] + "\n";
			}
		} else if (levelNo == TWO) {
			for (int i = 0; i < LEVEL_DATA_SIZE; i++) {
				line += (i + 1) + ".\t" + secondNames[i] + " " + secondScores[i] + "\n";
			}
		} else if (levelNo == THREE) {
			for (int i = 0; i < LEVEL_DATA_SIZE; i++) {
				line += (i + 1) + ".\t" + thirdNames[i] + " " + thirdScores[i] + "\n";
			}
		} else {
			for (int i = 0; i < LEVEL_DATA_SIZE; i++) {
				line += (i + 1) + ".\t" + fourthNames[i] + " " + fourthScores[i] + "\n";
			}
		}
		return line;
	}
}
