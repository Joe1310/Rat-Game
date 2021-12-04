public class Inventory {
    static int gasQuantity;
    static int deathRatQuantity;
    static int poisonQuantity;
    static int sterilisationQuantity;
    static int femaleSexChangeQuantity;
    static int maleSexChangeQuantity;
    static int noEntrySignQuantity;
    static int bombQuantity;

    public Inventory(int gas, int deathRat, int poison, int sterilisation, int femaleSexChange, int maleSexChange, int noEntrySign, int bomb) {
        gasQuantity = gas;
        deathRatQuantity = deathRat;
        poisonQuantity = poison;
        sterilisationQuantity = sterilisation;
        femaleSexChangeQuantity = femaleSexChange;
        maleSexChangeQuantity = maleSexChange;
        noEntrySignQuantity = noEntrySign;
        bombQuantity = bomb;
    }

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


    public static int getSterilization() {
        return sterilisationQuantity;
    }
    public static void addSterilization() {
        if (sterilisationQuantity < 5) {
            sterilisationQuantity += 1;
        }
    }
    public static void removeSterilization() {
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


}
