public class DeathRat extends Rat {
	
    private int numOfKilled = 0;
    private int timeStationary = 0;
    
    public DeathRat(int health, boolean sterile, int[] location, String directionFacing) {
        super(health, 5, sterile, location, directionFacing, "DeathRat.png", "death");//add file for rat image
    }
    
    private void killRat(){
        for(int i = 0; rats.size() >= i; i ++){
            if (this.location == rats.get(i).location && this != rats.get(i)){
                rats.get(i).ratDeath();// not sure if correct
                numOfKilled = numOfKilled ++;               
            }
        }
        if (numOfKilled == 5){
            removeEntity();
        }
    }
    
    public void act() {
        timeStationary ++;
        killRat();
        if (timeStationary >= 5){
            move();
        } 
    }

    public String toString(){
        return "D" + " " + this.location[0] + " " + this.location[1] + " " + health + " " + directionFacing;
    }
}
