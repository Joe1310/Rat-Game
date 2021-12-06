/**
 * Class to model a tile for the map.
 *
 * @author Joe, Jay
 * @version 1.0
 */
public class Tile {
    private final char tileType;

    /**
     * Constructor to create a tile entity.
     *
     * @param tileType The type of tile (Grass, Tunnel, Path).
     * @param x        x coordinate of the tile.
     * @param y        y coordinate of the tile.
     */
    public Tile(char tileType, int x, int y) {
        this.tileType = tileType;
    }

    /**
     * Method to get the tile type associated with the tile.
     *
     * @return Tile type associated with the tile.
     */
    public char getTileType() {
        return this.tileType;
    }
}
