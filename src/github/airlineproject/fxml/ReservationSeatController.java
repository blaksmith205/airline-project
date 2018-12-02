/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github.airlineproject.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
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
    private HBox Seatsbox;

    @FXML
    private ToggleGroup Toggle1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
