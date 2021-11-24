public class Explosion extends Entity {

    public Explosion(int[] location) {
        super(location, "Explosion.png");
    }

    public void act() {
    	removeEntity();
    }
}
