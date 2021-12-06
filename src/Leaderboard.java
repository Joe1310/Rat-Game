import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/***
 * The class is responsible for dealing with LeaderBoard and it's functionality.
 *
 * @author Patel, Elliot, Joe, Shivraj.
 * @version 2.0
 */

public class Leaderboard {

	// Array size of the different levels
	private static final int LEVEL_DATA_SIZE = 10;

	private static final int LEVEL_1_NUM = 1;
	private static final int LEVEL_2_NUM = 2;
	private static final int LEVEL_3_NUM = 3;
	private static final int LEVEL_4_NUM = 4;

	private static final int INDEX_TWO = 2;
	private static final int INDEX_THREE = 3;
	private static final int INDEX_FOUR = 4;
	private static final int INDEX_FIVE = 5;
	private static final int INDEX_SIX = 6;
	private static final int INDEX_SEVEN = 7;
	private static final int INDEX_EIGHT = 8;
	private static final int INDEX_NINE = 9;

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

		File first = new File("src/leaderboards/Leaderboard1.txt");
		Scanner level1;
		level1 = new Scanner(first);
		readLeaderboard(level1, LEVEL_1_NUM);

		File second = new File("src/leaderboards/Leaderboard2.txt");
		Scanner level2;
		level2 = new Scanner(second);
		readLeaderboard(level2, LEVEL_2_NUM);

		File third = new File("src/leaderboards/Leaderboard3.txt");
		Scanner level3;
		level3 = new Scanner(third);
		readLeaderboard(level3, LEVEL_3_NUM);

		File fourth = new File("src/leaderboards/Leaderboard4.txt");
		Scanner level4;
		level4 = new Scanner(fourth);
		readLeaderboard(level4, LEVEL_4_NUM);
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

		for (int i = 0; i < LEVEL_DATA_SIZE; i++) {
			tempNames[i] = board.next();
			tempScores[i] = board.nextInt();
		}

		if (levelNo == LEVEL_1_NUM) {
			firstNames = tempNames;
			firstScores = tempScores;
		} else if (levelNo == LEVEL_2_NUM) {
			secondNames = tempNames;
			secondScores = tempScores;
		} else if (levelNo == LEVEL_3_NUM) {
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

		File leaderboard1 = new File("src/leaderboards/Leaderboard1.txt");
		try {
			FileWriter lvl1 = new FileWriter(leaderboard1);
			lvl1.write(getLeaderboardData(LEVEL_1_NUM));
			lvl1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		File leaderboard2 = new File("src/leaderboards/Leaderboard2.txt");
		try {
			FileWriter lvl2 = new FileWriter(leaderboard2);
			lvl2.write(getLeaderboardData(LEVEL_2_NUM));
			lvl2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		File leaderboard3 = new File("src/leaderboards/Leaderboard3.txt");
		try {
			FileWriter lvl3 = new FileWriter(leaderboard3);
			lvl3.write(getLeaderboardData(LEVEL_3_NUM));
			lvl3.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		File leaderboard4 = new File("src/leaderboards/Leaderboard4.txt");
		try {
			FileWriter lvl4 = new FileWriter(leaderboard4);
			lvl4.write(getLeaderboardData(LEVEL_4_NUM));
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
		if (level == LEVEL_1_NUM) {
			updatePosition(firstScores, firstNames, playerName, score, LEVEL_1_NUM);
		} else if (level == LEVEL_2_NUM) {
			updatePosition(secondScores, secondNames, playerName, score, LEVEL_2_NUM);
		} else if (level == LEVEL_3_NUM) {
			updatePosition(thirdScores, thirdNames, playerName, score, LEVEL_3_NUM);
		} else {
			updatePosition(fourthScores, fourthNames, playerName, score, LEVEL_4_NUM);
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

		int loc;
		// Checks where in the leaderboard the player should be placed.

		if (levelScores[0] > score) {
			if (levelScores[1] > score) {
				if (levelScores[INDEX_TWO] > score) {
					if (levelScores[INDEX_THREE] > score) {
						if (levelScores[INDEX_FOUR] > score) {
							if (levelScores[INDEX_FIVE] > score) {
								if (levelScores[INDEX_SIX] > score) {
									if (levelScores[INDEX_SEVEN] > score) {
										if (levelScores[INDEX_EIGHT] > score) {
											if (levelScores[INDEX_NINE] > score) {
												loc = 10;
											} else {
												loc = 9;
											}
										} else {
											loc = 8;
										}
									} else {
										loc = 7;
									}
								} else {
									loc = 6;
								}
							} else {
								loc = 5;
							}
						} else {
							loc = 4;
						}
					} else {
						loc = 3;
					}
				} else {
					loc = 2;
				}
			} else {
				loc = 1;
			}
		} else{
			loc = 0;
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
		if(loc < 10) {
			for (int i = LEVEL_DATA_SIZE - 1; i > loc; i--) {
				scores[i] = scores[i - 1];
				names[i] = names[i - 1];
			}
			scores[loc] = score;
			names[loc] = playerID;

			if (levelNo == LEVEL_1_NUM) {
				firstScores = scores;
				firstNames = names;
			} else if (levelNo == LEVEL_2_NUM) {
				secondScores = scores;
				secondNames = names;
			} else if (levelNo == LEVEL_3_NUM) {
				thirdScores = scores;
				thirdNames = names;
			} else {
				fourthScores = scores;
				fourthNames = names;
			}
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

		if (levelNo == LEVEL_1_NUM) {
			for (int i = 0; i < LEVEL_DATA_SIZE; i++) {
				line += firstNames[i] + " " + firstScores[i] + "\n";
			}
		} else if (levelNo == LEVEL_2_NUM) {
			for (int i = 0; i < LEVEL_DATA_SIZE; i++) {
				line += secondNames[i] + " " + secondScores[i] + "\n";
			}
		} else if (levelNo == LEVEL_3_NUM) {
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

		if (levelNo == LEVEL_1_NUM) {
			for (int i = 0; i < LEVEL_DATA_SIZE; i++) {
				line += (i + 1) + ".\t" + firstNames[i] + " " + firstScores[i] + "\n";
			}
		} else if (levelNo == LEVEL_2_NUM) {
			for (int i = 0; i < LEVEL_DATA_SIZE; i++) {
				line += (i + 1) + ".\t" + secondNames[i] + " " + secondScores[i] + "\n";
			}
		} else if (levelNo == LEVEL_3_NUM) {
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
