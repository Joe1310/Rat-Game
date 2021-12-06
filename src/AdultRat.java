import java.util.Arrays;

/**
 * Class to model an AdultRat entity.
 *
 * @author Nick, Elliot
 * @version 2.0
 */
public class AdultRat extends Rat {

    private String sex;
    private boolean isPregnant;
    private boolean isMating;
    private int timeMating;
    private int babyAmount;
    private int timePregnant;
    private int matingCooldown;

    /**
     * Constructor to create an AdultRat entity.
     *
     * @param sex             Gender of the rat
     * @param location        Location of the rat
     * @param directionFacing Direction the rat is currently facing
     * @param health          Current health of the rat
     * @param sterile         If rat is sterile or not
     */
    public AdultRat(String sex, int[] location, String directionFacing, int health, boolean sterile) {
        super(health, sterile, location, directionFacing, ("sprites/" + sex + "Rat" + directionFacing + ".png"), (sex + "Rat"));
        this.sex = sex;
        this.isPregnant = false;
        this.timeMating = 0;
        this.timePregnant = 0;
        this.babyAmount = randomize(2) + 2;
        this.matingCooldown = 0;
    }

    /**
     * Constructor to create an AdultRat entity with attributes pulled from a saved game file.
     *
     * @param sex             Gender of the rat
     * @param location        Current location of the rat
     * @param directionFacing Current direction facing
     * @param health          Current health of the rat
     * @param sterile         Boolean for sterilisation
     * @param pregnant        Pregnant or not
     * @param mating          Mating or not for staying still whilst having sex
     * @param timePregnant    The time the rat has been pregnant for
     * @param timeMating      Time procreating for
     * @param matingCooldown  Cool down before rats can have sex again
     * @param babyAmount      The amount of babies the rat can have
     */
    public AdultRat(String sex, int[] location, String directionFacing, int health, boolean sterile, boolean pregnant,
                    boolean mating, int timePregnant, int timeMating, int matingCooldown, int babyAmount) {
        super(health, sterile, location, directionFacing, ("sprites/" + sex + "Rat" + directionFacing + ".png"), (sex + "Rat"));
        this.sex = sex;
        this.isPregnant = pregnant;
        this.isMating = mating;
        this.timePregnant = timePregnant;
        this.timeMating = timeMating;
        this.matingCooldown = matingCooldown;
        this.babyAmount = babyAmount;
    }

    /**
     * Method to complete actions every tick to do mating, procreating and moving.
     */
    public void act() {
        //checks if the rat can mate
        if (matingCooldown <= 0) {
            //checks if their is another rat on the same tile
            procreateCheck();
        } else {
            //decreases the mating cool down
            matingCooldown--;
        }

        if (isMating) {
            mating();
        } else {
            if (wait == 0) {
                if (isPregnant) {
                    pregnancy();
                }
                move();
            }
        }
    }

    /**
     * Method to set the Rats type.
     */
    public void updateRatType() {
        setRatType(this.sex + "Rat");
    }

    /**
     * Method to get the gender of the rat.
     *
     * @return Returns the gender of the rat.
     */
    public String getSex() {
        return this.sex;
    }

    /**
     * Method to set the gender of the rat.
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Method to change if the rat is pregnant or not.
     *
     * @param pregnant Is the rat pregnant or not.
     */
    public void setPregnant(boolean pregnant) {
        this.isPregnant = pregnant;
    }

    /**
     * Method to return the attributes of the AdultRat entity.
     *
     * @return Returns a String of the attributes of the AdultRat.
     */
    public String toString() {
        return (this.sex + " " + this.location[0] + " " + this.location[1] + " " + directionFacing + " " + health + " "
                + sterile + " " + isPregnant + " " + isMating + " " + timePregnant + " " + timeMating + " "
                + matingCooldown + " " + babyAmount);
    }

    /**
     * Method to kill the rat entity and increase the players score.
     */
    @Override
    protected void ratDeath() {
        // adultRat rat death
        rats.remove(this);
        removeEntity();
        //checks if the mother is pregnant if so adds the unborn children to the score
        if (this.isPregnant) {
            for (int i = 0; i < babyAmount; i++) {
                Level.addScore();
            }
        }
        Level.addScore();
    }

    /**
     * Method to check if any rats of the opposite gender are on the same tile as it, if so makes
     * the female rat pregnant
     */
    private void procreateCheck() {

        for (int i = Rat.getRats().size() - 1; i > -1; i--) {
            int cooldown = 60;
            if (Arrays.equals(Rat.getRats().get(i).location, this.location) &&
                    (Rat.getRats().get(i).getRatType().equals("FRat") && this.sex.equals("M")) && !this.sterile && !Rat.getRats().get(i).sterile
                    && !((AdultRat) Rat.getRats().get(i)).isPregnant && ((AdultRat) Rat.getRats().get(i)).matingCooldown == 0) {
                isMating = true;
                matingCooldown = cooldown;
                ((AdultRat) Rat.getRats().get(i)).isMating = true;
                ((AdultRat) Rat.getRats().get(i)).isPregnant = true;
                ((AdultRat) Rat.getRats().get(i)).matingCooldown = cooldown;
            } else if (Arrays.equals(Rat.getRats().get(i).location, this.location) &&
                    (Rat.getRats().get(i).getRatType().equals("MRat") && this.sex.equals("F")) && !Rat.getRats().get(i).sterile
                    && !this.sterile && !this.isPregnant && ((AdultRat) Rat.getRats().get(i)).matingCooldown == 0) {
                isPregnant = true;
                isMating = true;
                matingCooldown = cooldown;
                ((AdultRat) Rat.getRats().get(i)).isMating = true;
                ((AdultRat) Rat.getRats().get(i)).matingCooldown = cooldown;
            }
        }
    }

    /**
     * Method to check if the rats have been mating for long enough.
     */
    private void mating() {
        int matingTime = 6;
        if (timeMating == matingTime) {
            isMating = false;
            timeMating = 0;
        } else {
            timeMating++;
        }
    }

    /**
     * Method to check if the mother has been pregnant for long enough to give birth.
     */
    private void pregnancy() {
        // checks if the mother has been pregnant for long enough
        int pregnancyTime = 4;
        if (timePregnant != pregnancyTime) {
            timePregnant++;
        } else {
            //starts spawning baby rats
            if (babyAmount != 0) {
                makeBabyRat();
                babyAmount--;
            } else {
                // resets everything after the mother has given birth
                isPregnant = false;
                this.babyAmount = randomize(2) + 2;
            }
        }
    }

    /**
     * Method to create a new BabyRat entity.
     */
    private void makeBabyRat() {
        // creates the random number of baby's
        int[] babyLocation = new int[2];
        babyLocation[0] = this.location[0];
        babyLocation[1] = this.location[1];
        String[] directions = {"N", "E", "S", "W"};
        //assigns a random direction to spread the rats across the map more evenly
        String newDirection = directions[(randomize(4))];
        new BabyRat(babyLocation, newDirection, this.sterile);
    }
}