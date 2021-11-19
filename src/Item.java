public class Item extends Entity{
    private int quantity;
    private int spawnRate;

    /**
     *
     * @param quantity The number of this item
     * @param spawnRate The rate at which this item is increased
     */
    private Item(int quantity, int spawnRate) {
        this.quantity = quantity;
        this.spawnRate = spawnRate;
    }

    private void increaseItem() {
        this.quantity += 1;
    }

    public void useItem() {
        this.quantity -= 1;
    }

    public int getItems() {
        return this.quantity;
    }

}
