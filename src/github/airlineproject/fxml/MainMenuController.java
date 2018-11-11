package github.airlineproject.fxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/**
 *
 * @author William Blanc <test@email.com>
 */
public class MainMenuController implements Initializable {

    @FXML
    private VBox segment1;
    
    @FXML
    private VBox segment2;
    
    @FXML
    private ChoiceBox<String> flightChoice;

    @FXML
    private Button reserveButton;

    private ArrayList<VBox> segments;
    private String selectedFlight;
    
    @FXML
    void createFlight(ActionEvent event) {

    }

    @FXML
    void makeReservation(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Visual appearence for each main element
        segments = new ArrayList<>();
        segments.addAll(Arrays.asList(segment1, segment2)); // Add all segments to the array for easy manipulation
        segments.forEach((box) -> {
            box.setStyle("-fx-border-color: black");    // Add black border around each segment
            box.setPadding(new Insets(5));              // Set a padding of 5 pixels all around the boxes
        });
        
        // Add flight options to choice box from the file
        ArrayList<String> flights = new ArrayList<>();
        flights.addAll(Arrays.asList("AA1230", "AA1456", "None"));
        flightChoice.getItems().addAll(flights);
        flightChoice.getSelectionModel().select("None");
        
        // Add listener to the segment 2 choice box
        flightChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                selectedFlight = flightChoice.getItems().get((int)newValue);
                // Disable the button if None is selected
                if("None".equals(flightChoice.getItems().get((int)newValue))){
                    reserveButton.setDisable(true);
                } else{
                    reserveButton.setDisable(false);
                }
            }
        });
    }

}
