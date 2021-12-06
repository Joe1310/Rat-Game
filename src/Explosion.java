import java.util.Arrays;

/**
 * Class to model an Explosion entity.
 *
 * @author Elliot
 * @version 1.0
 */
public class Explosion extends Entity {
    private int wait = 1;

    /**
     * Constructor to create an Explosion object.
     *
     * @param location The x, y coordinates of the explosion.
     */
    public Explosion(int[] location) {
        super(location, "sprites/Explosion.png", "Explosion");
    }

    /**
     * Method to run the actions of the entity every tick.
     */
    public void act() {
        explodeRats();
        explodeItems();
        wait--;
        if (wait == 0) {
            removeEntity();
        }
    }

    /**
     * Method to kill Rats in the explosion.
     */
    private void explodeRats() {
        for (int i = Rat.getRats().size() - 1; i > -1; i--) {
            if (Arrays.equals(Rat.getRats().get(i).location, this.location)) {
                Rat.getRats().get(i).ratDeath();
            }
        }
    }

    /**
     * Method to kill all entities in the explosion.
     */
    private void explodeItems() {
        for (int i = Entity.getEntities().size() - 1; i > -1; i--) {
            if (Arrays.equals(Entity.getEntities().get(i).location, this.location) &&
                    (Entity.getEntities().get(i) != this)) {
                Entity.getEntities().get(i).removeEntity();
            }
        }
    }

    /**
     * Method to return the attribute values of the Explosion entity.
     *
     * @return Returns attribute values as single String.
     */
    public String toString() {
        return (this.getType() + " " + location[0] + " " + location[1]);
    }
}
