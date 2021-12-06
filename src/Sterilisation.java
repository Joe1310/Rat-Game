import java.util.Arrays;

/**
 * Class to model the sterilisation item.
 *
 * @author Jay, Elliot
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
        super(location, "sprites/Sterilisation.png", "Steriliser");
    }

    /**
     * Constructor to create a  sterilisation object pulled from a saved game file (sets time remaining to what
     * it was when the game was saved).
     *
     * @param location The x, y coordinates that the sterilisation object is to be placed at.
     * @param timer    Time left before the sterilisation object is deleted.
     */
    public Sterilisation(int[] location, int timer) {
        super(location, "sprites/Sterilisation.png", "Steriliser");
        this.timer = timer;
    }

    /**
     * Method to run the actions of the entity every tick.
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
     * Method to sterilise a rat that goes through the Sterilisation entity.
     */
    private void steriliseRats() {
        int[] corner = {this.location[0] - 1, this.location[1] - 1}; //Sets the radius to northwest of the steriliser
        for (int i = Rat.getRats().size() - 1; i > -1; i--) {
            for (int k = corner[1]; k <= (this.location[1] + 1); k++) {
                for (int j = corner[0]; j <= (this.location[0] + 1); j++) {
                    int[] testLocation = {j, k};
                    if (checkRat(testLocation, i)) {
                        Rat.getRats().get(i).sterilise();
                    }
                }
            }
        }
    }

    /**
     * Method to check if a rat is on the Sterilisation entity.
     *
     * @param tempLocation Sterilisation entity location.
     * @param index        Rat index.
     * @return Boolean of whether the given rat is on the same tile as the Sterilisation Entity.
     */
    private boolean checkRat(int[] tempLocation, int index) {
        return Arrays.equals(Rat.getRats().get(index).location, tempLocation);
    }

    /**
     * Method to return the attribute values of the Sterilisation entity.
     *
     * @return Returns attribute values as single String.
     */
    public String toString() {
        return (getType() + " " + location[0] + " " + location[1] + " " + timer);
    }
}
