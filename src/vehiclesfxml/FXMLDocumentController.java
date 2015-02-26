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
import java.util.stream.Stream;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author cmpjbate
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    // Anchor
    private AnchorPane anchorPane;
    
    // Layouts
    private VBox vBoxLeft;
    private HBox hBoxYearCheckboxes;
    private HBox hBoxChartSelection;
    private Pane paneCharts;
    
    // Combo boxes
    private ComboBox comboBoxYears;
    
    // Chart rabio buttons
    private RadioButton rbPieChart;
    private RadioButton rbLineChart;
    private RadioButton rbBarChart;
    
    // Pane charts
    private Pane panePieChart;
    private Pane paneBarChart;
    private Pane paneLineChart;
    
    // Charts
    private PieChart pieChart;
    private BarChart barChart;
    private LineChart lineChart;
    
    // Sales variables
    private List<Sales> sales;
    private List<Integer> years;
    
    // Scene variables
    private Scene scene;
    
    // Checkboxes
    CheckBox[] yearCheckBoxes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Layout
        this.paneCharts = (Pane)this.anchorPane.lookup("#paneCharts");
        this.vBoxLeft = (VBox)this.paneCharts.lookup("#vBoxLeft");
        this.hBoxYearCheckboxes = (HBox)this.vBoxLeft.lookup("#hBoxYearCheckboxes");
        this.hBoxChartSelection = (HBox)this.vBoxLeft.lookup("#hBoxChartSelection");
        
        // Checkboxes
        this.comboBoxYears = (ComboBox)this.vBoxLeft.lookup("#comboBoxYears");
        
        // Radio buttons
        this.rbPieChart = (RadioButton)this.hBoxChartSelection.lookup("#rbPieChart");
        this.rbLineChart = (RadioButton)this.hBoxChartSelection.lookup("#rbLineChart");
        this.rbBarChart = (RadioButton)this.hBoxChartSelection.lookup("#rbBarChart");
        
        // Chart panes
        this.panePieChart = (Pane)this.paneCharts.lookup("#panePieChart");
        this.paneBarChart = (Pane)this.paneCharts.lookup("#paneBarChart");
        this.paneLineChart = (Pane)this.paneCharts.lookup("#paneLineChart");
        
        // Charts
        this.pieChart = (PieChart)this.panePieChart.lookup("#pieChart");
        this.barChart = (BarChart)this.paneBarChart.lookup("#barChart");
        this.lineChart = (LineChart)this.paneLineChart.lookup("#lineChart");
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
        this.setupBarChart();
        this.setupLineChart();
        this.setupChartSelection();
    }
    
    //***************************//
    // YEAR CHECKBOXES FUNCTIONS //
    //***************************//
    public void setupYearCheckboxes()
    {
        yearCheckBoxes = new CheckBox[years.size()];
        
        for (int i = 0; i < years.size(); i++)
        {
            yearCheckBoxes[i] = new CheckBox(years.get(i).toString());
            yearCheckBoxes[i].setSelected(true);
            yearCheckBoxes[i].addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event) {
                    constructBarChart();
                }
            });
        }
        this.hBoxYearCheckboxes.getChildren().addAll(yearCheckBoxes);
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
    
    //*********************//
    // BAR CHART FUNCTIONS //
    //*********************//
    private void setupBarChart()
    {
        this.barChart.animatedProperty().set(false);
        this.constructBarChart();
    }
    
    private void constructBarChart()
    {
        this.barChart.getData().clear();
        
        for (CheckBox cb : this.yearCheckBoxes)
        {
            if (cb.isSelected())
            {
                XYChart.Series series = new XYChart.Series();
                
                series.setName(cb.getText());
                
                /*
                for (Sales sale : this.sales)
                {
                    if (sale.getYear() == Integer.parseInt(cb.getText()))
                    {
                        series.getData().add(new XYChart.Data<>(sale.getVehicle(), sale.getQuantity()));
                    }
                }*/
                
                this.sales.stream().filter(x -> x.getYear() == Integer.parseInt(cb.getText())).forEach(x -> {
                    series.getData().add(new XYChart.Data<>(x.getVehicle(), x.getQuantity()));
                });
                
                this.barChart.getData().add(series);
            }
        }
        
        if (this.barChart.getData().size() == 0)
        {
            this.barChart.setTitle("Please select a year(s)");
        }else{
            this.barChart.setTitle("Sales Figures By Vehicle");
        }
    }
    
    //**********************//
    // LINE CHART FUNCTIONS //
    //**********************//
    private void setupLineChart()
    {
        this.constructLineChart();
    }
    
    private void constructLineChart()
    {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        
        xAxis.setLabel("Regions");
        yAxis.setLabel("Sales");
        
        //List<String> yea = this.sales.stream().map(x -> x.getRegion()).distinct().collect(Collectors.toList());
        
        for (int year : this.years)
        {
            XYChart.Series series = new XYChart.Series();
            
            List<Integer> quarters = this.sales.stream().filter(x -> x.getYear() == year).map(x -> x.getQTR()).distinct().collect(Collectors.toList());
                        
            for (Integer quarter : quarters)
            {
                double quantity = this.sales.stream().filter(x -> x.getYear() == year).filter(x -> x.getQTR() == quarter).mapToDouble(x -> x.getQuantity()).sum(); 
                
                series.getData().add(new XYChart.Data(year + " | Q" + quarter, quantity));
            }
            
            /*
            double quantity = this.sales.stream().filter(x -> x.getRegion().equals(region)).mapToDouble(x -> x.getQuantity()).sum();
            
            series.getData().add(new XYChart.Data(region, quantity));
            */
            
            
            
            this.lineChart.getData().add(series);
        }
    }
    
    //***************************//
    // CHART SELECTION FUNCTIONS //
    //***************************//
    private void setupChartSelection()
    {
        this.pieChart.visibleProperty().bind(this.rbPieChart.selectedProperty());
        this.barChart.visibleProperty().bind(this.rbBarChart.selectedProperty());
        this.lineChart.visibleProperty().bind(this.rbLineChart.selectedProperty());
        
        this.pieChart.visibleProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                for (CheckBox cb : yearCheckBoxes)
                {
                    cb.disableProperty().set(newValue);
                }
                
                comboBoxYears.disableProperty().set(!newValue);
            }            
        });
    }
}
