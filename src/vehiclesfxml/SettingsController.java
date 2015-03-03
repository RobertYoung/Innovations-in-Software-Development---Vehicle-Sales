/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclesfxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cmpryoun
 */
public class SettingsController implements Initializable {

    // Window variables
    private Scene scene;
    private Stage stage;
    
    // Dashboard variables
    public VehiclesDashboard vehicleDashboard;
    
    @FXML
    private ComboBox cbStyle;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void init()
    {
        this.setupStyleComboBox();
    }
    
    //*****************//
    // SCENE FUNCTIONS //
    //*****************//
    public void setScene(Scene scene)
    {
        this.scene = scene;
        this.stage = (Stage)this.scene.getWindow();
        this.vehicleDashboard.setStyle();
    }
    
    public void closeSettingsView()
    {
        stage.close();
    }

    //*********************//
    // COMBO BOX FUNCTIONS //
    //*********************//
    private void setupStyleComboBox() {
        ObservableList<String> options = 
            FXCollections.observableArrayList(
                "Lotus Theme",
                "Contrast Theme"
            );
        
        this.cbStyle.getItems().addAll(options);
        this.cbStyle.setValue(vehicleDashboard.currentStyle + " Theme");
        this.cbStyle.valueProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.startsWith("Lotus"))
                    vehicleDashboard.setLotusStyle();
                else if (newValue.startsWith("Contrast"))
                    vehicleDashboard.setContrastStyle();
            }
            
        });
    }
    
    //*****************//
    // STYLE FUNCTIONS //
    //*****************//
    public void setStyle(String style) 
    {
        this.scene.getStylesheets().clear();
        this.scene.getStylesheets().add(style);
    }
}
