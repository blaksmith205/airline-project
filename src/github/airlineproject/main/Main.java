package github.airlineproject.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author William Blanc <test@email.com>
 */
public class Main extends Application {
    
    public static final String FXML_LOCATION = "/github/airlineproject/fxml/";
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(FXML_LOCATION + "MainMenu.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Pheonix Flight");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
