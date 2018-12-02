package github.airlineproject.fxml;

import github.airlineproject.util.Passenger;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class for PassengerViewer.fxml
 *
 * @author William Blanc <test@email.com>
 */
public class PassengerViewerController implements Initializable {

    @FXML
    private TableColumn<Passenger, String> IDColumn;

    @FXML
    private TableColumn<Passenger, String> nameColumn;

    @FXML
    private TableColumn<Passenger, String> flightColumn;

    @FXML
    private TableColumn<Passenger, String> seatColumn;

    @FXML
    private TableView<Passenger> passengerTable;

    @FXML
    private Label topLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID")); // Set column to the ID of the passengers
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName")); // Set column to the full name of the passenger
        flightColumn.setCellValueFactory(new PropertyValueFactory<>("flightNum")); // Set column to the flight number of the passenger
        seatColumn.setCellValueFactory(new PropertyValueFactory<>("seatNum")); // Set column to the seat number of the passenger
    }

    /**
     * Sets the table contents to the passengers
     *
     * @param passengers: Collection of passengers
     */
    public void setTable(ArrayList<Passenger> passengers, String label) {
        passengerTable.setItems(FXCollections.observableArrayList(passengers)); // Set the table values to the passengers
        topLabel.setText(label);    // Update top text
    }
}
