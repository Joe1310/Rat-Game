import java.util.Arrays;

public class DeathRat extends Rat {
	
    private int killCount;
    
    public DeathRat(int health, boolean sterile, int[] location, String directionFacing) {
        super(health, sterile, location, directionFacing, ("DeathRat" + directionFacing + ".png"), "DeathRat");//add file for rat image
        killCount = 0;
    }
    
    public DeathRat(int health, boolean sterile, int[] location, String directionFacing, int killCount) {
        super(health, sterile, location, directionFacing, ("DeathRat" + directionFacing + ".png"), "DeathRat");//add file for rat image
        this.killCount = killCount;
    }
    
    public void act() {
        killRat();
        if (wait == 1) {
        	move();
        }
    }
    
    private void killRat() {
    	for (int i = rats.size()-1; i > -1; i--) {
			if (Arrays.equals(rats.get(i).location, this.location) && 
					!rats.get(i).getRatType().equals("DeathRat")) {
				rats.get(i).ratDeath();
				killCount++;
	        }
        }
		if (killCount >= 5){
            removeEntity();
        }
    }

    public String toString() {
        return "D" + " " + this.location[0] + " " + this.location[1] + " " + health + " " + directionFacing;
    }
}
