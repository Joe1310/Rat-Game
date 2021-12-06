import java.util.Arrays;

/**
 * Class to model a DeathRat entity.
 *
 * @author Nick, Elliot
 * @version 2.0
 */
public class DeathRat extends Rat {

    private int killCount;

    /**
     * Constructor to create a DeathRat entity.
     *
     * @param location        Location of the rat.
     * @param directionFacing Direction the rat is currently facing.
     */
    public DeathRat(int[] location, String directionFacing) {
        super(1000, true, location, directionFacing, ("sprites/DeathRat" + directionFacing + ".png"),
                "DeathRat");
        killCount = 0;
    }

    /**
     * Constructor to create a DeathRat entity with attributes pulled from a saved game file.
     *
     * @param location        Location of the rat.
     * @param directionFacing Direction the rat is currently facing.
     * @param killCount       The number of rats this death rat has killed.
     */
    public DeathRat(int[] location, String directionFacing, int killCount) {
        super(1000, true, location, directionFacing, ("sprites/DeathRat" + directionFacing + ".png"),
                "DeathRat"); //add file for rat image
        this.killCount = killCount;
    }

    /**
     * Method to continuously call the DeathRats actions.
     */
    public void act() {
        //checks if their is another rat on its tile and kills any that are.
        killRat();
        if (wait == 1) {
            move();
        }
    }

    /**
     * Method to return the attributes of the DeathRat entity.
     *
     * @return Returns a String of the attributes of the DeathRat.
     */
    public String toString() {
        return "D" + " " + this.location[0] + " " + this.location[1] + " " + directionFacing
                + " " + killCount;
    }

    /**
     * Method to kill any Rats that come into contact with the DeathRat excluding other DeathRats.
     * If the DeathRat meets its max kills then it also kills itself.
     */
    private void killRat() {
        //searches through the list of rats and sees if any are on the same tile
        for (int i = rats.size() - 1; i > -1; i--) {
            if (Arrays.equals(rats.get(i).location, this.location)
                    && !rats.get(i).getRatType().equals("DeathRat")) {
                //if they are it removes the other rat and adds one to the kill count
                rats.get(i).ratDeath();
                killCount++;
            }
        }
        //checks if the kill count is greater or equal to 5 and if so then removes the death rat
        if (killCount >= 5) {
            ratDeath();
        }
    }
}
