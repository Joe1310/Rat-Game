import java.util.ArrayList;
import java.util.Random;

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
        int n = rand.nextInt(i);
        return n;
    }

    public abstract void act();

    public void modifySpeed(int newSpeed) {
        this.speed = newSpeed;
    }

    public void modifyHealth(int newHealth) {
        this.health = newHealth;
    }

    public String getOpositeDirection() {
        if (directionFacing == "E"){
            return ("W");
        }else if ( directionFacing == "W"){
            return ("E");
        }else if (directionFacing == "N"){
            return ("S");
        }else {
            return "N";
        }
    }

    public void move() {
        //checkNoEntry();
        ArrayList<String> temp = Map.getMovementOptions(location[0], location[1]); // location[0] is the x coord and location[1] is the y coord
        Random rand = new Random();
        for (String direction : temp ){
            if (direction.equals(getOpositeDirection()) && temp.size() > 1){
                temp.remove(direction);
            }
        }
        // Obtain a random direction available.
        int n = rand.nextInt(temp.size());
        switch (temp.get(n)){
            case "N":       // at first the starting would be 0; for any direction like an array
                location[1] = location[1] - 1; // 50 px is the size of the tile
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
        int[] tempLocation = this.location;
        switch (directionFacing){
            case "N":
                tempLocation[1]--;
                break;
            case "S":
                tempLocation[1]++;
                break;
            case "W":
                tempLocation[0]--;
                break;
            case "E":
                tempLocation[0]++;
                break;
        }
        for(Entity ent : Entity.getEntities()) {
            if (ent.location == tempLocation && ent.getType() == "NES") {
                ((NoEntrySign)ent).degrade();
                this.directionFacing = getOpositeDirection();
            }
        }
    }


}