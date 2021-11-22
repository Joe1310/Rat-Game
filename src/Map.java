import java.util.ArrayList;


public class Map {
    private final int itemSpawnRate;
    private int[] entityLocations;
    private final int maxRat;
    private String mapName;
    public static Tile[][] tileLayout;
    private String mapInfo;

    /**
     *
     * @param mapName holds name of the map
     * @param tileLayout is a text file containing the tile layout
     * @param itemSpawnRate has item spawn rate
     * @param entityLocations has the locations of both rats and items in entity
     */
    public Map(String mapName, Tile[][] tileLayout,int itemSpawnRate,
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
        Map.tileLayout = tileLayout;
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

    public static ArrayList<String> getMovementOptions(int x, int y){
        ArrayList<String> movementOptions = new ArrayList<String>();
        if(tileLayout[y][x + 1].getTileType().equals("Path") ||
                tileLayout[y][x + 1].getTileType().equals("Tunnel")){
            movementOptions.add("right");
        } else if(tileLayout[y][x - 1].getTileType().equals("Path") ||
                tileLayout[y][x - 1].getTileType().equals("Tunnel")){
            movementOptions.add("left");
        } else if(tileLayout[y - 1][x].getTileType().equals("Path") ||
                tileLayout[y - 1][x].getTileType().equals("Tunnel")){
            movementOptions.add("up");
        } else if(tileLayout[y + 1][x].getTileType().equals("Path") ||
                tileLayout[y + 1][x].getTileType().equals("Tunnel")){
            movementOptions.add("down");
        }
        return movementOptions;
    }
}
