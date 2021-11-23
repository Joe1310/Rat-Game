import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.ArrayList;

public abstract class Entity {
	
	private static ArrayList<Entity> entities;
	private String imageName;
	protected int[] location;
	
    public Entity(int[] location, String imageName) {
    	this.location = location;
    	this.imageName = imageName;
    	entities.add(this);
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
    
    public abstract void act();
}