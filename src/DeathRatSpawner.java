/**
 * Class to model a DeathRatSpawner entity.
 *
 * @author Elliot, Ollie
 * @version 1.0
 */
public class DeathRatSpawner extends Entity {
    private int countdown;

    /**
     * Constructor to create the DeathRatSpawner object.
     *
     * @param location The x, y coordinates of the object.
     */
    public DeathRatSpawner(int[] location) {
        super(location, "DeathRatN.png", "DRS");
        this.countdown = 6;
    }

    /**
     * Constructor to create a DeathRatSpawner object from a saved game file.
     *
     * @param location The x, y coordinates of the object.
     * @param countdown The time remaining before the spawner creates a deathRat.
     */
    public DeathRatSpawner(int[] location, int countdown) {
        super(location, "DeathRatN.png", "DRS");
        this.countdown = countdown;
    }

    /**
     * Method to run the actions of the entity every tick.
     */
    public void act() {
    	if (countdown == 0) {
            Entity deathRat = new DeathRat(this.location, "N");
            removeEntity();
        } else {
        	countdown--;
        }
    }

    /**
     * Method to return the attribute values of the DeathRatSpawner entity.
     *
     * @return Returns attribute values as single String.
     */
    public String toString() {
    	return (this.getType() + " " + location[0] + " " + location[1] + " " + countdown);
    }
}
