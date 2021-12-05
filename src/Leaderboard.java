import java.util.ArrayList;

public class Leaderboard {


	private static int[][] first = new int[10][2];
	private static int[][] second = new int[10][2];
	private static int[][] third = new int[10][2];
	
	static ArrayList<PlayerData> players = new ArrayList<PlayerData>();
	
	/*
	//Remove this method in final copy
	public static void main(String args[]) {
		
		PlayerData player = new PlayerData();
		players.add(player);
		PlayerData player1 = new PlayerData("Nick", 10, 2);
		players.add(player1);
		PlayerData player2 = new PlayerData("El", 10, 3);
		players.add(player2);
		
		updateLevel(1,1,5);
		updateLevel(1,2,2);
		updateLevel(1,3,10);
		
		updateLevel(2,4,5);
		updateLevel(2,5,6);
		updateLevel(2,6,1);
		
		updateLevel(3,7,10);
		updateLevel(3,8,3);
		updateLevel(3,9,5);
		
		updateLevel(1,7,11);
		updateLevel(2,1,17);
		updateLevel(3,6,7);
		
		//System.out.println(printScoreBoard());
		System.out.println(getPlayerData());
			
	}
	*/
	
	//Constructor for saving the PlayerData
	public Leaderboard(int[][] first, int[][] second, int[][] third, ArrayList<PlayerData> players) {
		Leaderboard.first = first;
		Leaderboard.second = second;
		Leaderboard.third = third;
		Leaderboard.players = players;
	}

	//Method updates the player's data in the given level
	public static void updateLevel(int level, int playerID, int score) {

		if(level == 1) {
			LevelScoreboard(first, playerID, score);
		} else if(level == 2) {
			LevelScoreboard(second, playerID, score);
		} else {
			LevelScoreboard(third, playerID, score);
		}
	}

	//Method checks for the ranking of the player on the basis of their scores and updates them
	public static void LevelScoreboard(int[][] level, int playerID, int score) {

		int loc = 0;

		if(level.equals(null)) {
			level[0][0] = score;
			level[0][1] = playerID;
		} else {
			if(level[0][0] > score) {
				if(level[1][0] > score) {
					if(level[2][0] > score) {
						if(level[3][0] > score) {
							if(level[4][0] > score) {
								if(level[5][0] > score) {
									if(level[6][0] > score) {
										if(level[7][0] > score) {
											if(level[8][0] > score) {
												if(level[9][0] > score) {
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
		}

		updateScoreboard(level, loc, playerID, score);
	}

	///Method updates the Score board of the given level.
	private static void updateScoreboard(int[][] level, int loc, int playerID, int score) {

		if(loc == 2) {
			level[2][0] = score;
			level[2][1] = playerID;
		} else {
			for(int i = 9; i > loc ; i --){
				level[i][0] = level[i - 1][0];
				level[i][1] = level[i - 1][1];
			}
			level[loc][0] = score;
			level[loc][1] = playerID;
		}
	}

	//Remove this method in final copy
	public static String printScoreBoard() {
		String line = "";
		line += "Rank" +"\t" + "Score" +"\t"+ "PlayerID\n" + "\n   Level 1\n\n";
		
		for(int i = 0; i < 10; i ++) {
			line += i+1 +"\t"+ first[i][0] +"\t"+ first[i][1];
			line += "\n";
		}
		line += "\n   Level 2\n\n";
		for(int i = 0; i < 10; i ++) {
				line += i+1 +"\t"+ second[i][0] +"\t"+ second[i][1];
			line += "\n";
		}
		line += "\n   Level 3\n\n";
		for(int i = 0; i < 10; i ++) {
				line += i+1 +"\t"+ third[i][0] +"\t"+ third[i][1];
				line += "\n";
		}
		line += "\n";
		
		return line;
	}
	
	//Method for storing the PlayerData in text file
	public static String getPlayerData() {
		
		String line = "";
		
		for(int i = 0; i < 10; i ++) {
			line += first[i][0] + " " + first[i][1] + "\n";
		}
		for(int i = 0; i < 10; i ++) {
			line += second[i][0] + " " + second[i][1] + "\n";
		}
		for(int i = 0; i < 10; i ++) {
			line += third[i][0] + " " + third[i][1] + "\n";
		}
		
		for(int i = 0; i < players.size(); i ++) {
			line += players.get(i).toString() + "\n";
		}
		
		return line;
	}
}
