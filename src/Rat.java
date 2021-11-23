import java.util.ArrayList;
import java.util.Random;
import java.util.Random;

public abstract class Rat extends Entity{
    protected int health = 100;
    protected double speed;
    protected boolean sterile;
    protected String directionFacing;
    protected int population;
    protected static ArrayList<Rat> rats;

    public Rat(int health, double speed, boolean sterile, int[] location, String directionFacing, int population, String image){
        super(location, image);
    	this.health = health;
        this.speed = speed;
        this.sterile = sterile;
        this.directionFacing = directionFacing;
        this.population = population;
        rats.add(this);
        populationUpdateAdd();//add image of rat file 
    }
    
    //i changed this so you can set the max to whatever you want so it can be used for baby rat getting the random sex sorry if this messes something up - Elliot
    public int randomize(int i){
        Random rand = new Random();
        // Obtain a number between [0 - 5].
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
        ArrayList<String> temp = Map.getMovementOptions(location[0], location[1]);
        Random rand = new Random();
        for (String direction : temp ){
            if (direction == getOpositeDirection () && temp.size() > 1 ){
                temp.remove(direction);
            }    
        }
        // Obtain a number between [0 - 5].
        int n = rand.nextInt(temp.size());
        switch (temp.get(n)){
            case "n":
                location[1] = location[1] + 1;
                this.directionFacing = "n";
            break;
            case "s":
                location[1] = location[1] - 1;
                this.directionFacing = "s";
            break;
            case "w":
                location[0] = location[0] - 1;
                this.directionFacing = "w";
            break;
            case "e":
                location[0] = location[0] + 1;
                this.directionFacing = "e";
            break;    
        }
    }

    protected void ratDeath() {
        rats.remove(this);
        removeEntity();
        //code for player score increase
    }
    
    protected ArrayList<Rat> getRats() {
    	return rats;
    }
    
    public void populationUpdateAdd() {
        population = population ++; 
    }

    public void populationUpdateSub() {
        population = population --; 
    }
}
