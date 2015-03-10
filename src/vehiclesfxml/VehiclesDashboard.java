/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclesfxml;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/**
 *
 * @author cmpjbate
 */
public class VehiclesDashboard extends Application {
    
    // JSON variables
    private String json = "";//"[{\"QTR\":1,\"Quantity\":25,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":1,\"Quantity\":5,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":1,\"Quantity\":5,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":1,\"Quantity\":52,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":1,\"Quantity\":35,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":1,\"Quantity\":23,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":1,\"Quantity\":80,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":1,\"Quantity\":35,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":1,\"Quantity\":50,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":2,\"Quantity\":32,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":2,\"Quantity\":9,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":2,\"Quantity\":7,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":2,\"Quantity\":53,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":2,\"Quantity\":127,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":2,\"Quantity\":20,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":2,\"Quantity\":82,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":2,\"Quantity\":47,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":2,\"Quantity\":45,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":3,\"Quantity\":62,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":3,\"Quantity\":13,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":3,\"Quantity\":3,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":3,\"Quantity\":48,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":3,\"Quantity\":120,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":3,\"Quantity\":17,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":3,\"Quantity\":107,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":3,\"Quantity\":42,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":3,\"Quantity\":67,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":4,\"Quantity\":9,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":4,\"Quantity\":7,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":4,\"Quantity\":2,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":4,\"Quantity\":57,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":4,\"Quantity\":92,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":4,\"Quantity\":25,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":4,\"Quantity\":92,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2011},{\"QTR\":4,\"Quantity\":23,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2011},{\"QTR\":4,\"Quantity\":52,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2011},{\"QTR\":1,\"Quantity\":35,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":1,\"Quantity\":17,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":1,\"Quantity\":2,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":1,\"Quantity\":43,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":1,\"Quantity\":102,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":1,\"Quantity\":28,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":1,\"Quantity\":78,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":1,\"Quantity\":38,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":1,\"Quantity\":23,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":2,\"Quantity\":43,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":2,\"Quantity\":18,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":2,\"Quantity\":1,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":2,\"Quantity\":38,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":2,\"Quantity\":92,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":2,\"Quantity\":72,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":2,\"Quantity\":85,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":2,\"Quantity\":32,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":2,\"Quantity\":43,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":3,\"Quantity\":63,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":3,\"Quantity\":23,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":3,\"Quantity\":5,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":3,\"Quantity\":33,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":3,\"Quantity\":87,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":3,\"Quantity\":95,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":3,\"Quantity\":98,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":3,\"Quantity\":45,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":3,\"Quantity\":44,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":4,\"Quantity\":13,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":4,\"Quantity\":9,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":4,\"Quantity\":3,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":4,\"Quantity\":52,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":4,\"Quantity\":95,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":4,\"Quantity\":92,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":4,\"Quantity\":72,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2012},{\"QTR\":4,\"Quantity\":23,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2012},{\"QTR\":4,\"Quantity\":29,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2012},{\"QTR\":1,\"Quantity\":9,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":1,\"Quantity\":6,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":1,\"Quantity\":4,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2013},{\"QTR\":1,\"Quantity\":30,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":1,\"Quantity\":82,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":1,\"Quantity\":105,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2013},{\"QTR\":1,\"Quantity\":88,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":1,\"Quantity\":9,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":1,\"Quantity\":35,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2013},{\"QTR\":2,\"Quantity\":17,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":2,\"Quantity\":11,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":2,\"Quantity\":5,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2013},{\"QTR\":2,\"Quantity\":27,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":2,\"Quantity\":89,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":2,\"Quantity\":100,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2013},{\"QTR\":2,\"Quantity\":82,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":2,\"Quantity\":7,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":2,\"Quantity\":52,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2013},{\"QTR\":3,\"Quantity\":23,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":3,\"Quantity\":11,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":3,\"Quantity\":2,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2013},{\"QTR\":3,\"Quantity\":25,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":3,\"Quantity\":67,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":3,\"Quantity\":123,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2013},{\"QTR\":3,\"Quantity\":123,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":3,\"Quantity\":13,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":3,\"Quantity\":63,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2013},{\"QTR\":4,\"Quantity\":5,\"Region\":\"America\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":4,\"Quantity\":3,\"Region\":\"America\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":4,\"Quantity\":7,\"Region\":\"America\",\"Vehicle\":\"Exige\",\"Year\":2013},{\"QTR\":4,\"Quantity\":32,\"Region\":\"Asia\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":4,\"Quantity\":53,\"Region\":\"Asia\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":4,\"Quantity\":117,\"Region\":\"Asia\",\"Vehicle\":\"Exige\",\"Year\":2013},{\"QTR\":4,\"Quantity\":105,\"Region\":\"Europe\",\"Vehicle\":\"Elise\",\"Year\":2013},{\"QTR\":4,\"Quantity\":5,\"Region\":\"Europe\",\"Vehicle\":\"Evora\",\"Year\":2013},{\"QTR\":4,\"Quantity\":48,\"Region\":\"Europe\",\"Vehicle\":\"Exige\",\"Year\":2013}]";
    
    // Sales variables
    private List<Sales> sales = new LinkedList<Sales>();
    
    // FXML variables
    private DashboardController dashboardFXML;
    private LoginController loginFXML;
    private SettingsController settingsFXML;
    private AboutController aboutFXML;
    
    // Statis constant variables
    public static String WINDOW_TITLE = "| LOTUS | ";
    
    // Stage variables
    public Stage stage;
    
    // Style variables
    public String currentStyle = "Lotus";
    
    // Network variables
    public SalesService service;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        
        //add application icon 
        this.stage.getIcons().add(new Image("images/lotus.png"));
        
        //this.displayLogin();
        
        this.setLotusStyle();
        
        service = new SalesService();
        service.setAddress("http://glynserver.cms.livjm.ac.uk/DashService/SalesGetSales");
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent e) {
                json = e.getSource().getValue().toString();
                sales = (new Gson()).fromJson(json, new TypeToken<LinkedList<Sales>>() {}.getType());
                
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        dashboardFXML.setSalesData(sales);
                        dashboardFXML.setupUserInterface();
                    }
            });

            }          
        });
        
        service.start();
        
        this.displayDashboard();
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
    
    //*****************//
    // LOGIN FUNCTIONS //
    //*****************//
    public void displayLogin()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = fxmlLoader.load();            
            Scene scene = new Scene(root, 500, 300);

            stage.setTitle(VehiclesDashboard.WINDOW_TITLE + "Login");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            
            this.loginFXML = fxmlLoader.getController();
            this.loginFXML.vehicleDashboard = this;
            this.loginFXML.setScene(scene);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //*********************//
    // DASHBOARD FUNCTIONS //
    //*********************//
    public void displayDashboard()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root = fxmlLoader.load();            
            Scene scene = new Scene(root, 1024, 758);           
            
            stage.setTitle(VehiclesDashboard.WINDOW_TITLE + "Vehicle Sales Dashboard");
            stage.setScene(scene);
            stage.centerOnScreen(); 
            
            this.dashboardFXML = fxmlLoader.getController();
            this.dashboardFXML.setScene(scene);
            //this.getSalesData();
            this.dashboardFXML.vehicleDashboard = this;
            this.dashboardFXML.setSalesData(sales);
            this.dashboardFXML.setupUserInterfaceInitial();
                      
            service.restart();
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //*****************//
    // ABOUT FUNCTIONS //
    //*****************//
    public void displayAboutInfo() throws IOException
    {
        Stage aboutStage = new Stage();
        FXMLLoader aboutLoader = new FXMLLoader(getClass().getResource("About.fxml"));
        Parent aboutRoot = aboutLoader.load();
        Scene aboutScene = new Scene(aboutRoot, 500, 200);
        aboutFXML = aboutLoader.getController();
        
        aboutFXML.vehicleDashboard = this;
        aboutStage.setTitle(VehiclesDashboard.WINDOW_TITLE + "About");
        aboutStage.setScene(aboutScene);
        aboutStage.show();
        aboutFXML.setScene(aboutScene);
    }
    
    //********************//
    // SETTINGS FUNCTIONS //
    //********************//
    public void displaySettings() throws IOException
    {
        Stage settingsStage = new Stage();
        FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("Settings.fxml"));
        Parent settingsRoot = settingsLoader.load();
        Scene settingsScene = new Scene(settingsRoot, 400, 250);
        settingsFXML = settingsLoader.getController();
        
        settingsStage.setTitle(VehiclesDashboard.WINDOW_TITLE + "Settings");
        settingsFXML.vehicleDashboard = this;
        settingsStage.setScene(settingsScene);
        settingsStage.show();
        settingsFXML.setScene(settingsScene);
        settingsFXML.init();
    }
    
    //***********************//
    // APPLICATION FUNCTIONS //
    //***********************//
    public static void closeApplication()
    {
        Platform.exit();
    }
    
    //*****************//
    // STYLE FUNCTIONS //
    //*****************//
    public void setLotusStyle()
    {
        this.currentStyle = "Lotus";
        this.setStyle();
    }
    
    public void setContrastStyle()
    {
        this.currentStyle = "Contrast";
        this.setStyle();
    }
    
    public void setStyle()
    {
        if (this.dashboardFXML != null)
            this.dashboardFXML.setStyle("css/" + this.currentStyle + ".css");
        
        if (this.loginFXML != null)
            this.loginFXML.setStyle("css/" + this.currentStyle + ".css");
        
        if (this.settingsFXML != null)
            this.settingsFXML.setStyle("css/" + this.currentStyle + ".css");
        
        if (this.aboutFXML != null)
            this.aboutFXML.setStyle("css/" + this.currentStyle + ".css");
    }
    
    public void restartService()
    {
        this.service.restart();
    }
    
    public static class SalesService extends Service<String> {
        private StringProperty address = new SimpleStringProperty();

        public final void setAddress(String address) {
            this.address.set(address);
        }

        public final String getAddress() {
            return address.get();
        }

        public final StringProperty addressProperty() {
           return address;
        }

        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                private URL url;
                private HttpURLConnection connect;
                private String markup = "";

                @Override
                protected String call() {
                    try {
                        url = new URL(getAddress());
                        connect = (HttpURLConnection)url.openConnection();
                        connect.setRequestMethod("GET");
                        connect.setRequestProperty("Accept", "application/json");
                        connect.setRequestProperty("Content-Type", "application/json");                        

                        markup = (new BufferedReader(new InputStreamReader(connect.getInputStream()))).readLine();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    finally {
                        if (connect != null) {
                            connect.disconnect();
                        }
                    }

                    return markup;
                }
            };
        }
    }
}
