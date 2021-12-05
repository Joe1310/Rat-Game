/**
 * <p> 1. File name: AdultRat.java</p>
 * <p> 3. Creation date: 08.11.2021</p>
 * <p> 4. Last modification date: 05.12.2021</p>
 * <p> 6. Copyright notice: group 02 - CS230 - Swansea University - 2021/22</p>
 * <p> 7. Purpose of the program: Adult rat behavior</p>
 * <p> 8. Version history: 1.0 - pure code; 2.0 - comment added</p>
 * @author Raj, Nick, Elliot, Oliver, Joe, Jay, Shivraj & Patel
 * @version 2.0
 */

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

	/**
	 * AdultRat constructor with some inputs
	 * @param sex
	 * @param location
	 * @param directionFacing
	 * @param health
	 * @param sterile
	 */
    public AdultRat(String sex, int[] location, String directionFacing, int health, boolean sterile){
    	super(health, sterile, location, directionFacing, (sex + "Rat" + directionFacing + ".png"), (sex + "Rat"));
        this.sex = sex;
        this.isPregnant = false;
        this.timeMating = 0;
        this.timePregnant = 0;
        this.babyAmmount = randomize(2) + 2;
        this.matingCooldown = 0;
    }

	/**
	 * AdultRat constructor with more inputs
	 * @param sex
	 * @param location
	 * @param directionFacing
	 * @param health
	 * @param sterile
	 * @param pregnant
	 * @param mating
	 * @param timePregnant
	 * @param timeMating
	 * @param matingCooldown
	 * @param babyAmmount
	 */
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

	/**
	 * Check if the Rat is pregnant
	 * <p> no side-effect</p>
	 * <p> not referentially transparent </p>
	 * @return 'true' is the rat is pregnant
	 */
    public boolean getIsPregnant(){
    	return this.isPregnant;
	}

	/**
	 * ratDeath - whenever called, the rat dies
	 * and score added; if the rat is pregnant then soore added twice
	 * <p> has side effect</p>
	 */
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

	/**
	 * This method checks if the rat is mating and set the behavior accordingly
	 * <p> no side-effect</p>
	 */
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

	/**
	 * Mating, Checks if a male and female rat are on top of each other,
	 * Stop Movement, Make female rat pregnant
	 * <p> has side-effect</p>
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

	/**
	 * Checks if the rat's meting time is same as the max mating time and stop mating
	 * if MATING_TIME is equal to it's time.
	 * <p> has side effect</p>
	 */
	public void mating() {
		if (timeMating == MATING_TIME) {
			isMating = false;
			timeMating = 0;
		} else {
			timeMating++;
		}
    }
    
    /**
     * Makes pregnant female rat pump out a few babies
	 * <p> has side-effect</p>
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

	/**
	 * Creates baby rats
	 * <p> has side-effect</p>
	 */
	public void makeBabyRat() {
    	int[] babyLocation = new int[2];
    	babyLocation[0] = this.location[0];
    	babyLocation[1] = this.location[1];
    	String[] directions = {"N","E","S","W"};
    	String newDirection = directions[(randomize(4))];
    	Entity baby = new BabyRat(babyLocation, newDirection, this.sterile);
    }

	/**
	 * updates rat's type
	 * <p> has side-effect</p>
	 */
    public void updateRatType() {
    	setRatType(this.sex + "Rat");
    }

	/**
	 * get the sex of the rat
	 * <p> not referentially transparent</p>
	 * @return the sex of the rat
	 */
	public String getSex(){
        return this.sex;
    }

	/**
	 * set sex of the rat
	 * <p> has side-effect</p>
	 * @param sex
	 */
    public void setSex(String sex) {
        this.sex = sex;
    }

	/**
	 * set the pregnancy of the rat
	 * <p> has side effect</p>
	 * @param pregnant
	 */
	public void setPregnant(boolean pregnant) {
    	this.isPregnant = pregnant;
    }

    public String toString(){
        return (this.sex + " " + this.location[0] + " " + this.location[1] + " " + directionFacing + " " + health + " " 
        			+ sterile + " " + isPregnant + " " + isMating + " " + timePregnant + " " + timeMating + " " 
        				+ matingCooldown + " " + babyAmmount);
    }
}