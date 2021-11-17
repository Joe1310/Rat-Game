/**
 *
 */
public class Tile {
    private String tileType;
    private int x;
    private int y;
    private String[] movementOptions;

    /**
     * @param tileType The type of tile (Grass, Tunnel, Path).
     * @param x x coordinate of the tile.
     * @param y y coordinate of the tile.
     */
    public Tile(String tileType, int x, int y){
        this.x = x;
        this.y = y;
        this.tileType = tileType;
        movementOptions = getMovementOptions();
    }

    /**
     * @return return a list of movement options from that tile.
     */
    public static String[] getMovementOptions(){
        return null;
    }

}
