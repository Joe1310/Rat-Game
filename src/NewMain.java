import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
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

public class NewMain extends Application {
	
	// The dimensions of the canvas
	private static int CANVAS_WIDTH = 1850;
	private static int CANVAS_HEIGHT = 1000;
	
	public boolean saved = false;

	public Stage levelStage = new Stage();
	private Canvas canvas;
	private static PlayerData currentPlayer;

	@Override
	public void start(Stage primaryStage) throws IOException {

		Stage menuWindow = new Stage();
		menuWindow.setTitle("Rat Game");

		Message message = new Message();
		String messageOfTheDay = message.MessageOfTheDay();
		TextArea MOTD = new TextArea(messageOfTheDay);
		MOTD.setEditable(false);
		MOTD.setStyle("-fx-font-size: 1.5em;");
		MOTD.setPrefWidth(messageOfTheDay.length() * 10);
		MOTD.setPrefHeight(30);

		Button startButton = new Button("START");
		Button highScoresButton = new Button("HIGH SCORES");
		Button howToPlayButton = new Button("HOW TO PLAY");
		Button quitButton = new Button("QUIT");

		Image image = new Image("Logo.png");
		ImageView logoView = new ImageView();
		logoView.setImage(image);
		logoView.setFitWidth(200);
		logoView.setPreserveRatio(true);

		Image maRat = new Image("MaRAT.png"); //Praise be
		ImageView maRatView = new ImageView();
		maRatView.setImage(maRat);
		maRatView.setFitWidth(200);
		maRatView.setPreserveRatio(true);

		startButton.setOnAction(event -> {
			menuWindow.close();
			profileSelect(primaryStage);
		});
		highScoresButton.setOnAction(event -> {
			menuWindow.close();
			highScores(primaryStage);
		});
		howToPlayButton.setOnAction(event -> {
			menuWindow.close();
			howToPlay(primaryStage);
		});
		quitButton.setOnAction(event -> menuWindow.close());

		VBox container = new VBox(logoView, MOTD, maRatView, startButton, highScoresButton,
				howToPlayButton, quitButton);
		container.setSpacing(15);
		container.setPadding(new Insets(25));

		menuWindow.setScene(new Scene(container));
		//Launch
		menuWindow.show();
	}
	public void howToPlay(Stage primaryStage){
		Stage howToPlayWindow = new Stage();
		howToPlayWindow.setResizable(false);
		howToPlayWindow.setTitle("HOW TO PLAY");
		TextArea text = new TextArea("Welcome exterminator. The city needs your help; the parks are being overrun" +
				" with rats, and we need you to eliminate them before it’s too late.\n Aim:\n" +
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
				start(primaryStage);
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

	private void highScores(Stage primaryStage) {
	}
	
	public void profileSelect(Stage primaryStage){
		Stage profileSelectWindow = new Stage();
		profileSelectWindow.setTitle("RAT GAME: PROFILE SELECT");
		ComboBox<String> profileSelect = new ComboBox<String>();
		File profileFolder = new File("src/profiles");
		File[] profiles = profileFolder.listFiles();
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
			File profile = new File("src/profiles/" + name + ".txt");
			currentPlayer = new PlayerData(profile);
			profileSelectWindow.close();
			levelSelect(primaryStage);
		});
		
		Button create = new Button("CREATE");
		create.setOnAction(event -> {
			profileSelectWindow.close();
			profileCreator(primaryStage);
		});
		
		Button delete = new Button("DELETE");
		delete.setOnAction(event -> {
			String name = profileSelect.getValue();
			File data = new File("src/profiles/" + name + ".txt");
			data.delete();
			profileSelect.getItems().remove(name);
		});
		
		Button backButton = new Button(" BACK ");
		backButton.setOnAction(event -> {
			profileSelectWindow.close();
			try {
				start(primaryStage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		GridPane container = new GridPane();
		container.getChildren().addAll(profileSelect, submit, delete, create, backButton);
		container.setConstraints(submit, 1, 0);
		container.setConstraints(delete, 2, 0);
		container.setConstraints(backButton, 2, 1);
		container.setConstraints(create, 1, 1);
		
		
		//Style container
		container.setPadding(new Insets(25));
		//Set view in window
		profileSelectWindow.setScene(new Scene(container));
		
		profileSelectWindow.setResizable(false);
		//Launch
		profileSelectWindow.show();
	}
	
	
	public void profileCreator(Stage primaryStage) {
		Stage profileCreateWindow = new Stage();
		GridPane grid = new GridPane();
		profileCreateWindow.setTitle("RAT GAME: CREATE PROFILE");
		//Create view in Java
		Label title = new Label("Enter your Name: ");
		TextField name = new TextField();
		name.setPromptText("Enter your name :)");
		name.setPrefColumnCount(15);
		Button submit = new Button("Submit");
		
		Button backButton = new Button("BACK");
		backButton.setOnAction(event -> {
			profileCreateWindow.close();
			profileSelect(primaryStage);
		});
		submit.setOnAction(event -> {
			String playerName = name.getText();
			File file = new File("src/profiles/" + playerName + ".txt");
			Scanner scan = null;
			try {
				scan = new Scanner(file);
			} catch (FileNotFoundException e) {
				try {
					if (file.createNewFile()) {
						file.createNewFile();
						profileCreateWindow.close();
						profileSelect(primaryStage);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		grid.setConstraints(submit, 1, 0);
		grid.setConstraints(backButton, 2, 0);
		grid.getChildren().addAll(name, submit, backButton);
		//Style container
		grid.setPadding(new Insets(25));
		//Set view in window
		profileCreateWindow.setResizable(false);
		
		profileCreateWindow.setScene(new Scene(grid));
		//Launch
		profileCreateWindow.show();
	}

	public void levelSelect(Stage primaryStage){
		Stage levelMenuWindow = new Stage();
		levelMenuWindow.setTitle("RAT GAME: LEVEL SELECT");
		//Create view in Java
		Label title = new Label("Select a level");
		Button level1Button = new Button("LEVEL 1");
		Button level2Button = new Button("LEVEL 2");
		Button level3Button = new Button("LEVEL 3");
		Button level4Button = new Button("LEVEL 4");
		Button loadSavedButton = new Button("LOAD LAST SAVE");
		Button backButton = new Button("BACK");
		level1Button.setOnAction(event -> {
			levelMenuWindow.close();
			level1();
			mainTick();

		});
		level2Button.setOnAction(event -> {
			levelMenuWindow.close();
			level2();
			mainTick();
		});
		level3Button.setOnAction(event -> {
			levelMenuWindow.close();
			level3();
			mainTick();
		});
		level4Button.setOnAction(event -> {
			levelMenuWindow.close();
			level4();
			mainTick();
		});
		loadSavedButton.setOnAction(event -> {
			levelMenuWindow.close();
			savedLevel();
			mainTick();
		});
		backButton.setOnAction(event -> {
			levelMenuWindow.close();
			try {
				start(primaryStage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		VBox container = new VBox(title,level1Button,level2Button,level3Button,level4Button, loadSavedButton, backButton);
		//Style container
		container.setSpacing(15);
		container.setPadding(new Insets(25));
		//Set view in window
		levelMenuWindow.setScene(new Scene(container));
		//Launch
		levelMenuWindow.show();
	}

	public void drawGame(String filename) {
		// Get the Graphic Context of the canvas. This is what we draw on.
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Entity.setGC(gc);

		Level gameLevel = new Level(filename);

		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		// Set the background to beige.
		gc.setFill(Color.WHITESMOKE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		Map map = gameLevel.getMap();
		map.setGC(gc); //remove this later
		map.tileOut(gc);
		map.entityTick();
	}

	public void win(){
		Stage winScreen = new Stage();
		winScreen.setTitle("YOU WIN");
		Label title = new Label("You Won! Congrats");
		Label scoreTitle = new Label("Score: " + Level.getLevelScore());
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

	public void lose(){
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


	public int mainTick() {
		ScheduledExecutorService test = Executors.newScheduledThreadPool(1);
		int num = 0;
		test.scheduleWithFixedDelay(new Runnable() {
			public void run() {
				if (Rat.getRats().size() == 0) {
					System.out.println("Win");
					Platform.runLater(() -> levelStage.close());
					Entity.getEntities().clear();
					Rat.getRats().clear();
					Platform.runLater(() -> win());
					test.shutdown();
				}else if (Rat.getRats().size() == Level.getMap().maxRat) {
					Platform.runLater(() -> levelStage.close());
					Entity.getEntities().clear();
					Rat.getRats().clear();
					Platform.runLater(() -> lose());
					test.shutdown();
				} else if (saved) {
					Entity.getEntities().clear();
					Rat.getRats().clear();
					saved = false;
					test.shutdown();
				}
			}
		}, 0, 250, TimeUnit.MILLISECONDS);
		return num;
	}

	/**
	 *
	 */
	public void level1(){
		CANVAS_WIDTH = 650;
		CANVAS_HEIGHT = 400;
		levelStage.setTitle("RAT GAME : LVL1");
		Pane root = buildGUI();
		Scene level1Scene = new Scene(root);
		drawGame("level.txt");
		levelStage.setScene(level1Scene);
		levelStage.setResizable(false);
		levelStage.show();
	}

	/**
	 *
	 */
	public void level2(){
		CANVAS_WIDTH = 1100;
		CANVAS_HEIGHT = 800;
		levelStage.setTitle("RAT GAME : LVL2");
		Pane root = buildGUI();
		Scene level2Scene = new Scene(root);
		drawGame("level2.txt");
		levelStage.setScene(level2Scene);
		levelStage.setResizable(false);
		levelStage.show();
	}

	/**
	 *
	 */
	public void level3(){
		levelStage.setTitle("RAT GAME : LVL3");
		Pane root = buildGUI();
		Scene level3Scene = new Scene(root);
		drawGame("level3.txt");
		levelStage.setScene(level3Scene);
		levelStage.setResizable(false);
		levelStage.show();
	}

	/**
	 *
	 */
	public void level4(){
		CANVAS_WIDTH = 1200;
		CANVAS_HEIGHT = 800;
		levelStage.setTitle("RAT GAME : LVL4");
		Pane root = buildGUI();
		Scene level4Scene = new Scene(root);
		drawGame("level4.txt");
		levelStage.setScene(level4Scene);
		levelStage.show();
	}
	
	public void savedLevel(){
		CANVAS_WIDTH = 1200;
		CANVAS_HEIGHT = 800;
		levelStage.setTitle("RAT GAME : LVL???");
		Pane root = buildGUI();
		Scene savedlevelScene = new Scene(root);
		drawGame(currentPlayer.getPlayerName() + "savedGame.txt");
		levelStage.setScene(savedlevelScene);
		levelStage.show();
	}

	/**
	 * @return return Pane for level stage.
	 */
	private Pane buildGUI() {
		setupInventory();
		// Create top-level panel that will hold all GUI
		BorderPane root = new BorderPane();

		// Create canvas
		canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Entity.setGC(gc);
		root.setCenter(canvas);


		Image gasImg = new Image("Gas.png");
		ImageView gasButton = new ImageView(gasImg);
		gasButton.setX(CANVAS_WIDTH - 50);
		gasButton.setY(0);

		Image deathRatImg = new Image("DeathRatN.png");
		ImageView deathRatButton = new ImageView(deathRatImg);
		deathRatButton.setX(CANVAS_WIDTH - 50);
		deathRatButton.setY(50);

		Image poisonImg = new Image("Poison.png");
		ImageView poisonButton = new ImageView(poisonImg);
		poisonButton.setX(CANVAS_WIDTH - 50);
		poisonButton.setY(100);

		Image sterilisationImg = new Image("Sterilisation.png");
		ImageView sterilisationButton = new ImageView(sterilisationImg);
		sterilisationButton.setX(CANVAS_WIDTH - 50);
		sterilisationButton.setY(150);

		Image femaleSexChangeImg = new Image("FemaleSexChange.png");
		ImageView femaleSexChangeButton = new ImageView(femaleSexChangeImg);
		femaleSexChangeButton.setX(CANVAS_WIDTH - 50);
		femaleSexChangeButton.setY(200);

		Image maleSexChangeImg = new Image("MaleSexChange.png");
		ImageView maleSexChangeButton = new ImageView(maleSexChangeImg);
		maleSexChangeButton.setX(CANVAS_WIDTH - 50);
		maleSexChangeButton.setY(250);

		Image noEntrySignImg = new Image("NoEntrySign.png");
		ImageView noEntrySignButton = new ImageView(noEntrySignImg);
		noEntrySignButton.setX(CANVAS_WIDTH - 50);
		noEntrySignButton.setY(300);

		Image bombImg = new Image("Bomb.png");
		ImageView bombButton = new ImageView(bombImg);
		bombButton.setX(CANVAS_WIDTH - 50);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		gasButton.setOnMouseDragged(event -> {
			gasButton.setX((int)event.getSceneX()-25);
			gasButton.setY((int)event.getSceneY()-25);
		});
		
		gasButton.setOnMouseReleased(event -> {
			gasButton.setX(CANVAS_WIDTH - 50);
			gasButton.setY(0);
			if (Inventory.getGas() > 0 && checkLegalTile(getMouseLocation(event))) {
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
			deathRatButton.setX(CANVAS_WIDTH - 50);
			deathRatButton.setY(50);
			if (Inventory.getDeathRat() > 0 && checkLegalTile(getMouseLocation(event))) {
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
			poisonButton.setX(CANVAS_WIDTH - 50);
			poisonButton.setY(100);
			if (Inventory.getPoison() > 0 && checkLegalTile(getMouseLocation(event))) {
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
			sterilisationButton.setX(CANVAS_WIDTH - 50);
			sterilisationButton.setY(150);
			if (Inventory.getSterilisation() > 0 && checkLegalTile(getMouseLocation(event))) {
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
			femaleSexChangeButton.setX(CANVAS_WIDTH - 50);
			femaleSexChangeButton.setY(200);
			if (Inventory.getFemaleSexChange() > 0 && checkLegalTile(getMouseLocation(event))) {
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
			maleSexChangeButton.setX(CANVAS_WIDTH - 50);
			maleSexChangeButton.setY(250);
			if (Inventory.getMaleSexChange() > 0 && checkLegalTile(getMouseLocation(event))) {
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
			noEntrySignButton.setX(CANVAS_WIDTH - 50);
			noEntrySignButton.setY(300);
			if (Inventory.getNoEntrySign() > 0 && checkLegalTile(getMouseLocation(event))) {
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
			bombButton.setX(CANVAS_WIDTH - 50);
			bombButton.setY(350);
			if (Inventory.getBomb() > 0 && checkLegalTile(getMouseLocation(event))) {
				Inventory.removeBomb();
				Entity bomb = new Bomb(getMouseLocation(event));
				bomb.draw();
			}
		});

		return root;
	}
	
	public static PlayerData getCurrentPlayer() {
		return currentPlayer;
	}

	private int[] getMouseLocation(MouseEvent event) {
		int x = (int)event.getSceneX();
		int y = (int)event.getSceneY();

		x = ((x-(x%50))/50);
		y = ((y-(y%50))/50);

		return new int[]{x,y};
	}

	private boolean checkLegalTile(int[] location) {
		boolean result = false;
		System.out.println(location[0]);
		System.out.println((CANVAS_WIDTH/50)-2);
		if (!(location[0] > (CANVAS_WIDTH/50)-2) && !(location[1] > (CANVAS_HEIGHT/50)-1)) {
			if (Map.getTileType(location[0], location[1]) == 'P') {
				result = true;
			}
		}
		return result;
	}

	private static void setupInventory() {
		Inventory.gasQuantity = 5;
		Inventory.deathRatQuantity = 5;
		Inventory.poisonQuantity = 5;
		Inventory.sterilisationQuantity = 5;
		Inventory.femaleSexChangeQuantity = 5;
		Inventory.maleSexChangeQuantity = 5;
		Inventory.noEntrySignQuantity = 5;
		Inventory.bombQuantity = 5;
	}

	public static void main(String[] args) {
		launch(args);
	}
}