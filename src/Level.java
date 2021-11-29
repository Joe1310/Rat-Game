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
	
	Inventory items;
	String inventory;
	int playerScore;
	int levelScore = 0;
	int levelNumber;
	
	
	//Constructor
	public Level(int parTime, int maxRats, Tile[][] tileLayout, int levelNumber) {
		this.tileLayout = tileLayout;
		this.parTime = parTime;
		this.maxRats = maxRats;
		this.levelNumber = levelNumber;
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
	public Level readLevel(Scanner scan) {
		
		int row, column;
		Tile[][] tileLayout;
		
		this.parTime = scan.nextInt();
		this.maxRats = scan.nextInt();
		row = scan.nextInt();
		column = scan.nextInt();
		
		tileLayout = new Tile[row][column];
		for (int i = 0; i < row; i ++) {
			String str = scan.next();
			for (int j = 0; j < column; j ++) {
				Tile tile = new Tile(str.charAt(j), i, j);
				tileLayout[i][j] = tile;
			}
		}
		
		//Error: tileLayout is Tile type but while reading text it is char type
		Level level = new Level((parTime - remainingTime), maxRats, tileLayout, levelNumber);
		return level;
	}
	
	//Method reads Game data using Scanner and creates objects
	public void readGame(Scanner scan) {
		
		int aliveRats;
		
		this.remainingTime = scan.nextInt();
		aliveRats = scan.nextInt();
		
		for (int i = 0; i < aliveRats; i ++) {
			if(scan.hasNext("M") || scan.hasNext("F")){
				MakeAdultRat(scan);
			} else if(scan.hasNext("B")) {
				MakeBabyRat(scan);
			} else {
				MakeDeadRat(scan);
			}
		}
		
		this.inventory = scan.next();
		this.items = new Inventory(inventory.charAt(0), 
				inventory.charAt(1), inventory.charAt(2), 
				inventory.charAt(3), inventory.charAt(4), 
				inventory.charAt(5), inventory.charAt(6));
		
		this.playerScore = scan.nextInt();
		this.levelNumber = scan.nextInt();
		
		//Makes level object
		readFile(this.levelNumber + "level.txt");
		
	}
	
	//Reads Rat's properties using scanner and makes an object for Adult Rat
	public void MakeAdultRat(Scanner scan) {
		
		String sex;
		int x, y;
		int health;
		String direction;
		boolean sterile, isPregnant;
		
		sex = scan.next();
		x = scan.nextInt();
		y = scan.nextInt();
		int[] location = {x,y};
		health = scan.nextInt();
		direction = scan.next();
		sterile = scan.nextBoolean();
		isPregnant = scan.nextBoolean();
		
		Entity rat = new AdultRat(health, sterile, location, direction, sex, isPregnant);
	}
	
	//Reads Rat's properties using scanner and makes an object for Baby Rat
	public void MakeBabyRat(Scanner scan) {
		
		String sex;
		int x, y;
		int health;
		String direction;
		boolean sterile, isPregnant;
		
		sex = scan.next();
		x = scan.nextInt();
		y = scan.nextInt();
		int[] location = {x,y};
		health = scan.nextInt();
		direction = scan.next();
		sterile = scan.nextBoolean();
		
		Entity rat = new BabyRat(health, sterile, location, direction);
	}
	
	//Reads Rat's properties using scanner and makes an object for Dead Rat
	public void MakeDeadRat(Scanner scan) {
		
		String sex;
		int x, y;
		int health;
		String direction;
		boolean sterile, isPregnant;
		
		sex = scan.next();
		x = scan.nextInt();
		y = scan.nextInt();
		int[] location = {x,y};
		health = scan.nextInt();
		direction = scan.next();
		sterile = scan.nextBoolean();
		
		Entity rat = new DeathRat(health, sterile, location, direction);
	}

	//Method saves the ongoing Game using playerID
	public void saveCurrent(String playerID) {
		
		String saveFile = playerID + "savedGame.txt";
		
		try {
			
			FileWriter saveGame = new FileWriter(saveFile);
			saveGame.write(remainingTime + "\n");
			saveGame.write(Rat.getRats().size() + "\n");
			for(int i = 0; i < Rat.getRats().size(); i ++) {
				saveGame.write(Rat.getRats().toString() + "\n");
			}
			saveGame.write(this.inventory + "\n");
			saveGame.write(playerScore + "\n");
			saveGame.write(levelNumber + "\n");
			saveGame.close();
			
		} catch (IOException e) {
			
			System.out.println("\nAn error occurred while saving the current game.");
			e.printStackTrace();
			
		}	
	}

	//This method creates map object and returns the object
	public Map constructMap() {
		return new Map(tileLayout, itemSpawnRate, entityLocations, maxRats);
	}
	
	//Get method for fetching items
	public Inventory getInventory() {
		return items;
	}
	
	//Get method for fetching level score
	public int getLevelScore() {
		return levelScore;
	}
}
