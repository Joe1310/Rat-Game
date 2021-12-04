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
        updateBombImage();
    }

    public void act() {
        if (countdown == 0) {
            spawnExplosions();
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
    	Entity explosion = new Explosion(this.location);
    	int[] tempLocation = {this.location[0], this.location[1]};
        ArrayList<String> directions = Map.getMovementOptions(tempLocation[0], tempLocation[1]);
        for (String d : directions) {
			switch(d) {
				case "N":
					explodeNorthLine(tempLocation);
					break;
				case "S":
					explodeSouthLine(tempLocation);
					break;
				case "E":
					explodeEastLine(tempLocation);
					break;
				case "W":
					explodeWestLine(tempLocation);
					break;
			}		
		}
    }
    
    private void explodeNorthLine(int[] exLocation) {
    	Entity explosion = new Explosion(exLocation);
    	if((Map.getTileType(exLocation[0], exLocation[1] - 1) != 'G')) {
	   		int[] newLocation = {exLocation[0], exLocation[1] - 1};
	   		explodeNorthLine(newLocation);
	   	}
    }
    
    private void explodeSouthLine(int[] exLocation) {
    	Entity explosion = new Explosion(exLocation);
    	if((Map.getTileType(exLocation[0], exLocation[1] + 1) != 'G')) {
    		int[] newLocation = {exLocation[0], exLocation[1] + 1};
    		explodeSouthLine(newLocation);
    	}
    }
    
    private void explodeEastLine(int[] exLocation) {
    	Entity explosion = new Explosion(exLocation);
    	if((Map.getTileType(exLocation[0] + 1, exLocation[1]) != 'G')) {
    		int[] newLocation = {exLocation[0] + 1, exLocation[1]};
    		explodeEastLine(newLocation);
    	}
    }
    
    private void explodeWestLine(int[] exLocation) {
    	Entity explosion = new Explosion(exLocation);
    	if((Map.getTileType(exLocation[0] - 1, exLocation[1]) != 'G')) {
    		int[] newLocation = {exLocation[0] - 1, exLocation[1]};
    		explodeWestLine(newLocation);
    	}
    }
    
    
    private void updateBombImage() {
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
}
