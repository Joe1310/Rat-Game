import java.util.ArrayList;

public class Bomb extends Entity{
    private int countdown;

    public Bomb(int[] location) {
        super(location, "Bomb5.png", "Bomb");
        this.countdown = 20;
    }

    public Bomb(int[] location, int countdown) {
        super(location, "Bomb.png", "Bomb");
        this.countdown = countdown;
        setImageName(getBombImage());
    }

    public void act() {
        if (countdown == 0) {
            spawnExplosions();
            removeEntity();
        }
        countdown--;
        setImageName(getBombImage());
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
					while(!(Map.getTileType(tempLocation[0], tempLocation[1]) == 'G')) {
						tempLocation[0] = location[0];
						tempLocation[1] = location[1] - 1;
					}
					break;
				case "S":
					while(!(Map.getTileType(tempLocation[0], tempLocation[1]) == 'G')) {
						tempLocation[0] = location[0];
						tempLocation[1] = location[1] - 1;
					}
					break;
				case "E":
					while(!(Map.getTileType(tempLocation[0], tempLocation[1]) == 'G')) {
						tempLocation[0] = location[0];
						tempLocation[1] = location[1] - 1;
					}
					break;
				case "W":
					while(!(Map.getTileType(tempLocation[0], tempLocation[1]) == 'G')) {
						tempLocation[0] = location[0];
						tempLocation[1] = location[1] - 1;
					}
					break;
			}		
		}
    }
    
    private String getBombImage() {
    	switch(countdown) {
    	case 20:
    		return "Bomb5.png";
 		case 16:
 			return "Bomb4.png";
 		case 12:
 			return "Bomb3.png";
 		case 8:
 			return "Bomb2.png";
 		case 4:
 			return "Bomb1.png";
 		default:
 			return "Bomb.png";
         }
    }
}
