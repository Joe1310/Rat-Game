public class NoEntrySign extends Item {
	private int health;
	private int location[];
	
	public NoEntrySign() {
		health = 5;
	}
	
	public NoEntrySign(int health) {
		this.health = health;
	}
	
	public void degrade() {
		health--;
		if (health == 0) {
			remove();
		}
	}
}
