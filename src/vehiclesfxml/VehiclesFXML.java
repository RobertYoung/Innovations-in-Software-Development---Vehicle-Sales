/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclesfxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author cmpjbate
 */
public class VehiclesFXML extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Label labelQtr = new Label();
        labelQtr.setText("Qtr");
        CheckBox cb1 = new CheckBox("First");
        CheckBox cb2 = new CheckBox("Second");
        CheckBox cb3 = new CheckBox("Third");
        CheckBox cb4 = new CheckBox("Fourth");
        
        cb1.setSelected(true);
        cb2.setSelected(true);
        cb3.setSelected(true);
        cb4.setSelected(true);
        
        //(Pane)FXMLLoader.load(getClass().getResource("fxml_tableview.fxml"));

        
        //topHBox.set

        Scene scene = new Scene(parent, 1024, 768);

        primaryStage.setTitle("Vehicles FXML");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
