import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class NewMain extends Application {
    // The dimensions of the canvas
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 400;

    // Timeline which will cause tick method to be called periodically.
    private static Timeline tickTimeline;

    @Override
    public void start(Stage primaryStage) throws IOException {

        Stage newWindow = new Stage();
        newWindow.setTitle("Rat Game");
        //Create view in Java
        Button startButton = new Button("START");
        Button loadButton = new Button("LOAD");
        Button highScoresButton = new Button("HIGH SCORES");
        Button quitButton = new Button("QUIT");
        startButton.setOnAction(event -> {
            newWindow.close();
            play(primaryStage);
        });
        loadButton.setOnAction(event -> {
            newWindow.close();
            load(primaryStage);
        });
        highScoresButton.setOnAction(event -> {
            newWindow.close();
            highScores(primaryStage);
        });
        quitButton.setOnAction(event -> {
            newWindow.close();
        });
        VBox container = new VBox(startButton, loadButton, highScoresButton, quitButton);
        //Style container
        container.setSpacing(15);
        container.setPadding(new Insets(25));
        //Set view in window
        newWindow.setScene(new Scene(container));
        //Launch
        newWindow.show();
    }

    private void highScores(Stage primaryStage) {
    }

    private void load(Stage primaryStage) {
    }

    public void play(Stage primaryStage){
        Pane root = buildMap();
        Scene gameScene = new Scene(root);
        primaryStage.setScene(gameScene);
        primaryStage.show();
    }

    /**
     * Create the GUI.
     * @return The panel that contains the created GUI.
     */
    private Pane buildMap() {
        // Create top-level panel that will hold all GUI nodes.
        BorderPane root = new BorderPane();

        // Create the canvas that we will draw on.
        // We store this as a global variable so other methods can access it.
        // The canvas in the GUI. This needs to be a global variable
        // (in this setup) as we need to access it in different methods.
        // We could use FXML to place code in the controller instead.
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.setCenter(canvas);

        // Create a toolbar with some nice padding and spacing
        HBox toolbar = new HBox();
        toolbar.setSpacing(10);
        toolbar.setPadding(new Insets(10, 10, 10, 10));
        root.setTop(toolbar);

        // Finally, return the border pane we built up.
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
