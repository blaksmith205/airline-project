/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github.airlineproject.fxml;

import github.airlineproject.util.Flight;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class for ReservationSeat.fxml
 *
 * @author Chente
 */
public class ReservationSeatController implements Initializable {

    @FXML
    private Button Continuebutton;

    @FXML
    private TextField namebox;

    @FXML
    private TextField IDbox;

    @FXML
    private Button CancelButton;

    @FXML
    private HBox seatsBox;

    private ArrayList<ToggleButton> allButtons; // List for all ToggleButtons

    private Flight flight;  // Store information related to the Flight this map is for
    @FXML
    private ToggleGroup seatToggle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initToggleButtons();
    }

    /**
     * Set the stored Flight object to one passed in
     *
     * @param flight
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
        correctButtonText();
    }

    /**
     * Initialize all ToggleButtons and add proper listeners / to ToggleGroup
     */
    private void initToggleButtons() {
        // Initialize all buttons stored in HBox
        allButtons = new ArrayList<>(); // Buttons are added top-down, left-to-right

        int col = 0;    // Counter for columns
        for (Node child : seatsBox.getChildren()) {
            int row = 1;    // counter for rows
            VBox colBox = (VBox) child;   // Cast child to VBox
            // Get the buttons stored in non-empty columns
            if (!colBox.getChildren().isEmpty()) {
                for (Node button : colBox.getChildren()) {
                    ToggleButton tButton = (ToggleButton) button;    // Cast stored child to ToggleButton
                    tButton.setText(String.format("%d%c", row, Flight.getChar(col)));   // Convert col num to mapped char
                    tButton.setToggleGroup(seatToggle); // Add all buttons to the same ToggleGroup
                    tButton.textProperty().addListener(new ToggleButtonTextListener(tButton));  // Add listener to text property
                    allButtons.add(tButton);    // Add the ToggleButton to the array
                    row++;
                }
                col++;
            }
        }
    }

    /**
     * Corrects the text for each ToggleButton to reflect the seat map of the
     * Flight object
     */
    private void correctButtonText() {
        int buttonNum = 0;  // keep track of the button in the list
        for (int col = 0; col < Flight.SEAT_MAP_COL; col++) {
            for (int row = 0; row < Flight.SEAT_MAP_ROW; row++) {
                ToggleButton tb = allButtons.get(buttonNum);  // Obtain the ToggleButton from list
                if (flight.getSeatMap()[row][col] == 'X') {  // If char in seatMap is X
                    tb.setText(tb.getText().replaceFirst("[A-Z]", "X"));
                }
                buttonNum++;    // Increment buttonNumber
            }
        }
    }

    /**
     * Text property listener for ToggleButtons
     */
    private class ToggleButtonTextListener implements ChangeListener<String> {

        private ToggleButton tb;

        public ToggleButtonTextListener(ToggleButton toggleButton) {
            tb = toggleButton;
        }

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (newValue.charAt(1) == 'X') {
                tb.setDisable(true);    // Disable ToggleButton if the second char is X
            }
        }
    }
}
