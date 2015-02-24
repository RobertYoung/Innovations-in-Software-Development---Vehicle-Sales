/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclesfxml;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author cmpryoun
 */
public class Sales {
    private IntegerProperty QTR = new SimpleIntegerProperty();
    private IntegerProperty Quantity = new SimpleIntegerProperty();;
    private StringProperty Region = new SimpleStringProperty();
    private StringProperty Vehicle = new SimpleStringProperty();;
    private IntegerProperty Year =  new SimpleIntegerProperty();;
    
    public Sales(int qtr, int quantity, String region, String vehicle, int year)
    {
        this.setQTR(qtr);
        
    }
    
    //***************//
    // QTR FUNCTIONS //
    //***************//
    public void setQTR(int qtr)
    {
        this.QTR.setValue(qtr);
    }
    
    public int getQTR()
    {
        return this.QTR.getValue();
    }
    
    public IntegerProperty qtrProperty()
    {
        return this.QTR;
    }
    
    //********************//
    // QUANTITY FUNCTIONS //
    //********************//
    public void setQuantity(int quantity)
    {
        this.Quantity.setValue(quantity);
    }
    
    public int getQuantity()
    {
        return this.Quantity.getValue();
    }
    
    public IntegerProperty quantityProperty()
    {
        return this.Quantity;
    }
    
    //******************//
    // REGION FUNCTIONS //
    //******************//
    public void setRegion(String region)
    {
        this.Region.setValue(region);
    }
    
    public String getRegion()
    {
        return this.Region.getValue();
    }
    
    public StringProperty regionProperty()
    {
        return this.Region;
    }
    
    //*******************//
    // VEHICLE FUNCTIONS //
    //*******************//
    public void setVehicle(String vehicle)
    {
        this.Vehicle.setValue(vehicle);
    }
    
    public String getVehicle()
    {
        return this.Vehicle.getValue();
    }
    
    public StringProperty vehicleProperty()
    {
        return this.Vehicle;
    }
    
    //****************//
    // YEAR FUNCTIONS //
    //****************//
    public void setYear(int year)
    {
        this.Year.setValue(year);
    }
    
    public int getYear()
    {
        return this.Year.getValue();
    }
    
    public IntegerProperty yearProperty()
    {
        return this.Year;
    }
}
