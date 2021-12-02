
public class AdultRat extends Rat {

    private String sex;
    private boolean isPregnant;

    public AdultRat(String sex, int health, boolean sterile, int[] location, String directionFacing, boolean isPregnant){
    	super(health, 5, sterile, location, directionFacing, (sex + "Rat.png"), (sex + "Rat"));
        this.sex = sex;
        this.isPregnant = isPregnant;
    }

    public void act() {
    	move();
        //pregnancy();
    }

    public void procreate(){
        for (int i = 0; i < randomize(5); i++){
            Entity baby = new BabyRat(100, sterile, this.location,this.directionFacing);// call constructor of baby rat
        }
    }

    public void pregnancy() {
        for (int i = 0; rats.size() >= i; i++) {
            if ((this.location == rats.get(i).location) && (rats.get(i) != rats.get(i)))
                if(rats.get(i).getRatType() == "adultRat") {
                    procreate();
                }
        }
    }

    public String getSex(){
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String toString(){
        return this.sex + " " + this.location[0] + " " + this.location[1] + " " + health + " " + directionFacing + " " + sterile + " " + isPregnant;
    }
}