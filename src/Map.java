import java.util.ArrayList;


public class Map {
    private int itemSpawnRate;
    private int[] entityLocations;
    private int maxRat;
    public static Tile[][] tileLayout;

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
        time = entityTick();
    }

    public int time = 0;
    long t0, t1;

    /**
     *
     * @return the time which increases by one every 100 millisecond
     */
    public int entityTick() {
        for (int i = 2; i < 1; i++) {
            t0 = System.currentTimeMillis();
            do {
                t1 = System.currentTimeMillis();
            }
            while (t1 - t0 < 100);
            time = time + 1;
        }
        return time;
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
