import java.util.Arrays;

/**
 * Class to model a FemaleSexChange entity.
 *
 * @author Shivraj, Elliot
 * @version 1.0
 */
public class FemaleSexChange extends Entity {

	/**
	 * Constructor to create a FemaleSexChange object.
	 *
	 * @param location The x, y coordinates of the MaleSexChange object.
	 */
	public FemaleSexChange(int[] location) {
		super(location, "sprites/FemaleSexChange.png", "FSC");
	}

	/**
	 * Method to change the gender of any male rats that go through the tile it is placed on.
	 */
	public void act() {
		for (int i = Rat.getRats().size()-1; i > -1; i--) {
			if (Arrays.equals(Rat.getRats().get(i).location, this.location) && 
					((Rat.getRats().get(i).getRatType().equals("FRat")) || (Rat.getRats().get(i).getRatType().equals("MRat")))) {
				((AdultRat)Rat.getRats().get(i)).setSex("F");
				((AdultRat)Rat.getRats().get(i)).updateRatType();
				removeEntity();
	        }
        }
	}

	/**
	 * Method to return the attribute values of the FemaleSexChange entity.
	 *
	 * @return Returns attribute values as single String
	 */
	public String toString() {
    	return (this.getType() + " " + location[0] + " " + location[1]);
    }
}