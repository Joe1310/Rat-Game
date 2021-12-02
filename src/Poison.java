import java.util.Arrays;

public class Poison extends Entity {
	
	public Poison(int[] location) {
		super(location, "Poison.png", "Poison");
	}
	
	public void act() {
		killRats();
	}
	
	private void killRats() {
		for (int i = Rat.getRats().size()-1; i > -1; i--) {
			if (Arrays.equals(Rat.getRats().get(i).location, this.location) && 
					!(Rat.getRats().get(i).getRatType().equals("DeathRat"))) {
				(Rat.getRats().get(i)).ratDeath();
				removeEntity();
	        }
        }
	}
}