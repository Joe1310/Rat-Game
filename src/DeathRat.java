public class DeathRat extends Rat {
    private int numOfKilled = 0;
    private int timeStionary;
    public DeathRat(int health, double speed, boolean sterile, int[] location, char directionFacing, int population) {
        super(health, speed, sterile, location, directionFacing, population);//add file for rat image        
    }
    private void killRat(){
        //removes rat that has been killed (need to research)
        for(int i = 0; Rats.size() >= i; i ++){
            if (this.Rat.location = Rats(i).location){
                //remove rat
                numOfKilled = numOfKilled ++;               
            }
        }
    }
    private void startMoving(){
        //need to reaserch timers
    }


    
}
