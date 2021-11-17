public class Tile {
    private String tileType;
    private int x;
    private int y;
    private String[] movementOptions;

    public Tile(String tileType, int x, int y){
        this.x = x;
        this.y = y;
        this.tileType = tileType;
        movementOptions = getMovementOptions();
    }

    public static String[] getMovementOptions(){
        return null;
    }

}
