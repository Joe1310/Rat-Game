import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Level {

	//Variables
	 static int parTime;
	 int remainingTime = 0;
	 static int maxRats;
	 static int itemSpawnRate;
	 static Tile[][] tileLayout;

	 static Map map;
	 Inventory inventory;
	 String items;
	 int playerScore = 0;
	 static int levelScore = 0;

	//Constructor
	public Level(String filename) {
		levelScore = 0;
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
	public  void readFreshLevel(Scanner scan) {

		int bots = scan.nextInt();
		for(int k = 0; k < bots; k ++) {
			MakeBabyRat(scan);
		}
		readLevel(scan);

	}

	//Method reads Level data and creates MAP
	public  void readLevel(Scanner scan) {

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
	public  void readGame(Scanner scan) {

		int entities = scan.nextInt();

		//being changed
		for (int i = 0; i < entities; i ++) {
			if(scan.hasNext("D")){
				MakeDeadRat(scan);
			} else if(scan.hasNext("B")) {
				MakeBabyRat(scan);
			} else if(scan.hasNext("M")) {
				MakeAdultRat(scan);
			} else if(scan.hasNext("F")) {
				MakeAdultRat(scan);
			} else if(scan.hasNext("DRS")) {
				MakeDRS(scan);
			} else if(scan.hasNext("Bomb")) {
				MakeBomb(scan);
			} else if(scan.hasNext("Explosion")) {
				MakeExplosion(scan);
			} else if(scan.hasNext("NES")) {
				MakeNES(scan);
			} else if(scan.hasNext("Gas")) {
				MakeGas(scan);
			} else if(scan.hasNext("Poison")) {
				MakePoison(scan);
			} else if(scan.hasNext("MSC")) {
				MakeMSC(scan);
			} else if(scan.hasNext("FSC")) {
				MakeFSC(scan);
			} else if(scan.hasNext("Sterliser")) {
				MakeSteriliser(scan);
			}
		}



		playerScore = scan.nextInt();
		readLevel(scan);

	}

	//Reads Adult Rat and adds it to Entity
	public  void MakeAdultRat(Scanner scan) {

		String sex = scan.next();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int[] location = {x,y};
		String direction = scan.next();
		int health = scan.nextInt();
		boolean sterile = scan.nextBoolean();
		boolean isPregnant = scan.nextBoolean();
		boolean isMating = scan.nextBoolean();
		int timePregnant = scan.nextInt();
		int timeMating = scan.nextInt();
		int matingCooldown = scan.nextInt();
		int babyAmmount = scan.nextInt();
		
		Entity rat = new AdultRat(sex, location,  direction, health, sterile, 
				isPregnant, isMating, timePregnant, timeMating, matingCooldown, babyAmmount);
	}

	//Reads Rat's properties using scanner and makes an object for Baby Rat
	public  void MakeBabyRat(Scanner scan) {

		if(scan.hasNext("BM") || scan.hasNext("BF")) {
			String sex = scan.next();
			int x = scan.nextInt();
			int y = scan.nextInt();
			int[] location = {x,y};
			int health = scan.nextInt();
			String direction = scan.next();
			boolean sterile = scan.nextBoolean();

			Entity rat = new BabyRat(sex, location, direction, health, sterile);
		} else {
			String sex = scan.next();
			int x = scan.nextInt();
			int y = scan.nextInt();
			int[] location = {x,y};
			String direction = scan.next();

			Entity rat = new BabyRat(sex, location, direction, 100, false);
		}
	}

	//Reads Rat's properties using scanner and makes an object for Dead Rat
	public  void MakeDeadRat(Scanner scan) {

		String type = scan.next();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int[] location = {x,y};
		int health = scan.nextInt();
		String direction = scan.next();
		

		Entity rat = new DeathRat(location, direction);
	}
	
	public  void MakeDRS(Scanner scan) {

		String type = scan.next();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int[] location = {x,y};
		int timer = scan.nextInt();
		
		Entity drs = new DeathRatSpawner(location, timer);
	}
	
	public  void MakeBomb(Scanner scan) {

		String type = scan.next();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int[] location = {x,y};
		int timer = scan.nextInt();
		
		Entity bomb = new Bomb(location, timer);
	}
	
	public  void MakeExplosion(Scanner scan) {

		String type = scan.next();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int[] location = {x,y};
		
		Entity drs = new Explosion(location);
	}
	
	public  void MakeNES(Scanner scan) {

		String type = scan.next();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int[] location = {x,y};
		int health = scan.nextInt();
		
		Entity drs = new DeathRatSpawner(location, health);
	}
	
	public  void MakeGas(Scanner scan) {

		String type = scan.next();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int[] location = {x,y};
		int spreadLimit = scan.nextInt();
		boolean hasSpread = scan.nextBoolean();
		String direction = scan.next();
		
		Entity gas = new Gas(location, spreadLimit, direction, hasSpread);
	}
	
	public  void MakePoison(Scanner scan) {

		String type = scan.next();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int[] location = {x,y};
		
		Entity poison = new Poison(location);
	}
	
	public  void MakeMSC(Scanner scan) {

		String type = scan.next();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int[] location = {x,y};
		
		Entity msc = new MaleSexChange(location);
	}
	
	public  void MakeFSC(Scanner scan) {

		String type = scan.next();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int[] location = {x,y};
		
		Entity fsc = new FemaleSexChange(location);
	}
	
	public  void MakeSteriliser(Scanner scan) {

		String type = scan.next();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int[] location = {x,y};
		int timer = scan.nextInt();
		
		Entity steriliser = new Sterilisation(location, timer);
	}


	//Method saves the ongoing Game using playerID
	public static  void saveCurrent(int playerID) {

		String saveFile = playerID + "savedGame.txt";
		try {
			FileWriter saveGame = new FileWriter(saveFile);
			saveGame.write(Entity.getEntities().size() +"\n");
			for (Entity ent : Entity.getEntities()) {
				saveGame.write(ent + "\n");
			}
			saveGame.write(parTime + "\n");
			saveGame.write(maxRats + "\n");
			saveGame.write(itemSpawnRate + "\n");
			int column = tileLayout[0].length;
			int row = tileLayout[1].length;
			saveGame.write(column + " ");
			saveGame.write(row + "\n");
			for (int i = 0 ; i < row; i++){
				for(int j = 0 ; j < column; j++){
					saveGame.write(tileLayout[i][j].getTileType());
				}
				saveGame.write("\n");
			}
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

	 int getItemSpawnRate() {
		return itemSpawnRate;
	}

	public static void addScore() {
		levelScore = levelScore + 10;
	}

	public static void score() {
		int bonus = Level.parTime - Map.count;
		if(bonus < 0) {
			bonus = 0;
		}

		levelScore = levelScore + bonus;

	}

	 static int getLevelScore() {
		score();
		return levelScore;
	}

	 int getPlayerScore() {
		return playerScore;
	}
}