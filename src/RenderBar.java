import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.canvas.*;


/**
 * This class is used to draw a progress bar for the level being played, showing how close to winning/losing you are
 * @author Oliver
 * @version 1.0
 */
public class RenderBar {
	
	/**
	 * This function draws the progress bar
	 * @param gc The GraphicsContext used to render the progress bar onto
	 * @param width The width of the level being played
	 * @param height The height of the level being played
	 */
	public static void drawProgressBar(GraphicsContext gc, int width, int height) {
		
		double maxRats = Level.maxRats;
		double maleCounter = 0;
		double femaleCounter = 0;
		double babyCounter = 0;
		double deathRatCounter = 0;
		
		double endPoint = 50;
		
		// Gets how many of each type of rat there are
		for (Rat rat : Rat.getRats()) {
			switch(rat.getRatType()) {
				case "MRat":
					maleCounter += 1;
					break;
				case "FRat":
					femaleCounter += 1;
					break;
				case "BabyRat":
					babyCounter += 1;
					break;
				case "DeathRat":
					deathRatCounter += 1;
					break;
			}
		}
		
		// Backdrop of progress bar
		gc.setFill(Color.rgb(201,201,201));
		double progressBarLength = width-100;
		gc.clearRect(50, height, progressBarLength, 25);
		gc.fillRect(50, height, progressBarLength, 25);
		
		// Bar representing male rats
		gc.setFill(Color.rgb(44,141,232));
		double maleBarLength = progressBarLength * (maleCounter / maxRats);
		gc.fillRect(endPoint, height, maleBarLength, 25);
		// endPoint is where the last bar ends, so the next bar knows where to start from
		endPoint += maleBarLength;
		
		// Bar representing female rats
		gc.setFill(Color.rgb(252,12,248));
		double femaleBarLength = progressBarLength * (femaleCounter / maxRats);
		gc.fillRect(endPoint, height, femaleBarLength, 25);
		endPoint += femaleBarLength;
		
		// Bar representing baby rats
		gc.setFill(Color.rgb(155,155,155));
		double babyBarLength = progressBarLength * (babyCounter / maxRats);
		gc.fillRect(endPoint, height, babyBarLength, 25);
		endPoint += babyBarLength;
		
		// Bar representing death rats
		gc.setFill(Color.rgb(66,66,66));
		double deathRatBarLength = progressBarLength * (deathRatCounter / maxRats);
		gc.fillRect(endPoint, height, deathRatBarLength, 25);
		
		
		
		
	}
}
