/**
 * <p> 1. File name: MaleSexChange.java</p>
 * <p> 3. Creation date: 08.11.2021</p>
 * <p> 4. Last modification date: 05.12.2021</p>
 * <p> 6. Copyright notice: group 02 - CS230 - Swansea University - 2021/22</p>
 * <p> 7. Purpose of the program: Changing the sex of the male rat</p>
 * <p> 8. Version history: 1.0 - pure code; 2.0 - comment added</p>
 * @author Raj, Nick, Elliot, Oliver, Joe, Jay, Shivraj & Patel
 */




import java.util.Arrays;

public class MaleSexChange extends Entity {
	
	public MaleSexChange(int[] location) {
		super(location, "MaleSexChange.png", "MSC");
	}
	
	public void act() {
		for (int i = Rat.getRats().size()-1; i > -1; i--) {
			if (Arrays.equals(Rat.getRats().get(i).location, this.location) && 
					((Rat.getRats().get(i).getRatType().equals("FRat")) || (Rat.getRats().get(i).getRatType().equals("MRat")))) {
				((AdultRat)Rat.getRats().get(i)).setSex("M");
				((AdultRat)Rat.getRats().get(i)).setPregnant(false);
				((AdultRat)Rat.getRats().get(i)).updateRatType();
				removeEntity();
	        }
        }
	}
	
	public String toString() {
    	return (this.getType() + " " + location[0] + " " + location[1]);
    }
}

