import java.util.ArrayList;

/**
 * Class to model a Bomb item
 *
 * @author Elliot, Ollie
 * @version 1.0
 */
public class Bomb extends Entity{
    private int countdown;

	/**
	 * Constructor to create a Bomb entity.
	 *
	 * @param location The x, y coordinates of the bomb on the map.
	 */
    public Bomb(int[] location) {
        super(location, "Bomb4.png", "Bomb");
        this.countdown = 16;
    }

	/**
	 * Constructor used for bombs created from a saved game file (contains custom countdown
	 * so the timer is kept from the time it was saved.
	 *
	 * @param location The x, y coordinates of the bomb on the map.
	 * @param countdown The time remaining before the bomb blows up in quarter seconds.
	 */
    public Bomb(int[] location, int countdown) {
        super(location, "Bomb.png", "Bomb");
        this.countdown = countdown;
        updateBombImage();
    }

	/**
	 * Method to blow up the bomb if the countdown has finished and reduce the time remaining on the countdown
	 * if the bomb hasnt already hit 0. Also changes the image of the bomb to match the time remaining.
	 */
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

	/**
	 * Method to spawn the explosion images.
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

	/**
	 * Method to spawn the explosions to the north of the bomb.
	 *
	 * @param exLocation The x, y coordinate of the explosion entity
	 */
	private void explodeNorthLine(int[] exLocation) {
    	Entity explosion = new Explosion(exLocation);
    	if((Map.getTileType(exLocation[0], exLocation[1] - 1) != 'G')) {
	   		int[] newLocation = {exLocation[0], exLocation[1] - 1};
	   		explodeNorthLine(newLocation);
	   	}
    }

	/**
	 * Method to spawn the explosions to the south of the bomb.
	 *
	 * @param exLocation The x, y coordinates of the explosion entity.
	 */
	private void explodeSouthLine(int[] exLocation) {
    	Entity explosion = new Explosion(exLocation);
    	if((Map.getTileType(exLocation[0], exLocation[1] + 1) != 'G')) {
    		int[] newLocation = {exLocation[0], exLocation[1] + 1};
    		explodeSouthLine(newLocation);
    	}
    }

	/**
	 * Method to spawn the explosions to the east of the bomb.
	 *
	 * @param exLocation The x, y coordinates of the explosion entity.
	 */
	private void explodeEastLine(int[] exLocation) {
    	Entity explosion = new Explosion(exLocation);
    	if((Map.getTileType(exLocation[0] + 1, exLocation[1]) != 'G')) {
    		int[] newLocation = {exLocation[0] + 1, exLocation[1]};
    		explodeEastLine(newLocation);
    	}
    }

	/**
	 * Method to spawn the explosions to the west of the bomb.
	 *
	 * @param exLocation The x, y coordinates of the explosion entity.
	 */
	private void explodeWestLine(int[] exLocation) {
    	Entity explosion = new Explosion(exLocation);
    	if((Map.getTileType(exLocation[0] - 1, exLocation[1]) != 'G')) {
    		int[] newLocation = {exLocation[0] - 1, exLocation[1]};
    		explodeWestLine(newLocation);
    	}
    }

	/**
	 * Method to update the bombs image dependent on the time remaining before the bomb explodes.
	 */
	private void updateBombImage() {
    	switch(countdown){
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

	/**
	 * Method to return the attribute values of the bomb entity (Item Type, x coordinate, y coordinate, countdown).
	 *
	 * @return Returns attribute values as single String
	 */
	public String toString() {
    	return (this.getType() + " " + location[0] + " " + location[1] + " " + countdown);
    }
}
