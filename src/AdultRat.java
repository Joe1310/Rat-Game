import java.util.Arrays;

public class AdultRat extends Rat {

	private final int MATING_TIME = 6;
	private final int PREGNANCY_TIME = 4;
    private String sex;
    private boolean isPregnant;
    private boolean isMating;
    private int timeMating;
    private int babyAmmount;
    private int timePregnant;
    private int wait;

    public AdultRat(String sex, int health, boolean sterile, int[] location, String directionFacing, boolean isPregnant){
    	super(health, 5, sterile, location, directionFacing, (sex + "Rat" + directionFacing + ".png"), (sex + "Rat"));
        this.sex = sex;
        this.isPregnant = isPregnant;
        this.timeMating = 0;
        this.timePregnant = 0;
        this.babyAmmount = randomize(3) + 2;
        this.wait = 1;
    }

    public void act() {
    	procreateCheck();
    	if (!isMating) {
    		if(wait == 0) {
    			move();
    			if(isPregnant) {
    				pregnancy();
    			}
    			wait = 1;
    		} else {
    			wait--;
    		}
    	}
        if (isMating) {
        	mating();
        }
    }

    /*
     * Mating
     * Checks if a male and female rat are on top of each other
     * Stop Movement
     * Make female rat pregnant
     */
    public void procreateCheck(){
    	for (int i = Rat.getRats().size()-1; i > -1; i--) {
    	    if (Arrays.equals(Rat.getRats().get(i).location, this.location) && 
    				(Rat.getRats().get(i).getRatType().equals("FRat") && this.sex.equals("M")) && !this.isMating
    					&& !((AdultRat)Rat.getRats().get(i)).isPregnant) {
    			isMating = true;
    		} else if (Arrays.equals(Rat.getRats().get(i).location, this.location) && 
					(Rat.getRats().get(i).getRatType().equals("MRat") && this.sex.equals("F"))
					&& this.sterile == false && !this.isPregnant) {
    			isPregnant = true;
    			isMating = true;
    		}
        }
    }
    
    public void mating() {
    	isMating = true;
		if (timeMating == MATING_TIME) {
			isMating = false;
			timeMating = 0;
		} else {
			timeMating++;
		}
    }
    
    /*
     * Makes pregnant female rat pump out a few babies
     */
    public void pregnancy() {
        if(timePregnant != PREGNANCY_TIME) {
        	timePregnant++;
        } else {
        	if (babyAmmount != 0){
        		makeBabyRat();
        		babyAmmount--;
        	} else {
        		isPregnant = false;
        		this.babyAmmount = randomize(3) + 2;
        	}	
        }
    }
    
    public void makeBabyRat() {
    	int[] babyLocation = new int[2];
    	babyLocation[0] = this.location[0];
    	babyLocation[1] = this.location[1];
    	Entity baby = new BabyRat(100, false, babyLocation, this.directionFacing);
    }

    public String getSex(){
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String toString(){
        return this.sex + " " + this.location[0] + " " + this.location[1] + " " + health + " " + directionFacing + " " + sterile + " " + isPregnant;
    }
}