import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to model the sterilisation item.
 *
 * @author Elliot, Ollie
 * @version 1.0
 */
class Sterilisation extends Entity {
    private int timer = 15;

    /**
     * Constructor to create a sterilisation object.
     *
     * @param location The x, y coordinates that the sterilisation object is to be placed at.
     */
    public Sterilisation(int[] location) {
        super(location, "Sterilisation.png", "Steriliser");
    }

    /**
     * Constructor for sterilisation objects pulled from saved game files (sets time remaining to what
     * it was when the game was saved).
     *
     * @param location The x, y coordinates that the sterilisation object is to be placed at.
     * @param timer Time left before the sterilisation object is deleted.
     */
    public Sterilisation(int[] location, int timer) {
        super(location, "Sterilisation.png", "Steriliser");
        this.timer = timer;
    }

    /**
     * Method to cause the sterilisation object to sterilise a rat and then delete itself.
     */
    public void act() {
        steriliseRats();
        disappear();
    }

    /**
     * Method to cause the sterilisation object to delete itself if the timer is finished
     */
    public void disappear() {
        timer--;
        if (timer == 0) {
            removeEntity();
        }
    }

    /**
     *
     */
    private void steriliseRats() {
    	int[] corner = {this.location[0] - 1, this.location[1] - 1}; //Sets the radius to northwest of the steriliser
        for (int i = Rat.getRats().size()-1; i > -1; i--) {
        	for(int k = corner[1]; k <= (this.location[1] + 1); k++) {
        		for(int j = corner[0]; j <= (this.location[0] + 1); j++) {
        			int [] testLocation = {j, k};
        			if (checkRat(testLocation, i)) {
        				Rat.getRats().get(i).sterilise();
        			}
        		}
        	}
        }
    }

    /**
     * Method to
     *
     * @param tempLocation
     * @param index
     * @return
     */
    private boolean checkRat(int[] tempLocation, int index) {
    	if (Arrays.equals(Rat.getRats().get(index).location, tempLocation)) {
			return true;
        } else {
        	return false;
        }
    }
    
    public String toString() {
    	return (getType() + " " + location[0] + " " + location[1] + " " + timer);
    }

}