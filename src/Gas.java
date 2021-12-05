/**
 * <p> 1. File name: Gas.java</p>
 * <p> 3. Creation date: 08.11.2021</p>
 * <p> 4. Last modification date: 05.12.2021</p>
 * <p> 6. Copyright notice: group 02 - CS230 - Swansea University - 2021/22</p>
 * <p> 7. Purpose of the program: damage rat's health with gas behavior & characteristics</p>
 * <p> 8. Version history: 1.0 - pure code; 2.0 - comment added</p>
 * @author Raj, Nick, Elliot, Oliver, Joe, Jay, Shivraj & Patel
 * @version 2.0
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Gas extends Entity {
	private int spreadLimit;
	private int timer = 10;
	private String direction;
	private boolean hasSpread;

	/**
	 * Gas constructor
	 * <p> no side-effect </p>
	 * <p> not referentially transparent </p>
	 * @param location
	 * @param hasSpread
	 */
	public Gas(int[] location, boolean hasSpread) {
		super(location, "Gas.png", "Gas");
		this.spreadLimit = 4;
		this.hasSpread = hasSpread;
		this.direction = null;
	}

	/**
	 * Gas constructor
	 * @param location
	 * @param spreadLimit
	 * @param direction
	 * @param hasSpread
	 */
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
				(Rat.getRats().get(i)).damageRat(20);
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
    			+ " " + direction + " " + hasSpread);
    }
}
