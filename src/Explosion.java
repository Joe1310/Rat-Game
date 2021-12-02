import java.util.Arrays;

public class Explosion extends Entity {
	private int wait = 1;

    public Explosion(int[] location) {
        super(location, "Explosion.png", "Explosion");
    }

    public void act() {
    	killRats();
    	wait--;
    	if (wait == 0) {
    		removeEntity();
    	}
    }
    
	private void killRats() {
		for (int i = Rat.getRats().size()-1; i > -1; i--) {
			if (Arrays.equals(Rat.getRats().get(i).location, this.location) && 
					!(Rat.getRats().get(i).getRatType().equals("DeathRat"))) {
				(Rat.getRats().get(i)).ratDeath();
				removeEntity();
	        }
        }
	}
    
    
    public String toString() {
    	return ("Explosion");
    }
}
