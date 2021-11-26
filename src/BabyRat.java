
public class BabyRat extends Rat {
    private String sex = null;
    private int age;

    public BabyRat(int health, double speed, boolean sterile, int[] location, String directionFacing){
        super(health, 2.0, sterile, location, directionFacing, "BabyRat.png", "baby");
        this.age = 0;
        this.ratName = "adultRat";
    }
    
    public void act() {
    	move();
        increaseAge();
    }

    public void increaseAge() {
        
        if (this.age == 5) {
            becomeAdult();
        }
    }

    public void becomeAdult(){
        Entity newBabyRat = new AdultRat(this.health, 1.0, this.sterile, this.location, this.directionFacing, randomSex(), false);// check if correct
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
        return sex + " " + speed + " " + sterile + " " + directionFacing + " " + age + " " + health + " " + rats.size();
    }
}
