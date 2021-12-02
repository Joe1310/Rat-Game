import java.util.Arrays;

public class Poison extends Entity {
	
	public Poison(int[] location) {
		super(location, "Poison.png", "Poison");
	}
	
	public void act() {
		for (int i = Rat.getRats().size()-1; i > -1; i--) {
			if (Arrays.equals(Rat.getRats().get(i).location, this.location) && 
					!(Rat.getRats().get(i).getRatType().equals("death"))) {
				(Rat.getRats().get(i)).ratDeath();
	        }
        }
	}
}