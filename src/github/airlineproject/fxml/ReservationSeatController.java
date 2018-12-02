/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github.airlineproject.fxml;

import github.airlineproject.util.DataFormatter;
import github.airlineproject.util.FileIO;
import github.airlineproject.util.Flight;
import github.airlineproject.util.Passenger;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class for ReservationSeat.fxml
 *
 * @author Chente
 */
public class ReservationSeatController implements Initializable {

    @FXML
    private Button continueButton;

    @FXML
    private TextField namebox;

    @FXML
    private TextField IDbox;

    @FXML
    private Button cancelButton;

    @FXML
    private HBox seatsBox;

    @FXML
    private ToggleGroup seatToggle;

    private ArrayList<ToggleButton> allButtons; // List for all ToggleButtons

    private Flight flight;  // Store information related to the Flight this reservation is for
    private Passenger passenger;    // Store information releated to created Passenger
    private ToggleButton selectedSeat;

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
        if (flight != null) {
            correctButtonText();
        }
    }

    /**
     * Obtains the created Passenger
     * @return 
     */
    public Passenger getPassenger(){
        return passenger;
    }
    /**
     * Close the window if the cancel button is clicked
     *
     * @param event
     */
    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    // check the fields and reserve the flight
    @FXML
    private void reserveFlight(ActionEvent event) {
        String name = "", ID = "";
        // Check name Field
        if (!namebox.getText().isEmpty()) {
            name = namebox.getText();
        } else {
            AddFlightController.showAlertBox("Name can't be empty, and should be of form firstName lastName");
        }
        // Check ID Field
        if (!IDbox.getText().isEmpty()) {
            ID = IDbox.getText();
        } else {
            AddFlightController.showAlertBox("ID can't be empty");
        }

        // Check if a seat is selected and create a passenger if all other fields are correct
        if (seatToggle.getSelectedToggle() != null) {
            if (name.isEmpty() | ID.isEmpty()) {
                AddFlightController.showAlertBox("Make sure the text fields are not empty");
            } else {
                passenger = new Passenger(ID, name, selectedSeat.getText(), flight.getFlight());
            }
        } else {
            AddFlightController.showAlertBox("A seat must be selected");
        }

        // Final check for Passenger object 
        if (passenger != null) {
            // update seat map for flight object
            flight.updateSeatMap(passenger.getSeatNum());
            
            // Overwrite flight file
            DataFormatter.createFlightFile(flight.getFlight() + ".txt");

            // append reservations.txt
            FileIO.fileAppender(FileIO.FILE_DIR, "reservations.txt", passenger.toFileString());

            // close window
            Stage stage = (Stage) continueButton.getScene().getWindow();
            stage.close();
        }
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
                    tButton.selectedProperty().addListener(new ToggleButtonSelectedListener(tButton));  // Add listener to selected property
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

    private class ToggleButtonSelectedListener implements ChangeListener<Boolean> {

        private ToggleButton tb;

        public ToggleButtonSelectedListener(ToggleButton toggleButton) {
            tb = toggleButton;
        }

        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (newValue) {
                selectedSeat = tb;
            }
        }
    }
}
