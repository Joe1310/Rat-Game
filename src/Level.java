import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Level {

	//Variables
	static int parTime;
	static int remainingTime = 0;
	static int maxRats;
	static int itemSpawnRate;
	static Tile[][] tileLayout;
	static int[] arrayOfLocation = null;

	static Map map;
	static Inventory inventory;
	static String items;
	static int playerScore = 0;
	static int levelScore = 0;

	//Constructor
	public Level(String filename) {

		File file = new File(filename);
		Scanner scan = null;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(filename.contains("level")) {
			readFreshLevel(scan);
		} else {
			readGame(scan);
		}

	}

	//Reads fresh level data
	public static void readFreshLevel(Scanner scan) {

		int bots = scan.nextInt();
		for(int k = 0; k < bots; k ++) {
			MakeBabyRat(scan);
		}
		readLevel(scan);

	}

	//Method reads Level data and creates MAP
	public static void readLevel(Scanner scan) {

		int row, column;
		String line;

		parTime = scan.nextInt();
		maxRats = scan.nextInt();
		itemSpawnRate = scan.nextInt();

		column = scan.nextInt();
		row = scan.nextInt();
		tileLayout = new Tile[column][row];
		for (int i = 0 ; i < row; i++){
			line = scan.next();
			for(int j = 0 ; j < column; j++){
				Tile tile = new Tile(line.charAt(j), j, i);
				tileLayout[j][i] = tile;
			}
		}

		map = new Map(tileLayout, row, column, maxRats);
	}

	//Method reads Game data using Scanner and creates objects
	public static void readGame(Scanner scan) {

		int aliveRats = scan.nextInt();

		for (int i = 0; i < aliveRats; i ++) {
			if(scan.hasNext("D")){
				MakeDeadRat(scan);
			} else if(scan.hasNext("B")) {
				MakeBabyRat(scan);
			} else {
				MakeAdultRat(scan);
			}
		}



		playerScore = scan.nextInt();

		readLevel(scan);

	}

	//Reads Adult Rat and adds it to Entity
	public static void MakeAdultRat(Scanner scan) {

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

		Entity rat = new AdultRat(sex, health, sterile, location, direction, isPregnant);
	}

	//Reads Rat's properties using scanner and makes an object for Baby Rat
	public static void MakeBabyRat(Scanner scan) {

		String sex;
		int x, y;
		int health;
		String direction;
		boolean sterile;

		if(scan.hasNext("BM") || scan.hasNext("BF")) {
			sex = scan.next();
			x = scan.nextInt();
			y = scan.nextInt();
			int[] location = {x,y};
			health = scan.nextInt();
			direction = scan.next();
			sterile = scan.nextBoolean();

			Entity rat = new BabyRat(sex, health, sterile, location, direction);
		} else {
			sex = scan.next();
			x = scan.nextInt();
			y = scan.nextInt();
			int[] location = {x,y};
			direction = scan.next();

			Entity rat = new BabyRat(sex, 100, false, location, direction);
			
			//REMOVE THIS
			int[] itemlocation = {5,3}; //remove
			int i = Entity.getEntities().size();
			if (i == 1) {
				Entity gas = new Bomb(itemlocation); //remove
			}
			//REMOVE THIS
		}
	}

	//Reads Rat's properties using scanner and makes an object for Dead Rat
	public static void MakeDeadRat(Scanner scan) {

		String sex;
		int x, y;
		int health;
		String direction;
		boolean sterile;

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
	public static void saveCurrent(String playerID) {

		String saveFile = playerID + "savedGame.txt";
		try {

			FileWriter saveGame = new FileWriter(saveFile);
			saveGame.write("Saved Game Data Here.");
			saveGame.close();

		} catch (IOException e) {
			System.out.println("\nAn error occurred while saving the current game.");
			e.printStackTrace();
		}
	}

	//Get methods
	static Map getMap() {
		return map;
	}

	static int getItemSpawnRate() {
		return itemSpawnRate;
	}

	static int getLevelScore() {
		return levelScore;
	}

	static int getPlayerScore() {
		return playerScore;
	}
}