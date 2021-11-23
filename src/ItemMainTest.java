import java.util.Random;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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

	// The size to draw the shapes
	private static final int SHAPE_SIZE = 30;
	
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
		
		Button noEntryButton = new Button("NoEntrySign");
		sidebar.getChildren().addAll(noEntryButton);

		noEntryButton.setMaxWidth(Double.MAX_VALUE);
		
		noEntryButton.setOnAction(e -> {
			createNES();
		});
		
		return root;
	}
	
	private void createNES() {
		// Create a new random number generator
		Random r = new Random();
		
		// Create a new coordinate
		int x = r.nextInt(CANVAS_WIDTH);
		int y = r.nextInt(CANVAS_HEIGHT);
		int[] location = new int[2];
		location[0] = x;
		location[1] = y;
		
		// Access the graphic context of the canvas
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		Entity nes = new NoEntrySign(location);
		nes.draw(gc);
	}
}
	