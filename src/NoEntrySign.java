public class NoEntrySign extends Item {
	private int health;
	
	public NoEntrySign() {
		health = 5;
	}
	
	public NoEntrySign(int health) {
		this.health = health;
	}
	
	public void degrade() {
		health = health - 1;
		if (health == 0) {
			remove();
		}
	}
}
