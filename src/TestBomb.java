public class TestBomb extends Entity{

    public TestBomb(int[] location) {
        super(location, "Bomb.png");
    }

    public void act() {
        spawnExplosions();
        System.out.println("Bang!");
    }

    private void spawnExplosions() {
    	int[] temp = location;
        for (int i = 50; i <= 300; i+=50) {
            temp[1] += i;
            Entity test = new Explosion(temp);
        }
        for (int i = 50; i <= 300; i+=50) {
            temp[1] -= i;
            Entity test = new Explosion(temp);
        }
        for (int i = 50; i <= 300; i+=50) {
        	temp[0] += i;
        	Entity test = new Explosion(temp);
        }
        for (int i = 50; i <= 300; i+=50) {
            temp[0] -= i;
            Entity test = new Explosion(temp);
        }
    }
}
