/**
 * <p> 1. File name: Map.java</p>
 * <p> 3. Creation date: 08.11.2021</p>
 * <p> 4. Last modification date: 05.12.2021</p>
 * <p> 6. Copyright notice: group 02 - CS230 - Swansea University - 2021/22</p>
 * <p> 7. Purpose of the program: Behavior of maps</p>
 * <p> 8. Version history: 1.0 - pure code; 2.0 - comment added</p>
 * @author Raj, Nick, Elliot, Oliver, Joe, Jay, Shivraj & Patel
 * @version 2.0
 */

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Class to model a Map of Tile objects
 *
 * @author Joe, Jay
 * @version 1.0
 */
public class Map {
    public int maxRat;
    private static Tile[][] tileLayout;
    private final int GRID_SIZE = 50;
    private int row;
    private int column;
    private Image grassImage;
    private Image pathImage;
    private Image tunnelImage;
    private int timer;
    public static int count;
    private static GraphicsContext gc;

    /**
     * Constructor to create a Map object
     *
     * @param tileLayout Layout of Tile objects in the map.
     * @param x Number of rows.
     * @param y Number of columns.
     * @param maxRat Maximum number of rats before the game is lost.
     */
    public Map(Tile[][] tileLayout, int x, int y, int maxRat) {
        this.row = x;
        this.column = y;
        Map.tileLayout = tileLayout;
        this.maxRat = maxRat;
    }

    /**
     * Method draw the tiles and return the layout.`
     *
     * @param gc Graphics context used to draw tiles.
     */
    public void tileOut(GraphicsContext gc) {
        grassImage = new Image("Grass2.png");
        tunnelImage = new Image("Tunnel.png");
        pathImage = new Image("PathTile.png");

        for (int i = 0; i < (row); i++) {
            for (int j = 0; j < column; j++) {
                Image tileImage = null;
                if (Map.tileLayout[j][i].getTileType() == 'G') {
                    tileImage = grassImage;
                } else if (Map.tileLayout[j][i].getTileType() == 'T') {
                    tileImage = tunnelImage;
                } else if (Map.tileLayout[j][i].getTileType() == 'P') {
                    tileImage = pathImage;
                }
                gc.drawImage(tileImage, j * GRID_SIZE, i * GRID_SIZE);
            }
        }
    }

    /**
     * Method to redraw the tunnels so that they are drawn over the rats.
     *
     * @param gc Graphics context used to draw tiles.
     */
    public void tunnelOut(GraphicsContext gc) {
        tunnelImage = new Image("Tunnel.png");

        for (int i = 0; i < (row); i++) {
            for (int j = 0; j < column; j++) {
                Image tileImage = null;
                if (Map.tileLayout[j][i].getTileType() == 'T') {
                    tileImage = tunnelImage;
                }
                gc.drawImage(tileImage, j * GRID_SIZE, i * GRID_SIZE);
            }
        }
    }

    /**
     * Method to continuously call the movement method on the rats, redraw the tiles and keep track of how
     * long the level has been played for.
     *
     * @return Tick which increases by 1 every 0.5 seconds
     */
    public int entityTick() {
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        int tick = 0;
        exec.scheduleWithFixedDelay(new Runnable() {
            public void run() {
            	drawCounter();
            	RenderBar.drawProgressBar(gc, column * 50, row * 50);
                ArrayList<Entity> ents = Entity.getEntities();
                int maxEntities = ents.size();
                tileOut(Map.gc); //Remove this too
                //for loop for drawing
                for (Entity ent : Entity.getEntities()) {
                    ent.draw();
                }
                tunnelOut(Map.gc);
                //for loop for acting
                for (int i = maxEntities-1; i >= 0; i--) {
                	if (ents.size() > 0) {
                		ents.get(i).act();
                	}
                	if (i > Entity.getEntities().size()) {
                		i = Entity.getEntities().size();
                	}
                }
                if (Rat.wait == 1) {
                	Rat.wait--;
                } else {
                    Rat.wait = 1;
                }
                timer++;

                if (timer % 4 == 0){
                    count++;
                }
                if (Rat.getRats().size() == 0) {
                    exec.shutdown();
                }else if (Rat.getRats().size() >= Level.getMap().maxRat) {
                    exec.shutdown();
                }
                if (Rat.getRats().size() == 0){
                    exec.shutdown();
                }
            }
        }, 0, 250, TimeUnit.MILLISECONDS);
        return tick;
    }

    /**
     * Method to draw the item counters to the screen.
     */
    public void drawCounter() {
    	gc.clearRect(((column + 1) * 50), 0, 50, 400);
    	gc.drawImage(new Image(Integer.toString(Inventory.getGas())+".png"),((column + 1) * 50), 0);
    	gc.drawImage(new Image(Integer.toString(Inventory.getDeathRat())+".png"),((column + 1) * 50), 50);
    	gc.drawImage(new Image(Integer.toString(Inventory.getPoison())+".png"),((column + 1) * 50), 100);
    	gc.drawImage(new Image(Integer.toString(Inventory.getSterilisation())+".png"),((column + 1) * 50), 150);
    	gc.drawImage(new Image(Integer.toString(Inventory.getFemaleSexChange())+".png"),((column + 1) * 50), 200);
    	gc.drawImage(new Image(Integer.toString(Inventory.getMaleSexChange())+".png"),((column + 1) * 50), 250);
    	gc.drawImage(new Image(Integer.toString(Inventory.getNoEntrySign())+".png"),((column + 1) * 50), 300);
    	gc.drawImage(new Image(Integer.toString(Inventory.getBomb())+".png"),((column + 1) * 50), 350);
    }

    /**
     * Method to set the graphics context that the map is drawn to.
     *
     * @param grc Graphics Context for the map.
     */
    public static void setGC(GraphicsContext grc) {
    	gc = grc;
    }

    /**
     * Method to get the tile type of a specific tile.
     *
     * @param x The x coordinate of the tile.
     * @param y The y coordinate of the tile.
     * @return Returns the tile type of the tile.
     */
    public static char getTileType(int x, int y) {
    	return Map.tileLayout[x][y].getTileType();
    }

    /**
     * Method to get the movement options from a specific tile.
     *
     * @param x The x coordinate of the tile.
     * @param y the y coordinate of the tile.
     * @return Returns the options to move (N - North, E - East, S - South, W - West) from the given tile location.
     */
    public static ArrayList<String> getMovementOptions(int x, int y){
        ArrayList<String> movementOptions = new ArrayList<String>();
        if(tileLayout[x + 1][y].getTileType() == 'P' ||
                tileLayout[x + 1][y].getTileType() == 'T'){
            movementOptions.add("E");
        } 
        if(tileLayout[x - 1][y].getTileType() == 'P' ||
                tileLayout[x - 1][y].getTileType() == 'T'){
            movementOptions.add("W");
        } 
        if(tileLayout[x][y - 1].getTileType() == 'P' ||
                tileLayout[x][y - 1].getTileType() == 'T'){
            movementOptions.add("N");
        } 
        if(tileLayout[x][y + 1].getTileType() == 'P' ||
                tileLayout[x][y + 1].getTileType() == 'T'){
            movementOptions.add("S");
        }
        return movementOptions;
    }
}
