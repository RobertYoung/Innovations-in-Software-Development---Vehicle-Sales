/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclesfxml;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author cmpryoun
 */
public class Sales {
    private int QTR;
    private int Quantity;
    private String Region;
    private String Vehicle;
    private int Year;
    
    public Sales(int qtr, int quantity, String region, String vehicle, int year)
    {
        this.setQTR(qtr);
        this.setQuantity(quantity);
        this.setRegion(region);
        this.setVehicle(vehicle);
        this.setYear(year);
    }
    
    //***************//
    // QTR FUNCTIONS //
    //***************//
    public void setQTR(int qtr)
    {
        this.QTR = qtr;
    }
    
    public int getQTR()
    {
        return this.QTR;
    }
    
    /*
    public IntegerProperty qtrProperty()
    {
        return this.QTR;
    }*/
    
    //********************//
    // QUANTITY FUNCTIONS //
    //********************//
    public void setQuantity(int quantity)
    {
        this.Quantity = quantity;
    }
    
    public int getQuantity()
    {
        return this.Quantity;
    }
    
    /*
    public IntegerProperty quantityProperty()
    {
        return this.Quantity;
    }*/
    
    //******************//
    // REGION FUNCTIONS //
    //******************//
    public void setRegion(String region)
    {
        this.Region = region;
    }
    
    public String getRegion()
    {
        return this.Region;
    }
    
    /*
    public StringProperty regionProperty()
    {
        return this.Region;
    }*/
    
    //*******************//
    // VEHICLE FUNCTIONS //
    //*******************//
    public void setVehicle(String vehicle)
    {
        this.Vehicle = vehicle;
    }
    
    public String getVehicle()
    {
        return this.Vehicle;
    }
    
    /*
    public StringProperty vehicleProperty()
    {
        return this.Vehicle;
    }*/
    
    //****************//
    // YEAR FUNCTIONS //
    //****************//
    public void setYear(int year)
    {
        this.Year = year;
    }
    
    public int getYear()
    {
        return this.Year;
    }
    
    /*
    public IntegerProperty yearProperty()
    {
        return this.Year;
    }*/
    
    //******************//
    // HELPER FUNCTIONS //
    //******************//
    public String toString()
    {
        return String.format("Sales: %s %s %s %s %s", 
                ("QTR: "+ this.getQTR()),
                ("Quantity: " + this.getQuantity()),
                ("Region: " + this.getRegion()),
                ("Vehicle: " + this.getVehicle()),
                ("Year: " + this.getYear()));
    }
    
    public String getValueFromString(String property)
    {
        for (Method method : this.getClass().getMethods())
        {
            if (method.getName().equals("get" + property))
            {
                try {
                    Object value = method.invoke(this, null);                    
                    
                    if (value != null)
                        return value.toString();
                    
                    return "";
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return "";
    }
}
