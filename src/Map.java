import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Map {
    private int itemSpawnRate;
    private int[] entityLocations;
    private int maxRat;
    public static Tile[][] tileLayout;

 //   public static void main(String[] args) {
 //       entityTick();
 //   }

    /**
     * @param tileLayout is a text file containing the tile layout
     * @param itemSpawnRate has item spawn rate
     * @param entityLocations has the locations of both rats and items in entity
     */
    public Map(Tile[][] tileLayout,int itemSpawnRate,
               int[] entityLocations, int maxRat) {


        Map.tileLayout = tileLayout;
        this.itemSpawnRate = itemSpawnRate;
        this.entityLocations = entityLocations;
        this.maxRat = maxRat;
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

            @Override
            public void run() {
                tick++;
                System.out.println(tick);
            }
        }, 0, 500, TimeUnit.MILLISECONDS);
        return tick;
    }

    public static ArrayList<String> getMovementOptions(int x, int y){
        ArrayList<String> movementOptions = new ArrayList<String>();
        if(tileLayout[x + 1][y].getTileType().equals("Path") ||
                tileLayout[x + 1][y].getTileType().equals("Tunnel")){
            movementOptions.add("e");
        } else if(tileLayout[x - 1][y].getTileType().equals("Path") ||
                tileLayout[x - 1][y].getTileType().equals("Tunnel")){
            movementOptions.add("w");
        } else if(tileLayout[x][y - 1].getTileType().equals("Path") ||
                tileLayout[x][y - 1].getTileType().equals("Tunnel")){
            movementOptions.add("n");
        } else if(tileLayout[x][y + 1].getTileType().equals("Path") ||
                tileLayout[x][y + 1].getTileType().equals("Tunnel")){
            movementOptions.add("s");
        }
        return movementOptions;
    }
}
