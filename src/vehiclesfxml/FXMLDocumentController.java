/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclesfxml;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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
    private PieChart pieChart;
    private ComboBox comboBoxYears;
    
    // Sales variables
    private List<Sales> sales;
    private List<Integer> years;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.hBoxYearCheckboxes = (HBox)this.vBoxLeft.lookup("#hBoxYearCheckboxes");
        this.pieChart = (PieChart)this.vBoxLeft.lookup("#pieChart");
        this.comboBoxYears = (ComboBox)this.vBoxLeft.lookup("#comboBoxYears");
    }    
    
    //**********************//
    // SALES DATA FUNCTIONS //
    //**********************//
    public void setSalesData(List<Sales> sales)
    {
        this.sales = sales;
        this.years = this.sales.stream().map(x -> x.getYear()).distinct().collect(Collectors.toList());
    }
    
    //***************************//
    // SETUP INTERFACE FUNCTIONS //
    //***************************//
    public void setupUserInterface()
    {
        this.setupYearCheckboxes();
        this.setupPieChart();
        this.setupYearComboBox();
    }
    
    //***************************//
    // YEAR CHECKBOXES FUNCTIONS //
    //***************************//
    public void setupYearCheckboxes()
    {
        CheckBox[] checkBoxes = new CheckBox[years.size()];
        
        for (int i = 0; i < years.size(); i++)
        {
            checkBoxes[i] = new CheckBox(years.get(i).toString());
            checkBoxes[i].setSelected(true);
        }

        this.hBoxYearCheckboxes.getChildren().addAll(checkBoxes);
    }
    
    //*************************//
    // YEAR COMBOBOX FUNCTIONS //
    //*************************//
    private void setupYearComboBox()
    {
        this.comboBoxYears.getItems().clear();
        
        for (int i = 0; i < this.years.size(); i++)
        {
            this.comboBoxYears.getItems().add(this.years.get(i).toString());
        }

        this.comboBoxYears.getSelectionModel().selectFirst();
    }
    
    //*********************//
    // PIE CHART FUNCTIONS //
    //*********************//
    public void setupPieChart()
    {
        /*
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Grapefruit", 13),
                new PieChart.Data("Oranges", 25),
                new PieChart.Data("Plums", 10),
                new PieChart.Data("Pears", 22),
                new PieChart.Data("Apples", 30));
        */
        
        System.out.println(this.comboBoxYears.getValue());
        
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                    new PieChart.Data("A", 15),
                    new PieChart.Data("B", 40));
        
        this.pieChart.setData(data);
    }
}
