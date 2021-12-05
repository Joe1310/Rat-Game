import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Leaderboard {


	private static int[] firstScores = new int[10];
	private static String[] firstNames = new String[10];
	private static int[] secondScores = new int[10];
	private static String[] secondNames = new String[10];
	private static int[] thirdScores = new int[10];
	private static String[] thirdNames = new String[10];
	private static int[] fourthScores = new int[10];
	private static String[] fourthNames = new String[10];
	
	//Constructor for saving the PlayerData
	public Leaderboard() {
		try {
			readLeaderboards();
		} catch (FileNotFoundException e) {
			System.out.println("Leaderboards not found");
		}
	}
	
	public void readLeaderboards() throws FileNotFoundException {
		File first = new File("src/leaderboards/Leaderboard1.txt");
		Scanner level1 = null;
		level1 = new Scanner(first);
		readLeaderboard(level1, 1);
		
		File second = new File("src/leaderboards/Leaderboard2.txt");
		Scanner level2 = null;
		level2 = new Scanner(second);
		readLeaderboard(level2, 2);
		
		File third = new File("src/leaderboards/Leaderboard3.txt");
		Scanner level3 = null;
		level3 = new Scanner(third);
		readLeaderboard(level3, 3);
		
		File fourth = new File("src/leaderboards/Leaderboard4.txt");
		Scanner level4 = null;
		level4 = new Scanner(fourth);
		readLeaderboard(level4, 4);
	}
	
	public void readLeaderboard(Scanner board, int levelNo) {
		String[] tempNames = new String[10];
		int[] tempScores = new int[10];
		for (int i = 0; i < 10; i++) {
			tempNames[i] = board.next();
			tempScores[i] = board.nextInt();
		}
		
		if(levelNo == 1) {
			firstNames = tempNames;
			firstScores = tempScores;
		} else if(levelNo == 2) {
			secondNames = tempNames;
			secondScores = tempScores;
		} else if(levelNo == 3){
			thirdNames = tempNames;
			thirdScores = tempScores;
		} else {
			fourthNames = tempNames;
			fourthScores = tempScores;
		}
		
	}
	
	public static void writeLeaderboards() {
		File leaderboard1 = new File("src/leaderboards/Leaderboard1.txt");
		try {
			FileWriter lvl1 = new FileWriter(leaderboard1);
			lvl1.write(getLeaderboardData(1));
			lvl1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File leaderboard2 = new File("src/leaderboards/Leaderboard2.txt");
		try {
			FileWriter lvl2 = new FileWriter(leaderboard2);
			lvl2.write(getLeaderboardData(2));
			lvl2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File leaderboard3 = new File("src/leaderboards/Leaderboard3.txt");
		try {
			FileWriter lvl3 = new FileWriter(leaderboard3);
			lvl3.write(getLeaderboardData(3));
			lvl3.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File leaderboard4 = new File("src/leaderboards/Leaderboard4.txt");
		try {
			FileWriter lvl4 = new FileWriter(leaderboard4);
			lvl4.write(getLeaderboardData(4));
			lvl4.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	//Method updates the player's data in the given level
	public static void inputPlayerScore(int level, String playerName, int score) {
		if(level == 1) {
			updatePosition(firstScores, firstNames, playerName, score, 1);
		} else if(level == 2) {
			updatePosition(secondScores, secondNames, playerName, score, 2);
		} else if(level == 3){
			updatePosition(thirdScores, thirdNames, playerName, score, 3);
		} else {
			updatePosition(fourthScores, fourthNames, playerName, score, 4);
		}
	}

	//Method checks for the ranking of the player on the basis of their scores and updates them
	public static void updatePosition(int[]levelScores, String[]levelNames, String playerID, int score, int levelNo) {

		int loc = 0;

		if(levelScores[0] > score) {
			if(levelScores[1] > score) {
				if(levelScores[2] > score) {
					if(levelScores[3] > score) {
						if(levelScores[4] > score) {
							if(levelScores[5] > score) {
								if(levelScores[6] > score) {
									if(levelScores[7] > score) {
										if(levelScores[8] > score) {
											if(levelScores[9] > score) {
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
		} else {
			loc = 0;
		}

		updateScoreboard(levelScores, levelNames, loc, playerID, score, levelNo);
	}

	///Method updates the Score board of the given level.
	private static void updateScoreboard(int[] scores, String[] names, int loc, String playerID, int score, int levelNo) {
		
		if (loc != 10) {
			for (int i = 9; i > loc; i--) {
					scores[i] = scores[i - 1];
					names[i] = names[i - 1];
			}
			scores[loc] = score;
			names[loc] = playerID;
			
			if (levelNo == 1) {
				firstScores = scores;
				firstNames = names;
			} else if (levelNo == 2) {
				secondScores = scores;
				secondNames = names;
			} else if (levelNo == 3) {
				thirdScores = scores;
				thirdNames = names;
			} else {
				fourthScores = scores;
				fourthNames = names;
			}
		}
	}
	
	//Method for storing the PlayerData in text file
	public static String getLeaderboardData(int levelNo) {
		
		String line = "";
		
		if (levelNo == 1) {
			for(int i = 0; i < 10; i ++) {
				line += firstNames[i] + " " + firstScores[i] + "\n";
			}
		} else if (levelNo == 2) {
			for(int i = 0; i < 10; i ++) {
				line += secondNames[i] + " " + secondScores[i] + "\n";
			}
		} else if (levelNo == 3) {
			for(int i = 0; i < 10; i ++) {
				line += thirdNames[i] + " " + thirdScores[i] + "\n";
			}
		} else {
			for(int i = 0; i < 10; i ++) {
				line += fourthNames[i] + " " + fourthScores[i] + "\n";
			}
		}
		
		return line;
	}
}
