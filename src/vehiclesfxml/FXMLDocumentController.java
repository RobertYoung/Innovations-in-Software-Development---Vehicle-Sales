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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    
    // Scene variables
    private Scene scene;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.hBoxYearCheckboxes = (HBox)this.vBoxLeft.lookup("#hBoxYearCheckboxes");
        this.pieChart = (PieChart)this.vBoxLeft.lookup("#pieChart");
        this.comboBoxYears = (ComboBox)this.vBoxLeft.lookup("#comboBoxYears");
    }    
    
    //*****************//
    // SCENE FUNCTIONS //
    //*****************//
    public void setScene(Scene scene)
    {
        this.scene = scene;
    }
    
    private ObservableList<Node> getChildren()
    {
        return ((AnchorPane) scene.getRoot()).getChildren();
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
        this.setupYearComboBox();
        this.setupPieChart();
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
        int year = Integer.parseInt((String)this.comboBoxYears.getValue());
        List<Sales> yearData = this.sales.stream().filter(x -> x.getYear() == year).collect(Collectors.toList());
        
        int qtr1 = 0;
        int qtr2 = 0;
        int qtr3 = 0;
        int qtr4 = 0;
        
        for (Sales sale : yearData)
        {
            switch (sale.getQTR())
            {
                case 1:
                    qtr1 += sale.getQuantity();
                    break;
                case 2:
                    qtr2 += sale.getQuantity();
                    break;
                case 3:
                    qtr3 += sale.getQuantity();
                    break;
                case 4:
                    qtr4 += sale.getQuantity();
                    break;
            }
        }
        
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                    new PieChart.Data("Quarter 1", qtr1),
                    new PieChart.Data("Quarter 2", qtr2),
                    new PieChart.Data("Quarter 3", qtr3),
                    new PieChart.Data("Quarter 4", qtr4)
                );
        
        this.pieChart.setData(pieData);
        

        for (int i = 0; i < pieData.size(); i++)
        {
            PieChart.Data qtr = pieData.get(i);
            Label caption = new Label("");
            
            qtr.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){

                @Override
                public void handle(MouseEvent e) {
                
                    Bounds bounds = qtr.getNode().getBoundsInParent();
                    
                    caption.setTranslateX(bounds.getMaxX() - 70);
                    caption.setTranslateY(bounds.getMaxY() + 180);
                    caption.setText(String.valueOf(((int)qtr.getPieValue())));
                    ObservableList<Node> children = getChildren();
                    
                    if (children.indexOf(caption) == -1)
                         getChildren().add(caption);
                    else{
                        caption.visibleProperty().set(true);
                    }
                }
            });
            
            qtr.getNode().addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){

                @Override
                public void handle(MouseEvent e) {
                
                    ObservableList<Node> children = getChildren();
                    
                    if (children.indexOf(caption) != -1)
                         caption.visibleProperty().set(false);
                }
            });
        }
        
        this.pieChart.labelsVisibleProperty().set(true);
        this.pieChart.setTitle("Quarterly Sales Figues for " + year);
    }
}
