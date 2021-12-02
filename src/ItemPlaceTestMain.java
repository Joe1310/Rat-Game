
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.scene.image.*;
import javafx.stage.Stage;

import java.util.EventListener;
import java.util.ArrayList;


public class ItemPlaceTestMain extends Application{
	// The dimensions of the window
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 400;

	// The dimensions of the canvas
	private static final int CANVAS_WIDTH = 400;
	private static final int CANVAS_HEIGHT = 400;
		
	// The canvas in the GUI. This needs to be a global variable
	// (in this setup) as we need to access it in different methods.
	// We could use FXML to place code in the controller instead.
	private Canvas canvas;
		
	public void start(Stage primaryStage) {
		// Build the GUI 
		Pane root = buildGUI();	
		
		// Create a scene from the GUI
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		// Display the scene on the stage
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}


	private Pane buildGUI() {
		// Create top-level panel that will hold all GUI
		BorderPane root = new BorderPane();
				
		// Create canvas
		canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Entity.setGC(gc);
		root.setCenter(canvas);
		
		
		Image gasImg = new Image("Gas2.png");
		ImageView gasButton = new ImageView(gasImg);
		gasButton.setX(WINDOW_WIDTH - 50);
		gasButton.setY(0);
		
		Image deathRatImg = new Image("DeathRatN.png");
		ImageView deathRatButton = new ImageView(deathRatImg);
		deathRatButton.setX(WINDOW_WIDTH - 50);
		deathRatButton.setY(50);
		
		Image poisonImg = new Image("Poison.png");
		ImageView poisonButton = new ImageView(poisonImg);
		poisonButton.setX(WINDOW_WIDTH - 50);
		poisonButton.setY(100);
		
		Image sterilizationImg = new Image("Sterilization.png");
		ImageView sterilizationButton = new ImageView(sterilizationImg);
		sterilizationButton.setX(WINDOW_WIDTH - 50);
		sterilizationButton.setY(150);
		
		Image femaleSexChangeImg = new Image("FemaleSexChange.png");
		ImageView femaleSexChangeButton = new ImageView(femaleSexChangeImg);
		femaleSexChangeButton.setX(WINDOW_WIDTH - 50);
		femaleSexChangeButton.setY(200);
		
		Image maleSexChangeImg = new Image("MaleSexChange.png");
		ImageView maleSexChangeButton = new ImageView(maleSexChangeImg);
		maleSexChangeButton.setX(WINDOW_WIDTH - 50);
		maleSexChangeButton.setY(250);
		
		Image noEntrySignImg = new Image("NoEntrySign.png");
		ImageView noEntrySignButton = new ImageView(noEntrySignImg);
		noEntrySignButton.setX(WINDOW_WIDTH - 50);
		noEntrySignButton.setY(300);
		
		Image bombImg = new Image("Bomb.png");
		ImageView bombButton = new ImageView(bombImg);
		bombButton.setX(WINDOW_WIDTH - 50);
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
				gasButton.setX(WINDOW_WIDTH - 50);
				gasButton.setY(0);
				Entity gas = new Gas(getMouseLocation(event), false);
				gas.draw();
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
				deathRatButton.setX(WINDOW_WIDTH - 50);
				deathRatButton.setY(50);
				Entity deathRat = new DeathRatSpawner(getMouseLocation(event));
				deathRat.draw();
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
				poisonButton.setX(WINDOW_WIDTH - 50);
				poisonButton.setY(100);
				Entity poison = new Poison(getMouseLocation(event));
				poison.draw();
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
				sterilizationButton.setX(WINDOW_WIDTH - 50);
				sterilizationButton.setY(150);
				Entity sterilization = new Sterilization(getMouseLocation(event), false);
				sterilization.draw();
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
				femaleSexChangeButton.setX(WINDOW_WIDTH - 50);
				femaleSexChangeButton.setY(200);
				Entity femaleSexChange = new FemaleSexChange(getMouseLocation(event));
				femaleSexChange.draw();
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
				maleSexChangeButton.setX(WINDOW_WIDTH - 50);
				maleSexChangeButton.setY(250);
				Entity maleSexChange = new MaleSexChange(getMouseLocation(event));
				maleSexChange.draw();
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
				noEntrySignButton.setX(WINDOW_WIDTH - 50);
				noEntrySignButton.setY(300);
				Entity noEntrySign = new NoEntrySign(getMouseLocation(event));
				noEntrySign.draw();
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
				bombButton.setX(WINDOW_WIDTH - 50);
				bombButton.setY(350);
				Entity bomb = new Bomb(getMouseLocation(event));
				bomb.draw();
			}
		});
		

		return root;
	}

	private int[] getMouseLocation(MouseEvent event) {
		int x = (int)event.getSceneX()-100;
		int y = (int)event.getSceneY();
		
		x = ((x-(x%50))/50);
		y = ((y-(y%50))/50);
		
		int[] location = {x,y};

		return location;
	}
	

}
