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
			System.out.println("Bye!");
			removeEntity();
		}
	}
}
