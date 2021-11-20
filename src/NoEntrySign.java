public class NoEntrySign extends Entity {
	private int health;
	
	public NoEntrySign(int[] location) {
		super(location);
		health = 5;
	}
	
	public NoEntrySign(int[] location, int health) {
		super(location);
		this.health = health;
	}
	
	public void degrade() {
		health--;
		if (health == 0) {
			removeEntity(this);
		}
	}
}
