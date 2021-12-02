
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
import javafx.stage.Stage;


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
		root.setCenter(canvas);
		
		// Create a siderbar with some nice padding and spacing
		VBox sidebar = new VBox();
		sidebar.setSpacing(10);
		sidebar.setPadding(new Insets(50, 10, 0, 0)); 
		root.setRight(sidebar);
		
		Button gasButton = new Button("Gas");
		Button deathRatButton = new Button("Death Rat");
		Button poisonButton = new Button("Poison");
		Button sterilizationButton = new Button("Sterilization");
		Button femaleSexChangeButton = new Button("Female Sex change");
		Button maleSexChangeButton = new Button("Male Sex Change");
		Button noEntrySignButton = new Button("No Entry Sign");
		Button bombButton = new Button("Bomb");
		
		sidebar.getChildren().addAll(gasButton, deathRatButton, poisonButton, sterilizationButton, femaleSexChangeButton, maleSexChangeButton, noEntrySignButton, bombButton);
		
		
		gasButton.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				
				GraphicsContext gc = canvas.getGraphicsContext2D();

				Entity gas = new Gas(getMouseLocation(event), false);
				gas.draw();
			}
		});
		
		deathRatButton.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				
				GraphicsContext gc = canvas.getGraphicsContext2D();

				Entity deathRat = new DeathRatSpawner(getMouseLocation(event));
				deathRat.draw();
			}
		});
		
		poisonButton.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				
				GraphicsContext gc = canvas.getGraphicsContext2D();

				Entity poison = new Poison(getMouseLocation(event));
				poison.draw();
			}
		});


		femaleSexChangeButton.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				
				GraphicsContext gc = canvas.getGraphicsContext2D();

				Entity femaleSexChange = new FemaleSexChange(getMouseLocation(event));
				femaleSexChange.draw();
			}
		});
		
		maleSexChangeButton.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				
				GraphicsContext gc = canvas.getGraphicsContext2D();

				Entity maleSexChange = new MaleSexChange(getMouseLocation(event));
				maleSexChange.draw();
			}
		});
		
		noEntrySignButton.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				
				GraphicsContext gc = canvas.getGraphicsContext2D();

				Entity noEntrySign = new NoEntrySign(getMouseLocation(event));
				noEntrySign.draw();
			}
		});
		
		bombButton.setOnMouseReleased(new EventHandler<MouseEvent>() {		
			public void handle(MouseEvent event) {
				
				GraphicsContext gc = canvas.getGraphicsContext2D();

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
