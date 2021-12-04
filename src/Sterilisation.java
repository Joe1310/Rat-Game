import java.util.ArrayList;
import java.util.Arrays;

class Sterilisation extends Entity {
    private int spreadLimit;
    private String direction;
    private boolean hasSpread;
    private int timer = 15;

    public Sterilisation(int[] location, boolean hasSpread) {
        super(location, "Sterilisation.png", "Sterilisation");
        this.spreadLimit = 3;
        this.hasSpread = hasSpread;
        this.direction = null;

    }
    public Sterilisation(int[] location, int spreadLimit, String direction, boolean hasSpread) {
        super(location, "Sterilisation.png", "Sterilisation");
        this.spreadLimit = spreadLimit;
        this.hasSpread = hasSpread;
        this.direction = direction;
    }

    public void act() {
        steriliseRat();
        if (!hasSpread && spreadLimit > 0) {
            spread();
            hasSpread = true;
        }
        disappear();
    }

    public void disappear() {
        timer--;
        if (timer == 0) {
            removeEntity();
        }
    }

    private void steriliseRat() {
        for (int i = Rat.getRats().size()-1; i > -1; i--) {
            if (Arrays.equals(Rat.getRats().get(i).location, this.location) &&
                    !(Rat.getRats().get(i).getRatType().equals("DeathRat"))) {
                (Rat.getRats().get(i)).ratSterile();
            }
        }
    }

    public void spread() {
        ArrayList<String> directions = Map.getMovementOptions(location[0], location[1]);
        directions.remove(direction);
        for (String d : directions) {
            int[] tempLocation = new int[2];
            switch(d) {
                case "N":
                    tempLocation[0] = location[0];
                    tempLocation[1] = location[1] - 1;
                    Entity nSterile = new Sterilisation(tempLocation, spreadLimit - 1, "S", false);
                    break;
                case "S":
                    tempLocation[0] = location[0];
                    tempLocation[1] = location[1] + 1;
                    Entity sSterile = new Sterilisation(tempLocation, spreadLimit - 1, "N", false);
                    break;
                case "E":
                    tempLocation[0] = location[0] + 1;
                    tempLocation[1] = location[1];
                    Entity eSterile = new Sterilisation(tempLocation, spreadLimit - 1, "W", false);
                    break;
                case "W":
                    tempLocation[0] = location[0] - 1;
                    tempLocation[1] = location[1];
                    new Sterilisation(tempLocation, spreadLimit - 1, "E", false);
                    break;
            }
        }
    }

}