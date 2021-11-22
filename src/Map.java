public class Map {
    private int itemSpawnRate;
    private int[] entityLocations;
    private int maxRat;

    private String[] tileLayout;


    /**
     *
     * @param tileLayout is a text file containing the tile layout
     * @param itemSpawnRate has item spawn rate
     * @param entityLocations has the locations of both rats and items in entity
     */
    public Map(String[] tileLayout,int itemSpawnRate,
               int[] entityLocations, int maxRat) {

        this.tileLayout = tileLayout;
        this.itemSpawnRate = itemSpawnRate;
        this.entityLocations = entityLocations;
        this.maxRat = maxRat;
    }

    /**
     *
     */
    public int time = 0;
    long t0, t1;

    public void entityTick(int[] args) {
        for (int i = 2; i < 1; i++){
            t0 = System.currentTimeMillis();
            do {
                t1 = System.currentTimeMillis();
            }
            while (t1 - t0 < 500);
            time = time + 1;
        }
    }
}
