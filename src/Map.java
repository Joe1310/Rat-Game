import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;




public class Map {
    int maxRat;
    static Tile[][] tileLayout;
    public int tick;
    private Canvas canvas;
    final int GRID_CELL_WIDTH = 50;
    final int GRID_CELL_HEIGHT = 50;
    Image grassImage;
    Image dirtImage;
    Image tunnelImage;

    /**
     *
     * @param tileLayout
     * @param maxRat
     */
    public Map(Tile[][] tileLayout, int maxRat) {

        this.tileLayout = tileLayout;
        this.maxRat = maxRat;
        tick = entityTick();
    }

    public Tile[][] tileOut(Tile[][] x) {
        grassImage = new Image("GrassTile(1).png");
        tunnelImage = new Image("Tunnel.png");
        dirtImage = new Image("Dirt.png");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int tempY = 0;

        for (int i = 0; i < (x.length); i++) {
            for (int j = 0; j < x.length; j++) {
                Image tileType = null;
                if (x[i][j].getTileType() == 'G') {
                    tileType = grassImage;
                } else if (x[i][j].getTileType() == 'T') {
                    tileType = tunnelImage;
                } else if (x[i][j].getTileType() == 'D') {
                    tileType = dirtImage;
                }
                gc.drawImage(tileType, i * GRID_CELL_WIDTH, tempY * GRID_CELL_HEIGHT);
                tempY += 1;
            }
        }
        System.out.println(Arrays.toString(x));
        return x;
    }


    /**
     *
     * @return Tick which increases by 1 every 0.5 seconds
     */
    public static int entityTick() {
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        int tick = 0;
        exec.scheduleWithFixedDelay(new Runnable() {
            private int tick = 0;

            public void run() {
                tick++;
                System.out.println(tick);
            }
        }, 0, 500, TimeUnit.MILLISECONDS);
        return tick;
    }

    public static ArrayList<String> getMovementOptions(int x, int y){
        ArrayList<String> movementOptions = new ArrayList<String>();
        if(tileLayout[x + 1][y].getTileType() == 'p' ||
                tileLayout[x + 1][y].getTileType() == 't'){
            movementOptions.add("e");
        } else if(tileLayout[x - 1][y].getTileType() == 'p' ||
                tileLayout[x - 1][y].getTileType() == 't'){
            movementOptions.add("w");
        } else if(tileLayout[x][y - 1].getTileType() == 'p' ||
                tileLayout[x][y - 1].getTileType() == 't'){
            movementOptions.add("n");
        } else if(tileLayout[x][y + 1].getTileType() == 'p' ||
                tileLayout[x][y + 1].getTileType() == 't'){
            movementOptions.add("s");
        }
        return movementOptions;
    }

}
