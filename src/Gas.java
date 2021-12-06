import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to model the Gas item.
 *
 * @author Elliot, Ollie
 * @version 1.0
 */
public class Gas extends Entity {

    private final int spreadLimit;
    private int timer = 10;
    private final String direction;
    private boolean hasSpread;
    private int wait = 2;

    /**
     * Constructor to create a Gas object.
     *
     * @param location  The x, y coordinates of the gas object.
     * @param hasSpread Boolean of if the gas has expanded or not.
     */
    public Gas(int[] location, boolean hasSpread) {
        super(location, "sprites/Gas.png", "Gas");
        this.spreadLimit = 5;
        this.hasSpread = hasSpread;
        this.direction = null;
    }

    /**
     * Constructor to create a Gas object when the gas expands.
     *
     * @param location    The x, y coordinates of the gas object.
     * @param spreadLimit How many tiles the gas can spread from its current location.
     * @param direction   The direction the gas came from.
     * @param hasSpread   Boolean of if the gas has expanded or not.
     */
    public Gas(int[] location, int spreadLimit, String direction, boolean hasSpread) {
        super(location, "sprites/Gas.png", "Gas");
        this.spreadLimit = spreadLimit;
        this.hasSpread = hasSpread;
        this.direction = direction;
    }

    /**
     * Method to run the actions of the entity every tick.
     */
    public void act() {
        damageRats();
        if (wait == 0) {
            if (!hasSpread && spreadLimit > 0) {
                spread();
                hasSpread = true;
            }
        } else {
            wait--;
        }
        diffuse();
    }

    /**
     * Method to damage any rats that are inside the gas.
     */
    private void damageRats() {
        for (int i = Rat.getRats().size() - 1; i > -1; i--) {
            if (Arrays.equals(Rat.getRats().get(i).location, this.location) &&
                    !(Rat.getRats().get(i).getRatType().equals("DeathRat"))) {
                (Rat.getRats().get(i)).damageRat(20);
            }
        }
    }

    /**
     * Method to remove gas once it has been there for its full time.
     */
    public void diffuse() {
        timer--;
        if (timer == 0) {
            removeEntity();
        }
    }

    /**
     * Method to expand the gas cloud.
     */
    public void spread() {
        ArrayList<String> directions = Map.getMovementOptions(location[0], location[1]);
        directions.remove(direction); // Removes direction it came from to prevent redrawing.
        for (String d : directions) {
            int[] tempLocation = new int[2];
            switch (d) {
                case "N":
                    tempLocation[0] = location[0];
                    tempLocation[1] = location[1] - 1;
                    new Gas(tempLocation, spreadLimit - 1, "S", false);
                    break;
                case "S":
                    tempLocation[0] = location[0];
                    tempLocation[1] = location[1] + 1;
                    new Gas(tempLocation, spreadLimit - 1, "N", false);
                    break;
                case "E":
                    tempLocation[0] = location[0] + 1;
                    tempLocation[1] = location[1];
                    new Gas(tempLocation, spreadLimit - 1, "W", false);
                    break;
                case "W":
                    tempLocation[0] = location[0] - 1;
                    tempLocation[1] = location[1];
                    new Gas(tempLocation, spreadLimit - 1, "E", false);
                    break;
            }
        }
    }

    /**
     * Method to return the attribute values of the Gas entity.
     *
     * @return Returns attribute values as single String.
     */
    public String toString() {
        return (this.getType() + " " + location[0] + " " + location[1] + " " + spreadLimit
                + " " + direction + " " + hasSpread);
    }
}
