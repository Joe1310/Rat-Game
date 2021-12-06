import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * <p> 1. File name: Main.java</p>
 * <p> 3. Creation date: 08.11.2021</p>
 * <p> 4. Last modification date: 05.12.2021</p>
 * <p> 6. Copyright notice: group 02 - CS230 - Swansea University - 2021/22</p>
 * <p> 7. Purpose of the program: Handling multiple behavior of classes</p>
 * <p> 8. Version history: 1.0 - pure code; 2.0 - comment added</p>
 * @author Joe, Oliver, Elliot, Nick, Jay, Shivraj, Patel
 * @version 2.0
 */
public class Main extends Application {

	public boolean saved = false;
	public Stage levelStage = new Stage();

	private static final int STAGE1_CANVAS_WIDTH = 650;
	private static final int STAGE1_CANVAS_HEIGHT = 350;
	private static final int STAGE2_CANVAS_WIDTH = 1100;
	private static final int STAGE2_CANVAS_HEIGHT = 800;
	private static final int STAGE3_CANVAS_WIDTH = 950;
	private static final int STAGE3_CANVAS_HEIGHT = 500;
	private static final int STAGE4_CANVAS_WIDTH = 1550;
	private static final int STAGE4_CANVAS_HEIGHT = 700;

	private static PlayerData currentPlayer;

	private Canvas canvas;

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Method to get the current players PlayerData object.
	 *
	 * @return Returns the current players PlayerData object.
	 */
	public static PlayerData getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Setup the new application and display the main menu.
	 *
	 * @param primaryStage The stage that is to be used for the application.
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {

		Stage menuWindow = new Stage();
		menuWindow.setTitle("Rat Game");
		new Leaderboard();

		Message message = new Message();
		String messageOfTheDay = message.MessageOfTheDay();
		TextArea MOTD = new TextArea(messageOfTheDay);
		MOTD.setMaxWidth(500);
		MOTD.setWrapText(true);
		MOTD.setEditable(false);
		MOTD.setStyle("-fx-font-size: 1.5em; -fx-border-color: #ffffff; -fx-focus-color: #FFFFFF; -fx-faint-focus-color: #FFFFFF;");
		MOTD.setPrefHeight(messageOfTheDay.length() + messageOfTheDay.length()/8);

		Button startButton = new Button("START");
		Button highScoresButton = new Button("HIGH SCORES");
		Button howToPlayButton = new Button("HOW TO PLAY");
		Button quitButton = new Button("QUIT");

		Image image = new Image("images/Logo.png");
		ImageView logoView = new ImageView();
		logoView.setImage(image);
		logoView.setFitWidth(200);
		logoView.setPreserveRatio(true);

		Image maRat = new Image("images/MaRAT.png"); //Praise be
		ImageView maRatView = new ImageView();
		maRatView.setImage(maRat);
		maRatView.setFitWidth(200);
		maRatView.setPreserveRatio(true);

		startButton.setOnAction(event -> {
			menuWindow.close();
			profileSelect();
		});
		highScoresButton.setOnAction(event -> {
			menuWindow.close();
			highScores();
		});
		
		howToPlayButton.setOnAction(event -> {
			menuWindow.close();
			howToPlay();
		});
		quitButton.setOnAction(event -> menuWindow.close());
		
		GridPane container = new GridPane();
		GridPane.setConstraints(MOTD, 0, 1, 3, 1);
		GridPane.setConstraints(maRatView, 1, 2, 2, 5);
		GridPane.setConstraints(startButton, 0, 2);
		GridPane.setConstraints(highScoresButton, 0, 3);
		GridPane.setConstraints(howToPlayButton, 0, 4);
		GridPane.setConstraints(quitButton, 0, 5);
		container.setPadding(new Insets(40));
		container.getChildren().addAll(MOTD, maRatView, startButton, highScoresButton,
				howToPlayButton, quitButton);
		BorderPane border = new BorderPane();
		border.setMaxWidth(400);
		border.setPrefWidth(400);
		border.setTop(logoView);
		BorderPane.setAlignment(logoView, Pos.TOP_CENTER);
		border.setCenter(container);
		border.setStyle("-fx-background-color: #FFFFFF;");

		menuWindow.setScene(new Scene(border));
		//Launch
		menuWindow.show();
	}

	/**
	 * Method to display a new menu telling the players how to play the game.
	 */
	private void howToPlay(){
		Stage howToPlayWindow = new Stage();
		howToPlayWindow.setResizable(false);
		howToPlayWindow.setTitle("HOW TO PLAY");
		TextArea text = new TextArea("Welcome exterminator. The city needs your help; the parks are being overrun" +
				" with rats, and we need you to eliminate them before it's too late.\n Aim:\n" +
				"To eliminate all the Rats within the time frame, use the items you supplied. If rats of an opposite" +
				" gender meet, they will procreate and reproduce.\n So, eliminate them quickly.\n Inventory:\n" +
				"We have equipped you with an arsenal at your disposal, found in your inventory, that can be used by " +
				"clicking on an item and dragging it onto the\n map where you would like. Once you have released " +
				"the item, the item will activate.\n Items at your disposal:\n Gas:\n" +
				"Gas, when placed, will immediately start spreading across the map, and any rat that passes through " +
				"it will be damaged. After the gas has spread,\n it will begin to dissipate.\n Death rat:\n" +
				"Is a robotic rat that, after 3 seconds of warming up, will move through the paths and kill any rat " +
				"it comes across; however, after the death rat has\n killed five rats, it will self-destruct.\n" +
				"Sterilisation:\n Is a rare radioactive isotope that will sterilise any rat within its radius " +
				"so it can no longer procreate when placed.\n Female sex change:\n" +
				"Will change the gender of a male rat to a female rat. These can be used to help stop the rats " +
				"from reproducing.\n(If a female hit it, nothing will change).\n Male sex change:\n" +
				"Will change the gender of a female rat to a male rat. These can be used to help stop the " +
				"rats from reproducing.\n(If a male hit it, nothing will change).\n No entry signs:\n" +
				"It can be used to black the paths of rats and trap them but be careful after 5 hits, the " +
				"signs break.\n Bomb:\n Bombs can be placed onto a path. After 5 seconds, they will " +
				"detonate, sending an explosion vertically and horizontally, killing any rat it hits.");
		text.setEditable(false);
		text.setStyle("-fx-font-size: 1.5em;");
		text.setPrefWidth(1200);
		text.setPrefHeight(600);
		Button menuButton = new Button("Return");

		menuButton.setOnAction(event -> {
			howToPlayWindow.close();
			try {
				start(howToPlayWindow);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		VBox container = new VBox(text, menuButton);

		//Style container
		container.setSpacing(15);
		container.setPadding(new Insets(25));
		//Set view in window
		howToPlayWindow.setScene(new Scene(container));
		//Launch
		howToPlayWindow.show();
	}

	/**
	 * Method to display a gui where users can choose which levels high scores they want to view.
	 */
	private void highScores() {
		Stage highScoresWindow = new Stage();
		highScoresWindow.setTitle("RAT GAME: HIGH SCORES");
		VBox container = new VBox();
		//Create view in Java
		Label title = new Label("Select a level:");
		
		Button level1ScoresButton = new Button("LEVEL 1");
		level1ScoresButton.setOnAction(event -> {
			highScoresWindow.close();
			levelScores(1);
		});
		
		Button level2ScoresButton = new Button("LEVEL 2");
		level2ScoresButton.setOnAction(event -> {
			highScoresWindow.close();
			levelScores(2);
		});
		
		Button level3ScoresButton = new Button("LEVEL 3");
		level3ScoresButton.setOnAction(event -> {
			highScoresWindow.close();
			levelScores(3);
		});
		

		Button level4ScoresButton = new Button("LEVEL 4");
		level4ScoresButton.setOnAction(event -> {
			highScoresWindow.close();
			levelScores(4);
		});

		Button backButton = new Button("BACK");
		backButton.setOnAction(event -> {
			highScoresWindow.close();
			try {
				start(highScoresWindow);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		container.setSpacing(15);
		container.setPadding(new Insets(25));
		container.getChildren().addAll(title, level1ScoresButton, level2ScoresButton, level3ScoresButton, level4ScoresButton, backButton);
		highScoresWindow.setScene(new Scene(container));
		highScoresWindow.show();
	}

	/**
	 * Method to show the high scores of a given level.
	 *
	 * @param levelNo Level number of high scores to be displayed.
	 */
	private void levelScores(int levelNo) {

		Stage levelScoresWindow = new Stage();
		VBox container = new VBox();
		levelScoresWindow.setResizable(false);
		levelScoresWindow.setTitle("HIGH SCORES: LEVEL " + levelNo);
		String scores = Leaderboard.formatLeaderboardData(levelNo);
		TextArea text = new TextArea(scores);
		text.setEditable(false);
		text.setStyle("-fx-font-size: 1.5em;");
		text.setPrefWidth(200);
		text.setPrefHeight(400);
		Button backButton = new Button("Back");

		backButton.setOnAction(event -> {
			levelScoresWindow.close();
			highScores();
		});
		
		container.getChildren().addAll(text, backButton);
		levelScoresWindow.setScene(new Scene(container));
		levelScoresWindow.show();
	}

	/**
	 * Method to display a gui where users can select which profile they want to play as.
	 */
	private void profileSelect(){
		Stage profileSelectWindow = new Stage();
		profileSelectWindow.setTitle("RAT GAME: PROFILE SELECT");
		ComboBox<String> profileSelect = new ComboBox<String>();
		File profileFolder = new File("src/profiles");
		File[] profiles = profileFolder.listFiles();
		assert profiles != null;
		for(File profile : profiles) {
			if(profile.getName().endsWith(".txt")) {
				String profileName = profile.getName();
				profileName = profileName.substring(0, profileName.length()-4);
				profileSelect.getItems().add(profileName);
			}
		}

		Button submit = new Button("SUBMIT");
		submit.setOnAction(event -> {
			String name = profileSelect.getValue();
			if(name != null) {
				File profile = new File("src/profiles/" + name + ".txt");
				currentPlayer = new PlayerData(profile);
				profileSelectWindow.close();
				levelSelect();
			}
		});

		Button delete = new Button("DELETE");
		delete.setOnAction(event -> {
			String name = profileSelect.getValue();
			if(name != null) {
				File data = new File("src/profiles/" + name + ".txt");
				data.delete();
				profileSelect.getItems().remove(name);
			}
		});

		Button create = new Button("CREATE");
		create.setOnAction(event -> {
			profileSelectWindow.close();
			profileCreator();
		});
		
		Button backButton = new Button(" BACK ");
		backButton.setOnAction(event -> {
			profileSelectWindow.close();
			try {
				start(profileSelectWindow);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		GridPane container = new GridPane();
		container.getChildren().addAll(profileSelect, submit, delete, create, backButton);
		GridPane.setConstraints(submit, 1, 0);
		GridPane.setConstraints(delete, 2, 0);
		GridPane.setConstraints(backButton, 2, 1);
		GridPane.setConstraints(create, 1, 1);
		
		
		//Style container
		container.setPadding(new Insets(25));
		//Set view in window
		profileSelectWindow.setScene(new Scene(container));
		
		profileSelectWindow.setResizable(false);
		//Launch
		profileSelectWindow.show();
	}


	/**
	 * Method to display a gui for users to create new profiles to play as.
	 */
	private void profileCreator() {
		Stage profileCreateWindow = new Stage();
		GridPane grid = new GridPane();
		profileCreateWindow.setTitle("RAT GAME: CREATE PROFILE");
		//Create view in Java
		TextField name = new TextField();
		name.setPromptText("Enter your name :)");
		name.setPrefColumnCount(15);
		Button submit = new Button("Submit");
		
		Button backButton = new Button("BACK");
		backButton.setOnAction(event -> {
			profileCreateWindow.close();
			profileSelect();
		});
		submit.setOnAction(event -> {
			String playerName = name.getText();
			File file = new File("src/profiles/" + playerName + ".txt");
			try {
				new Scanner(file);
			} catch (FileNotFoundException e) {
				try {
					if (file.createNewFile()) {
						file.createNewFile();
						profileCreateWindow.close();
						profileSelect();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		GridPane.setConstraints(submit, 1, 0);
		GridPane.setConstraints(backButton, 2, 0);
		grid.getChildren().addAll(name, submit, backButton);
		//Style container
		grid.setPadding(new Insets(25));
		//Set view in window
		profileCreateWindow.setResizable(false);
		
		profileCreateWindow.setScene(new Scene(grid));
		//Launch
		profileCreateWindow.show();
	}

	/**
	 * Method to display a gui where the user can select a level to play.
	 */
	private void levelSelect(){
		Stage levelMenuWindow = new Stage();
		levelMenuWindow.setTitle("RAT GAME: LEVEL SELECT");
		VBox container = new VBox();
		//Create view in Java
		Label title = new Label("Select a level");
		Button level1Button = new Button("LEVEL 1");
		level1Button.setOnAction(event -> {
			levelMenuWindow.close();
			level1();
			mainTick();
		});
		container.getChildren().addAll(title, level1Button);
		if (currentPlayer.getMaxLevel() >= 2) {
			Button level2Button = new Button("LEVEL 2");
			container.getChildren().add(level2Button);
			level2Button.setOnAction(event -> {
				levelMenuWindow.close();
				level2();
				mainTick();
			});
		} if (currentPlayer.getMaxLevel() >= 3) {
			Button level3Button = new Button("LEVEL 3");
			container.getChildren().add(level3Button);
			level3Button.setOnAction(event -> {
				levelMenuWindow.close();
				level3();
				mainTick();
			});
		} if (currentPlayer.getMaxLevel() >= 4){
			Button level4Button = new Button("LEVEL 4");
			container.getChildren().add(level4Button);
			level4Button.setOnAction(event -> {
				levelMenuWindow.close();
				level4();
				mainTick();
			});
		} if (new File("src/saves/" + currentPlayer.getPlayerName() + "SavedGame.txt").exists()) {
			Button loadSavedButton = new Button("LOAD LAST SAVE");
			container.getChildren().add(loadSavedButton);
			loadSavedButton.setOnAction(event -> {
				levelMenuWindow.close();
				savedLevel();
				mainTick();
			});
		}
		Button backButton = new Button("BACK");
		backButton.setOnAction(event -> {
			levelMenuWindow.close();
			try {
				start(levelMenuWindow);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		container.getChildren().add(backButton);
		//Style container
		container.setSpacing(15);
		container.setPadding(new Insets(25));
		//Set view in window
		levelMenuWindow.setScene(new Scene(container));
		//Launch
		levelMenuWindow.show();
	}

	/**
	 * Method to initialise a level and start the game.
	 *
	 * @param filename Level filename.
	 */
	private void drawGame(String filename) {
		// Get the Graphic Context of the canvas. This is what we draw on.
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Entity.setGC(gc);

		Level gameLevel = new Level(filename);
		int[] spawn = gameLevel.getItemSpawnRates();
		if (!filename.contains("SavedGame")) {
				Inventory.setupNewInventory(spawn[0], spawn[1], spawn[2], spawn[3], spawn[4], spawn[5], spawn[6], spawn[7]);
		} else {
			int[] quan = gameLevel.getInventoryQuantities();
			int[] timer = gameLevel.getInventoryTimers();
			Inventory.setupSpawnRates(spawn[0], spawn[1], spawn[2], spawn[3], spawn[4], spawn[5], spawn[6], spawn[7]);
			Inventory.setupQuantity(quan[0], quan[1], quan[2], quan[3], quan[4], quan[5], quan[6], quan[7]);
			Inventory.setupTimers(timer[0], timer[1], timer[2], timer[3], timer[4], timer[5], timer[6], timer[7]);
		}

		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		// Set the background to beige.
		gc.setFill(Color.WHITESMOKE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		Map map = Level.getMap();
		Map.setGC(gc); //remove this later
		map.tileOut(gc);
		map.entityTick();
	}

	/**
	 * Method to display a win screen.
	 */
	private void win(){
		Stage winScreen = new Stage();
		winScreen.setTitle("YOU WIN");
		Label title = new Label("You Won! Congrats");
		int score = Level.getLevelScore();
		Label scoreTitle = new Label("Score: " + score);
		Leaderboard.inputPlayerScore(Level.getLevelNumber(), getCurrentPlayer().getPlayerName(), score);
		Level.resetLevelScore();
		Leaderboard.writeLeaderboards();
		Button menuButton = new Button("Main Menu");
		currentPlayer.updateMaxLevel(Level.getLevelNumber());
		currentPlayer.updatePlayerFile();

		menuButton.setOnAction(event -> {
			winScreen.close();
			try {
				start(winScreen);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		VBox container = new VBox(title, scoreTitle, menuButton);
		//Style container
		container.setSpacing(15);
		container.setPadding(new Insets(25));
		//Set view in window
		winScreen.setScene(new Scene(container));
		//Launch
		winScreen.show();
	}

	/**
	 * Method to display a lose screen.
	 */
	private void lose(){
		Stage loseScreen = new Stage();
		loseScreen.setTitle("YOU LOST");
		Label title = new Label("You Lost! Better Luck Next Time");
		Button menuButton = new Button("Main Menu");

		menuButton.setOnAction(event -> {
			loseScreen.close();
			try {
				start(loseScreen);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		VBox container = new VBox(title, menuButton);
		//Style container
		container.setSpacing(15);
		container.setPadding(new Insets(25));
		//Set view in window
		loseScreen.setScene(new Scene(container));
		//Launch
		loseScreen.show();
	}


	/**
	 * Method to constantly check if either a win/lose condition has been met or the save button has been pressed.
	 */
	private void mainTick() {
		ScheduledExecutorService test = Executors.newScheduledThreadPool(1);
		test.scheduleWithFixedDelay(() -> {
			Inventory.act();
			if (Rat.getRats().size() == 0) {
				Platform.runLater(() -> levelStage.close());
				Entity.getEntities().clear();
				Rat.getRats().clear();
				Platform.runLater(this::win);
				test.shutdown();
			}else if (Rat.getRats().size() == Level.getMap().maxRat) {
				Platform.runLater(() -> levelStage.close());
				Entity.getEntities().clear();
				Rat.getRats().clear();
				Platform.runLater(this::lose);
				test.shutdown();
			} else if (saved) {
				Entity.getEntities().clear();
				Rat.getRats().clear();
				saved = false;
				test.shutdown();
			}
		}, 0, 250, TimeUnit.MILLISECONDS);
	}

	/**
	 * Method to initialise level 1.
	 */
	private void level1(){
		levelStage.setTitle("RAT GAME : LVL1");
		Pane root = buildGUI(STAGE1_CANVAS_WIDTH, STAGE1_CANVAS_HEIGHT);
		Scene level1Scene = new Scene(root);
		drawGame("src/levels/level.txt");
		levelStage.setScene(level1Scene);
		levelStage.setResizable(false);
		levelStage.show();
	}

	/**
	 * Method to initialise level 2.
	 */
	private void level2(){
		levelStage.setTitle("RAT GAME : LVL2");
		Pane root = buildGUI(STAGE2_CANVAS_WIDTH, STAGE2_CANVAS_HEIGHT);
		Scene level2Scene = new Scene(root);
		drawGame("src/levels/level2.txt");
		levelStage.setScene(level2Scene);
		levelStage.setResizable(false);
		levelStage.show();
	}

	/**
	 * Method to initialise level 3.
	 */
	private void level3(){
		levelStage.setTitle("RAT GAME : LVL3");
		Pane root = buildGUI(STAGE3_CANVAS_WIDTH, STAGE3_CANVAS_HEIGHT);
		Scene level3Scene = new Scene(root);
		drawGame("src/levels/level3.txt");
		levelStage.setScene(level3Scene);
		levelStage.setResizable(false);
		levelStage.show();
	}

	/**
	 * Method to initialise level 4.
	 */
	private void level4(){
		levelStage.setTitle("RAT GAME : LVL4");
		Pane root = buildGUI(STAGE4_CANVAS_WIDTH, STAGE4_CANVAS_HEIGHT);
		Scene level4Scene = new Scene(root);
		drawGame("src/levels/level4.txt");
		levelStage.setScene(level4Scene);
		levelStage.show();
	}

	/**
	 * Method to initialise a saved level.
	 */
	private void savedLevel(){
		levelStage.setTitle("RAT GAME : LVL" + getSavedLevel());
		int savedGameCanvasWidth = 0;
		int savedGameCanvasHeight = 0;
		switch(getSavedLevel()) {
			case 1:
				savedGameCanvasWidth = STAGE1_CANVAS_WIDTH;
				savedGameCanvasHeight = STAGE1_CANVAS_HEIGHT;
				break;
			case 2:
				savedGameCanvasWidth = STAGE2_CANVAS_WIDTH;
				savedGameCanvasHeight = STAGE2_CANVAS_HEIGHT;
				break;
			case 3:
				savedGameCanvasWidth = STAGE3_CANVAS_WIDTH;
				savedGameCanvasHeight = STAGE3_CANVAS_HEIGHT;
				break;
			case 4:
				savedGameCanvasWidth = STAGE4_CANVAS_WIDTH;
				savedGameCanvasHeight = STAGE4_CANVAS_HEIGHT;
				break;
		}
		Pane root = buildGUI(savedGameCanvasWidth, savedGameCanvasHeight);
		Scene savedlevelScene = new Scene(root);
		drawGame("src/saves/" + currentPlayer.getPlayerName() + "SavedGame.txt");
		levelStage.setScene(savedlevelScene);
		levelStage.setResizable(false);
		levelStage.show();
	}

	/**
	 * Method to get the numeric value of the saved level for the current player.
	 *
	 * @return Returns the saved level file name of the current player.
	 */
	private int getSavedLevel() {
		File f = new File("src/saves/" + currentPlayer.getPlayerName() + "SavedGame.txt");
		Scanner scan = null;
		try {
			scan = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}

		assert scan != null;
		int ent = scan.nextInt();
		scan.nextLine();
		for (int i = 0; i < ent; i++) {
			scan.nextLine();
		}
		return scan.nextInt();
	}
	
	/**
	 * Method to build the GUI of the level
	 *
	 * @return return Pane for level stage.
	 */
	private Pane buildGUI(int width, int height) {
		// Create top-level panel that will hold all GUI
		BorderPane root = new BorderPane();

		// Create canvas
		canvas = new Canvas(width, height + 50);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Entity.setGC(gc);
		root.setCenter(canvas);

		Image gasImg = new Image("sprites/Gas.png");
		ImageView gasButton = new ImageView(gasImg);
		gasButton.setX(width - 100);
		gasButton.setY(0);

		Image deathRatImg = new Image("sprites/DeathRatN.png");
		ImageView deathRatButton = new ImageView(deathRatImg);
		deathRatButton.setX(width - 100);
		deathRatButton.setY(50);

		Image poisonImg = new Image("sprites/Poison.png");
		ImageView poisonButton = new ImageView(poisonImg);
		poisonButton.setX(width - 100);
		poisonButton.setY(100);

		Image sterilisationImg = new Image("sprites/Sterilisation.png");
		ImageView sterilisationButton = new ImageView(sterilisationImg);
		sterilisationButton.setX(width - 100);
		sterilisationButton.setY(150);

		Image femaleSexChangeImg = new Image("sprites/FemaleSexChange.png");
		ImageView femaleSexChangeButton = new ImageView(femaleSexChangeImg);
		femaleSexChangeButton.setX(width - 100);
		femaleSexChangeButton.setY(200);

		Image maleSexChangeImg = new Image("sprites/MaleSexChange.png");
		ImageView maleSexChangeButton = new ImageView(maleSexChangeImg);
		maleSexChangeButton.setX(width - 100);
		maleSexChangeButton.setY(250);

		Image noEntrySignImg = new Image("sprites/NoEntrySign.png");
		ImageView noEntrySignButton = new ImageView(noEntrySignImg);
		noEntrySignButton.setX(width - 100);
		noEntrySignButton.setY(300);

		Image bombImg = new Image("sprites/Bomb.png");
		ImageView bombButton = new ImageView(bombImg);
		bombButton.setX(width - 100);
		bombButton.setY(350);

		Button saveButton = new Button("SAVE");
		root.setBottom(saveButton);

		root.getChildren().addAll(gasButton, deathRatButton, poisonButton, sterilisationButton,
				femaleSexChangeButton, maleSexChangeButton, noEntrySignButton, bombButton);

		saveButton.setOnMouseClicked(event ->{
			Level.saveCurrent();
			levelStage.close();
			try {
				start(levelStage);
				saved = true;
			} catch (IOException e) {
				System.out.println("Failed to save game.");
			}
		});

		gasButton.setOnMouseDragged(event -> {
			gasButton.setX((int)event.getSceneX()-25);
			gasButton.setY((int)event.getSceneY()-25);
		});

		gasButton.setOnMouseReleased(event -> {
			gasButton.setX(width - 100);
			gasButton.setY(0);
			if (Inventory.getGas() > 0 && checkLegalTile(event, width, height)) {
				Inventory.removeGas();
				Entity gas = new Gas(getMouseLocation(event), false);
				gas.draw();
			}
		});

		deathRatButton.setOnMouseDragged(event -> {
			deathRatButton.setX((int)event.getSceneX()-25);
			deathRatButton.setY((int)event.getSceneY()-25);
		});
		deathRatButton.setOnMouseReleased(event -> {
			deathRatButton.setX(width - 100);
			deathRatButton.setY(50);
			if (Inventory.getDeathRat() > 0 && checkLegalTile(event, width, height)) {
				Inventory.removeDeathRat();
				Entity deathRat = new DeathRatSpawner(getMouseLocation(event));
				deathRat.draw();
			}
		});

		poisonButton.setOnMouseDragged(event -> {
			poisonButton.setX((int)event.getSceneX()-25);
			poisonButton.setY((int)event.getSceneY()-25);
		});
		poisonButton.setOnMouseReleased(event -> {
			poisonButton.setX(width - 100);
			poisonButton.setY(100);
			if (Inventory.getPoison() > 0 && checkLegalTile(event, width, height)) {
				Inventory.removePoison();
				Entity poison = new Poison(getMouseLocation(event));
				poison.draw();
			}
		});

		sterilisationButton.setOnMouseDragged(event -> {
			sterilisationButton.setX((int)event.getSceneX()-25);
			sterilisationButton.setY((int)event.getSceneY()-25);
		});
		sterilisationButton.setOnMouseReleased(event -> {
			sterilisationButton.setX(width - 100);
			sterilisationButton.setY(150);
			if (Inventory.getSterilisation() > 0 && checkLegalTile(event, width, height)) {
				Inventory.removeSterilisation();
				Entity sterilisation = new Sterilisation(getMouseLocation(event));
				sterilisation.draw();
			}
		});

		femaleSexChangeButton.setOnMouseDragged(event -> {
			femaleSexChangeButton.setX((int)event.getSceneX()-25);
			femaleSexChangeButton.setY((int)event.getSceneY()-25);
		});
		femaleSexChangeButton.setOnMouseReleased(event -> {
			femaleSexChangeButton.setX(width - 100);
			femaleSexChangeButton.setY(200);
			if (Inventory.getFemaleSexChange() > 0 && checkLegalTile(event, width, height)) {
				Inventory.removeFemaleSexChange();
				Entity femaleSexChange = new FemaleSexChange(getMouseLocation(event));
				femaleSexChange.draw();
			}
		});

		maleSexChangeButton.setOnMouseDragged(event -> {
			maleSexChangeButton.setX((int)event.getSceneX()-25);
			maleSexChangeButton.setY((int)event.getSceneY()-25);
		});
		maleSexChangeButton.setOnMouseReleased(event -> {
			maleSexChangeButton.setX(width - 100);
			maleSexChangeButton.setY(250);
			if (Inventory.getMaleSexChange() > 0 && checkLegalTile(event, width, height)) {
				Inventory.removeMaleSexChange();
				Entity maleSexChange = new MaleSexChange(getMouseLocation(event));
				maleSexChange.draw();
			}
		});

		noEntrySignButton.setOnMouseDragged(event -> {
			noEntrySignButton.setX((int)event.getSceneX()-25);
			noEntrySignButton.setY((int)event.getSceneY()-25);
		});
		noEntrySignButton.setOnMouseReleased(event -> {
			noEntrySignButton.setX(width - 100);
			noEntrySignButton.setY(300);
			if (Inventory.getNoEntrySign() > 0 && checkLegalTile(event, width, height)) {
				Inventory.removeNoEntrySign();
				Entity noEntrySign = new NoEntrySign(getMouseLocation(event));
				noEntrySign.draw();
			}
		});

		bombButton.setOnMouseDragged(event -> {
			bombButton.setX((int)event.getSceneX()-25);
			bombButton.setY((int)event.getSceneY()-25);
		});
		bombButton.setOnMouseReleased(event -> {
			bombButton.setX(width - 100);
			bombButton.setY(350);
			if (Inventory.getBomb() > 0 && checkLegalTile(event, width, height)) {
				Inventory.removeBomb();
				Entity bomb = new Bomb(getMouseLocation(event));
				bomb.draw();
			}
		});
		return root;
	}

	/**
	 * Method to get the location of the players mouse.
	 *
	 * @param event What kind of mouse event has happened.
	 * @return Returns the tile coordinate values of the event.
	 */
	private int[] getMouseLocation(MouseEvent event) {
		int x = (int)event.getSceneX();
		int y = (int)event.getSceneY();

		x = ((x-(x%50))/50);
		y = ((y-(y%50))/50);

		return new int[]{x,y};
	}

	/**
	 * Method to check if the tile is a legal location to place an item.
	 *
	 * @param event What kind of mouse event has happened.
	 * @param width Width of the screen.
	 * @param height Height of the screen.
	 * @return Returns if the tile is a legal location for an item to be placed.
	 */
	private boolean checkLegalTile(MouseEvent event, int width, int height) {
		boolean result = false;
		if (((int)event.getSceneX() <= width - 100) && ((int)event.getSceneY() <= height-75)) {
			if (Map.getTileType(getMouseLocation(event)[0], getMouseLocation(event)[1]) == 'P') {
				result = true;
			}
		}
		return result;
	}
}