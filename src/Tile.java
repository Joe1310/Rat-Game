import java.util.ArrayList;

/**
 *
 */
public class Tile {
    private final char tileType;
    private final int x;
    private final int y;

    /**
     * @param tileType The type of tile (Grass, Tunnel, Path).
     * @param x x coordinate of the tile.
     * @param y y coordinate of the tile.
     */
    public Tile(char tileType, int x, int y){
        this.x = x;
        this.y = y;
        this.tileType = tileType;
    }

    /**
     * @return return a list of movement options from that tile.
     */
    public ArrayList<String> getMovementOptions() {
        return Map.getMovementOptions(x, y);
    }

    public char getTileType(){
        return this.tileType;
    }
}
