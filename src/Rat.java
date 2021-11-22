import java.util.ArrayList;
import java.util.Scanner;

public abstract class Rat extends Entity{
    protected int health = 100;
    protected double speed;
    protected boolean sterile;
    protected char directionFacing;
    protected int population;
    protected static ArrayList<Rat> rats;

    public Rat(int health, double speed, boolean sterile, int[] location, char directionFacing, int population, String image){
        super(location, image);
    	this.health = health;
        this.speed = speed;
        this.sterile = sterile;
        this.directionFacing = directionFacing;
        this.population = population;
        rats.add(this);
        populationUpdateAdd(); 
    }
    
    public abstract void act();

    public void modifySpeed(int newSpeed) { 
        this.speed = newSpeed;
    }

    public void modifyHealth(int newHealth) {
        this.health = newHealth;
    }

    private void move() {
        //move rat
        //waiting for movement options
    }
    
    private ArrayList<Rat> getRats() {
        return rats;
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
