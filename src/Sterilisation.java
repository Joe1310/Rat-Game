import java.util.ArrayList;
import java.util.Arrays;

class Sterilisation extends Entity {
    private int timer = 15;

    public Sterilisation(int[] location) {
        super(location, "Sterilisation.png", "Sterilisation");
    }
    
    public Sterilisation(int[] location, int timer) {
        super(location, "Sterilisation.png", "Sterilisation");
        this.timer = timer;
    }

    public void act() {
        steriliseRats();
        disappear();
    }

    public void disappear() {
        timer--;
        if (timer == 0) {
            removeEntity();
        }
    }

    private void steriliseRats() {
    	int[] corner = {this.location[0] - 1, this.location[1] - 1}; //Sets the radius to northwest of the steriliser
        for (int i = Rat.getRats().size()-1; i > -1; i--) {
        	for(int k = corner[1]; k <= (this.location[1] + 1); k++) {
        		for(int j = corner[0]; j <= (this.location[0] + 1); j++) {
        			int [] testLocation = {j, k};
        			if (checkRat(testLocation, i)) {
        				Rat.getRats().get(i).sterilise();
        			}
        		}
        	}
        }
    }
    
    private boolean checkRat(int[] tempLocation, int index) {
    	if (Arrays.equals(Rat.getRats().get(index).location, tempLocation)) {
			return true;
        } else {
        	return false;
        }
    }

}