public class Bomb extends Entity{
    private int countdown;

    public Bomb(int[] location) {
        super(location, "Bomb.png");
        this.countdown = 10;
    }

    public Bomb(int[] location, int countdown) {
        super(location, "Bomb.png");
        this.countdown = countdown;
    }



    public void act() {
        if (countdown == 0) {
            spawnExplosions();
            removeEntity();
        }
        countdown -= 1;
    }

    private void spawnExplosions() {
        Entity explosion = new Explosion(location);
        int[] temp = location;
        int i = 0;

        while (Map.getMovementOptions(location[0], location[1]+i).contains("n")) {
            temp[1] += i;
            explosion = new Explosion(temp);
            i++;
        }
        temp = location;
        i = 0;
        while (Map.getMovementOptions(location[0], location[1]-i).contains("s")) {
            temp[1] -= i;
            explosion = new Explosion(temp);
            i++;
        }
        temp = location;
        i = 0;
        while (Map.getMovementOptions(location[0]+i, location[1]).contains("e")) {
            temp[0] += i;
            explosion = new Explosion(temp);
            i++;
        }
        temp = location;
        i = 0;
        while (Map.getMovementOptions(location[0]-i, location[1]).contains("w")) {
            temp[0] -= i;
            explosion = new Explosion(temp);
            i++;
        }
    }
}


