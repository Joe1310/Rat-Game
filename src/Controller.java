import javafx.event.ActionEvent;

public class Controller {
    public void Start(ActionEvent e){
        System.out.println("Start");
    }
    public void Load(ActionEvent e){
        System.out.println("Load");
    }
    public void HighScores(ActionEvent e){
        System.out.println("HighScores");
    }
    public void Quit(ActionEvent e){
        System.out.println("Quitting");
        System.exit(0);
    }
}
