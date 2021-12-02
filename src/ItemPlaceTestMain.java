
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
		
		Image image8 = new Image("Bomb.png");
		ImageView img8 = new ImageView(image8);
		img8.setX(100);
		img8.setY(100);
		
		root.getChildren().addAll(img8);
		
		// Create a scene from the GUI
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		img8.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {	
				img8.setX((int)event.getSceneX());
				img8.setY((int)event.getSceneY());
			}
		});
			
		img8.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				img8.setX(100);
				img8.setY(100);
				Entity bomb = new Bomb(getMouseLocation(event));
				bomb.draw();
			}
		});
		
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
		root.setCenter(canvas);
		
		// Create a sidebar with some nice padding and spacing
		VBox sidebar = new VBox();
		sidebar.setSpacing(10);
		sidebar.setPadding(new Insets(0, 10, 0, 0)); 
		root.setRight(sidebar);
		
		
		Image image = new Image("Gas2.png");
		ImageView img = new ImageView(image);
		
		Image image1 = new Image("DeathRat.png");
		ImageView img1 = new ImageView(image1);
		
		Image image2 = new Image("Poison.png");
		ImageView img2 = new ImageView(image2);
		
		Image image3 = new Image("Sterilization.png");
		ImageView img3 = new ImageView(image3);
		
		Image image4 = new Image("FemaleSexChange.png");
		ImageView img4 = new ImageView(image4);
		
		Image image5 = new Image("MaleSexChange.png");
		ImageView img5 = new ImageView(image5);
		
		Image image6 = new Image("NoEntrySign.png");
		ImageView img6 = new ImageView(image6);
		
		Image image7 = new Image("Bomb.png");
		ImageView img7 = new ImageView(image7);
		
		
		Button gasButton = new Button("", img);
		Button deathRatButton = new Button("", img1);
		Button poisonButton = new Button("", img2);
		Button sterilizationButton = new Button("", img3);
		Button femaleSexChangeButton = new Button("", img4);
		Button maleSexChangeButton = new Button("", img5);
		Button noEntrySignButton = new Button("", img6);
		Button bombButton = new Button("", img7);
		
		sidebar.getChildren().addAll(gasButton, deathRatButton, poisonButton, sterilizationButton, femaleSexChangeButton, maleSexChangeButton, noEntrySignButton, bombButton);
		
		gasButton.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				Entity gas = new Gas(getMouseLocation(event), false);
				gas.draw();
			}
		});
		
		deathRatButton.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				Entity deathRat = new DeathRatSpawner(getMouseLocation(event));
				deathRat.draw();
			}
		});
		
		poisonButton.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				Entity poison = new Poison(getMouseLocation(event));
				poison.draw();
			}
		});
		
		sterilizationButton.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				Entity sterilization = new Sterilization(getMouseLocation(event));
				sterilization.draw();
			}
		});

		femaleSexChangeButton.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				Entity femaleSexChange = new FemaleSexChange(getMouseLocation(event));
				femaleSexChange.draw();
			}
		});
		
		maleSexChangeButton.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				Entity maleSexChange = new MaleSexChange(getMouseLocation(event));
				maleSexChange.draw();
			}
		});
		
		noEntrySignButton.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				Entity noEntrySign = new NoEntrySign(getMouseLocation(event));
				noEntrySign.draw();
			}
		});
		
		bombButton.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				Entity bomb = new Bomb(getMouseLocation(event));
				bomb.draw();
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
	

}
