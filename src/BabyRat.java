public class BabyRat extends Rat {
    private int age;
    private String sex;

    public BabyRat(int health, boolean sterile, int[] location, String directionFacing){
        super(health, sterile, location, directionFacing, "BabyRat.png", "BabyRat");
        this.age = 0;
        this.sex = randomSex();
    }

    public BabyRat(String sex, int health, boolean sterile, int[] location, String directionFacing){
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
        Entity newAdultRat = new AdultRat(this.sex, this.health, this.sterile, this.location, this.directionFacing, false);// check if correct
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
        return "B" + sex + " "  + health  + " " + sterile + " " + this.location[0] + " " + this.location[1] + " " + directionFacing;
    }
}