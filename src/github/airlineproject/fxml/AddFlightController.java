package github.airlineproject.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author William Blanc
 */
public class AddFlightController implements Initializable {
    
    
    @FXML
    private TextField fromDate;

    @FXML
    private TextField arriveTime;

    @FXML
    private TextField fromBox;

    @FXML
    private TextField departTime;

    @FXML
    private TextField toBox;

    @FXML
    private TextField numberField;

    private String flightNumber;

    public String getFlightNumber() {
        return flightNumber;
    }

    @FXML
    void createFlightAndReturn(ActionEvent event) {
        // Set internal values to appropriate info
        flightNumber = numberField.getText();
        
        // Append flights.txt
        
        // Close the stage and return to caller
        Stage stage = (Stage)numberField.getScene().getWindow();
        stage.close();
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
