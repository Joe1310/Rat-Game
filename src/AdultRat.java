import java.util.random;
public class AdultRat extends Rat {
    private boolean sex;
    private boolean isPregnant;

    public AdultRat(int health, double speed, boolean sterile, int[] location, char directionFacing, int population, boolean sex, boolean isPregnant){
        super(health, speed, sterile, location, directionFacing, population, "AdultRat.png");
        this.sex = sex;
        this.isPregnant = isPregnant;
    }

    public void procreate(){
        for (int i = 0; i < randomize(); i++){
            Entity test = new BabyRat(100, 2, sterile, this.location,this.directionFacing, this.population);// call constructor of baby rat
        }          
    }

    public void pregnancy(){
        for(int i = 0; Rats.size() >= i; i ++){
            if (this.Rat.location = Rats(i).location && this.Rat.sex != Rats(i).sex && this.Rat.sex = false){// check if working
                procreate();                 
            }
        }
       
    }
}
