import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PlayerData {

	private String playerName;
	private int maxLevel;
	
	//Constructor for Player object
	public PlayerData(File playerFile) {
		if (playerFile.length() != 0) {
			Scanner prof = null;
			try {
				prof = new Scanner(playerFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			readPlayerData(prof);
		} else {
			initialiseData(playerFile);
		}
	}
	
	private void readPlayerData(Scanner scan) {
		playerName = scan.next();
		maxLevel = scan.nextInt();
		scan.close();
	}
	
	private void initialiseData(File playerFile) {
		String playerName = playerFile.getName();
		this.playerName = playerName.substring(0, playerName.length()-4);
		this.maxLevel = 1;
		updatePlayerFile();
	}
	
	public void updatePlayerFile() {
		File player = new File("src/profiles/" + getPlayerName() + ".txt");
		try {
			FileWriter playerFile = new FileWriter(player);
			playerFile.write(playerName + "\n" + maxLevel);
			playerFile.close();

		} catch (IOException e) {
			System.out.println("\nAn error occurred while saving the current game.");
			e.printStackTrace();
		}
	}
	
	//Updates max level of the player if the previous level is smaller than the new one
	public void updateMaxLevel(int level) {
		switch (level) {
			case 1:
				if (maxLevel < 2) {
					maxLevel = 2;
				}
				break;
			case 2:
				if (maxLevel < 3) {
					maxLevel = 3;
				}
				break;
			case 3:
				if (maxLevel < 4) {
					maxLevel = 4;
				}
				break;
		}
	
	}

	
	//Get method for player's name
	public String getPlayerName() {
		return playerName;
	}

	//Get method for player's max level unlocked
	public int getMaxLevel() {
		return maxLevel;
	}
}
