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

    // functions get'ItemName' returns the amount of them in the inventory
    // functions add'ItemName' adds one to the amount of items, as long as its below 5 which is the item cap
    // functions remove'ItemName' takes one off the amount of them
    public static int getGas() {
        return gasQuantity;
    }
    public static void addGas() {
        if (gasQuantity < 5) {
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
        if (deathRatQuantity < 5) {
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
        if (poisonQuantity < 5) {
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
        if (sterilisationQuantity < 5) {
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
        if (femaleSexChangeQuantity< 5) {
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
        if (maleSexChangeQuantity < 5) {
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
        if (noEntrySignQuantity < 5) {
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
        if (bombQuantity < 5) {
            bombQuantity += 1;
        }
    }
    public static void removeBomb() {
        bombQuantity -= 1;
    }

    public static void setupInventory(int gas, int deathRat, int poison, int sterilisation, int femaleSexChange, int maleSexChange, int noEntrySign, int bomb) {
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

}
