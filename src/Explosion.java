import java.util.Arrays;

public class Explosion extends Entity {
	private int wait = 1;

    public Explosion(int[] location) {
        super(location, "Explosion.png", "Explosion");
    }

    public void act() {
    	explodeRats();
    	explodeItems();
    	wait--;
    	if (wait == 0) {
    		removeEntity();
    	}
    }
    
    private void explodeRats() {
		for (int i = Rat.getRats().size()-1; i > -1; i--) {
			if (Arrays.equals(Rat.getRats().get(i).location, this.location)) {
				Rat.getRats().get(i).ratDeath();
	        }
        }
	}
    
    private void explodeItems() {
    	for (int i = Entity.getEntities().size()-1; i > -1; i--) {
			if (Arrays.equals(Entity.getEntities().get(i).location, this.location) && 
					(Entity.getEntities().get(i) != this)) {
				Entity.getEntities().get(i).removeEntity();
	        }
        }
    }
    
    public String toString() {
    	return (this.getType() + " " + location[0] + " " + location[1]);
    }
}
