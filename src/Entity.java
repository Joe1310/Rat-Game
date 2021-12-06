import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * Class to model an Entity object.
 *
 * @author Elliot, Ollie
 * @version 1.0
 */
public abstract class Entity {

    private static final ArrayList<Entity> ENTITIES = new ArrayList<Entity>();
    private String imageName;
    private final String entityType;
    protected int[] location;

    private static GraphicsContext gc;

    /**
     * Constructor to create an Entity object.
     *
     * @param location   The x, y coordinates of the entity.
     * @param imageName  The image to be associated to the entity.
     * @param entityType The type of object the entity is.
     */
    public Entity(int[] location, String imageName, String entityType) {
        this.location = location;
        this.imageName = imageName;
        this.entityType = entityType;
        ENTITIES.add(this);
    }

    /**
     * Method to draw the entity to the Graphics context.
     */
    public void draw() {
        int imageSize = 50;
        gc.drawImage(new Image(imageName), (location[0] * imageSize), (location[1] * imageSize));
    }

    /**
     * Method to set the Graphics Context that the entity will use to be drawn to.
     *
     * @param gc Graphics context object used by the entity.
     */
    public static void setGC(GraphicsContext gc) {
        Entity.gc = gc;
    }

    /**
     * Method to get the ArrayList of all entities.
     *
     * @return Returns an ArrayList of all entities.
     */
    public static ArrayList<Entity> getEntities() {
        return ENTITIES;
    }

    /**
     * Method to remove the entity from the list of all entities.
     */
    public void removeEntity() {
        ENTITIES.remove(this);
    }

    /**
     * Method to get the type of the entity.
     *
     * @return Returns the entity's type.
     */
    public String getType() {
        return entityType;
    }

    /**
     * Method to run actions for entities every tick.
     */
    public abstract void act();

    /**
     * Method to set the image associated with the entity.
     *
     * @param imageName Name of the image.
     */
    protected void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
