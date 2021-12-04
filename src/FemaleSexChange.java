import java.util.Arrays;

public class FemaleSexChange extends Entity {
	
	public FemaleSexChange(int[] location) {
		super(location, "FemaleSexChange.png", "FSexChange");
	}
	
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
}