public class TestBomb extends Entity{

    public TestBomb(int[] location) {
        super(location, "Bomb.png");
    }

    public void act() {
        spawnExplosions();
        System.out.println(this.location[0] + " " + this.location[1]);
        removeEntity();
    }

    private void spawnExplosions() {
    	if (true) {
	    	if (true) {
	    		int[] explosionLocation = {this.location[0], this.location[1]};
	    		Entity explo = new Explosion(explosionLocation);
	    	}
	    	if (true) {
	    		if (true) {
	        		int[] explosionLocation = {this.location[0], this.location[1] - 50};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	        	if (true) {
	        		int[] explosionLocation = {this.location[0], this.location[1] + 50};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	        	if (true) {
	        		int[] explosionLocation = {this.location[0] - 50, this.location[1]};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	        	if (true) {
	        		int[] explosionLocation = {this.location[0] + 50, this.location[1]};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	    	}
	    	if (true) {
	    		if (true) {
	        		int[] explosionLocation = {this.location[0], this.location[1] - 100};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	        	if (true) {
	        		int[] explosionLocation = {this.location[0], this.location[1] + 100};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	        	if (true) {
	        		int[] explosionLocation = {this.location[0] - 100, this.location[1]};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	        	if (true) {
	        		int[] explosionLocation = {this.location[0] + 100, this.location[1]};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	    	}
	    	if (true) {
	    		if (true) {
	        		int[] explosionLocation = {this.location[0], this.location[1] - 150};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	        	if (true) {
	        		int[] explosionLocation = {this.location[0], this.location[1] + 150};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	        	if (true) {
	        		int[] explosionLocation = {this.location[0] - 150, this.location[1]};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	        	if (true) {
	        		int[] explosionLocation = {this.location[0] + 150, this.location[1]};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	    	}
    	}
    	
    }
    
    public String toString() {
    	return ("Bomb");
    }

}
