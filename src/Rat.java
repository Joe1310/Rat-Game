import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

/**
 * <p> 1. File name: Rat.java</p>
 * <p> 3. Creation date: 08.11.2021</p>
 * <p> 4. Last modification date: 05.12.2021</p>
 * <p> 6. Copyright notice: group 02 - CS230 - Swansea University - 2021/22</p>
 * <p> 7. Purpose of the program: Rat behavior extension of Entity</p>
 * <p> 8. Version history: 1.0 - pure code; 2.0 - comment added</p>
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
     * AdultRat constructor with some inputs and initializing variables
     * and adds the rat to the array list of all rats and passes location,
     * image and rat type to the super class entity.
     * @param location location of the rat
     * @param directionFacing direction the rat is currently facing
     * @param health current health of the rat
     * @param sterile if rat is sterile or not
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
     * returns a randome number between 0 and the passed int.
     * @param i is the range for the random number from 0 to i
     * @return Int random value
     */
    public int randomize(int i){
        //random function that takes in the range
        Random rand = new Random();     
        return rand.nextInt(i);
    }

    /**
     * abstract method called every tick
     */
    public abstract void act();

    /**
     * When called removes the parameter damage from health and if the health is less than
     * or equal to 0 it calls ratDeath() to kill the rat.
     * @param damage hte damage that needs to be removed from health
     * <p>has side-effect</p>
     */
    public void damageRat(int damage) {
        //removes the damage from the health
        this.health = this.health - damage;
        //if the health is 0 kills the rat
        if (this.health <= 0) {
        	ratDeath();
        }
    }

    public String getOpositeDirection() {
        //removes the oposite direction so that the rat doesn't go back on itself
        //unless the rat is at a dead end
        if (directionFacing.equals("E")){
            return ("W");
        }else if (directionFacing.equals("W")){
            return ("E");
        }else if (directionFacing.equals("N")){
            return ("S");
        }else {
            return ("N");
        }
    }

    /**
     * move() gets the moment options from map fot the tile that the rat is on and picks a random one of those to
     * change location to, it also removes the opposite direction to the one its facing unless its at a dead end
     * it also removes any possible directions with a no entry sign on them.
     * after picking a random direction the method then changes the direction facing to the correct
     * direction based of the way it just moved
     * <p>has side-effect</p>
     */
    public void move() {
        // location[0] is the x coord and location[1] is the y coord
        ArrayList<String> temp = Map.getMovementOptions(location[0], location[1]);
        temp = checkNoEntry(temp);
        for (int i = temp.size()-1; i >= 0; i--) {
            if (temp.get(i).equals(getOpositeDirection()) && temp.size() > 1){
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
     * ratDeath() when called removes that rat from the array list of rats and then removes the entity from the game
     * and then adds to the score unless the rat was a death rat
     * <p>has side-effect</p>
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
     * sterilise() sets sterile to true.
     * <p>has side-effect</p>
     */
    protected void sterilise() {
       sterile = true;
    }

    /**
     * gets the rat type
     * @return String ratType
     */
    public String getRatType() {
        return ratType;
    }

    /**
     * sets the type of the rat
     * @param ratType String of the rat type i.e "DeathRat"
     * <p>has side-effect</p>
     */
    protected void setRatType(String ratType) {
    	this.ratType = ratType;
    }

    /**
     * updateRatImage() calls the setImageName() in entity by using the Direction facing and the rat type to construct
     * the uniform image naming system.
     * <p>has side-effects</p>
     */
    public void updateRatImage() {
        //creates the rat image name
    	setImageName(ratType + directionFacing + ".png");
    }

    /**
     * gets the array list of rats
     * @return rats: arrayList of all rat objects in the game
     */
    protected static ArrayList<Rat> getRats() {
        return rats;
    }

    /**
     * checkNoEntry() gets called every time the rat moves, it check's every direction
     * passed to it to see if theirs a no entry sign on it, if the there is it removes the
     * direction from the array list and degrades the no entry sign.
     * @param directions an array list of all possible moment option from that location
     * @return amended list of directions
     * <p>has side-effect</p>
     */
    private ArrayList<String> checkNoEntry(ArrayList<String> directions) {
    	int[] tempLocation = new int[2];
    	String tempDirection;
    	//checks if theirs a no entry sign in that direction
	    for (int i = directions.size()-1; i >= 0; i--) {
    		if (directions.get(i).equals("N")) {
	        	tempLocation[0] = this.location[0];
	        	tempLocation[1] = this.location[1] - 1;
	        	tempDirection = "N";
	        } else if (directions.get(i).equals("S")) {
	        	tempLocation[0] = this.location[0];
	        	tempLocation[1] = this.location[1] + 1;
	        	tempDirection = "S";
	        } else if (directions.get(i).equals("E")) {
	        	tempLocation[0] = this.location[0] + 1;
	        	tempLocation[1] = this.location[1];
	        	tempDirection = "E";
	        } else {
	        	tempLocation[0] = this.location[0] - 1;
	        	tempLocation[1] = this.location[1];
	        	tempDirection = "W";
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
	    
	    return directions;
    }
}
