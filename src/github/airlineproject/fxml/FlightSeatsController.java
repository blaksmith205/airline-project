package github.airlineproject.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class for FlightSeats.fxml
 *
 * @author Chente
 */
public class FlightSeatsController implements Initializable {

    @FXML
    private VBox filledseats;

    @FXML
    private Label flightTitle;

    String Text;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setSeatMap(char[][] seats, String seatlabel) {

        for (int i = 0; i < filledseats.getChildren().size(); i++) {
            HBox tempbox = (HBox) filledseats.getChildren().get(i);
            int column = 0;
            for (int j = 0; j < tempbox.getChildren().size(); j++) {
                Label label = (Label) tempbox.getChildren().get(j);
                if (label.getText() == "") {
                    continue;
                }
                if (label.getText().charAt(0) != seats[i][column]) {
                    column++;
                    label.setText("X");
                } else {
                    column++;
                }
            }
        }

        flightTitle.setText(seatlabel);
    }
}
