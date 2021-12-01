import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

/**
 * Sample application that demonstrates the use of JavaFX Canvas
 * @author Liam O'Reilly
 *
 */
public class ItemMainTest extends Application {
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
		root.setCenter(canvas);
		
		// Create a siderbar with some nice padding and spacing
		VBox sidebar = new VBox();
		sidebar.setSpacing(10);
		sidebar.setPadding(new Insets(10, 10, 10, 10)); 
		root.setLeft(sidebar);
		
		Button tick = new Button("Tick");
		Button noEntryButton = new Button("NoEntrySign");
		Button bombButton = new Button("Bomb!");
		
		sidebar.getChildren().addAll(tick,noEntryButton,bombButton);

		noEntryButton.setMaxWidth(Double.MAX_VALUE);
		
		noEntryButton.setOnAction(e -> {
			createNES();
		});
		
		tick.setOnAction(e -> {
			tick();
		});
		
		bombButton.setOnAction(e -> {
			createBomb();
		});
		
		return root;
	}
	
	private void createNES() {
		// Create a new random number generator
		Random r = new Random();
		
		// Create a new coordinate
		int x = r.nextInt(8);
		int y = r.nextInt(8);
		int[] location = new int[2];
		location[0] = x;
		location[1] = y;
		
		// Access the graphic context of the canvas
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		Entity nes = new NoEntrySign(location);
		nes.draw();
	}
	
	private void createBomb() {
		// Create a new random number generator
				Random r = new Random();
				
				// Create a new coordinate
				int x = r.nextInt(8);
				int y = r.nextInt(8);
				int[] location = {x,y};
				
				// Access the graphic context of the canvas
				GraphicsContext gc = canvas.getGraphicsContext2D();
				
				Entity bomb = new TestBomb(location);
				bomb.draw();
	}
	
	private void tick() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		ArrayList<Entity> ents = Entity.getEntities();
		int maxEntities = ents.size();
		
		//for loop for acting
		for (int i = maxEntities-1; i > -1; i--) {
			if (maxEntities != i) {
				ents.get(i).act();
				maxEntities = ents.size();
			}
		}
		//for loop for drawing
		for (Entity ent : Entity.getEntities()) {
			ent.draw();
		}
	}
}
	