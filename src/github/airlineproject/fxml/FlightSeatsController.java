/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github.airlineproject.fxml;

import github.airlineproject.util.Passenger;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
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

    public void setSeatMap(char[][] Seats, String seatlabel) {

        for (int i = 0; i < filledseats.getChildren().size(); i++) {
            HBox tempbox = (HBox) filledseats.getChildren().get(i);
            int column = 0;
            for (int j = 0; j < tempbox.getChildren().size(); j++) {
                Label label = (Label) tempbox.getChildren().get(j);
                if (label.getText() == "") {

                    continue;
                }
                if (label.getText().charAt(0) != Seats[i][column]) {
                    column++;
                    label.setText("X");
                }
            }
        }

        flightTitle.setText(seatlabel);
    }
}
