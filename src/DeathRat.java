/**
 * <p> 1. File name: DeathRat.java</p>
 * <p> 3. Creation date: 08.11.2021</p>
 * <p> 4. Last modification date: 05.12.2021</p>
 * <p> 6. Copyright notice: group 02 - CS230 - Swansea University - 2021/22</p>
 * <p> 7. Purpose of the program: Death rat behavior</p>
 * <p> 8. Version history: 1.0 - pure code; 2.0 - comment added</p>
 * @author Raj, Nick, Elliot, Oliver, Joe, Jay, Shivraj & Patel
 */



import java.util.Arrays;

public class DeathRat extends Rat {
	
    private int killCount;
    
    public DeathRat(int[] location, String directionFacing) {
        super(1000, true, location, directionFacing, ("DeathRat" + directionFacing + ".png"), "DeathRat");//add file for rat image
        killCount = 0;
    }
    
    public DeathRat(int[] location, String directionFacing, int killCount) {
        super(1000, true, location, directionFacing, ("DeathRat" + directionFacing + ".png"), "DeathRat");//add file for rat image
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
            ratDeath();
        }
    }

    public String toString() {
        return "D" + " " + this.location[0] + " " + this.location[1] + " " + directionFacing + " " + killCount;
    }
}
