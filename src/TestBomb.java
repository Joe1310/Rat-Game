public class TestBomb extends Entity{

    public TestBomb(int[] location) {
        super(location, "Bomb.png", "Bomb");
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
	        		int[] explosionLocation = {this.location[0], this.location[1] - 1};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	        	if (true) {
	        		int[] explosionLocation = {this.location[0], this.location[1] + 1};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	        	if (true) {
	        		int[] explosionLocation = {this.location[0] - 1, this.location[1]};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	        	if (true) {
	        		int[] explosionLocation = {this.location[0] + 1, this.location[1]};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	    	}
	    	if (true) {
	    		if (true) {
	        		int[] explosionLocation = {this.location[0], this.location[1] - 2};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	        	if (true) {
	        		int[] explosionLocation = {this.location[0], this.location[1] + 2};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	        	if (true) {
	        		int[] explosionLocation = {this.location[0] - 2, this.location[1]};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	        	if (true) {
	        		int[] explosionLocation = {this.location[0] + 2, this.location[1]};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	    	}
	    	if (true) {
	    		if (true) {
	        		int[] explosionLocation = {this.location[0], this.location[1] - 3};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	        	if (true) {
	        		int[] explosionLocation = {this.location[0], this.location[1] + 3};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	        	if (true) {
	        		int[] explosionLocation = {this.location[0] - 3, this.location[1]};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	        	if (true) {
	        		int[] explosionLocation = {this.location[0] + 3, this.location[1]};
	        		Entity explo = new Explosion(explosionLocation);
	        	}
	    	}
    	}
    	
    }
    
    public String toString() {
    	return ("Bomb");
    }

}
