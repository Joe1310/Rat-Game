public class MaleSexChange extends Entity {
	
	public MaleSexChange(int[] location) {
		super(location, "MSexChange.png");
	}
	
	public void act() {
		for (Rat rat : Rat.getRats()) {
			if (rat.location == this.location && rat.getRatType() == "adult") {
				if (((AdultRat)rat).getSex() == "f") {
				rat.ratDeath();
				removeEntity();
				}
			}
		}		
	}
}

