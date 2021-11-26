
public class AdultRat extends Rat {

    private static String sex;
    private boolean isPregnant;


    public AdultRat(int health, double speed, boolean sterile, int[] location, String directionFacing, String sex, boolean isPregnant){
        super(health, speed, sterile, location, directionFacing, "AdultRat.png");
        this.sex = sex;
        this.isPregnant = isPregnant;
        this.ratName = "adultRat";
    }
    public void act() {
    	increaseAge();
        pregnancy();
    }

    public void procreate(){
        for (int i = 0; i < randomize(5); i++){
            Entity newAdultRat = new BabyRat(100, 2, sterile, this.location,this.directionFacing);// call constructor of baby rat
        }          
    }
    

    public void pregnancy() {
        for (int i = 0; rats.size() >= i; i++) {
            if ((this.location == rats.get(i).location) && (rats.get(i).sex != rats.get(i).sex))
                if(rats.get(i).ratName == "adultRat") {
                    procreate();
            }
        }
    }

    
    public String getSex(){
    	return this.sex;
    }


    public static void setSex(String sex) {
        AdultRat.sex = sex;
    }

    public String toString(){
        return this.sex + " " + speed + " " + sterile + " " + directionFacing + " " + age + " " + health + rats.size();
    }

    @Override
    public void act(AdultRat rat) {

    }
}
