import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.ArrayList;

public abstract class Entity {
	
	private static ArrayList<Entity> entities;
	private String imageName;
	private int[] location;
	
    public Entity(int[] location) {
    	this.location = location;
    	// something to add this entity to the entities array
    }
    
    public void draw(GraphicsContext gc) {
    	gc.drawImage(new Image(imageName), location[0], location[1]);
    }
    
    public ArrayList<Entity> getEntites() {
    	return(entities);
    }
    
    public void removeEntity() {
    	entities.remove(this);
    }
}