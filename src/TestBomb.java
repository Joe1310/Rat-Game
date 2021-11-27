public class TestBomb extends Entity{

    public TestBomb(int[] location) {
        super(location, "Bomb.png");
    }

    public void act() {
        spawnExplosions();
        System.out.println(this.location[0] + " " + this.location[1]);
    }

    private void spawnExplosions() {
    	int[] temp = this.location;
    	temp[1] += 50;
        Entity explo = new Explosion(temp);
        temp = this.location;
    }

}
