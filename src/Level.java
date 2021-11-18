import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//File extends Map class
public class Level {

	//Variables 
	
	int levelNumber;
	//Map levelMap;
	int[] inventory;
	int levelScore;
	
    //Main method to test the Level file and it's functions.
	public static void main(String[] args) {
		
		Level("levelInfo.txt");
		
	}
    
	public static void Level(String fn) {
		
		File filename = new File(fn);
		
		//Scanner
		Scanner scan = null;

		try {

			scan = new Scanner(filename);

		} catch (FileNotFoundException e) {
			System.out.println("Could not find "+filename);
			e.printStackTrace();
		}
		
		System.out.println(readLevelData(scan));
		
	}
	
	public static String readLevelData(Scanner scan) {
		
		//Variables
		int column;
		int row;
		String[] grid;
		String[] rats;
		int rat;
		String items;
		int maxRats;
		String levelData;
		int parTime; //seconds
		int itemSpawnRate; //seconds;
		
		column = scan.nextInt();
		row = scan.nextInt();
		grid = new String[row];
		
		for (int i = 0; i < row; i ++) {
			grid[i] = scan.nextLine();
		}
		
		rat = scan.nextInt();
		rats = new String[rat];
		for(int i = 0; i < rat; i ++) {
			rats[i] = scan.nextLine();
		}
		
		items = scan.nextLine();
		
		maxRats = scan.nextInt();
		
		parTime = scan.nextInt();
		
		itemSpawnRate = scan.nextInt();
		
		levelData = column + " " + row + "\n"
				+ grid + "\n" + rats + "\n" + items + "\n"
				+ maxRats + "\n";
		
		return levelData;
	}
	
	
	public static void constructMap() {
		
		//This method uses JavaFx to construct the Map.
	}
	
	public void saveCurrent() {
		//We might need playerID for storing different players ongoing game
		try {
			FileWriter saveGame = new FileWriter("savedGame.txt");
			saveGame.write("Level Layout");
			saveGame.write("List of rats and their properties");
			
			for(int i = 0; i < inventory.length; i++) {
				saveGame.write(inventory[i] + " ");
			}
			
			saveGame.write("parTime");
			saveGame.write("itemSpawnRate");
			saveGame.write("PlayerID and Score");
			saveGame.close();
			System.out.println("Game saved successfully.");
			
			
		} catch (IOException e) {
			System.out.println("An error occurred while saving the current game.");
			e.printStackTrace();
		}
		
		
	}

}
