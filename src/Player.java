
public class Player {

	private String playerName;
	private int playerScore;
	private int maxLevelUnlocked;
	
	//Constructor for Player object
	public Player(String name, int score, int level) {
		setPlayerName(name);
		setPlayerScore(score);
		updateMaxLevelUnlocked(level);
	}
	
	//Set method for player's name
	public void setPlayerName(String name) {
		playerName =  name;
	}
	
	//Set method for player's score
	public void setPlayerScore(int score) {
		playerScore =  score;
	}

	//Updates max level of the player if the previous level is smaller than the new one
	public void updateMaxLevelUnlocked(int level) {
		if(maxLevelUnlocked < level) {
			maxLevelUnlocked =  level;
		}
	
	}

	//Get method for player's name
	public String getPlayerName() {
		return playerName;
	}
	
	//Get method for player's score
	public int getPlayerScore() {
		return playerScore;
	}

	//Get method for player's max level unlocked
	public int getMaxLevelUnlocked() {
		return maxLevelUnlocked;
	}
	
	//toString method
	public String toString() {
		return getPlayerName() + " " + getPlayerScore() + " " + getMaxLevelUnlocked();
	}
}
