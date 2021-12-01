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
        grassImage = new Image("Grass.png");
        tunnelImage = new Image("Tunnel.png");
        pathImage = new Image("PathTile.png");

        for (int i = 0; i < (row); i++) {
            for (int j = 0; j < column; j++) {
                Image tileImage = null;
                if (Map.tileLayout[i][j].getTileType() == 'G') {
                    tileImage = grassImage;
                } else if (Map.tileLayout[i][j].getTileType() == 'T') {
                    tileImage = pathImage;
                } else if (Map.tileLayout[i][j].getTileType() == 'P') {
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

                //for loop for drawing
                for (Entity ent : Entity.getEntities()) {
                    ent.draw();
                }
                //for loop for acting
                for (int i = maxEntities-1; i >= 0; i--) {
                    if (maxEntities != i) {
                        ents.get(i).act();
                    }
                }
            }
            
        }, 0, 500, TimeUnit.MILLISECONDS);
        return tick;
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
