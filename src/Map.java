import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class Map {
    private int itemSpawnRate;
    private int[] entityLocations;
    private int maxRat;
    private String mapName;
    private String[] tileLayout;
    private String mapInfo;

    /**
     *
     * @param mapName holds name of the map
     * @param tileLayout is a text file containing the tile layout
     * @param itemSpawnRate has item spawn rate
     * @param entityLocations has the locations of both rats and items in entity
     */
    public Map(String mapName, String[] tileLayout,int itemSpawnRate,
               int[] entityLocations, int maxRat) {
 //       File filename = new File(mapInfo);
 //       Scanner scan = null;

//        try {
//            scan = new Scanner(filename);
//        } catch (FileNotFoundException e) {
//            System.out.println("No File found");
//            e.printStackTrace();
//        }
//        assert scan != null;
//        mapInfo = mapLayout(scan)

        this.mapName = mapName;
        this.tileLayout = tileLayout;
        this.itemSpawnRate = itemSpawnRate;
        this.entityLocations = entityLocations;
        this.maxRat = maxRat;
    }

    /**
     *
     * @param x imports scanner
     * @return the tile layout
     */
    //Will read tileLayout
 /**
    public static String mapLayout(Scanner x) {
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
*/
    /**
     *
     */
    public void spawnEntity(String ent) {
    }
}
