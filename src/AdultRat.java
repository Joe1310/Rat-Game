public class AdultRat extends Rat {
    private boolean sex;
    private boolean isPregnant;

    public AdultRat(int health, double speed, boolean sterile, int[] location, char directionFacing, int population, boolean sex, boolean isPregnant){
        super(health, speed, sterile, location, directionFacing, population);
        this.sex = sex;
        this.isPregnant = isPregnant;
        Rats += this.AdultRat;
    }

    public void procreate(){
        //creats instances of baby rat  
    }

    public void pregnancy(){
        //check wich other rats are on the same location and if their of the oposit gender if so procreate
    }




    
}
