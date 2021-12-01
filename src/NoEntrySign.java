public class NoEntrySign extends Entity {
	private int health;
	
	public NoEntrySign(int[] location) {
		super(location, "NoEntrySign.png", "NES");
		health = 5;
	}
	
	public NoEntrySign(int[] location, int health) {
		super(location, "NoEntrySign.png", "NES");
		this.health = health;
	}
	
	public void act() {
		
	}
	
	public void degrade() {
		health--;
		if (health == 0) {
			removeEntity();
		}
		switch(health) {
			case 4:
				setImageName("NoEntrySign2.png");
				break;
			case 3:
				setImageName("NoEntrySign3.png");
				break;
			case 2:
				setImageName("NoEntrySign4.png");
				break;
			case 1:
				setImageName("NoEntrySign5.png");
				break;
		}
	}
}
