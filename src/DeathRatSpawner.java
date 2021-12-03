public class DeathRatSpawner extends Entity {
    private int countdown;

    public DeathRatSpawner(int[] location) {
        super(location, "DeathRatN.png", "DRS");
        this.countdown = 6;
    }
    
    public DeathRatSpawner(int[] location, int countdown) {
        super(location, "DeathRatN.png", "DRS");
        this.countdown = countdown;
    }

    public void act() {
    	if (countdown == 0) {
            Entity deathRat = new DeathRat(1000, true, this.location, "N");
            removeEntity();
        } else {
        	countdown--;
        }
    }
}
