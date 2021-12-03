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
        switch(countdown) {
			case 16:
				setImageName("Bomb4.png");
				break;
			case 12:
				setImageName("Bomb3.png");
				break;
			case 8:
				setImageName("Bomb2.png");
				break;
			case 4:
				setImageName("Bomb1.png");
				break;
        }
    }

    public void act() {
        if (countdown == 0) {
            //spawnExplosions();
            removeEntity();
        }
        countdown--;
        
        switch(countdown) {
			case 16:
				setImageName("Bomb4.png");
				break;
			case 12:
				setImageName("Bomb3.png");
				break;
			case 8:
				setImageName("Bomb2.png");
				break;
			case 4:
				setImageName("Bomb1.png");
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
        	System.out.println(d);
			int[] tempLocation = new int[2];
			switch(d) {
				case "N":
					System.out.println();
					while((Map.getTileType(tempLocation[0], tempLocation[1]) != 'G')) {
						tempLocation[0] = location[0];
						tempLocation[1] = location[1] - 1;
						System.out.println(tempLocation);
					}
					break;
				case "S":
					while((Map.getTileType(tempLocation[0], tempLocation[1]) != 'G')) {
						tempLocation[0] = location[0];
						tempLocation[1] = location[1] - 1;
						System.out.println(tempLocation);
					}
					break;
				case "E":
					while((Map.getTileType(tempLocation[0], tempLocation[1]) != 'G')) {
						tempLocation[0] = location[0];
						tempLocation[1] = location[1] - 1;
						System.out.println(tempLocation);
					}
					break;
				case "W":
					while((Map.getTileType(tempLocation[0], tempLocation[1]) != 'G')) {
						tempLocation[0] = location[0];
						tempLocation[1] = location[1] - 1;
						System.out.println(tempLocation);
					}
					break;
			}		
		}
    }
}
