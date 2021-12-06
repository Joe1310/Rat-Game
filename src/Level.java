import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class to model a Level object
 *
 * @author Patel, Elliot, Nick, Jay, Joe
 * @version 1.0
 */
public class Level {

	public static int maxRats;
	private static int parTime;
	private static final int[] ITEM_SPAWN_RATES = new int[8];
	private static final int[] INVENTORY_QUANTITIES = new int[8];
	private static final int[] INVENTORY_TIMERS = new int[8];
	private static Tile[][] tileLayout;
	private static int levelNumber = 1;
	private static Map map;
	private static int levelScore = 0;

	/**
	 * Constructor to create a Level object.
	 *
	 * @param filename Name of file where level data is stored.
	 */
	public Level(String filename) {
		levelScore = 0;
		File file = new File(filename);
		Scanner scan = null;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (filename.contains("SavedGame")) {
			assert scan != null;
			readGame(scan);
		} else {
			if(filename.contains("1")) {
				levelNumber = 1;
			} else if(filename.contains("2")) {
				levelNumber = 2;
			} else if(filename.contains("3")) {
				levelNumber = 3;
			} else if (filename.contains("4")){
				levelNumber = 4;
			}
			assert scan != null;
			readFreshLevel(scan);
		}
	}

	/**
	 * Method to save the current game in a text file using the currentPlayers name to differentiate the game saves.
	 */
	public static void saveCurrent() {

		String saveFile = "src/saves/" + Main.getCurrentPlayer().getPlayerName() + "SavedGame.txt";
		try {
			FileWriter saveGame = new FileWriter(saveFile);
			saveGame.write(Entity.getEntities().size() +"\n");
			for (Entity ent : Entity.getEntities()) {
				saveGame.write(ent + "\n");
			}
			saveGame.write(levelNumber + "\n");
			saveGame.write(levelScore + "\n");
			saveGame.write(Inventory.returnQuantity() + "\n");
			saveGame.write(Inventory.returnTimers() + "\n");
			saveGame.write(parTime - map.count + "\n");
			saveGame.write(maxRats + "\n");
			saveGame.write(Inventory.returnSpawnRates() + "\n");
			int row = tileLayout[0].length;
			int column = tileLayout.length;
			saveGame.write(column + " ");
			saveGame.write(row + "\n");
			for (int i = 0 ; i < row; i++){
				for (Tile[] tiles : tileLayout) {
					saveGame.write(tiles[i].getTileType());
				}
				saveGame.write("\n");
			}
			saveGame.close();
		} catch (IOException e) {
			System.out.println("\nAn error occurred while saving the current game.");
		}
	}

	/**
	 * Method to get the Map entity.
	 *
	 * @return Returns Map entity.
	 */
	public static Map getMap() {
		return map;
	}

	/**
	 * Method to get the spawn rates of items.
	 *
	 * @return Returns the spawn rates of items.
	 */
	public int[] getItemSpawnRates() {
		return ITEM_SPAWN_RATES;
	}

	/**
	 * Method to get the amounts of each item in the inventory.
	 *
	 * @return Returns item counts.
	 */
	public int[] getInventoryQuantities() {
		return INVENTORY_QUANTITIES;
	}

	/**
	 * Method to get the amount of times for each item to spawn in the inventory.
	 *
	 * @return Return timers before the items spawn
	 */
	public int[] getInventoryTimers() {
		return INVENTORY_TIMERS;
	}

	/**
	 * Method to get the Level number.
	 *
	 * @return Returns level number.
	 */
	public static int getLevelNumber() {
		return levelNumber;
	}

	/**
	 * Method to add to the players score for each rat kill.
	 */
	public static void addScore() {
		levelScore = levelScore + 10;
	}

	/**
	 * Method to get the Score the player got in the level.
	 *
	 * @return Returns the score on the level.
	 */
	public static int getLevelScore() {
		score();
		return levelScore;
	}

	/**
	 * Method to reset the score on the level.
	 */
	public static void resetLevelScore() {
		levelScore = 0;
	}

	/**
	 * Method to Read a fresh level (Not already played on) from a file.
	 *
	 * @param scan Scanner accessing the file of the level.
	 */
	private void readFreshLevel(Scanner scan) {

		int bots = scan.nextInt();
		for(int k = 0; k < bots; k ++) {
			MakeBabyRat(scan);
		}
		readLevel(scan);

	}

	/**
	 * Method to read the level data from a saved game file.
	 *
	 * @param scan Scanner accessing the file of the level.
	 */
	private void readLevel(Scanner scan) {

		int row, column;
		String line;

		parTime = scan.nextInt();
		maxRats = scan.nextInt();
		for(int i = 0; i < 8; i++) {
			ITEM_SPAWN_RATES[i] = scan.nextInt();
		}
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

	/**
	 * Method to read all the entities from a file and create them.
	 *
	 * @param scan Scanner accessing the file of the level.
	 */
	private void readGame(Scanner scan) {

		int entities = scan.nextInt();

		//being changed
		for (int i = 0; i < entities; i ++) {
			if(scan.hasNext("D")){
				MakeDeadRat(scan);
			} else if(scan.hasNext("BF") || scan.hasNext("BM")) {
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
			} else if(scan.hasNext("Steriliser")) {
				MakeSteriliser(scan);
			}
		}
		levelNumber = scan.nextInt();
		levelScore = scan.nextInt();
		for (int i = 0; i < 8; i++) {
			INVENTORY_QUANTITIES[i] = scan.nextInt();
		}
		for (int i = 0; i < 8; i++) {
			INVENTORY_TIMERS[i] = scan.nextInt();
		}
		readLevel(scan);
	}

	/**
	 * Method to create an AdultRat entity.
	 *
	 * @param scan Scanner accessing the file of the level.
	 */
	private void MakeAdultRat(Scanner scan) {
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
		int babyAmount = scan.nextInt();
		
		new AdultRat(sex, location,  direction, health, sterile,
				isPregnant, isMating, timePregnant, timeMating,
				matingCooldown, babyAmount);
	}

	/**
	 * Method to create a BabyRat entity.
	 *
	 * @param scan Scanner accessing the file of the level.
	 */
	//Reads Rat's properties using scanner and makes an object for Baby Rat
	private void MakeBabyRat(Scanner scan) {
		String sex = scan.next();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int[] location = {x,y};
		String direction = scan.next();
		int health = scan.nextInt();
		boolean sterile = scan.nextBoolean();

		new BabyRat(sex, location, direction, health, sterile);
	}

	/**
	 * Method to create a DeathRat entity.
	 *
	 * @param scan Scanner accessing the file of the level.
	 */
	private void MakeDeadRat(Scanner scan) {

		scan.next();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int[] location = {x,y};
		String direction = scan.next();
		int killCount = scan.nextInt();
		
		new DeathRat(location, direction, killCount);
	}

	/**
	 * Method to create a DeathRatSpawner entity.
	 *
	 * @param scan Scanner accessing the file of the level.
	 */
	private void MakeDRS(Scanner scan) {

		scan.next();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int[] location = {x,y};
		int timer = scan.nextInt();
		
		new DeathRatSpawner(location, timer);
	}

	/**
	 * Method to create a Bomb entity.
	 *
	 * @param scan Scanner accessing the file of the level.
	 */
	private void MakeBomb(Scanner scan) {

		scan.next();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int[] location = {x,y};
		int timer = scan.nextInt();
		
		new Bomb(location, timer);
	}

	/**
	 * Method to create an Explosion entity.
	 *
	 * @param scan Scanner accessing the file of the level.
	 */
	private void MakeExplosion(Scanner scan) {

		scan.next();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int[] location = {x,y};
		
		new Explosion(location);
	}

	/**
	 * Method to create a NoEntrySign entity.
	 *
	 * @param scan Scanner accessing the file of the level.
	 */
	private void MakeNES(Scanner scan) {

		scan.next();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int[] location = {x,y};
		int health = scan.nextInt();
		
		new NoEntrySign(location, health);
	}

	/**
	 * Method to create a Gas entity.
	 *
	 * @param scan Scanner accessing the file of the level.
	 */
	private void MakeGas(Scanner scan) {

		scan.next();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int[] location = {x,y};
		int spreadLimit = scan.nextInt();
		String direction = scan.next();
		boolean hasSpread = scan.nextBoolean();
		
		new Gas(location, spreadLimit, direction, hasSpread);
	}

	/**
	 * Method to create a Poison entity.
	 *
	 * @param scan Scanner accessing the file of the level.
	 */
	private void MakePoison(Scanner scan) {

		scan.next();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int[] location = {x,y};
		
		new Poison(location);
	}

	/**
	 * Method to create a MaleSexChange entity.
	 *
	 * @param scan Scanner accessing the file of the level.
	 */
	private void MakeMSC(Scanner scan) {

		scan.next();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int[] location = {x,y};
		
		new MaleSexChange(location);
	}
	/**
	 * Method to create a FemaleSexChange entity.
	 *
	 * @param scan Scanner accessing the file of the level.
	 */
	private void MakeFSC(Scanner scan) {

		scan.next();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int[] location = {x,y};
		
		new FemaleSexChange(location);
	}

	/**
	 * Method to create a Steriliser entity.
	 *
	 * @param scan Scanner accessing the file of the level.
	 */
	private void MakeSteriliser(Scanner scan) {
		scan.next();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int[] location = {x,y};
		int timer = scan.nextInt();
		
		new Sterilisation(location, timer);
	}

	/**
	 * Method to calculate the final score (including bonus score from time remaining).
	 */
	private static void score() {
		int bonus = Level.parTime - Map.count;
		if(bonus < 0) {
			bonus = 0;
		}
		levelScore = levelScore + bonus;
	}
}