public class Inventory {
    private int gasQuantity;
    private int deathRatQuantity;
    private int poisonQuantity;
    private int sterilizationQuantity;
    private int sexChangeQuantity;
    private int noEntrySignQuantity;
    private int bombQuantity;

    public Inventory(int gas, int deathRat, int poison, int sterilization, int sexChange, int noEntrySign, int bomb) {
        this.gasQuantity = gas;
        this.deathRatQuantity = deathRat;
        this.poisonQuantity = poison;
        this.sterilizationQuantity = sterilization;
        this.sexChangeQuantity = sexChange;
        this.noEntrySignQuantity = noEntrySign;
        this.bombQuantity = bomb;
    }

    // functions get'ItemName' returns the amount of them in the inventory
    // functions add'ItemName' adds one to the amount of items, as long as its below 5 which is the item cap
    // functions remove'ItemName' takes one off the amount of them
    public int getGas() {
        return gasQuantity;
    }
    public void addGas() {
        if (this.gasQuantity < 5) {
            this.gasQuantity += 1;
        }
    }
    public void removeGas() {
        this.gasQuantity -= 1;
    }


    public int getDeathRat() {
        return deathRatQuantity;
    }
    public void addDeathRat() {
        if (this.deathRatQuantity < 5) {
            this.deathRatQuantity += 1;
        }
    }
    public void removeDeathRat() {
        this.deathRatQuantity -= 1;
    }


    public int getPoison() {
        return poisonQuantity;
    }
    public void addPoison() {
        if (this.poisonQuantity < 5) {
            this.poisonQuantity += 1;
        }
    }
    public void removePoison() {
        this.poisonQuantity -= 1;
    }


    public int getSterilization() {
        return sterilizationQuantity;
    }
    public void addSterilization() {
        if (this.sterilizationQuantity < 5) {
            this.sterilizationQuantity += 1;
        }
    }
    public void removeSterilization() {
        this.sterilizationQuantity -= 1;
    }


    public int getSexChange() {
        return sexChangeQuantity;
    }
    public void addSexChange() {
        if (this.sexChangeQuantity < 5) {
            this.sexChangeQuantity += 1;
        }
    }
    public void removeSexChange() {
        this.sexChangeQuantity -= 1;
    }


    public int getNoEntrySign() {
        return noEntrySignQuantity;
    }
    public void addNoEntrySign() {
        if (this.noEntrySignQuantity < 5) {
            this.noEntrySignQuantity += 1;
        }
    }
    public void removeNoEntrySign() {
        this.noEntrySignQuantity -= 1;
    }


    public int getBomb() {
        return bombQuantity;
    }
    public void addBomb() {
        if (this.bombQuantity < 5) {
            this.bombQuantity += 1;
        }
    }
    public void removeBomb() {
        this.bombQuantity -= 1;
    }


}
