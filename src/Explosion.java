public class Explosion extends Entity {
	private int wait = 1;

    public Explosion(int[] location) {
        super(location, "Explosion.png");
    }

    public void act() {
    	wait--;
    	if (wait == 0) {
    		removeEntity();
    	}
    }
    
    public String toString() {
    	return ("Explosion");
    }
}
