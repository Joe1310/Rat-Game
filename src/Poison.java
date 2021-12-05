import java.util.Arrays;

/**
 * Class to model the Poison item.
 *
 * @author Elliot, Ollie
 * @version 1.0
 */
public class Poison extends Entity {

	/**
	 * Constructor to create a poison object.
	 *
	 * @param location The x, y coordinates of the poison object.
	 */
	public Poison(int[] location) {
		super(location, "Poison.png", "Poison");
	}

	/**
	 * Method to run the actions of the entity every tick.
	 */
	public void act() {
		killRats();
	}

	/**
	 * Method to kill rats that enter the gas.
	 */
	private void killRats() {
		for (int i = Rat.getRats().size()-1; i > -1; i--) {
			if (Arrays.equals(Rat.getRats().get(i).location, this.location) && 
					!(Rat.getRats().get(i).getRatType().equals("DeathRat"))) {
				(Rat.getRats().get(i)).ratDeath();
				removeEntity();
	        }
        }
	}

	/**
	 * Method to return the attribute values of the Poison entity.
	 *
	 * @return Returns attribute values as single String.
	 */
	public String toString() {
    	return (this.getType() + " " + location[0] + " " + location[1]);
    }
}