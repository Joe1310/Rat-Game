import java.util.ArrayList;

public class Bomb extends Entity{
    private int countdown;

    public Bomb(int[] location) {
        super(location, "Bomb.png", "Bomb");
        this.countdown = 10;
    }

    public Bomb(int[] location, int countdown) {
        super(location, "Bomb.png", "Bomb");
        this.countdown = countdown;
    }

    public void act() {
        if (countdown == 0) {
            spawnExplosions();
            removeEntity();
        }
        countdown--;
    }

    private void spawnExplosions() {
    	Entity explosion = new Explosion(location);
        int[] temp = {location[0], location[1]};
        ArrayList<String> directions = Map.getMovementOptions(location[0], location[1]);
        for (String d : directions) {
			int[] tempLocation = new int[2];
			switch(d) {
				case "N":
					tempLocation[0] = location[0];
					tempLocation[1] = location[1] - 1;
					Entity nGas = new Gas(tempLocation, 5, "S", false);
					break;
				case "S":
					tempLocation[0] = location[0];
					tempLocation[1] = location[1] + 1;
					Entity sGas = new Gas(tempLocation, 5, "N", false);
					break;
				case "E":
					tempLocation[0] = location[0] + 1;
					tempLocation[1] = location[1];
					Entity eGas = new Gas(tempLocation, 5, "W", false);
					break;
				case "W":
					tempLocation[0] = location[0] - 1;
					tempLocation[1] = location[1];
					Entity wGas = new Gas(tempLocation, 5, "E", false);
					break;
			}		
		}
    }
}
