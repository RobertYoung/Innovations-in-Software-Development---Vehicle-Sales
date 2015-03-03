/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclesfxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author apple
 */
public class AboutController implements Initializable {
    
    // Window variables
    private Scene scene;
    private Stage stage;
    
    // Dashboard variables
    public VehiclesDashboard vehicleDashboard;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
    public void closeAboutView()
    {
        stage.close();
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
