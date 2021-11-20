public abstract class Entity {
	
	private static Entity[] entities;
	private String imageName;
	private int[] location;
	
    public Entity(int[] location) {
    	this.location = location;
    	// something to add this entity to the entities array
    }
    
    public void draw(GraphicsContext gc) {
    	gc.drawImage = new Image(imageName);
    }
    
    public Entity[] getEntites() {
    	return(entities);
    }
    
    public void remove() {
    	
    }
}