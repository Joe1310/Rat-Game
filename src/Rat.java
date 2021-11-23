import java.util.ArrayList;
import java.util.Scanner;
import java.util.random;

public abstract class Rat extends Entity{
    protected int health = 100;
    protected double speed;
    protected boolean sterile;
    protected char directionFacing;
    protected int population;
    protected static ArrayList<Rat> Rats;

    public Rat(int health, double speed, boolean sterile, int[] location, char directionFacing, int population, String image){
        super(location, image);
    	this.health = health;
        this.speed = speed;
        this.sterile = sterile;
        this.directionFacing = directionFacing;
        this.population = population;
        rats.add(this);
        populationUpdateAdd();//add image of rat file 
    }
    public int randomize (){
        Random rand = new Random();
        // Obtain a number between [0 - 5].
        int n = rand.nextInt(5);
        return n;
    }
    
    public abstract void act();

    public void modifySpeed(int newSpeed) { 
        this.speed = newSpeed;
    }

    public void modifyHealth(int newHealth) {
        this.health = newHealth;
    }

    public Char getOpositeDirection (){
        if (directionFacing == 'e'){
            return 'w';
        }else if ( directionFacing == 'w'){
            return 'e';
        }else if (directionFacing == 'n'){
            return 's';
        }else if (directionFacing == 's'){
            return 'n';
        }
    }

    protected void move() {
        ArrayList<String> temp = getMovementOptions();
        Random rand = new Random();
        for (Char direction : temp ){
            if (direction == getOpositeDirection () && temp.size() > 1 ){
                temp.remove(direction);
            }    
        }
        // Obtain a number between [0 - 5].
        int n = rand.nextInt(temp.size());
        switch (temp(n)){
            case "n":
                location[1] = location[1] + 1;
                this.directionFacing = 'n';
            break;
            case "s":
                location[1] = location[1] - 1;
                this.directionFacing = 's';
            break;
            case "w":
                location[0] = location[0] - 1;
                this.directionFacing = 'w';
            break;
            case "e":
                location[0] = location[0] + 1;
                this.directionFacing = 'e';
            break;    
        }
    }
    
    private ArrayList<Rat> getRats() {
        return Rats;
    }

    private void ratDeath() {
        rats.remove(this);
        removeEntity();
    }
    
    public void populationUpdateAdd() {
        population = population ++; 
    }

    public void populationUpdateSub() {
        population = population --; 
    }
}
