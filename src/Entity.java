public abstract class Entity {
	
	protected int[] location;
	private Object entityType;
	
    public Entity(Object obj, int[] location){
    	entityType = obj;
    	this.location = location;
    }
    
    public abstract void draw();
}