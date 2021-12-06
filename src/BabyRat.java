/**
 * Class to model a BabyRat entity
 *
 * @author Nick, Elliot
 * @version 2.0
 */

public class BabyRat extends Rat {

    private int age;
    private final String sex;

    /**
     * Constructor to create a BabyRat object.
     *
     * @param location Location of the rat.
     * @param directionFacing Direction the rat is currently facing.
     * @param sterile If rat is sterile or not.
     */
    public BabyRat(int[] location, String directionFacing, boolean sterile){
        super(100, sterile, location, directionFacing, "sprites/BabyRat.png", "BabyRat");
        this.age = 0;
        //assigns random sex
        this.sex = randomSex();
    }

    /**
     * Constructor to create a BabyRat object with pulled from a saved game file.
     *
     * @param sex Gender of the rat
     * @param location Location of the rat
     * @param directionFacing Direction the rat is currently facing
     * @param sterile If rat is sterile or not
     */
    public BabyRat(String sex, int[] location, String directionFacing, int health, boolean sterile){
        super(health, sterile, location, directionFacing, "sprites/BabyRat.png", "BabyRat");
        this.age = 0;
        if (sex.equals("F") || (sex.equals("BF"))) {
            this.sex = "F";
        } else {
            this.sex = "M";
        }
    }

    /**
     * Method to complete actions every tick.
     */
    public void act() {
        move();
        increaseAge();
    }

    /**
     * Method to return the attributes of the BabyRat entity.
     *
     * @return Returns a String of the attributes of the BabyRat.
     */
    public String toString(){
        return "B" + sex + " " + this.location[0] + " " + this.location[1] + " " +
                directionFacing + " "  + health  + " " + sterile;
    }

    /**
     * Method to increase the age of the baby rat every tick and once it reaches 14 ticks of age it turns into an
     * adult rat.
     */
    private void increaseAge() {
        age++;
        //checks if age is equal to 14 and if so makes an adult rat in its place
        int MAX_AGE = 14;
        if (this.age == MAX_AGE) {
            becomeAdult();
        }
    }

    /**
     * Method to make the BabyRat turn into an AdultRat using its current attributes.
     */
    private void becomeAdult(){
        //removes the baby rat and spawns an adult rat in its place with all the same attributes
        new AdultRat(this.sex, this.location, this.directionFacing,
                this.health, this.sterile);
        //removes the baby rat
        removeEntity();
        //removes baby rat from the array list
        rats.remove(this);
    }

    /**
     * Method to pick a random sex for the BabyRat.
     *
     * @return Returns the sex of the rat.
     */
    private String randomSex() {
        //assigns random gender
        if (randomize(2) == 1) {
            return "F";
        } else {
            return "M";
        }
    }
}