import java.util.Random;

public class BabyRat extends Rat {
    private int age;
    
    public BabyRat(int health, double speed, boolean sterile, int[] location, String directionFacing, int population){
        super(health, 2.0, sterile, location, directionFacing, population, "BabyRat.png");
        this.age = 0;
    }
    
    public void act() {
    	move();
    	//increaseAge();
    }

    public void increaseAge(){
        this.age = this.age ++;
        if (age == 5) {
            becomeAdult();
        }
    }
    public void move (){
        increaseAge();      
    }
    //in progress
    public void becomeAdult(){
        Entity test = new AdultRat(this.health, 1.0, this.sterile, this.location, this.directionFacing, this.population, randomSex(), false);// check if correct
        removeEntity(); 
    }
    
    public char randomSex() {
    	if (randomize(2) > 1) {
    		return 'm';
    	} else {
    		return 'f';
    	}
    }

    
    
}
