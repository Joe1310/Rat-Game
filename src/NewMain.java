import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class NewMain extends Application {
    // The dimensions of the canvas
    private static final int TILE_SIZE = 50;
    private static final int CANVAS_WIDTH = 1850;
    private static final int CANVAS_HEIGHT = 1000;

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
        quitButton.setOnAction(event -> {
            menuWindow.close();
        });
        VBox container = new VBox(startButton, loadButton, highScoresButton, quitButton);
        //Style container
        container.setSpacing(15);
        container.setPadding(new Insets(25));
        //Set view in window
        menuWindow.setScene(new Scene(container));
        //Launch
        menuWindow.show();
    }

    private void highScores(Stage primaryStage) {
    }

    private void load(Stage primaryStage) {
    }

    public void play(Stage primaryStage){
        Stage levelMenuWindow = new Stage();
        levelMenuWindow.setTitle("Rat Game");
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

    public void level1(){
        Stage level1Stage = new Stage();
        level1Stage.setTitle("RAT GAME : LVL1");
        Pane root = buildGUI();
        Scene level1Scene = new Scene(root);
        drawGame("level.txt");
        level1Stage.setScene(level1Scene);
        level1Stage.show();
    }

    public void level2(){
        Stage level2Stage = new Stage();
        level2Stage.setTitle("RAT GAME : LVL2");
        Pane root = buildGUI();
        Scene level1Scene = new Scene(root);
        drawGame("level2.txt");
        level2Stage.setScene(level1Scene);
        level2Stage.show();
    }

    private Pane buildGUI() {
        // Create top-level panel that will hold all GUI nodes.
        BorderPane root = new BorderPane();

        // Create the canvas that we will draw on.
        // We store this as a global variable so other methods can access it.
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
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
