public class FemaleSexChange extends Entity {
	
	public FemaleSexChange(int[] location) {
		super(location, "FSexChange.png");
	}
	
	public void act() {
		for (Rat rat : Rat.getRats()) {
			if (rat.location == this.location && rat.getRatType() == "adult") {
				if (((AdultRat)rat).getSex() == "m") {
				((AdultRat)rat).setSex("f");
				removeEntity();
				}
			}
		}		
	}
}