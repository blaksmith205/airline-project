package github.airlineproject.fxml;

import github.airlineproject.util.DataFormatter;
import github.airlineproject.util.FileIO;
import github.airlineproject.util.Flight;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.util.ResourceBundle;
import java.util.regex.PatternSyntaxException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chente
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

    private Flight createdFlight;

    /**
     * Passes the created Flight object back to caller
     *
     * @return
     */
    public Flight getFlight() {
        return createdFlight;
    }

    @FXML
    void createFlightAndReturn(ActionEvent event) {
        // Check list for making sure all fields are correct
        boolean[] checkList = new boolean[6];
        // Create temp variables
        // Check flight number
        String flightNumber = numberField.getText();
        checkList[0] = checkFlightNumber(flightNumber);
        // get departCity
        String departCity = fromBox.getText();
        checkList[1] = true;
        // get arrivalCity
        String arriveCity = toBox.getText();
        checkList[2] = true;
        // get date
        String date = fromDate.getText();
        checkList[3] = checkDate(date);
        date = formatDate(date);
        // get dept time
        String deptartureTime = departTime.getText();
        checkList[4] = checkTime(deptartureTime, "Departure Time");
        // get arrival time
        String arrivalTime = arriveTime.getText();
        checkList[5] = checkTime(arrivalTime, "Arrival Time");
        
        // Check the list
        if (isFieldsCorrect(checkList)) {
            // Create Flight object
            createdFlight = new Flight(flightNumber, date, date, arrivalTime, departCity, departCity, 70);
            createdFlight.setSeatMap();
            
            // Close the stage and return to caller
            Stage stage = (Stage) numberField.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private boolean checkFlightNumber(String flightNumber) {
        if (flightNumber.length() == 6) {
            if (flightNumber.substring(0, flightNumber.indexOf("[0-9]")).length() == 2) { // If first 2 characters are letters
                if (!FileIO.exists(FileIO.FLIGHT_DIR, flightNumber)) {
                  return true;  
                }
                else{
                    showAlertBox(flightNumber + " already exists. Please create a different flight");
                }
            } else {
                showAlertBox("Flight Number should be of the form LLDDDD\n"
                        + "Where L is a letter, and D is a digit");
            }
        } else {
            showAlertBox("Flight Number field should be 6 characters long");
        }
        return false;
    }

    /**
     * Check to see if the date is properly formatted
     *
     * @param date: The string representation of the date
     * @return
     */
    public boolean checkDate(String date) {
        if (date.length() == 10) {
            try {
                String[] parts = date.split("/");
                if (Integer.parseInt(parts[1]) > 12) {
                    showAlertBox("Date should be of the form dd/mm/yyyy");
                }
                return true;
            } catch (PatternSyntaxException ex) {
                showAlertBox("Date should be of the form dd/mm/yyyy");
            }
        } else {
            showAlertBox("Date should be of the form dd/mm/yyyy");
        }
        return false;
    }

    public boolean checkTime(String time, String field) {
        if (time.length() == 5) {
            try {
                String[] parts = time.split(":");
                if (Integer.parseInt(parts[0]) > 24) {
                    showAlertBox(field + " hours should be on a 24 hr scale");
                }
                if (Integer.parseInt(parts[1]) > 60) {
                    showAlertBox(field + " minutes are too large, should be at most 59");
                }
                return true;
            } catch (PatternSyntaxException ex) {
                showAlertBox(field + " should be of the format hh:mm");
            }
        } else {
            showAlertBox(field + " should be of the format hh:mm");
        }
        return false;
    }

    public String formatDate(String date) {
        String[] parts = date.split("/");
        switch (Integer.parseInt(parts[1])) {
            case 1:
                return "Jan";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Apr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Aug";
            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dec";
        }
        return "Err";
    }

    /**
     * Function to easily create alert boxes
     *
     * @param header The header string
     * @param content The content String
     */
    private void showAlertBox(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Convenience method for creating an Alert Box with set header
     *
     * @param content
     */
    private void showAlertBox(String content) {
        showAlertBox("An Error Occured!", content);
    }

    /**
     * Checks if the fields are correct by the values in the checkList. If 1
     * false then returned false
     *
     * @param checkList Each element is the text field correction
     * @return
     */
    private boolean isFieldsCorrect(boolean[] checkList) {
        for (boolean check : checkList) {
            if (!check) {
                return false;
            }
        }
        return true;
    }
}
