/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclesfxml;

import com.sun.prism.paint.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author apple
 */
public class LoginController implements Initializable {

    // Scene variables
    private Scene scene;
    
    // Dashboard variables
    public VehiclesDashboard vehicleDashboard;
    
    // Anchor pane
    @FXML
    private AnchorPane anchorPane;
    
    // Textfields
    private TextField txtUsername;
    private PasswordField txtPassword;
    
    // Labels
    @FXML
    public Label lblOutput;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.txtUsername = (TextField)this.anchorPane.lookup("#txtUsername");
        this.txtPassword = (PasswordField)this.anchorPane.lookup("#txtPassword");
    }    
    
    //*****************//
    // SCENE FUNCTIONS //
    //*****************//
    public void setScene(Scene scene)
    {
        this.scene = scene;
        this.vehicleDashboard.setStyle();
    }
    
    //*****************//
    // LOGIN FUNCTIONS //
    //*****************//
    public void loginPressed()
    {
        if (this.txtUsername.getText().isEmpty())
        {
            this.lblOutput.setText("Please enter your username");
            return;
        }
        
        if (this.txtPassword.getText().isEmpty())
        {
            this.lblOutput.setText("Please enter your password");
            return;
        }
        
        if (this.txtUsername.getText().equals("admin") && this.txtPassword.getText().equals("password"))
        {
            this.vehicleDashboard.displayDashboard();
        }else{
            this.lblOutput.setText("The details entered are not correct, please try again");
        }
    }
    
    //******************//
    // CANCEL FUNCTIONS //
    //******************//
    public void cancelPressed()
    {
        VehiclesDashboard.closeApplication();
    }

    public void setStyle(String style) 
    {
        this.scene.getStylesheets().clear();
        this.scene.getStylesheets().add(style);
    }
}
