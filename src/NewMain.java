import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    private static final int TILE_SIZE = 50;
    private static int CANVAS_WIDTH = 1850;
    private static int CANVAS_HEIGHT = 1000;

    // Timeline which will cause tick method to be called periodically.
    private static Timeline tickTimeline;

    private Canvas canvas;

    @Override
    public void start(Stage primaryStage) throws IOException {

        Stage menuWindow = new Stage();
        menuWindow.setTitle("Rat Game");
        //Create view in Java
        Button startButton = new Button("START");
        Button loadButton = new Button("LOAD");
        Button highScoresButton = new Button("HIGH SCORES");
        Button howToPlayButton = new Button("HOW TO PLAY");
        Button quitButton = new Button("QUIT");
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
        VBox container = new VBox(startButton, loadButton, highScoresButton, howToPlayButton, quitButton);
        //Style container
        container.setSpacing(15);
        container.setPadding(new Insets(25));
        //Set view in window
        menuWindow.setScene(new Scene(container));
        //Launch
        menuWindow.show();
    }
    public void howToPlay(Stage primaryStage){
        Stage howToPlayWindow = new Stage();
        howToPlayWindow.setTitle("HOW TO PLAY");
        TextArea text = new TextArea("Drag and drop items onto the play area to stop the rats overproducing." +
                "\nIf too many rats are created, then you lose.\nHowever if you kill enough of them, YOU WIN!");
        text.setEditable(false);
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
            //level3(primaryStage);
        });
        level4Button.setOnAction(event -> {
            levelMenuWindow.close();
            //level4(primaryStage);
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

    public void level1(){
    	// Hard coding the width/height of the screen because there isn't a real reason for a dynamic one, since levels will never change
    	// Width is equal to 'number of tiles across, plus one, then multiply by 50'
    	// Height is equal to 'number of tiles down, multiplied by 50'
    	// Height needs to be a minimum of 400, as to fit all the side bar items on
    	CANVAS_WIDTH = 600;
    	CANVAS_HEIGHT = 400;
        Stage level1Stage = new Stage();
        level1Stage.setTitle("RAT GAME : LVL1");
        Pane root = buildGUI();
        Scene level1Scene = new Scene(root);
        drawGame("level.txt");
        level1Stage.setScene(level1Scene);
        level1Stage.show();
    }
    
    public void level2(){
    	CANVAS_WIDTH = 1050;
    	CANVAS_HEIGHT = 800;
        Stage level2Stage = new Stage();
        level2Stage.setTitle("RAT GAME : LVL2");
        Pane root = buildGUI();
        Scene level1Scene = new Scene(root);
        drawGame("level2.txt");
        level2Stage.setScene(level1Scene);
        level2Stage.show();
    }

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
				//if (Inventory.getSterilization() > 0 && checkLegalTile(getMouseLocation(event))) {
				//	Inventory.removeSterilization();
				//	Entity sterilization = new Sterilization(getMouseLocation(event), false);
				//	sterilization.draw();
				//}
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
		Inventory.sterilizationQuantity = 5;
		Inventory.femaleSexChangeQuantity = 5;
		Inventory.maleSexChangeQuantity = 5;
		Inventory.noEntrySignQuantity = 5;
		Inventory.bombQuantity = 5;
	}

    public static void main(String[] args) {
        launch(args);
    }
}
