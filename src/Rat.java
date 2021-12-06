import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

/**
 * Class to model a Rat entity.
 *
 * @author Nick, Elliot
 * @version 2.0
 */
public abstract class Rat extends Entity{
    public static int wait;
    protected static ArrayList<Rat> rats = new ArrayList<Rat>();
    protected int health;
    protected boolean sterile;
    protected String directionFacing;
    private String ratType;

    /**
     * Constructor to create a Rat entity.
     *
     * @param location Location of the rat
     * @param directionFacing Direction the rat is currently facing
     * @param health Current health of the rat
     * @param sterile If rat is sterile or not
     */
    public Rat(int health, boolean sterile, int[] location, String directionFacing, String image, String ratType){
        super(location, image, "Rat");
        this.ratType = ratType;
        this.health = health;
        this.sterile = sterile;
        this.directionFacing = directionFacing;
        rats.add(this);
    }

    /**
     * Abstract method to complete an action every tick
     */
    public abstract void act();

    /**
     * Method to damage the rat for a given amount of damage.
     *
     * @param damage amount of health the rat is to be damaged for.
     */
    public void damageRat(int damage) {
        //removes the damage from the health
        this.health = this.health - damage;
        //if the health is 0 kills the rat
        if (this.health <= 0) {
        	ratDeath();
        }
    }



    /**
     * Method to get the rat type.
     *
     * @return Returns rat type.
     */
    public String getRatType() {
        return ratType;
    }

    /**
     * Method to get the possible movement options for the rat and pick a random one for the rat to move to,
     * only choosing the way it came from if it is the only option the rat has.
     * Then makes sure the rat has the correct image based on the direction it just moved.
     */
    protected void move() {
        // location[0] is the x coord and location[1] is the y coord
        ArrayList<String> temp = Map.getMovementOptions(location[0], location[1]);
        checkNoEntry(temp);
        for (int i = temp.size()-1; i >= 0; i--) {
            if (temp.get(i).equals(getOppositeDirection()) && temp.size() > 1){
                temp.remove(temp.get(i));
            }
        }
        // Obtain a random direction available.
        if(temp.size() != 0) {
            int n = randomize(temp.size());
            switch (temp.get(n)){
                case "N":
                    location[1] = location[1] - 1;
                    this.directionFacing = "N";
                    break;
                case "S":
                    location[1] = location[1] + 1;
                    this.directionFacing = "S";
                    break;
                case "W":
                    location[0] = location[0] - 1;
                    this.directionFacing = "W";
                    break;
                case "E":
                    location[0] = location[0] + 1;
                    this.directionFacing = "E";
                    break;
            }
        }
        updateRatImage();
    }



    /**
     * Method to return a random number between 0 and the passed int.
     *
     * @param i The range for the random number from 0 to i
     * @return Returns a random Int value
     */
    protected int randomize(int i){
        //random function that takes in the range
        Random rand = new Random();
        return rand.nextInt(i);
    }

    /**
     * Method to set the rat to sterile.
     */
    protected void sterilise() {
        sterile = true;
    }

    /**
     * Method to remove rat entity.
     */
    protected void ratDeath() {
        //removes a rat if its been killed and adds to the score
        rats.remove(this);
        removeEntity();
        //unless its a death rat
        if(!ratType.equals("DeathRat")) {
        	Level.addScore();
        }
    }

    /**
     * Method to set the type of rat.
     */
    protected void setRatType(String ratType) {
    	this.ratType = ratType;
    }

    /**
     * Method to get the array list of rats.
     *
     * @return Returns arrayList of all rat objects in the game.
     */
    protected static ArrayList<Rat> getRats() {
        return rats;
    }

    /**
     * Method to update the rat image dependant on the way the rat is facing.
     */
    private void updateRatImage() {
        //creates the rat image name
        setImageName(ratType + directionFacing + ".png");
    }

    /**
     * Method to check if one of the movement options the rat has contains a NoEntrySign and gets rid of these options.
     *
     * @param directions an array list of all possible moment option from that location.
     */
    private void checkNoEntry(ArrayList<String> directions) {
    	int[] tempLocation = new int[2];
    	String tempDirection;
    	//checks if theirs a no entry sign in that direction
	    for (int i = directions.size()-1; i >= 0; i--) {
            switch (directions.get(i)) {
                case "N":
                    tempLocation[0] = this.location[0];
                    tempLocation[1] = this.location[1] - 1;
                    tempDirection = "N";
                    break;
                case "S":
                    tempLocation[0] = this.location[0];
                    tempLocation[1] = this.location[1] + 1;
                    tempDirection = "S";
                    break;
                case "E":
                    tempLocation[0] = this.location[0] + 1;
                    tempLocation[1] = this.location[1];
                    tempDirection = "E";
                    break;
                default:
                    tempLocation[0] = this.location[0] - 1;
                    tempLocation[1] = this.location[1];
                    tempDirection = "W";
                    break;
            }
    		//
			for (int j = getEntities().size()-1; j > -1; j--) {
				if (Arrays.equals(getEntities().get(j).location, tempLocation) && getEntities().get(j).getType().equals("NES")) {
				    //degrades the no entry sign
					((NoEntrySign)getEntities().get(j)).degrade();
					//removes that direction from the list of directions
					directions.remove(tempDirection);
		        }
	        }
	    }

    }

    /**
     * Method to get the opposite of the direction the rat is facing.
     *
     * @return opposite direction to what the rat is already facing.
     */
    private String getOppositeDirection() {
        //removes the opposite direction so that the rat doesn't go back on itself
        //unless the rat is at a dead end
        switch (directionFacing) {
            case "E":
                return ("W");
            case "W":
                return ("E");
            case "N":
                return ("S");
            default:
                return ("N");
        }
    }
}
