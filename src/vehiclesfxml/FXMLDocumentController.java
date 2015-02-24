/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclesfxml;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author cmpjbate
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private VBox vBoxLeft;
    private HBox hBoxYearCheckboxes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.hBoxYearCheckboxes = (HBox)this.vBoxLeft.lookup("#hBoxYearCheckboxes");
    }    
    
    //***************************//
    // YEAR CHECKBOXES FUNCTIONS //
    //***************************//
    public void setupYearCheckboxes(List<Integer> years)
    {
        CheckBox[] checkBoxes = new CheckBox[years.size()];
        
        for (int i = 0; i < years.size(); i++)
        {
            checkBoxes[i] = new CheckBox(years.get(i).toString());
            checkBoxes[i].setSelected(true);
            /*
            checkBoxes[i].addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    System.out.println("Firstly, Event Filters !");
                }
            });*/
        }

        this.hBoxYearCheckboxes.getChildren().addAll(checkBoxes);
    }
}
