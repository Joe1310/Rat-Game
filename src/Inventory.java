/**
 * This class hold the inventory that the player has, and controls it.
 *
 * @author Oliver
 * @version 2.0
 */
public class Inventory {
    private static int gasQuantity;
    private static int deathRatQuantity;
    private static int poisonQuantity;
    private static int sterilisationQuantity;
    private static int femaleSexChangeQuantity;
    private static int maleSexChangeQuantity;
    private static int noEntrySignQuantity;
    private static int bombQuantity;

    private static int gasSpawnRate;
    private static int deathRatSpawnRate;
    private static int poisonSpawnRate;
    private static int sterilisationSpawnRate;
    private static int femaleSexChangeSpawnRate;
    private static int maleSexChangeSpawnRate;
    private static int noEntrySignSpawnRate;
    private static int bombSpawnRate;

    private static int gasTimer;
    private static int deathRatTimer;
    private static int poisonTimer;
    private static int sterilisationTimer;
    private static int femaleSexChangeTimer;
    private static int maleSexChangeTimer;
    private static int noEntrySignTimer;
    private static int bombTimer;

    /**
     * Returns the number of gas items the player has.
     *
     * @return The number of gas items.
     */
    public static int getGas() {
        return gasQuantity;
    }

    /**
     * Adds 1 to the number of gas items the player has, with a maximum cap of 4 of that item.
     */
    public static void addGas() {
        if (gasQuantity < 4) {
            gasQuantity += 1;
        }
    }

    /**
     * Removes 1 from the number of gas items.
     */
    public static void removeGas() {
        gasQuantity -= 1;
    }


    /**
     * Returns the number of death rat items the player has.
     *
     * @return The number of death rat items.
     */
    public static int getDeathRat() {
        return deathRatQuantity;
    }

    /**
     * Adds 1 to the number of death rat items the player has, with a maximum cap of 4 of that item.
     */
    public static void addDeathRat() {
        if (deathRatQuantity < 4) {
            deathRatQuantity += 1;
        }
    }

    /**
     * Removes 1 from the number of death rat items.
     */
    public static void removeDeathRat() {
        deathRatQuantity -= 1;
    }


    /**
     * Returns the number of poison items the player has.
     *
     * @return The number of poison items.
     */
    public static int getPoison() {
        return poisonQuantity;
    }

    /**
     * Adds 1 to the number of poison items the player has, with a maximum cap of 4 of that item.
     */
    public static void addPoison() {
        if (poisonQuantity < 4) {
            poisonQuantity += 1;
        }
    }

    /**
     * Removes 1 from the number of poison items.
     */
    public static void removePoison() {
        poisonQuantity -= 1;
    }


    /**
     * Returns the number of sterilisation items the player has.
     *
     * @return The number of sterilisation items.
     */
    public static int getSterilisation() {
        return sterilisationQuantity;
    }

    /**
     * Adds 1 to the number of sterilisation items the player has, with a maximum cap of 4 of that item.
     */
    public static void addSterilisation() {
        if (sterilisationQuantity < 4) {
            sterilisationQuantity += 1;
        }
    }

    /**
     * Removes 1 from the number of sterilisation items.
     */
    public static void removeSterilisation() {
        sterilisationQuantity -= 1;
    }


    /**
     * Returns the number of female sex change items the player has.
     *
     * @return The number of female sex change items.
     */
    public static int getFemaleSexChange() {
        return femaleSexChangeQuantity;
    }

    /**
     * Adds 1 to the number of female sex change items the player has, with a maximum cap of 4 of that item.
     */
    public static void addFemaleSexChange() {
        if (femaleSexChangeQuantity < 4) {
            femaleSexChangeQuantity += 1;
        }
    }

    /**
     * Removes 1 from the number of female sex change items.
     */
    public static void removeFemaleSexChange() {
        femaleSexChangeQuantity -= 1;
    }


    /**
     * Returns the number of male sex change items the player has.
     *
     * @return The number of male sex change items.
     */
    public static int getMaleSexChange() {
        return maleSexChangeQuantity;
    }

    /**
     * Adds 1 to the number of male sex change items the player has, with a maximum cap of 4 of that item.
     */
    public static void addMaleSexChange() {
        if (maleSexChangeQuantity < 4) {
            maleSexChangeQuantity += 1;
        }
    }

    /**
     * Removes 1 from the number of male sex change items.
     */
    public static void removeMaleSexChange() {
        maleSexChangeQuantity -= 1;
    }


    /**
     * Returns the number of no entry sign items the player has.
     *
     * @return The number of no entry sign items.
     */
    public static int getNoEntrySign() {
        return noEntrySignQuantity;
    }

    /**
     * Adds 1 to the number of no entry sign items the player has, with a maximum cap of 4 of that item.
     */
    public static void addNoEntrySign() {
        if (noEntrySignQuantity < 4) {
            noEntrySignQuantity += 1;
        }
    }

    /**
     * Removes 1 from the number of no entry sign items.
     */
    public static void removeNoEntrySign() {
        noEntrySignQuantity -= 1;
    }


    /**
     * Returns the number of bomb items the player has.
     *
     * @return The number of bomb items.
     */
    public static int getBomb() {
        return bombQuantity;
    }

    /**
     * Adds 1 to the number of bomb items the player has, with a maximum cap of 4 of that item.
     */
    public static void addBomb() {
        if (bombQuantity < 4) {
            bombQuantity += 1;
        }
    }

    /**
     * Removes 1 from the number of bomb items.
     */
    public static void removeBomb() {
        bombQuantity -= 1;
    }

    /**
     * This sets up the whole inventory when given the integers representing the spawn rates of each item.
     *
     * @param gas             The spawn rate of the gas item.
     * @param deathRat        The spawn rate of the death rat item.
     * @param poison          The spawn rate of the poison item.
     * @param sterilisation   The spawn rate of the sterilisation item.
     * @param femaleSexChange The spawn rate of the female sex change item.
     * @param maleSexChange   The spawn rate of the male sex change item.
     * @param noEntrySign     The spawn rate of the no entry sign item.
     * @param bomb            The spawn rate of the bomb item.
     */
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
        deathRatTimer = deathRat;
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

    /**
     * This set the quantities of items using the given integers.
     *
     * @param gas             The quantity of the gas item.
     * @param deathRat        The quantity of the death rat item.
     * @param poison          The quantity of the poison item.
     * @param sterilisation   The quantity of the sterilisation item.
     * @param femaleSexChange The quantity of the female sex change item.
     * @param maleSexChange   The quantity of the male sex change item.
     * @param noEntrySign     The quantity of the no entry sign item.
     * @param bomb            The quantity of the bomb item.
     */
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

    /**
     * This sets the spawn rates of the items using the given integers.
     *
     * @param gas             The spawn rate of the gas item.
     * @param deathRat        The spawn rate of the death rat item.
     * @param poison          The spawn rate of the poison item.
     * @param sterilisation   The spawn rate of the sterilisation item.
     * @param femaleSexChange The spawn rate of the female sex change item.
     * @param maleSexChange   The spawn rate of the male sex change item.
     * @param noEntrySign     The spawn rate of the no entry sign item.
     * @param bomb            The spawn rate of the bomb item.
     */
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

    /**
     * This sets the timers of the items using the given integers.
     *
     * @param gas             The value for the gas timer.
     * @param deathRat        The value for the death rat timer.
     * @param poison          The value for the poison timer.
     * @param sterilisation   The value for the sterilisation timer.
     * @param femaleSexChange The value for the female sex change timer.
     * @param maleSexChange   The value for the male sex change timer.
     * @param noEntrySign     The value for the no entry sign timer.
     * @param bomb            The value for the bomb timer.
     */
    public static void setupTimers(int gas, int deathRat, int poison, int sterilisation, int femaleSexChange, int maleSexChange, int noEntrySign, int bomb) {
        gasTimer = gas;
        deathRatTimer = deathRat;
        poisonTimer = poison;
        sterilisationTimer = sterilisation;
        femaleSexChangeTimer = femaleSexChange;
        maleSexChangeTimer = maleSexChange;
        noEntrySignTimer = noEntrySign;
        bombTimer = bomb;
    }

    /**
     * This is ran once per game tick, and counts all the item timers down. Once they reach 0,
     * the add for that item is called and the timer is reset.
     */
    public static void act() {
        gasTimer -= 1;
        deathRatTimer -= 1;
        poisonTimer -= 1;
        sterilisationTimer -= 1;
        femaleSexChangeTimer -= 1;
        maleSexChangeTimer -= 1;
        noEntrySignTimer -= 1;
        bombTimer -= 1;

        if (gasTimer == 0) {
            addGas();
            gasTimer = gasSpawnRate;
        }
        if (deathRatTimer == 0) {
            addDeathRat();
            deathRatTimer = deathRatSpawnRate;
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

    /**
     * Returns all the quantities of each item.
     *
     * @return A string containing every item quantity.
     */
    public static String returnQuantity() {
        return (gasQuantity + " " + deathRatQuantity + " " + poisonQuantity + " " + sterilisationQuantity + " " +
                femaleSexChangeQuantity + " " + maleSexChangeQuantity + " " + noEntrySignQuantity + " " + bombQuantity);
    }

    /**
     * Returns all the spawn rates of each item.
     *
     * @return A string containing all the spawn rates of each item.
     */
    public static String returnSpawnRates() {
        return (gasSpawnRate + " " + deathRatSpawnRate + " " + poisonSpawnRate + " " + sterilisationSpawnRate + " " +
                femaleSexChangeSpawnRate + " " + maleSexChangeSpawnRate + " " + noEntrySignSpawnRate + " " +
                bombSpawnRate);
    }

    /**
     * Returns all the current counts on each item's timer.
     *
     * @return A string containing all the current counts on each item's timer.
     */
    public static String returnTimers() {
        return (gasTimer + " " + deathRatTimer + " " + poisonTimer + " " + sterilisationTimer + " " +
                femaleSexChangeTimer + " " + maleSexChangeTimer + " " + noEntrySignTimer + " " + bombTimer);
    }

}
