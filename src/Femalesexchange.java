public class Femalesexchange extends Entity {
	
	public Femalesexchange(int[] location) {
		super(location, "FSexChange.png");
	}


	@Override
	public void act(AdultRat rat) {
		String sx = rat.getSex();
		if (sx == "m"){
			AdultRat.setSex("f");
		}
	}
}
