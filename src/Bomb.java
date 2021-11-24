import java.util.ArrayList;

public class Bomb extends Entity{
    private int countdown;
    private ArrayList<Explosion> explosions;

    public Bomb(int[] location) {
        super(location, "Bomb.png");
        this.countdown = 5;
    }

    public void act() {
        if (countdown == 0) {
            spawnExplosions();
        }
        countdown -= 1;
    }

    private void spawnExplosions() {
        for (int i = 0; i < 5; i++) {
            int[] temp = location;
            while (Map.getMovementOptions(location[0], location[1]+i).contains("n")) {
                temp[1] += i;
                explosions.add(new Explosion(temp));
            }
            while (Map.getMovementOptions(location[0], location[1]-i).contains("s")) {
                temp[1] -= i;
                explosions.add(new Explosion(temp));
            }
            while (Map.getMovementOptions(location[0]+i, location[1]).contains("e")) {
                temp[0] += i;
                explosions.add(new Explosion(temp));
            }
            while (Map.getMovementOptions(location[0]-i, location[1]).contains("w")) {
                temp[0] -= i;
                explosions.add(new Explosion(temp));
            }
        }
    }
}


