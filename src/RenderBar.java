import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.canvas.*;

public class RenderBar {
	
	public static void drawProgressBar(GraphicsContext gc, int width, int height) {
		
		double maxRats = Level.maxRats;
		double maleCounter = 0;
		double femaleCounter = 0;
		double babyCounter = 0;
		double deathRatCounter = 0;
		
		double endPoint = 50;
		
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
		gc.clearRect(50, height, progressBarLength, 50);
		gc.fillRect(50, height, progressBarLength, 50);
		
		// Bar representing male rats
		gc.setFill(Color.rgb(44,141,232));
		double maleBarLength = progressBarLength * (maleCounter / maxRats);
		gc.fillRect(endPoint, height, maleBarLength, 50);
		endPoint += maleBarLength;
		
		// Bar representing female rats
		gc.setFill(Color.rgb(252,12,248));
		double femaleBarLength = progressBarLength * (femaleCounter / maxRats);
		gc.fillRect(endPoint, height, femaleBarLength, 50);
		endPoint += femaleBarLength;
		
		// Bar representing baby rats
		gc.setFill(Color.rgb(155,155,155));
		double babyBarLength = progressBarLength * (babyCounter / maxRats);
		gc.fillRect(endPoint, height, babyBarLength, 50);
		endPoint += babyBarLength;
		
		// Bar representing death rats
		gc.setFill(Color.rgb(66,66,66));
		double deathRatBarLength = progressBarLength * (deathRatCounter / maxRats);
		gc.fillRect(endPoint, height, deathRatBarLength, 50);
		
		
		
		
	}
}
