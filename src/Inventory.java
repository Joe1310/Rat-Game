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
    // functions remove'ItemName' takes one off the amount of them
    public int getGas() {
        return gasQuantity;
    }
    public void removeGas() {
        this.gasQuantity -= 1;
    }


    public int getDeathRat() {
        return deathRatQuantity;
    }
    public void removeDeathRat() {
        this.deathRatQuantity -= 1;
    }


    public int getPoison() {
        return poisonQuantity;
    }
    public void removePoison() {
        this.poisonQuantity -= 1;
    }


    public int getSterilization() {
        return sterilizationQuantity;
    }
    public void removeSterilization() {
        this.sterilizationQuantity -= 1;
    }


    public int getSexChange() {
        return sexChangeQuantity;
    }
    public void removeSexChange() {
        this.sexChangeQuantity -= 1;
    }


    public int getNoEntrySign() {
        return noEntrySignQuantity;
    }
    public void removeNoEntrySign() {
        this.noEntrySignQuantity -= 1;
    }


    public int getBomb() {
        return bombQuantity;
    }
    public void removeBomb() {
        this.bombQuantity -= 1;
    }

}
