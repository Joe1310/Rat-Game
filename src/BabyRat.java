public class BabyRat extends Rat {
    private int age;
    
    public BabyRat(int health, double speed, boolean sterile, int[] location, char directionFacing, int population){
        super(health, speed, sterile, location, directionFacing, population, "BabyRat.png");
        this.age = 0;
        modifySpeed(2);
        ;
    }
    
    public void act() {
    	move();
    	increaseAge();
    }

    public void increaseAge(){
        this.age = this.age ++;
        if (age == 5) {
            becomeAdult();
        }
    }
    private void move (){
        increaseAge();      
    }
    //in progress
    public void becomeAdult(){
        Entity test = new AdultRat(this.health , 1 , this.sterile, this.location, this.directionFacing, this.population);// check if correct
        removeEntity(); 
        Rat test = new Rat(this.health , 1 , this.sterile, this.location, this.directionFacing, this.population);// check if correct 
        this.BabyRat = null;  
    }


    
    
}
