/**
 * <p> 1. File name: Poison.java</p>
 * <p> 3. Creation date: 08.11.2021</p>
 * <p> 4. Last modification date: 05.12.2021</p>
 * <p> 6. Copyright notice: group 02 - CS230 - Swansea University - 2021/22</p>
 * <p> 7. Purpose of the program: damage rat's health with poison behavior & characteristics</p>
 * <p> 8. Version history: 1.0 - pure code; 2.0 - comment added</p>
 * @author Raj, Nick, Elliot, Oliver, Joe, Jay, Shivraj & Patel
 */

import java.util.Arrays;

public class Poison extends Entity {
	
	public Poison(int[] location) {
		super(location, "Poison.png", "Poison");
	}
	
	public void act() {
		killRats();
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
    	return (this.getType() + " " + location[0] + " " + location[1]);
    }
}