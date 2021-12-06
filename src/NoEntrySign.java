/**
 * Class to model a NoEntrySign item.
 *
 * @author Elliot
 * @version 1.0
 */
public class NoEntrySign extends Entity {
    private int health;

    /**
     * Constructor to create a NoEntrySign entity.
     *
     * @param location The coordinates x, y of the entity.
     */
    public NoEntrySign(int[] location) {
        super(location, "sprites/NoEntrySign.png", "NES");
        health = 5;
    }

    /**
     * Constructor to create a NoEntrySign from a saved game file (health is pulled from save).
     *
     * @param location The coordinates the sign is to be placed at.
     * @param health   The remaining number of rats that can hit the sign before it breaks.
     */
    public NoEntrySign(int[] location, int health) {
        super(location, "sprites/NoEntrySign.png", "NES");
        this.health = health;
    }

    /**
     * Needed so it can be extended from entity.
     */
    public void act() {

    }

    /**
     * Method to decrease the health of a NoEntrySign and update the image that is used by it.
     */
    public void degrade() {
        health--;
        if (health == 0) {
            removeEntity();
        }
        switch (health) {
            case 4:
                setImageName("sprites/NoEntrySign2.png");
                break;
            case 3:
                setImageName("sprites/NoEntrySign3.png");
                break;
            case 2:
                setImageName("sprites/NoEntrySign4.png");
                break;
            case 1:
                setImageName("sprites/NoEntrySign5.png");
                break;
        }
    }

    /**
     * Method to return the attribute values of the NoEntrySign entity.
     *
     * @return Returns attribute values as single String
     */
    public String toString() {
        return (this.getType() + " " + location[0] + " " + location[1] + " " + health);
    }
}
