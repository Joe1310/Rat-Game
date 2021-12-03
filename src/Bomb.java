import java.util.ArrayList;

public class Bomb extends Entity{
    private int countdown;

    public Bomb(int[] location) {
        super(location, "Bomb.png", "Bomb");
        this.countdown = 20;
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
        
        switch(countdown) {
		case 16:
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

    /*
     * Go every direction
     * Spawn Explosions
     * 
     */
    private void spawnExplosions() {
    	Entity explosion = new Explosion(location);
        int[] temp = {location[0], location[1]};
        ArrayList<String> directions = Map.getMovementOptions(location[0], location[1]);
        for (String d : directions) {
			int[] tempLocation = new int[2];
			switch(d) {
				case "N":
					while(Map.getTileType(tempLocation[0], tempLocation[1])
						tempLocation[0] = location[0];
						tempLocation[1] = location[1] - 1;
					
					break;
				case "S":
					tempLocation[0] = location[0];
					tempLocation[1] = location[1] + 1;
					
					break;
				case "E":
					tempLocation[0] = location[0] + 1;
					tempLocation[1] = location[1];
					
					break;
				case "W":
					tempLocation[0] = location[0] - 1;
					tempLocation[1] = location[1];
					
					break;
			}		
		}
    }
}
