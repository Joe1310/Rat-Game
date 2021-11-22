package Poison;
public class Poison extends Entity {
	private int health;
	
	public Poison(int[] location) {
		super(location, "Poison.png");
		health = 0;
	}
	
	public Poison(int[] location, int health) {
		super(location, "Poison.png");
		this.health = health;
	}
	
	public void act(int[] location) {
		this.super(location, "deathrat.png");
	}
	
	public void degrade() {
			removeEntity();
	}
}