public class DeathRat extends Rat {
    private int numOfKilled = 0;
    private int timeStationary = 0;
    public DeathRat(int health, double speed, boolean sterile, int[] location, char directionFacing, int population, String image) {
        super(health, speed, sterile, location, directionFacing, population, "DeathRat.png");//add file for rat image        
    }
    private void killRat(){
        for(int i = 0; Rats.size() >= i; i ++){
            if (this.Rat.location = Rats(i).location && this.Rat != Rats(i)){
                Rats(i).ratDeath();// not sure if correct
                numOfKilled = numOfKilled ++;               
            }
        }
        if ( numOfKilled = 5){
            removeEntity();
        }
    }
    public void act() {
        timeStationary ++;
        if (timeStationary >= 5){
            move();
        } 
    }  
}
