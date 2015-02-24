/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclesfxml;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author cmpjbate
 */
public class VehiclesFXML extends Application {
    
    // JSON variables
    private String json = "[{\"QTR\":1,\"Quantity\":25,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":1,\"Quantity\":5,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":1,\"Quantity\":5,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":1,\"Quantity\":52,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":1,\"Quantity\":35,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":1,\"Quantity\":23,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":1,\"Quantity\":80,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":1,\"Quantity\":35,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":1,\"Quantity\":50,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":2,\"Quantity\":32,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":2,\"Quantity\":9,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":2,\"Quantity\":7,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":2,\"Quantity\":53,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":2,\"Quantity\":127,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":2,\"Quantity\":20,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":2,\"Quantity\":82,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":2,\"Quantity\":47,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":2,\"Quantity\":45,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":3,\"Quantity\":62,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":3,\"Quantity\":13,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":3,\"Quantity\":3,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":3,\"Quantity\":48,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":3,\"Quantity\":120,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":3,\"Quantity\":17,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":3,\"Quantity\":107,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":3,\"Quantity\":42,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":3,\"Quantity\":67,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":4,\"Quantity\":9,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":4,\"Quantity\":7,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":4,\"Quantity\":2,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":4,\"Quantity\":57,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":4,\"Quantity\":92,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":4,\"Quantity\":25,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":4,\"Quantity\":92,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":4,\"Quantity\":23,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":4,\"Quantity\":52,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":1,\"Quantity\":35,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":1,\"Quantity\":17,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":1,\"Quantity\":2,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":1,\"Quantity\":43,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":1,\"Quantity\":102,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":1,\"Quantity\":28,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":1,\"Quantity\":78,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":1,\"Quantity\":38,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":1,\"Quantity\":23,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":2,\"Quantity\":43,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":2,\"Quantity\":18,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":2,\"Quantity\":1,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":2,\"Quantity\":38,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":2,\"Quantity\":92,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":2,\"Quantity\":72,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":2,\"Quantity\":85,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":2,\"Quantity\":32,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":2,\"Quantity\":43,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":3,\"Quantity\":63,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":3,\"Quantity\":23,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":3,\"Quantity\":5,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":3,\"Quantity\":33,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":3,\"Quantity\":87,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":3,\"Quantity\":95,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":3,\"Quantity\":98,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":3,\"Quantity\":45,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":3,\"Quantity\":44,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":4,\"Quantity\":13,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":4,\"Quantity\":9,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":4,\"Quantity\":3,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":4,\"Quantity\":52,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":4,\"Quantity\":95,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":4,\"Quantity\":92,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":4,\"Quantity\":72,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":4,\"Quantity\":23,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":4,\"Quantity\":29,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":1,\"Quantity\":9,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":1,\"Quantity\":6,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":1,\"Quantity\":4,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2013},{\"QTR\":1,\"Quantity\":30,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":1,\"Quantity\":82,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":1,\"Quantity\":105,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2013},{\"QTR\":1,\"Quantity\":88,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":1,\"Quantity\":9,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":1,\"Quantity\":35,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2013},{\"QTR\":2,\"Quantity\":17,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":2,\"Quantity\":11,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":2,\"Quantity\":5,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2013},{\"QTR\":2,\"Quantity\":27,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":2,\"Quantity\":89,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":2,\"Quantity\":100,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2013},{\"QTR\":2,\"Quantity\":82,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":2,\"Quantity\":7,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":2,\"Quantity\":52,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2013},{\"QTR\":3,\"Quantity\":23,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":3,\"Quantity\":11,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":3,\"Quantity\":2,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2013},{\"QTR\":3,\"Quantity\":25,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":3,\"Quantity\":67,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":3,\"Quantity\":123,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2013},{\"QTR\":3,\"Quantity\":123,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":3,\"Quantity\":13,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":3,\"Quantity\":63,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2013},{\"QTR\":4,\"Quantity\":5,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":4,\"Quantity\":3,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":4,\"Quantity\":7,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2013},{\"QTR\":4,\"Quantity\":32,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":4,\"Quantity\":53,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":4,\"Quantity\":117,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2013},{\"QTR\":4,\"Quantity\":105,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":4,\"Quantity\":5,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":4,\"Quantity\":48,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2013}]";
    
    // Sales variables
    private List<Sales> sales;
    
    // FXML variables
    private FXMLDocumentController fxml;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = fxmlLoader.load();            
            Scene scene = new Scene(root, 1024, 768);

            stage.setTitle("Vehicles Sales Dashboard");
            stage.setScene(scene);
            stage.show();
            
            fxml = fxmlLoader.getController();
        }catch (IOException e) {
            e.printStackTrace();
        }
        
        this.getSalesData();
        this.fxml.setSalesData(sales);
        this.fxml.setupUserInterface();
    }
    
    //**********************//
    // SALES DATA FUNCTIONS //
    //**********************//
    private void getSalesData()
    {
        Type type = new TypeToken<LinkedList<Sales>>() {}.getType();
        
        sales = new Gson().fromJson(json, type);

        for (Sales sale : sales)
        {
            System.out.println(sale.toString());
        }
    }
}
