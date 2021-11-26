public class DeathRatSpawner extends Entity {
    private int countdown;

    public DeathRatSpawner(int[] location) {
        super(location, "DeathRat.png");
        this.countdown = 6;
    }
    public DeathRatSpawner(int[] location, int countdown) {
        super(location, "DeathRat.png");
        this.countdown = countdown;
    }

    public void act() {
        if (countdown == 0) {
            Entity deathRat = new DeathRat(1, 1, true, this.location, "n");
            removeEntity();
        }
        countdown -= 1;
    }

}
