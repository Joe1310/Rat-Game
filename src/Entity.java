/**
 * <p> 1. File name: Entity.java</p>
 * <p> 3. Creation date: 08.11.2021</p>
 * <p> 4. Last modification date: 05.12.2021</p>
 * <p> 6. Copyright notice: group 02 - CS230 - Swansea University - 2021/22</p>
 * <p> 7. Purpose of the program: control and making connection between classes</p>
 * <p> 8. Version history: 1.0 - pure code; 2.0 - comment added</p>
 * @author Raj, Nick, Elliot, Oliver, Joe, Jay, Shivraj & Patel
 * @version 2.0
 */



import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.ArrayList;

public abstract class Entity {
	
	private static ArrayList<Entity> entities = new ArrayList<Entity>();
	private String imageName;
	private String entityType;
	protected int[] location;
	final int IMAGE_SIZE = 50;

	static GraphicsContext gc;
	
    public Entity(int[] location, String imageName, String entityType) {
    	this.location = location;
    	this.imageName = imageName;
    	this.entityType = entityType;
    	entities.add(this);
    }
    
    public void draw() {
    	this.gc.drawImage(new Image(imageName), (location[0] * IMAGE_SIZE) , (location[1] * IMAGE_SIZE));
    }

    public static void setGC(GraphicsContext gc){
    	Entity.gc = gc;
	}

    public static ArrayList<Entity> getEntities() {
    	return entities;
    }
    
    public void removeEntity() {
    	entities.remove(this);
    }
    
    public String getType() {
    	return entityType;
    }
    
    public static void clearEntities() {
    	gc.clearRect(0, 0, 1850, 1000);
    }
    
    protected void setImageName(String imageName) {
    	this.imageName = imageName;
    }
    
    public abstract void act();
}