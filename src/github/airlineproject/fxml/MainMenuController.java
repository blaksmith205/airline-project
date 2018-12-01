package github.airlineproject.fxml;

import github.airlineproject.main.Main;
import github.airlineproject.util.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private VBox segment3;

    @FXML
    private ChoiceBox<String> flightChoice;

    @FXML
    private Button reserveButton;

    @FXML
    private Button flightSeatButton;

    @FXML
    private Button flightPassengerButton;

    private ArrayList<VBox> segments;
    private ArrayList<Flight> flights;
    private ArrayList<Passenger> passengers;
    private String selectedFlight;

    @FXML
    public void createFlight(ActionEvent event) {
        Flight createdFlight = null;
        // Open a new window for the flight creation
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Main.FXML_LOCATION + "AddFlight.fxml"));
            Parent root = loader.load();
            AddFlightController flightController = loader.getController();  // Get the controller to pass the Flight Number back
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);    // Prevent other windows from being accessed
            window.setTitle("Add Flight");

            // Show the scene like the MainMenu
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.showAndWait();   // Wait for the created window to be closed

            createdFlight = flightController.getFlight();

            // create the seat map file for the flight
            DataFormatter.createFlightFile(createdFlight.getFlight());
        } catch (IOException ex) {
            System.err.println("Error occured when loading AddFlight.fxml from MainMenuController\n" + ex);
        }

        // Add flight to the array
        flights.add(createdFlight);

        // Add the created Flight to the ChoiceBox
        if (createdFlight != null) {
            if (!flightChoice.getItems().contains(createdFlight.getFlight())) {
                flightChoice.getItems().remove(0);  // Remove None from beginning of list
                flightChoice.getItems().add(createdFlight.getFlight());    // Add the created flight number

                flightChoice.getItems().sort(String::compareTo);    // Use String compare function to sort the list
                flightChoice.getItems().add(0, "None"); // Re-add None to beginning of the list
            }
        }
    }

    @FXML
    public void makeReservation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Main.FXML_LOCATION + "ReservationSeat.fxml"));
            Parent root = loader.load();
            ReservationSeatController reserveseat = loader.getController();  // Get the controller to pass the selected seat back
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Reservation");

            // Show the scene like the MainMenu
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.showAndWait();   // Wait for the created window to be closed

        } catch (IOException ex) {
            System.err.println("Error occured when loading Reservation Window\n" + ex);
        }

    }

    @FXML
    public void displaySeatMap(ActionEvent event) {

    }

    @FXML
    public void displayPassengers(ActionEvent event) {

    }

    @FXML
    public void displayAllFlights(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Main.FXML_LOCATION + "AllFlights.fxml"));
            Parent root = loader.load();
            AllFlightsController afControl = loader.getController();  // Get the controller to set table information
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);    // Prevent other windows from being accessed
            window.setTitle("Flight Viewer");
            afControl.setTable(flights);    // Set the table information

            // Show the scene like the MainMenu
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.showAndWait();   // Wait for the created window to be closed

        } catch (Exception ex) {
            System.err.println("Error occured when loading AllFlights.fxml from MainMenuController\n" + ex);
        }

    }

    @FXML
    /**
     * Action handler for display all passenger button. Loads a table with all
     * passengers from reservations.txt
     */
    public void displayAllPassengers(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Main.FXML_LOCATION + "PassengerViewer.fxml"));
            Parent root = loader.load();
            PassengerViewerController pvControl = loader.getController();  // Get the controller to set table information
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);    // Prevent other windows from being accessed
            window.setTitle("Passenger Viewer");
            pvControl.setTable(passengers, "All Passengers");    // Set the table information

            // Show the scene like the MainMenu
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.showAndWait();   // Wait for the created window to be closed

        } catch (Exception ex) {
            System.err.println("Error occured when loading PassengerViewer.fxml from MainMenuController\n" + ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Visual appearence for each main element
        segments = new ArrayList<>();
        segments.addAll(Arrays.asList(segment1, segment2, segment3)); // Add all segments to the array for easy manipulation
        segments.forEach((box) -> {
            box.setStyle("-fx-border-color: black");    // Add black border around each segment
            box.setPadding(new Insets(5));              // Set a padding of 5 pixels all around the boxes
        });

        initObjects();
    }

    /**
     * Initialize the ChoiceBox
     */
    private void initChoiceBox() {
        // Add flight options to choice box from the file
        ArrayList<String> flightNumbers = new ArrayList<>();
        for (Flight flight : flights) {
            flightNumbers.add(flight.getFlight());   // Add only the flight numbers from each line
        }
        flightChoice.getItems().add("None");
        flightChoice.getItems().addAll(flightNumbers);

        // Add listener to the segment 2 choice box
        flightChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                selectedFlight = flightChoice.getItems().get(newValue.intValue());
                // Disable the button if None is selected
                if ("None".equals(flightChoice.getItems().get(newValue.intValue()))) {
                    reserveButton.setDisable(true);
                    flightSeatButton.setDisable(true);
                    flightPassengerButton.setDisable(true);
                } else {
                    reserveButton.setDisable(false);
                    flightSeatButton.setDisable(false);
                    flightPassengerButton.setDisable(false);
                }
            }
        });
    }

    private void initObjects() {
        // Initialize the files
        initFiles();

        // load flights from reservations file and store in array list
        flights = DataFormatter.getFlights();

        // load passengers from reservations file and store in array list
        passengers = DataFormatter.getPassengers();

        // Initialize the ChoiceBox
        initChoiceBox();
    }

    /**
     * Create the flight and reservation text files if they do not exist
     */
    private void initFiles() {
        // Create flights.txt if it does not exist
        if (!FileIO.exists(FileIO.FILE_DIR, "flight.txt")) {
            FileIO.fileWriter(FileIO.FILE_DIR, "flight.txt", FileIO.FLIGHT_HEADER);
        }

        // Create reservations.txt if it does not exist
        if (!FileIO.exists(FileIO.FILE_DIR, "reservations.txt")) {
            FileIO.fileWriter(FileIO.FILE_DIR, "reservations.txt", FileIO.RESERVATION_HEADER);
        }
    }
}
