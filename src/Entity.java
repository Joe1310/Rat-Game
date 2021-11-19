public abstract class Entity {
	
	private Entity[] entities;
	private int[] location;
	private String imageName;
	
    public Entity(int[] location) {
    	this.location = location;
    	this 
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