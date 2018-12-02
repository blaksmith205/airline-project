package github.airlineproject.fxml;

import github.airlineproject.util.FileIO;
import github.airlineproject.util.Flight;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.PatternSyntaxException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class for AddFlight.fxml
 *
 * @author William
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
            createdFlight = new Flight(flightNumber, date, deptartureTime, arrivalTime, departCity, arriveCity, 70);

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
            if (!flightNumber.substring(0, 2).contains("[0-9]")) { // If first 2 characters are letters
                if (!FileIO.exists(FileIO.FLIGHT_DIR, flightNumber)) {
                    return true;
                } else {
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
                String[] parts;
                if (date.contains("/")) {   // Check to see if the date is in right format
                    parts = date.split("/");
                    if (Integer.parseInt(parts[1]) > 12) {  // Check to see if the day is not the month
                        showAlertBox("Date should be of the form dd/mm/yyyy");
                    } else {
                        return true;
                    }
                } else {
                    showAlertBox("Date should be of the form dd/mm/yyyy");
                }
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
            } catch (PatternSyntaxException | NumberFormatException ex) {
                showAlertBox(field + " should be of the format hh:mm on a 24 hr scale");
            }
        } else {
            showAlertBox(field + " should be of the format hh:mm on a 24 hr scale");
        }
        return false;
    }

    public String formatDate(String date) {
        String[] parts = date.split("/");
        String formattedDate = parts[0];    // Add the day

        // Map the month number to 3 letter month
        switch (Integer.parseInt(parts[1])) {
            case 1:
                formattedDate += "Jan";
                break;
            case 2:
                formattedDate += "Feb";
                break;
            case 3:
                formattedDate += "Mar";
                break;
            case 4:
                formattedDate += "Apr";
                break;
            case 5:
                formattedDate += "May";
                break;
            case 6:
                formattedDate += "Jun";
                break;
            case 7:
                formattedDate += "Jul";
                break;
            case 8:
                formattedDate += "Aug";
                break;
            case 9:
                formattedDate += "Sep";
                break;
            case 10:
                formattedDate += "Oct";
                break;
            case 11:
                formattedDate += "Nov";
                break;
            case 12:
                formattedDate += "Dec";
                break;
        }
        return (formattedDate + parts[2].substring(2));
    }

    /**
     * Function to easily create alert boxes
     *
     * @param header The header string
     * @param content The content String
     */
    public static void showAlertBox(String header, String content) {
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
    public static void showAlertBox(String content) {
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
