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

