import java.util.Arrays;
import java.util.Scanner;

public class Rat {
    protected int health = 100;
    protected double speed;
    protected boolean sterile;
    protected int[] location;
    protected Char directionFacing;
    protected  int population;
    protected static Rat[] Rats;

    public Rat(int health, double speed, boolean sterile, int[] location, char directionFacing, int population){
        this.health = health;
        this.speed = speed;
        this.sterile = sterile;
        this.location = location;
        this.directionFacing = directionFacing;
        this.population = population;
        Rats += this.Rat;
        populationUpdate(); 
    }

    public void modifySpeed(int newSpeed){ 
        this.speed = newSpeed;
    }

    public void modifyHealth(int newHealth){
        this.health = newHealth;
    }

    private void move (){
        //move rat
        //waiting for movement options
    }
    private Rat[] getRats (){
        return Rats;
    }

    private void ratDeath(){
        this.Rat = null;

    }
    public void populationUpdateAdd(){
        population = population ++; 
    }

    public void populationUpdateSub(){
        population = population --; 
    }
}
