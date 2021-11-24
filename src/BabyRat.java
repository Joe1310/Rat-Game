
public class BabyRat extends Rat {
    protected boolean becomeAld = false;
    
    public BabyRat(int health, double speed, boolean sterile, int[] location, String directionFacing){
        super(health, 2.0, sterile, location, directionFacing, "BabyRat.png");
        this.age = 0;
    }
    
    public void act() {
    	move();
        increaseAge();
    }

    @Override
    public void increaseAge() {
        super.increaseAge();
        if (this.age == 5) {
            becomeAdult();
        }
    }

    public void becomeAdult(){
        Entity newBabyRat = new AdultRat(this.health, 1.0, this.sterile, this.location, this.directionFacing, randomSex(), false);// check if correct
        removeEntity();
        this.becomeAld = true;
    }
    
    public char randomSex() {
    	if (randomize(2) > 1) {
    		return 'm';
    	} else {
    		return 'f';
    	}
    }

    
    
}
