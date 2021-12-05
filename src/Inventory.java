/**
 * This class hold the inventory that the player has, and controls it
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

    /**
     * Returns the number of gas items the player has
     * @return The number of gas items
     */
    public static int getGas() {
        return gasQuantity;
    }
    /**
     * Adds 1 to the number of gas items the player has, with a maximum cap of 4 of that item
     */
    public static void addGas() {
        if (gasQuantity < 4) {
            gasQuantity += 1;
        }
    }
    /**
     * Removes 1 from the number of gas items
     */
    public static void removeGas() {
        gasQuantity -= 1;
    }

    
    /**
     * Returns the number of death rat items the player has
     * @return The number of death rat items
     */
    public static int getDeathRat() {
        return deathRatQuantity;
    }
    /**
     * Adds 1 to the number of death rat items the player has, with a maximum cap of 4 of that item
     */
    public static void addDeathRat() {
        if (deathRatQuantity < 4) {
            deathRatQuantity += 1;
        }
    }
    /**
     * Removes 1 from the number of death rat items
     */
    public static void removeDeathRat() {
        deathRatQuantity -= 1;
    }


    /**
     * Returns the number of poison items the player has
     * @return The number of poison items
     */
    public static int getPoison() {
        return poisonQuantity;
    }
    /**
     * Adds 1 to the number of poison items the player has, with a maximum cap of 4 of that item
     */
    public static void addPoison() {
        if (poisonQuantity < 4) {
            poisonQuantity += 1;
        }
    }
    /**
     * Removes 1 from the number of poison items
     */
    public static void removePoison() {
        poisonQuantity -= 1;
    }


    /**
     * Returns the number of sterilisation items the player has
     * @return The number of sterilisation items
     */
    public static int getSterilisation() {
        return sterilisationQuantity;
    }
    /**
     * Adds 1 to the number of sterilisation items the player has, with a maximum cap of 4 of that item
     */
    public static void addSterilisation() {
        if (sterilisationQuantity < 4) {
            sterilisationQuantity += 1;
        }
    }
    /**
     * Removes 1 from the number of sterilisation items
     */
    public static void removeSterilisation() {
        sterilisationQuantity -= 1;
    }


    /**
     * Returns the number of female sex change items the player has
     * @return The number of female sex change items
     */
    public static int getFemaleSexChange() {
        return femaleSexChangeQuantity;
    }
    /**
     * Adds 1 to the number of female sex change items the player has, with a maximum cap of 4 of that item
     */
    public static void addFemaleSexChange() {
        if (femaleSexChangeQuantity< 4) {
            femaleSexChangeQuantity += 1;
        }
    }
    /**
     * Removes 1 from the number of female sex change items
     */
    public static void removeFemaleSexChange() {
        femaleSexChangeQuantity -= 1;
    }

    
    /**
     * Returns the number of male sex change items the player has
     * @return The number of male sex change items
     */
    public static int getMaleSexChange() {
        return maleSexChangeQuantity;
    }
    /**
     * Adds 1 to the number of male sex change items the player has, with a maximum cap of 4 of that item
     */
    public static void addMaleSexChange() {
        if (maleSexChangeQuantity < 4) {
            maleSexChangeQuantity += 1;
        }
    }
    /**
     * Removes 1 from the number of male sex change items
     */
    public static void removeMaleSexChange() {
        maleSexChangeQuantity -= 1;
    }


    /**
     * Returns the number of no entry sign items the player has
     * @return The number of no entry sign items
     */
    public static int getNoEntrySign() {
        return noEntrySignQuantity;
    }
    /**
     * Adds 1 to the number of no entry sign items the player has, with a maximum cap of 4 of that item
     */
    public static void addNoEntrySign() {
        if (noEntrySignQuantity < 4) {
            noEntrySignQuantity += 1;
        }
    }
    /**
     * Removes 1 from the number of no entry sign items
     */
    
    
    public static void removeNoEntrySign() {
        noEntrySignQuantity -= 1;
    }


    /**
     * Returns the number of bomb items the player has
     * @return The number of bomb items
     */
    public static int getBomb() {
        return bombQuantity;
    }
    /**
     * Adds 1 to the number of bomb items the player has, with a maximum cap of 4 of that item
     */
    public static void addBomb() {
        if (bombQuantity < 4) {
            bombQuantity += 1;
        }
    }
    /**
     * Removes 1 from the number of bomb items
     */
    public static void removeBomb() {
        bombQuantity -= 1;
    }

    /**
     * This setups everything 
     * @param gas
     * @param deathRat
     * @param poison
     * @param sterilisation
     * @param femaleSexChange
     * @param maleSexChange
     * @param noEntrySign
     * @param bomb
     */
    public static void setupNewInventory(int gas, int deathRat, int poison, int sterilisation, int femaleSexChange, int maleSexChange, int noEntrySign, int bomb) {
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
    
    public static void setupQuantity(int gas, int deathRat, int poison, int sterilisation, int femaleSexChange, int maleSexChange, int noEntrySign, int bomb) {
    	gasQuantity = gas;
    	deathRatQuantity = deathRat;
    	poisonQuantity = poison;
    	sterilisationQuantity = sterilisation;
    	femaleSexChangeQuantity = femaleSexChange;
    	maleSexChangeQuantity = maleSexChange;
    	noEntrySignQuantity = noEntrySign;
    	bombQuantity = bomb;
    }
    
    public static void setupSpawnRates(int gas, int deathRat, int poison, int sterilisation, int femaleSexChange, int maleSexChange, int noEntrySign, int bomb) {
    	gasSpawnRate = gas;
    	deathRatSpawnRate = deathRat;
    	poisonSpawnRate = poison;
    	sterilisationSpawnRate = sterilisation;
    	femaleSexChangeSpawnRate = femaleSexChange;
    	maleSexChangeSpawnRate = maleSexChange;
    	noEntrySignSpawnRate = noEntrySign;
    	bombSpawnRate = 0;
    }
    
    public static void setupTimers(int gas, int deathRat, int poison, int sterilisation, int femaleSexChange, int maleSexChange, int noEntrySign, int bomb) {
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

    public static String returnQuantity() {
    	return (gasQuantity + " " + deathRatQuantity + " " + poisonQuantity + " " + sterilisationQuantity + " " + femaleSexChangeQuantity + " " + maleSexChangeQuantity + " " + noEntrySignQuantity + " " + bombQuantity);
    }
    public static String returnSpawnRates() {
    	return (gasSpawnRate + " " + deathRatSpawnRate + " " + poisonSpawnRate + " " + sterilisationSpawnRate + " " + femaleSexChangeSpawnRate + " " + maleSexChangeSpawnRate + " " + noEntrySignSpawnRate + " " + bombSpawnRate);
    }
    public static String returnTimers() {
    	return (gasTimer + " " + deathratTimer + " " + poisonTimer + " " + sterilisationTimer + " " + femaleSexChangeTimer + " " + maleSexChangeTimer + " " + noEntrySignTimer + " " + bombTimer);
    }
    
}
