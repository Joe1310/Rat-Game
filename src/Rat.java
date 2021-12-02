import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public abstract class Rat extends Entity{
    protected int health = 100;
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

    public void modifyHealth(int newHealth) {
        this.health = newHealth;
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
        checkNoEntry();
        ArrayList<String> temp = Map.getMovementOptions(location[0], location[1]); // location[0] is the x coord and location[1] is the y coord
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
    }

    protected void ratDeath() {
        rats.remove(this);
        removeEntity();
    }

    public String getRatType() {
        return ratType;
    }

    protected static ArrayList<Rat> getRats() {
        return rats;
    }

    private void checkNoEntry() {
    	int[] tempLocation = new int[2];
        if (directionFacing.equals("N")) {
        	tempLocation[0] = this.location[0];
        	tempLocation[1] = this.location[1] - 1;
        } else if (directionFacing.equals("S")) {
        	tempLocation[0] = this.location[0];
        	tempLocation[1] = this.location[1] + 1;
        } else if (directionFacing.equals("E")) {
        	tempLocation[0] = this.location[0] + 1;
        	tempLocation[1] = this.location[1];
        } else {
        	tempLocation[0] = this.location[0] - 1;
        	tempLocation[1] = this.location[1];
        }
        
		//for loop for acting
		for (int i = getEntities().size()-1; i > -1; i--) {
			if (Arrays.equals(getEntities().get(i).location, tempLocation) && getEntities().get(i).getType().equals("NES")) {
				((NoEntrySign)getEntities().get(i)).degrade();
	            this.directionFacing = getOpositeDirection();
	        }
        }
    }
}