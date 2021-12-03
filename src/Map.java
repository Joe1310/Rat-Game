import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Map {
    int maxRat;
    static Tile[][] tileLayout;
    public int tick;
    final int GRID_SIZE = 50;
    private int row;
    private int column;
    Image grassImage;
    Image pathImage;
    Image tunnelImage;
    static GraphicsContext gc; //TEST PROBABLY NOT A GOOD IDEA

    /**
     *
     * @param tileLayout
     * @param maxRat
     */
    public Map(Tile[][] tileLayout, int x, int y, int maxRat) {
        this.row = x;
        this.column = y;
        this.tileLayout = tileLayout;
        this.maxRat = maxRat;
    }

    public Tile[][] tileOut(GraphicsContext gc) {
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
        return Map.tileLayout;
    }
    
    public Tile[][] tunnelOut(GraphicsContext gc) {
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
        return Map.tileLayout;
    }

    /**
     *
     * @return Tick which increases by 1 every 0.5 seconds
     */
    public int entityTick() {
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        int tick = 0;
        exec.scheduleWithFixedDelay(new Runnable() {
            public void run() {
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
                	if (Entity.getEntities().size() > 0) {
                		ents.get(i).act();
                	}
                }
                if (Rat.wait == 1) {
                	Rat.wait--;
                } else {
                	Rat.wait = 1;
                }
                System.out.println(Rat.rats.size());
            }
            
        }, 0, 250, TimeUnit.MILLISECONDS);
        return tick;
    }
    
    /*
     * REMOVE THIS PROBABLY
     */
    public static void setGC(GraphicsContext grc) {
    	gc = grc;
    }
    
    public static char getTileType(int x, int y) {
    	return Map.tileLayout[x][y].getTileType();
    }

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
