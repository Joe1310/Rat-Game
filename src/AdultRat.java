public class AdultRat extends Rat {
    private char sex;
    private boolean isPregnant;

    public AdultRat(int health, double speed, boolean sterile, int[] location, String directionFacing, int population, char sex, boolean isPregnant){
        super(health, speed, sterile, location, directionFacing, population, "AdultRat.png");
        this.sex = sex;
        this.isPregnant = isPregnant;
    }
    public void act() {
    	//increaseAge();
        pregnancy();
    }
    public void procreate(){
        for (int i = 0; i < randomize(5); i++){
            Entity test = new BabyRat(100, 2, sterile, this.location,this.directionFacing, this.population);// call constructor of baby rat
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
}
