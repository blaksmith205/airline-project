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
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Chente
 */
public class AllFlightsController implements Initializable {

    @FXML
    private TableColumn<Flight, String> departColumn;

    @FXML
    private TableColumn<Flight, Integer> passengers;

    @FXML
    private TableColumn<Flight, String> departtimecolumn;

    @FXML
    private TableColumn<Flight, String> flightColumn;

    @FXML
    private TableColumn<Flight, String> destinationColumn;

    @FXML
    private TableView<Flight> allFlights;

    @FXML
    private TableColumn<Flight, String> dateColumn;

    @FXML
    private TableColumn<Flight, String> arrivetimecolumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flightColumn.setCellValueFactory(new PropertyValueFactory<>("flight")); // Set column to the flight#
        departColumn.setCellValueFactory(new PropertyValueFactory<>("departCity")); // Set column for depart 
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destCity")); // Set column desination
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date")); // Set column to show date 
        departtimecolumn.setCellValueFactory(new PropertyValueFactory<>("departureTime"));//set column to show depart time
        arrivetimecolumn.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));//set column to show arrive time
        passengers.setCellValueFactory(new PropertyValueFactory<>("availableSeats"));//set column to show passengers 
    }

    public void setTable(ArrayList<Flight> flights) {
        allFlights.setItems(FXCollections.observableArrayList(flights)); // Set the table values to the passengers

    }

}
