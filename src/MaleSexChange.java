public class MaleSexChange extends Entity {
	
	public MaleSexChange(int[] location) {
		super(location, "MSexChange.png", "MSexChange");
	}
	
	public void act() {
		for (Rat rat : Rat.getRats()) {
			if (rat.location == this.location && rat.getRatType() == "adult") {
				((AdultRat)rat).setSex("m");
				removeEntity();
			}
		}
	}
}

