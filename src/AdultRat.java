import java.util.ArrayList;
import java.util.Arrays;

public class AdultRat extends Rat {

	private final int MATING_TIME = 6;
	private final int PREGNANCY_TIME = 4;
	private final int COOLDOWN = 60;
    private String sex;
    private boolean isPregnant;
    private boolean isMating;
    private int timeMating;
    private int babyAmmount;
    private int timePregnant;
    private int matingCooldown;

    public AdultRat(String sex, int[] location, String directionFacing, int health, boolean sterile){
    	super(health, sterile, location, directionFacing, (sex + "Rat" + directionFacing + ".png"), (sex + "Rat"));
        this.sex = sex;
        this.isPregnant = false;
        this.timeMating = 0;
        this.timePregnant = 0;
        this.babyAmmount = randomize(2) + 2;
        this.matingCooldown = 0;
    }
    
    public AdultRat(String sex, int[] location, String directionFacing, int health, boolean sterile, boolean pregnant, 
    			boolean mating, int timePregnant, int timeMating, int matingCooldown, int babyAmmount){
    	super(health, sterile, location, directionFacing, (sex + "Rat" + directionFacing + ".png"), (sex + "Rat"));
        this.sex = sex;
        this.isPregnant = pregnant;
        this.isMating = mating;
        this.timePregnant = timePregnant;
        this.timeMating = timeMating;
        this.matingCooldown = matingCooldown;
        this.babyAmmount = babyAmmount;
    }
    public boolean getIsPregnant(){
    	return this.isPregnant;
	}
	@Override
	protected void ratDeath() {
		rats.remove(this);
		removeEntity();
		if(this.isPregnant){
			for (int i = 0; i < babyAmmount; i++){
				Level.addScore();
			}
		}
		Level.addScore();
	}

    public void act() {
    	if (matingCooldown <= 0) {
    		procreateCheck();
    	} else {
    		matingCooldown--;
    	}

        if (isMating) {
        	mating();
        } else {
    		if(wait == 0) {
    			if(isPregnant) {
    				pregnancy();
    			}
    			move();
    		}
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
    				(Rat.getRats().get(i).getRatType().equals("FRat") && this.sex.equals("M")) && !this.sterile && !Rat.getRats().get(i).sterile
    					&& !((AdultRat)Rat.getRats().get(i)).isPregnant && ((AdultRat)Rat.getRats().get(i)).matingCooldown == 0) {
    			isMating = true;
    			matingCooldown = COOLDOWN;
    			((AdultRat)Rat.getRats().get(i)).isMating = true;
    			((AdultRat)Rat.getRats().get(i)).isPregnant = true;
    			((AdultRat)Rat.getRats().get(i)).matingCooldown = COOLDOWN;
    		} else if (Arrays.equals(Rat.getRats().get(i).location, this.location) && 
					(Rat.getRats().get(i).getRatType().equals("MRat") && this.sex.equals("F")) && !Rat.getRats().get(i).sterile
					&& !this.sterile && !this.isPregnant && ((AdultRat)Rat.getRats().get(i)).matingCooldown == 0) {
    			isPregnant = true;
    			isMating = true;
    			matingCooldown = COOLDOWN;
    			((AdultRat)Rat.getRats().get(i)).isMating = true;
    			((AdultRat)Rat.getRats().get(i)).matingCooldown = COOLDOWN;
    		}
        }
    }
    
    public void mating() {
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
        		this.babyAmmount = randomize(2) + 2;
        	}	
        }
    }
    
    public void makeBabyRat() {
    	int[] babyLocation = new int[2];
    	babyLocation[0] = this.location[0];
    	babyLocation[1] = this.location[1];
    	String[] directions = {"N","E","S","W"};
    	String newDirection = directions[(randomize(4))];
    	Entity baby = new BabyRat(babyLocation, newDirection, this.sterile);
    }

    public void updateRatType() {
    	setRatType(this.sex + "Rat");
    }
    
    public String getSex(){
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public void setPregnant(boolean pregnant) {
    	this.isPregnant = pregnant;
    }

    public String toString(){
        return (this.sex + " " + this.location[0] + " " + this.location[1] + " " + health + " " + directionFacing + " " 
        			+ sterile + " " + isPregnant + " " + isMating + " " + timePregnant + " " + timeMating + " " 
        				+ matingCooldown + " " + babyAmmount);
    }
}