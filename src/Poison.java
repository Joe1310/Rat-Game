public class Poison extends Entity {
	
	public Poison(int[] location) {
		super(location, "Poison.png", "Poison");
	}
	
	public void act() {
		for (Rat rat : Rat.getRats()) {
			if (rat.location == this.location && rat.getRatType() != "death") {
				rat.ratDeath();
				removeEntity();
			}
		}
	}
}