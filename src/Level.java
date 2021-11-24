import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Level {
	
	//Variables
	int parTime;
	int remainingTime = 0;
	int maxRats;
	Tile[][] tileLayout;

	int itemSpawnRate;
	int[] entityLocations;
	
	int[] inventory;
	int playerScore;
	int levelScore = 0;
	
	
	//Constructor
	public Level(int parTime, int maxRats, Tile[][] tileLayout) {
		this.tileLayout = tileLayout;
		this.parTime = parTime;
		this.maxRats = maxRats;
	}

	//Method reads file and calls appropriate function while passing scanner to it
	public void readFile(String file) {
		
		File fileName = new File(file);
		Scanner scan = null;
		try {
			scan = new Scanner(fileName);
			
		} catch (FileNotFoundException e) {
			System.out.println("Could not find "+fileName);
			e.printStackTrace();
		}
		
		//Condition for passing scanner to the appropriate method
		if(file.contains("level")) {
			readLevel(scan);
		} else {
			readGame(scan);
		}
		
	}
	
	//Method reads Level data and creates level objects
	public void readLevel(Scanner scan) {
		
		int maxRats;
		int row, column;
		char[][] tileLayout;
		
		parTime = scan.nextInt();
		maxRats = scan.nextInt();
		row = scan.nextInt();
		column = scan.nextInt();
		
		tileLayout = new char[row][column];
		for (int i = 0; i < row; i ++) {
			String str = scan.next();
			for (int j = 0; j < column; j ++) {
				tileLayout[i][j] = str.charAt(j);
			}
		}
		
		//Error: tileLayout is Tile type but while reading text it is char type
		Level level = new Level((parTime - remainingTime), maxRats, tileLayout);
	}
	
	//Method reads Game data using Scanner and creates objects
	public void readGame(Scanner scan) {
		
		int aliveRats;
		String sex;
		int x, y;
		int health;
		String direction;
		boolean sterile, isPregnant;
		String inventory;
		int levelNumber;
		
		this.remainingTime = scan.nextInt();
		
		aliveRats = scan.nextInt();
		
		for (int i = 0; i < aliveRats; i ++) {
			
			sex = scan.next();
			x = scan.nextInt();
			y = scan.nextInt();
			int[] location = {x,y};
			health = scan.nextInt();
			direction = scan.next();
			sterile = scan.nextBoolean();
			isPregnant = scan.nextBoolean();
			
			Entity rat = new AdultRat(health, 5, sterile, location, direction, 1, sex, isPregnant);
		}
		
		inventory = scan.next();
		Inventory item = new Inventory(inventory.charAt(0), 
				inventory.charAt(1), inventory.charAt(2), 
				inventory.charAt(3), inventory.charAt(4), 
				inventory.charAt(5), inventory.charAt(6));
		
		for(int i = 0; i < 7; i++) {
			this.inventory[i] = inventory.charAt(i);
		}
		
		this.playerScore = scan.nextInt();
		levelNumber = scan.nextInt();
		
		//Makes level object
		readFile(levelNumber + "level.txt");
		
	}

	//Method saves the ongoing Game using playerID
	public void saveCurrent(String playerID) {
		
		String saveFile = playerID + "savedGame.txt";
		
		try {
			
			FileWriter saveGame = new FileWriter(saveFile);
			saveGame.write(remainingTime + "\n");
			saveGame.write("Alive Rats number\n <List> \n");
			saveGame.write("<Inventory>\n");
			saveGame.write(playerScore);
			saveGame.write("\n<levelNumber>");
			saveGame.close();
			
		} catch (IOException e) {
			
			System.out.println("\nAn error occurred while saving the current game.");
			e.printStackTrace();
			
		}	
	}

	//This method creates map object and returns the object
	public Map constructMap() {

		Map levelMap = new Map(tileLayout, itemSpawnRate, entityLocations, maxRats);

		return levelMap;
	}

}
