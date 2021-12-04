import java.util.ArrayList;
import java.util.Arrays;

public class Gas extends Entity {
	private int spreadLimit;
	private int timer = 10;
	private String direction;
	private boolean hasSpread;
	
	public Gas(int[] location, boolean hasSpread) {
		super(location, "Gas.png", "Gas");
		this.spreadLimit = 4;
		this.hasSpread = hasSpread;
		this.direction = null;
	}
	
	public Gas(int[] location, int spreadLimit, String direction, boolean hasSpread) {
		super(location, "Gas.png", "Gas");
		this.spreadLimit = spreadLimit;
		this.hasSpread = hasSpread;
		this.direction = direction;
	}
	
	public void act() {
		damageRats();
		if (!hasSpread && spreadLimit > 0) {
			spread();
			hasSpread = true;
		}
		diffuse();
	}
	
	private void damageRats() {
		for (int i = Rat.getRats().size()-1; i > -1; i--) {
			if (Arrays.equals(Rat.getRats().get(i).location, this.location) && 
					!(Rat.getRats().get(i).getRatType().equals("DeathRat"))) {
				(Rat.getRats().get(i)).damageRat(10);
	        }
        }
	}
	
	public void diffuse() {
		timer--;
		if (timer == 0) {
			removeEntity();
		}
	}
	
	public void spread() {
		ArrayList<String> directions = Map.getMovementOptions(location[0], location[1]);
		directions.remove(direction);
		for (String d : directions) {
			int[] tempLocation = new int[2];
			switch(d) {
				case "N":
					tempLocation[0] = location[0];
					tempLocation[1] = location[1] - 1;
					Entity nGas = new Gas(tempLocation, spreadLimit - 1, "S", false);
					break;
				case "S":
					tempLocation[0] = location[0];
					tempLocation[1] = location[1] + 1;
					Entity sGas = new Gas(tempLocation, spreadLimit - 1, "N", false);
					break;
				case "E":
					tempLocation[0] = location[0] + 1;
					tempLocation[1] = location[1];
					Entity eGas = new Gas(tempLocation, spreadLimit - 1, "W", false);
					break;
				case "W":
					tempLocation[0] = location[0] - 1;
					tempLocation[1] = location[1];
					Entity wGas = new Gas(tempLocation, spreadLimit - 1, "E", false);
					break;
			}		
		}
	}
	
	public String toString() {
    	return (this.getType() + " " + location[0] + " " + location[1] + " " + spreadLimit 
    			+ " " + hasSpread + " " + direction);
    }
}
