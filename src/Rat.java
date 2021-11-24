import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public abstract class Rat extends Entity{
    protected int health = 100;
    protected double speed;
    protected boolean sterile;
    protected String directionFacing;
    protected static ArrayList<Rat> rats;
    protected int age;



    public Rat(int health, double speed, boolean sterile, int[] location, String directionFacing, String image){
        super(location, image);
    	this.health = health;
        this.speed = speed;
        this.sterile = sterile;
        this.directionFacing = directionFacing;
        rats.add(this);
    }
    public void increaseAge(){
        this.age = this.age ++;
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
        if (directionFacing == "e"){
            return ("w");
        }else if ( directionFacing == "w"){
            return ("e");
        }else if (directionFacing == "n"){
            return ("s");
        }else {
        	return "n";
        }
    }

    protected void move() {
        ArrayList<String> temp = Map.getMovementOptions(location[0], location[0]); // from 0,1 to 0,0
        Random rand = new Random();
        for (String direction : temp ){
            if (direction == getOpositeDirection() && temp.size() > 1 ){
                temp.remove(direction);
            }
        }
        // Obtain a number between [0 - 5].
        int n = rand.nextInt(temp.size());
        switch (temp.get(n)){
            case "n":       // at first the starting would be 0; for any direction like an array
                location[0] = location[0] + 50; // 50 px is the size of the tile
                this.directionFacing = "n";
            break;
            case "s":
                location[0] = location[0] - 50;
                this.directionFacing = "s";
            break;
            case "w":
                location[0] = location[0] - 50;
                this.directionFacing = "w";
            break;
            case "e":
                location[0] = location[0] + 50;
                this.directionFacing = "e";
            break;    
        }
    }

    protected void ratDeath() {
        rats.remove(this);
        removeEntity();
    }
    
    protected ArrayList<Rat> getRats() {
    	return rats;
    }


}
