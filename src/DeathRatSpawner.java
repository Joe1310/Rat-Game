public class DeathRatSpawner extends Entity {
    private int countdown;

    public DeathRatSpanwer(int[] location) {
        super(location, "DeathRat.png");
        this.countdown = 3;
    }
    public DeathRatSpanwer(int[] location, countdown) {
        super(location, "DeathRat.png");
        this.countdown = countdown;
    }

    public void act() {
        if (countdown == 0) {
            Entity deathRat = new DeathRat(1, 1, true, this.location, "n", 1, "DeathRat.png");
            removeEntity();
        }
        countdown -= 1;
    }

}
