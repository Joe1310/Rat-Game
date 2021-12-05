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
	
	private static ArrayList<Entity> entities = new ArrayList<Entity>();
	private String imageName;
	private final String entityType;
	protected int[] location;
	private final int IMAGE_SIZE = 50;

	private static GraphicsContext gc;

	/**
	 * Constructor to create an Entity object.
	 *
	 * @param location The x, y coordinates of the entity.
	 * @param imageName The image to be associated to the entity.
	 * @param entityType The type of object the entity is.
	 */
    public Entity(int[] location, String imageName, String entityType) {
    	this.location = location;
    	this.imageName = imageName;
    	this.entityType = entityType;
    	entities.add(this);
    }

	/**
	 * Method to draw the entity to the Graphics context.
	 */
    public void draw() {
    	this.gc.drawImage(new Image(imageName), (location[0] * IMAGE_SIZE) , (location[1] * IMAGE_SIZE));
    }

	/**
	 * Method to set the Graphics Context that the entity will use to be drawn to.
	 *
	 * @param gc Graphics context object used by the entity.
	 */
    public static void setGC(GraphicsContext gc){
    	Entity.gc = gc;
	}

	/**
	 * Method to get the ArrayList of all entities.
	 *
	 * @return Returns an ArrayList of all entities.
	 */
    public static ArrayList<Entity> getEntities() {
    	return entities;
    }

	/**
	 * Method to remove the entity from the list of all entities.
	 */
    public void removeEntity() {
    	entities.remove(this);
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
	 * Method to remove all entities from the screen.
	 */
    public static void clearEntities() {
    	gc.clearRect(0, 0, 1850, 1000);
    }

	/**
	 * Method to set the image associated with the entity.
	 *
	 * @param imageName Name of the image.
	 */
    protected void setImageName(String imageName) {
    	this.imageName = imageName;
    }

	/**
	 *
	 */
    public abstract void act();
}