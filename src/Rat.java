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

    private void move() {
        
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
