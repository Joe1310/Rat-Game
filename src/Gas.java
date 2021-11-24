public class Gas extends Entity {
	private int spread;
	
	public Gas(int[] location) {
		super(location, "Gas.png");
		spread = 9;
	}
	
	public Gas(int[] location, int spread) {
		super(location, "Gas.png");
		this.spread = spread;
	}
	
	public void act() {
		
	}
	
	public void expand() {
		Map.getMovementOptions(location[0], location[1]);
		
	}
	
}
