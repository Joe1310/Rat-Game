/**
 * <p> 1. File name: Explosion.java</p>
 * <p> 3. Creation date: 08.11.2021</p>
 * <p> 4. Last modification date: 05.12.2021</p>
 * <p> 6. Copyright notice: group 02 - CS230 - Swansea University - 2021/22</p>
 * <p> 7. Purpose of the program: removing items and rats from the map</p>
 * <p> 8. Version history: 1.0 - pure code; 2.0 - comment added</p>
 * @author Raj, Nick, Elliot, Oliver, Joe, Jay, Shivraj & Patel
 */



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
