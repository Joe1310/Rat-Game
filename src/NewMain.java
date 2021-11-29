import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class NewMain extends Application {
    // The dimensions of the canvas
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 400;

    // Timeline which will cause tick method to be called periodically.
    private static Timeline tickTimeline;

    // The width and height (in pixels) of each cell that makes up the game.
    private static final int GRID_CELL_WIDTH = 50;
    private static final int GRID_CELL_HEIGHT = 50;

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
            //level2(primaryStage);
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

    public void level1(){
        Stage level1Stage = new Stage();
        level1Stage.setTitle("RAT GAME : LVL1");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
