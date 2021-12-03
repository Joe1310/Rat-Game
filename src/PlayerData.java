
public class PlayerData {

	private String playerName;
	private int playerScore;
	private int maxLevelUnlocked;
	private static int[][] first = new int[3][2];
	private static int[][] second = new int[3][2];
	private static int[][] third = new int[3][2];
	
	public static void main(String args[]) {
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
		
		System.out.println(printScoreBoard());
	}
	

	public void setPlayerName(String name) {
		playerName =  name;
	}

	public void setMaxLevelUnlocked(int level) {
		maxLevelUnlocked =  level;
	}

	public String getplayerName() {
		return playerName;
	}

	public int getMaxLevelUnlocked() {
		return maxLevelUnlocked;
	}

	public static void updateLevel(int level, int playerID, int score) {

		if(level == 1) {
			LevelScoreboard(first, playerID, score);
		} else if(level == 2) {
			LevelScoreboard(second, playerID, score);
		} else {
			LevelScoreboard(third, playerID, score);
		}
	}

	public static void LevelScoreboard(int[][] level, int playerID, int score) {

		int loc = 0;

		if(level.equals(null)) {
			level[0][0] = score;
			level[0][1] = playerID;
		} else {
			if(level[0][0] > score) {
				if(level[1][0] > score) {
					if(level[2][0] > score) {
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

	private static void updateScoreboard(int[][] level, int loc, int playerID, int score) {

		if(loc == 2) {
			level[2][0] = score;
			level[2][1] = playerID;
		} else {
			for(int i = 2; i > loc ; i --){
				level[i][0] = level[i - 1][0];
				level[i][1] = level[i - 1][1];
			}
			level[loc][0] = score;
			level[loc][1] = playerID;
		}
	}

	public static String printScoreBoard() {
		String line = "";
		line += "Rank" +"\t" + "Score" +"\t"+ "PlayerID\n" + "\n   Level 1\n\n";
		
		for(int i = 0; i < 3; i ++) {
			line += i+1 +"\t"+ first[i][0] +"\t"+ first[i][1];
			line += "\n";
		}
		line += "\n   Level 2\n\n";
		for(int i = 0; i < 3; i ++) {
				line += i+1 +"\t"+ second[i][0] +"\t"+ second[i][1];
			line += "\n";
		}
		line += "\n   Level 3\n\n";
		for(int i = 0; i < 3; i ++) {
				line += i+1 +"\t"+ third[i][0] +"\t"+ third[i][1];
				line += "\n";
		}
		line += "\n";
		
		return line;
	}
}
