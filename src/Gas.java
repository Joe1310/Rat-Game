import java.util.ArrayList;

public class Gas extends Entity {
	private int spreadLimit;
	private int timer = 6;
	private String direction;
	private boolean hasSpread;
	
	public Gas(int[] location, boolean hasSpread) {
		super(location, "Gas2.png", "Gas");
		this.spreadLimit = 5;
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
		if (!hasSpread && spreadLimit > 0) {
			spread();
			hasSpread = true;
		}
		diffuse();
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
			switch(d) {
				case "n":
					int[] ntemp = {location[0], location[1] - 1};
					Entity nGas = new Gas(ntemp, spreadLimit - 1, "s", false);
					break;
				case "s":
					int[] stemp = {location[0], location[1] - 1};
					Entity sGas = new Gas(stemp, spreadLimit - 1, "n", false);
					break;
				case "e":
					int[] etemp = {location[0], location[1] - 1};
					Entity eGas = new Gas(etemp, spreadLimit - 1, "w", false);
					break;
				case "w":
					int[] wtemp = {location[0], location[1] - 1};
					Entity wGas = new Gas(wtemp, spreadLimit - 1, "e", false);
					break;
			}
				
		}
	}
}
