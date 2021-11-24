/* Mateing of rats - 3 sec.
 After mateing the female rat takes 3.5 sec to start giving birth -- One at a time.

 */

public class AdultRat extends Rat {
    private char sex;
    private boolean isPregnant;

    public AdultRat(int health, double speed, boolean sterile, int[] location, String directionFacing, char sex, boolean isPregnant){
        super(health, speed, sterile, location, directionFacing, "AdultRat.png");
        this.sex = sex;
        this.isPregnant = isPregnant;
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
    
    //youre gonna have to add a way to check the rats are adult rats becuase you cant call a rats sex if it isnt an adult
    public void pregnancy(){
        for(int i = 0; rats.size() >= i; i ++){
            if (this.location == rats.get(i).location && this.sex != rats.get(i).sex ){// check if working
                procreate();                 
            }
        }
    }

    protected boolean checkAdult(){
        if ()
    }


    public String toString(){
        return this.sex + " " + speed + " " + sterile + " " + directionFacing + " " + age + " " + health + rats.size();
    }
}
