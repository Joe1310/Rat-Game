/**
 * <p> 1. File name: BabyRat.java</p>
 * <p> 3. Creation date: 08.11.2021</p>
 * <p> 4. Last modification date: 05.12.2021</p>
 * <p> 6. Copyright notice: group 02 - CS230 - Swansea University - 2021/22</p>
 * <p> 7. Purpose of the program: Baby rat behavior</p>
 * <p> 8. Version history: 1.0 - pure code; 2.0 - comment added</p>
 * @author Raj, Nick, Elliot, Oliver, Joe, Jay, Shivraj & Patel
 */




public class BabyRat extends Rat {
    private int age;
    private String sex;

    public BabyRat(int[] location, String directionFacing, boolean sterile){
        super(100, sterile, location, directionFacing, "BabyRat.png", "BabyRat");
        this.age = 0;
        this.sex = randomSex();
    }

    public BabyRat(String sex, int[] location, String directionFacing, int health, boolean sterile){
        super(health, sterile, location, directionFacing, "BabyRat.png", "BabyRat");
        this.age = 0;
        if (sex.equals("BF") || sex.equals("F")) {
            this.sex = "F";
        } else {
            this.sex = "M";
        }
    }

    public void act() {
        move();
        increaseAge();
    }

    public void increaseAge() {
        age++;
        if (this.age == 14) {
            becomeAdult();
        }
    }

    public void becomeAdult(){
        Entity newAdultRat = new AdultRat(this.sex, this.location, this.directionFacing, this.health, this.sterile);// check if correct
        removeEntity();
        rats.remove(this);
    }

    public String randomSex() {
        if (randomize(2) == 1) {
            return "F";
        } else {
            return "M";
        }
    }

    public String toString(){
        return "B" + " " + sex  + " " + this.location[0] + " " + this.location[1] + " " + directionFacing + " "  + health  + " " + sterile;
    }
}