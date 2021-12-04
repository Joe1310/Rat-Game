import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.*;

import java.io.IOException;

public class NewMain extends Application {
    // The dimensions of the canvas
    private static int CANVAS_WIDTH = 1850;
    private static int CANVAS_HEIGHT = 1000;

    private Canvas canvas;

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
        Button loadButton = new Button("LOAD");
        Button highScoresButton = new Button("HIGH SCORES");
        Button howToPlayButton = new Button("HOW TO PLAY");
        Button quitButton = new Button("QUIT");

		Image image = new Image("Logo.png");
		ImageView logoView = new ImageView();
		logoView.setImage(image);
		logoView.setFitWidth(200);
		logoView.setPreserveRatio(true);

		Image maRat = new Image("MaRAT.png");
		ImageView maRatView = new ImageView();
		maRatView.setImage(maRat);
		maRatView.setFitWidth(200);
		maRatView.setPreserveRatio(true);

        startButton.setOnAction(event -> {
            menuWindow.close();
            play(primaryStage);
        });
        loadButton.setOnAction(event -> {
            menuWindow.close();
            load(primaryStage);
        });
        highScoresButton.setOnAction(event -> {
            menuWindow.close();
            highScores(primaryStage);
        });
        howToPlayButton.setOnAction(event -> {
            menuWindow.close();
            howToPlay(primaryStage);
        });
        quitButton.setOnAction(event -> {
            menuWindow.close();
        });

        VBox container = new VBox(logoView, MOTD, maRatView, startButton, loadButton, highScoresButton,
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

    private void load(Stage primaryStage) {
    }

    public void play(Stage primaryStage){
        Stage levelMenuWindow = new Stage();
        levelMenuWindow.setTitle("RAT GAME");
        //Create view in Java
        Label title = new Label("Select a level");
        Button level1Button = new Button("LEVEL 1");
        Button level2Button = new Button("LEVEL 2");
        Button level3Button = new Button("LEVEL 3");
        Button level4Button = new Button("LEVEL 4");
        Button backButton = new Button("BACK");
        level1Button.setOnAction(event -> {
            levelMenuWindow.close();
            level1();
        });
        level2Button.setOnAction(event -> {
            levelMenuWindow.close();
            level2();
        });
        level3Button.setOnAction(event -> {
            levelMenuWindow.close();
            level3();
        });
        level4Button.setOnAction(event -> {
            levelMenuWindow.close();
            level4();
        });
        backButton.setOnAction(event -> {
            levelMenuWindow.close();
            try {
                start(primaryStage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        VBox container = new VBox(title,level1Button,level2Button,level3Button,level4Button,backButton);
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
        gc.setFill(Color.BEIGE);
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
        Button menuButton = new Button("Main Menu");

        menuButton.setOnAction(event -> {
            winScreen.close();
            try {
                start(winScreen);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        VBox container = new VBox(title, menuButton);
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

	/**
	 *
	 */
	public void level1(){
		Stage level1Stage = new Stage();
		level1Stage.setTitle("RAT GAME : LVL1");
		Pane root = buildGUI();
		Scene level1Scene = new Scene(root);
		drawGame("level.txt");
		level1Stage.setScene(level1Scene);
		level1Stage.show();
	}

	/**
	 *
	 */
    public void level2(){
    	CANVAS_WIDTH = 1050;
    	CANVAS_HEIGHT = 800;
        Stage level2Stage = new Stage();
        level2Stage.setTitle("RAT GAME : LVL2");
        Pane root = buildGUI();
        Scene level2Scene = new Scene(root);
        drawGame("level2.txt");
        level2Stage.setScene(level2Scene);
        level2Stage.show();
    }

	/**
	 *
	 */
	public void level3(){
		Stage level3Stage = new Stage();
		level3Stage.setTitle("RAT GAME : LVL3");
		Pane root = buildGUI();
		Scene level3Scene = new Scene(root);
		drawGame("level3.txt");
		level3Stage.setScene(level3Scene);
		level3Stage.show();
	}

	/**
	 *
	 */
	public void level4(){
		Stage level4Stage = new Stage();
		level4Stage.setTitle("RAT GAME : LVL4");
		Pane root = buildGUI();
		Scene level4Scene = new Scene(root);
		drawGame("level4.txt");
		level4Stage.setScene(level4Scene);
		level4Stage.show();
	}

	/**
	 * @return
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
		
		
		Image gasImg = new Image("Gas2.png");
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
		
		Image sterilizationImg = new Image("Sterilization.png");
		ImageView sterilizationButton = new ImageView(sterilizationImg);
		sterilizationButton.setX(CANVAS_WIDTH - 50);
		sterilizationButton.setY(150);
		
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
		
		root.getChildren().addAll(gasButton, deathRatButton, poisonButton, sterilizationButton, femaleSexChangeButton, maleSexChangeButton, noEntrySignButton, bombButton);
		
		
		gasButton.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {	
				gasButton.setX((int)event.getSceneX()-25);
				gasButton.setY((int)event.getSceneY()-25);
			}
		});	
		gasButton.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				gasButton.setX(CANVAS_WIDTH - 50);
				gasButton.setY(0);
				if (Inventory.getGas() > 0 && checkLegalTile(getMouseLocation(event))) {
					Inventory.removeGas();
					Entity gas = new Gas(getMouseLocation(event), false);
					gas.draw();
				}
			}
		});

		deathRatButton.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {	
				deathRatButton.setX((int)event.getSceneX()-25);
				deathRatButton.setY((int)event.getSceneY()-25);
			}
		});	
		deathRatButton.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				deathRatButton.setX(CANVAS_WIDTH - 50);
				deathRatButton.setY(50);
				if (Inventory.getDeathRat() > 0 && checkLegalTile(getMouseLocation(event))) {
					Inventory.removeDeathRat();
					Entity deathRat = new DeathRatSpawner(getMouseLocation(event));
					deathRat.draw();
				}
			}
		});
		
		poisonButton.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {	
				poisonButton.setX((int)event.getSceneX()-25);
				poisonButton.setY((int)event.getSceneY()-25);
			}
		});	
		poisonButton.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				poisonButton.setX(CANVAS_WIDTH - 50);
				poisonButton.setY(100);
				if (Inventory.getPoison() > 0 && checkLegalTile(getMouseLocation(event))) {
					Inventory.removePoison();
					Entity poison = new Poison(getMouseLocation(event));
					poison.draw();
				}
			}
		});
		
		sterilizationButton.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {	
				sterilizationButton.setX((int)event.getSceneX()-25);
				sterilizationButton.setY((int)event.getSceneY()-25);
			}
		});	
		sterilizationButton.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				sterilizationButton.setX(CANVAS_WIDTH - 50);
				sterilizationButton.setY(150);
				if (Inventory.getSterilization() > 0 && checkLegalTile(getMouseLocation(event))) {
					Inventory.removeSterilization();
					Entity sterilisation = new Sterilisation(getMouseLocation(event));
					sterilisation.draw();
				}
			}
		});
		
		femaleSexChangeButton.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {	
				femaleSexChangeButton.setX((int)event.getSceneX()-25);
				femaleSexChangeButton.setY((int)event.getSceneY()-25);
			}
		});	
		femaleSexChangeButton.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				femaleSexChangeButton.setX(CANVAS_WIDTH - 50);
				femaleSexChangeButton.setY(200);
				if (Inventory.getFemaleSexChange() > 0 && checkLegalTile(getMouseLocation(event))) {
					Inventory.removeFemaleSexChange();
					Entity femaleSexChange = new FemaleSexChange(getMouseLocation(event));
					femaleSexChange.draw();
				}
			}
		});
		
		maleSexChangeButton.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {	
				maleSexChangeButton.setX((int)event.getSceneX()-25);
				maleSexChangeButton.setY((int)event.getSceneY()-25);
			}
		});	
		maleSexChangeButton.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				maleSexChangeButton.setX(CANVAS_WIDTH - 50);
				maleSexChangeButton.setY(250);
				if (Inventory.getMaleSexChange() > 0 && checkLegalTile(getMouseLocation(event))) {
					Inventory.removeMaleSexChange();
					Entity maleSexChange = new MaleSexChange(getMouseLocation(event));
					maleSexChange.draw();
				}
			}
		});
		
		noEntrySignButton.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {	
				noEntrySignButton.setX((int)event.getSceneX()-25);
				noEntrySignButton.setY((int)event.getSceneY()-25);
			}
		});	
		noEntrySignButton.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				noEntrySignButton.setX(CANVAS_WIDTH - 50);
				noEntrySignButton.setY(300);
				if (Inventory.getNoEntrySign() > 0 && checkLegalTile(getMouseLocation(event))) {
					Inventory.removeNoEntrySign();
					Entity noEntrySign = new NoEntrySign(getMouseLocation(event));
					noEntrySign.draw();
				}
			}
		});
		
		bombButton.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {	
				bombButton.setX((int)event.getSceneX()-25);
				bombButton.setY((int)event.getSceneY()-25);
			}
		});	
		bombButton.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				bombButton.setX(CANVAS_WIDTH - 50);
				bombButton.setY(350);
				if (Inventory.getBomb() > 0 && checkLegalTile(getMouseLocation(event))) {
					Inventory.removeBomb();
					Entity bomb = new Bomb(getMouseLocation(event));
					bomb.draw();
				}
			}
		});
		
		return root;
	}

    private int[] getMouseLocation(MouseEvent event) {
		int x = (int)event.getSceneX();
		int y = (int)event.getSceneY();
		
		x = ((x-(x%50))/50);
		y = ((y-(y%50))/50);
		
		int[] location = {x,y};
	
		return location;
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
