/**
 *
 */
public class Map {
    private String[][] tileLayout;
    private int itemSpawnRate;
    private int[] ratLocations;
    private int maxRat;
    private String mapName;

    /**
     *
     * @param mapName contains map name
     * @param tileLayout holds tile layout in two-dimensional array
     * @param ratLocations holds rat locations
     * @param itemSpawnRate holds spawn rate for items
     * @param maxRat contains max number of rats
     */
    public Map(String mapName, String[][] tileLayout, int[] ratLocations,int itemSpawnRate, int maxRat){
        this.mapName = getCurrentMap();
        this.tileLayout = tileLayout;
        this.ratLocations = getRatSpawn();
        this.itemSpawnRate = getSpawnRate(itemSpawnRate);
        this.maxRat = maxRat;
    }

    /**
     *
     * @param tileLayout holds tile layout in two-dimensional array
     * @return the layouts of the tiles on the map
     */
    private static String[][] getTileLayout(String[][] tileLayout) {
        return tileLayout;
    }

    /**
     *
     * @return rat locations
     */
    private static int[] getRatSpawn() {
        return null;
    }

    /**
     *
     * @param x holds spawn rate
     * @return spawn rate
     */
    private static int getSpawnRate(int x) {
        return x;
    }

    /**
     *
     * @return name of the current map
     */
    public String getCurrentMap() {
        return null;
    }

    /**
     *
     */
    public void spawnEntity() {

    }
}
