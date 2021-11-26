
public class BabyRat extends Rat {
    private int age;

    public BabyRat(int health, double speed, boolean sterile, int[] location, String directionFacing){
        super(health, 2, sterile, location, directionFacing, "BabyRat.png", "baby");
        this.age = 0;
    }
    
    public void act() {
    	move();
        increaseAge();
    }

    public void increaseAge() {
        age++;
        if (this.age == 10) {
            becomeAdult();
        }
    }

    public void becomeAdult(){
        Entity newBabyRat = new AdultRat(this.health, this.sterile, this.location, this.directionFacing, randomSex(), false);// check if correct
        removeEntity();
    }
    
    public String randomSex() {
    	if (randomize(2) > 1) {
    		return "m";
    	} else {
    		return "f";
    	}
    }

    public String toString(){
        return "b" + this.location[0] + " " + this.location[1] + " " +  sterile + " " + directionFacing + " " + age + " " + health;
    }
}
