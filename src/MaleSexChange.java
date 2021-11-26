public class Malesexchange extends AdultRat {
	
	public Malesexchange(int[] location) {
		super(location, "Malesexchange.png");
	}
	
	public void act() {
		char sx = getsex()
		if sx == 'f'{
				setsex('m')
		//need to create a setter function in class AdultRat as sex is a private data member		
		}
	}
}
