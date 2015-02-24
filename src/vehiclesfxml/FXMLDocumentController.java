/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclesfxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author cmpjbate
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private VBox leftVBox;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void setupYearCheckboxes()
    {
        CheckBox[] checkBoxes = new CheckBox[2];

        CheckBox checkBox = new CheckBox();
        
        this.leftVBox.getChildren().add(checkBox);
        
        /*
        for (byte index = 0; index < strings.size(); index++) {
            checkBoxes[index] = new CheckBox(strings.get(index));
            checkBoxes[index].setSelected(true);
            checkBoxes[index].addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    System.out.println("Firstly, Event Filters !");
                }
            });
*/
    }
}
