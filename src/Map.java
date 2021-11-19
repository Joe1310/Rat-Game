import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class Map {
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

    /**
    public Map.txt(String mapName, String[][] tileLayout, int[] ratLocations,int itemSpawnRate, int maxRat){
        this.mapName = getCurrentMap();
        this.tileLayout = tileLayout;
        this.ratLocations = getRatSpawn();
        this.itemSpawnRate = getSpawnRate(itemSpawnRate);
        this.maxRat = maxRat;


    }
     */
    public static void main(String[] args) {

        new Map("Map.txt");

    }

    public Map(String mapInfo) {
        File filename = new File(mapInfo);
        Scanner scan = null;

        try {
            scan = new Scanner(filename);
        } catch (FileNotFoundException e) {
            System.out.println("No File found" + filename);
            e.printStackTrace();
        }
        assert scan != null;
        System.out.println(mapData(scan));


    }

    /**
     *
     * @param x imports scanner
     * @return the tile layout
     */
    public static String mapData(Scanner x) {
        String[] tileLayout;
        String map = "";

        map += "Tile Layout: ";
        while (x.hasNextLine()) {
            tileLayout = x.nextLine().split(" ");
            map += "\n" + (Arrays.toString(tileLayout));
        }
        x.close();

        return map;
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
