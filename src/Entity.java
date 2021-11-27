import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.ArrayList;

public abstract class Entity {
	
	private static ArrayList<Entity> entities = new ArrayList<Entity>();
	private static ArrayList<Entity> toAdd = new ArrayList<Entity>();
	private static ArrayList<Entity> toRemove = new ArrayList<Entity>();
	private String imageName;
	protected int[] location;
	
    public Entity(int[] location, String imageName) {
    	this.location = location;
    	this.imageName = imageName;
    	toAdd.add(this);
    }
    
    public void draw(GraphicsContext gc) {
    	gc.drawImage(new Image(imageName), location[0], location[1]);
    }
    
    public static ArrayList<Entity> getEntities() {
    	return(entities);
    }
    
    public void removeEntity() {
    	toRemove.add(this);
    }
    
    public static void updateEntities() {
    	entities.removeAll(toRemove);
    	entities.addAll(toAdd);
    }
    
    public abstract void act();
}