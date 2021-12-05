/**
 * <p> 1. File name: Inventory.java</p>
 * <p> 3. Creation date: 08.11.2021</p>
 * <p> 4. Last modification date: 05.12.2021</p>
 * <p> 6. Copyright notice: group 02 - CS230 - Swansea University - 2021/22</p>
 * <p> 7. Purpose of the program: Monitoring the inventory</p>
 * <p> 8. Version history: 1.0 - pure code; 2.0 - comment added</p>
 * @author Oliver
 * @version 2.0
 */




public class Inventory {
    static int gasQuantity;
    static int deathRatQuantity;
    static int poisonQuantity;
    static int sterilisationQuantity;
    static int femaleSexChangeQuantity;
    static int maleSexChangeQuantity;
    static int noEntrySignQuantity;
    static int bombQuantity;
    
    static int gasSpawnRate;
    static int deathRatSpawnRate;
    static int poisonSpawnRate;
    static int sterilisationSpawnRate;
    static int femaleSexChangeSpawnRate;
    static int maleSexChangeSpawnRate;
    static int noEntrySignSpawnRate;
    static int bombSpawnRate;
    
    static int gasTimer;
    static int deathratTimer;
    static int poisonTimer;
    static int sterilisationTimer;
    static int femaleSexChangeTimer;
    static int maleSexChangeTimer;
    static int noEntrySignTimer;
    static int bombTimer;

    
    public static int getGas() {
        return gasQuantity;
    }
    public static void addGas() {
        if (gasQuantity < 4) {
            gasQuantity += 1;
        }
    }
    public static void removeGas() {
        gasQuantity -= 1;
    }


    public static int getDeathRat() {
        return deathRatQuantity;
    }
    public static void addDeathRat() {
        if (deathRatQuantity < 4) {
            deathRatQuantity += 1;
        }
    }
    public static void removeDeathRat() {
        deathRatQuantity -= 1;
    }


    public static int getPoison() {
        return poisonQuantity;
    }
    public static void addPoison() {
        if (poisonQuantity < 4) {
            poisonQuantity += 1;
        }
    }
    public static void removePoison() {
        poisonQuantity -= 1;
    }


    public static int getSterilisation() {
        return sterilisationQuantity;
    }
    public static void addSterilisation() {
        if (sterilisationQuantity < 4) {
            sterilisationQuantity += 1;
        }
    }
    public static void removeSterilisation() {
        sterilisationQuantity -= 1;
    }


    public static int getFemaleSexChange() {
        return femaleSexChangeQuantity;
    }
    public static void addFemaleSexChange() {
        if (femaleSexChangeQuantity< 4) {
            femaleSexChangeQuantity += 1;
        }
    }
    public static void removeFemaleSexChange() {
        femaleSexChangeQuantity -= 1;
    }

    public static int getMaleSexChange() {
        return maleSexChangeQuantity;
    }
    public static void addMaleSexChange() {
        if (maleSexChangeQuantity < 4) {
            maleSexChangeQuantity += 1;
        }
    }
    public static void removeMaleSexChange() {
        maleSexChangeQuantity -= 1;
    }


    public static int getNoEntrySign() {
        return noEntrySignQuantity;
    }
    public static void addNoEntrySign() {
        if (noEntrySignQuantity < 4) {
            noEntrySignQuantity += 1;
        }
    }
    public static void removeNoEntrySign() {
        noEntrySignQuantity -= 1;
    }


    public static int getBomb() {
        return bombQuantity;
    }
    public static void addBomb() {
        if (bombQuantity < 4) {
            bombQuantity += 1;
        }
    }
    public static void removeBomb() {
        bombQuantity -= 1;
    }

    public static void setupNewInventory(int gas, int deathRat, int poison, int sterilisation, int femaleSexChange,
                                         int maleSexChange, int noEntrySign, int bomb) {
    	gasSpawnRate = gas;
    	deathRatSpawnRate = deathRat;
    	poisonSpawnRate = poison;
    	sterilisationSpawnRate = sterilisation;
    	femaleSexChangeSpawnRate = femaleSexChange;
    	maleSexChangeSpawnRate = maleSexChange;
    	noEntrySignSpawnRate = noEntrySign;
    	bombSpawnRate = bomb;
    	
    	gasTimer = gas;
    	deathratTimer = deathRat;
    	poisonTimer = poison;
    	sterilisationTimer = sterilisation;
    	femaleSexChangeTimer = femaleSexChange;
    	maleSexChangeTimer = maleSexChange;
    	noEntrySignTimer = noEntrySign;
    	bombTimer = bomb;
    	
    	gasQuantity = 0;
    	deathRatQuantity = 0;
    	poisonQuantity = 0;
    	sterilisationQuantity = 0;
    	femaleSexChangeQuantity = 0;
    	maleSexChangeQuantity = 0;
    	noEntrySignQuantity = 0;
    	bombQuantity = 0;
    }
    
    public static void setupQuantity(int gas, int deathRat, int poison, int sterilisation, int femaleSexChange,
                                     int maleSexChange, int noEntrySign, int bomb) {
    	gasQuantity = gas;
    	deathRatQuantity = deathRat;
    	poisonQuantity = poison;
    	sterilisationQuantity = sterilisation;
    	femaleSexChangeQuantity = femaleSexChange;
    	maleSexChangeQuantity = maleSexChange;
    	noEntrySignQuantity = noEntrySign;
    	bombQuantity = 0;
    }
    
    public static void setupSpawnRates(int gas, int deathRat, int poison, int sterilisation, int femaleSexChange,
                                       int maleSexChange, int noEntrySign, int bomb) {
    	gasSpawnRate = gas;
    	deathRatSpawnRate = deathRat;
    	poisonSpawnRate = poison;
    	sterilisationSpawnRate = sterilisation;
    	femaleSexChangeSpawnRate = femaleSexChange;
    	maleSexChangeSpawnRate = maleSexChange;
    	noEntrySignSpawnRate = noEntrySign;
    	bombSpawnRate = bomb;
    }
    
    public static void setupTimers(int gas, int deathRat, int poison, int sterilisation, int femaleSexChange,
                                   int maleSexChange, int noEntrySign, int bomb) {
    	gasTimer = gas;
    	deathratTimer = deathRat;
    	poisonTimer = poison;
    	sterilisationTimer = sterilisation;
    	femaleSexChangeTimer = femaleSexChange;
    	maleSexChangeTimer = maleSexChange;
    	noEntrySignTimer = noEntrySign;
    	bombTimer = bomb;
    }
    
    public static void act() {
    	gasTimer -= 1;
    	deathratTimer -= 1;
    	poisonTimer -= 1;
    	sterilisationTimer -= 1;
    	femaleSexChangeTimer -= 1;
    	maleSexChangeTimer -= 1;
    	noEntrySignTimer-= 1;
    	bombTimer -= 1;
    	
    	if (gasTimer == 0) {
    		addGas();
    		gasTimer = gasSpawnRate;
    	}
    	if (deathratTimer == 0) {
    		addDeathRat();
    		deathratTimer = deathRatSpawnRate;
    	}
    	if (poisonTimer == 0) {
    		addPoison();
    		poisonTimer = poisonSpawnRate;
    	}
    	if (sterilisationTimer == 0) {
    		addSterilisation();
    		sterilisationTimer = sterilisationSpawnRate;
    	}
    	if (femaleSexChangeTimer == 0) {
    		addFemaleSexChange();
    		femaleSexChangeTimer = femaleSexChangeSpawnRate;
    	}
    	if (maleSexChangeTimer == 0) {
    		addMaleSexChange();
    		maleSexChangeTimer = maleSexChangeSpawnRate;
    	}
    	if (noEntrySignTimer == 0) {
    		addNoEntrySign();
    		noEntrySignTimer = noEntrySignSpawnRate;
    	}
    	if (bombTimer == 0) {
    		addBomb();
    		bombTimer = bombSpawnRate;
    	}
    }

    public String toString() {
    	String output = (gasQuantity + " " + deathRatQuantity + " " + poisonQuantity + " " + sterilisationQuantity + " "
                + femaleSexChangeQuantity + " " + maleSexChangeQuantity + " " + noEntrySignQuantity + " " + bombQuantity
                + "\n ");
    	output += (gasSpawnRate + " " + deathRatSpawnRate + " " + poisonSpawnRate + " " + sterilisationSpawnRate + " " +
                femaleSexChangeSpawnRate + " " + maleSexChangeSpawnRate + " " + noEntrySignSpawnRate + " " +
                bombSpawnRate + "\n ");
    	output += (gasTimer + " " + deathratTimer + " " + poisonTimer + " " + sterilisationTimer + " " +
                femaleSexChangeTimer + " " + maleSexChangeTimer + " " + noEntrySignTimer + " " + bombTimer);
    	
    	return output;
    }
}
