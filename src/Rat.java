import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public abstract class Rat extends Entity{
    protected int health;
    protected double speed;
    protected boolean sterile;
    protected String directionFacing;
    protected static ArrayList<Rat> rats = new ArrayList<Rat>();
    private String ratType;

    public Rat(int health, double speed, boolean sterile, int[] location, String directionFacing, String image, String ratType){
        super(location, image, "Rat");
        this.ratType = ratType;
        this.health = health;
        this.speed = speed;
        this.sterile = sterile;
        this.directionFacing = directionFacing;
        rats.add(this);
    }

    public int randomize(int i){
        Random rand = new Random();
        return rand.nextInt(i);
    }

    public abstract void act();

    public void modifySpeed(int newSpeed) {
        this.speed = newSpeed;
    }

    public void damageRat(int damage) {
        this.health =- damage;
        if (this.health <= 0) {
        	ratDeath();
        }
    }

    public String getOpositeDirection() {
        if (directionFacing.equals("E")){
            return ("W");
        }else if (directionFacing.equals("W")){
            return ("E");
        }else if (directionFacing.equals("N")){
            return ("S");
        }else {
            return ("N");
        }
    }

    public void move() {
        ArrayList<String> temp = Map.getMovementOptions(location[0], location[1]); // location[0] is the x coord and location[1] is the y coord
        temp = checkNoEntry(temp);
        for (int i = temp.size()-1; i >= 0; i--) {
            if (temp.get(i).equals(getOpositeDirection()) && temp.size() > 1){
                temp.remove(temp.get(i));
            }
        }
        // Obtain a random direction available.
        int n = randomize(temp.size());
        switch (temp.get(n)){
            case "N":
                location[1] = location[1] - 1;
                this.directionFacing = "N";
                break;
            case "S":
                location[1] = location[1] + 1;
                this.directionFacing = "S";
                break;
            case "W":
                location[0] = location[0] - 1;
                this.directionFacing = "W";
                break;
            case "E":
                location[0] = location[0] + 1;
                this.directionFacing = "E";
                break;
        }
        updateRatImage();
    }

    protected void ratDeath() {
        rats.remove(this);
        removeEntity();
    }

    public String getRatType() {
        return ratType;
    }
    
    public void updateRatImage() {
    	setImageName(directionFacing + ratType + ".png");
    }

    protected static ArrayList<Rat> getRats() {
        return rats;
    }

    private ArrayList<String> checkNoEntry(ArrayList<String> directions) {
    	int[] tempLocation = new int[2];
    	String tempDirection;
	    for (int i = directions.size()-1; i >= 0; i--) {
    		if (directions.get(i).equals("N")) {
	        	tempLocation[0] = this.location[0];
	        	tempLocation[1] = this.location[1] - 1;
	        	tempDirection = "N";
	        } else if (directions.get(i).equals("S")) {
	        	tempLocation[0] = this.location[0];
	        	tempLocation[1] = this.location[1] + 1;
	        	tempDirection = "S";
	        } else if (directions.get(i).equals("E")) {
	        	tempLocation[0] = this.location[0] + 1;
	        	tempLocation[1] = this.location[1];
	        	tempDirection = "E";
	        } else {
	        	tempLocation[0] = this.location[0] - 1;
	        	tempLocation[1] = this.location[1];
	        	tempDirection = "W";
	        }
			for (int j = getEntities().size()-1; j > -1; j--) {
				if (Arrays.equals(getEntities().get(j).location, tempLocation) && getEntities().get(j).getType().equals("NES")) {
					((NoEntrySign)getEntities().get(j)).degrade();
					directions.remove(tempDirection);
		        }
	        }
	    }
	    return directions;
    }
}