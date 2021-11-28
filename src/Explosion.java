public class Explosion extends Entity {
	private int wait = 2;

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
