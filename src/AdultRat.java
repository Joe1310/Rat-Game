
public class AdultRat extends Rat {

    private String sex;
    private boolean isPregnant;

    public AdultRat(int health, double speed, boolean sterile, int[] location, String directionFacing, String sex, boolean isPregnant){
        super(health, speed, sterile, location, directionFacing, "AdultRat.png", "adult");
        this.sex = sex;
        this.isPregnant = isPregnant;
        this.ratName = "adultRat";
    }
    
    public void act() {
        pregnancy();
    }

    public void procreate(){
        for (int i = 0; i < randomize(5); i++){
            Entity baby = new BabyRat(100, 2, sterile, this.location,this.directionFacing);// call constructor of baby rat
        }          
    }

    public void pregnancy() {
        for (int i = 0; rats.size() >= i; i++) {
            if ((this.location == rats.get(i).location) && (rats.get(i).sex != rats.get(i).getSex))
                if(rats.get(i).ratName == "adultRat") {
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
        return this.sex + " " + speed + " " + sterile + " " + directionFacing + " " + health;
    }
}
