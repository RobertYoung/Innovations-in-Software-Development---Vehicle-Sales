/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclesfxml;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author cmpjbate
 */
public class DashboardController implements Initializable {
    
    @FXML
    // Anchor
    private AnchorPane anchorPane;
    
    // Layouts
    private VBox vBoxLeft;
    private HBox hBoxYearCheckboxes;
    private HBox hBoxChartSelection;
    private Pane paneCharts;
    private VBox vBoxViewData;
    private VBox vBoxFilter;
    
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
    private ObservableList<Sales> saleTableData;
    private List<String> saleProperties;
    private List<Sales> searchedSales;
    
    // Scene variables
    private Scene scene;
    
    // Top nav bar
    private Label lblDateTime;
    private ImageView ivLogo;
    
    // View data
    private TableView tvData;
    private TableColumn colYear;
    private TextField txtSearchInput;
    private List<CheckBox> cbFilters;
    private BooleanProperty search = new SimpleBooleanProperty(false);
    private Button btnReset;
    private Button btnSearch;
    
    // Footer
    private GridPane gpTop3;
    
    // Checkboxes
    CheckBox[] yearCheckBoxes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Layout
        this.paneCharts = (Pane)this.anchorPane.lookup("#paneCharts");
        this.vBoxLeft = (VBox)this.paneCharts.lookup("#vBoxLeft");
        this.hBoxYearCheckboxes = (HBox)this.vBoxLeft.lookup("#hBoxYearCheckboxes");
        this.hBoxChartSelection = (HBox)this.vBoxLeft.lookup("#hBoxChartSelection");
        this.vBoxViewData = (VBox)this.anchorPane.lookup("#vBoxViewData");
        this.vBoxFilter = (VBox)this.anchorPane.lookup("#vBoxFilter");
        
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
        
        // View data
        this.tvData = (TableView)this.vBoxViewData.lookup("#tvData");
        this.txtSearchInput = (TextField)this.vBoxViewData.lookup("#txtSearchInput");
        this.btnReset = (Button)this.anchorPane.lookup("#btnReset");
        this.btnReset.setDisable(true);
        this.btnSearch = (Button)this.anchorPane.lookup("#btnSearch");
        this.btnSearch.setDefaultButton(true);
        
        // Top nav bar
        this.lblDateTime = (Label)this.anchorPane.lookup("#lblDateTime");
        this.ivLogo = (ImageView)this.anchorPane.lookup("#ivLogo");
        
        // Footer
        this.gpTop3 = (GridPane)this.anchorPane.lookup("#gpTop3");
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
        this.setupTableView();
        this.setupFilterTableData();
        this.setupDateTimeLabel();
        this.setupLogo();
        this.setupTop3();
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
                    constructLineChart();
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
            this.barChart.setTitle("Sales Figures by Vehicle");
        }
    }
    
    //**********************//
    // LINE CHART FUNCTIONS //
    //**********************//
    private void setupLineChart()
    {
        this.lineChart.setTitle("Vehicle Sales by Year");
        this.constructLineChart();
    }
    
    private void constructLineChart()
    {
        this.lineChart.getData().clear();
        
        for (CheckBox cb : this.yearCheckBoxes)
        {
            if (cb.isSelected())
            {
                int year = Integer.parseInt(cb.getText());
                XYChart.Series series = new XYChart.Series();
            
                series.setName(cb.getText());

                List<Integer> quarters = this.sales.stream().filter(x -> x.getYear() == year).map(x -> x.getQTR()).distinct().collect(Collectors.toList());

                for (Integer quarter : quarters)
                {
                    double quantity = this.sales.stream().filter(x -> x.getYear() == year).filter(x -> x.getQTR() == quarter).mapToDouble(x -> x.getQuantity()).sum(); 

                    series.getData().add(new XYChart.Data("Q" + quarter, quantity));
                }

                this.lineChart.getData().add(series);
                this.lineChart.setCursor(Cursor.CROSSHAIR);
            }
        }
    }
    
    //***************************//
    // CHART SELECTION FUNCTIONS //
    //***************************//
    private void setupChartSelection()
    {
        this.pieChart.visibleProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            for (CheckBox cb : yearCheckBoxes)
            {
                cb.disableProperty().set(newValue);
            }
            
            comboBoxYears.disableProperty().set(!newValue);            
        });
        
        this.pieChart.visibleProperty().bind(this.rbPieChart.selectedProperty());
        this.barChart.visibleProperty().bind(this.rbBarChart.selectedProperty());
        this.lineChart.visibleProperty().bind(this.rbLineChart.selectedProperty());
    }
    
    //*****************************//
    // FILTER TABLE DATA FUNCTIONS //
    //*****************************//
    private void setupFilterTableData()
    {
        this.cbFilters = new LinkedList<CheckBox>();
        
        for (String property : this.saleProperties)
        {
            if (property.equals("Quantity"))
                continue;
            
            // Hbox for filters
            HBox hBox = new HBox();
            
            hBox.setPadding(new Insets(2, 10, 2, 10));
            
            // Add label
            Label label = new Label(property);
            label.setPadding(new Insets(2, 10, 2, 0));
            hBox.getChildren().add(label);
            
            // Get distinct data
            List<String> data = this.sales.stream().map(x -> x.getValueFromString(property)).distinct().collect(Collectors.toList());
            
            List<CheckBox> filters = new LinkedList<CheckBox>();
            
            for (int i = 0; i < data.size(); i++)
            {
                String filter = data.get(i);
                CheckBox cb = new CheckBox(filter);
                cb.setSelected(true);
                cb.setId(property);
                cb.addEventFilter(ActionEvent.ACTION, (ActionEvent event) -> {
                    constructTableView();
                });
                
                filters.add(cb);
            }
            
            this.cbFilters.addAll(filters);
            
            hBox.getChildren().addAll(filters);
          
            // Add to filter box
            this.vBoxFilter.getChildren().add(hBox);
        }
    }
    
    //***************************//
    // TABLE VIEW DATA FUNCTIONS //
    //***************************//
    private void setupTableView()
    {
        this.saleTableData = FXCollections.observableArrayList(this.sales);
        
        saleProperties = new LinkedList<String>();     

        for (Method method : Sales.class.getMethods())
        {
            if (method.getName().startsWith("get") && !method.getName().endsWith("Class") && !method.getName().equals("getValueFromString"))
            {
                saleProperties.add(method.getName().replace("get", ""));
            }
        }
        
        for (String property : saleProperties)
        {
            TableColumn column = new TableColumn(property);

            column.setCellValueFactory(new PropertyValueFactory<Sales, String>(property));
            column.prefWidthProperty().bind(this.tvData.widthProperty().divide(saleProperties.size())); 
                        
            this.tvData.getColumns().add(column);
        }        
        
        this.tvData.setColumnResizePolicy((param) -> true);
        
        this.constructTableView();
    }
    
    private void constructTableView()
    {
        this.tvData.getItems().clear();
        this.saleTableData = FXCollections.observableArrayList(this.sales);
        
        if (this.search.get())
        {
            this.search.set(false);
            this.tvData.setItems(FXCollections.observableArrayList(this.searchedSales));
            return;
        }
        
        //this.btnReset.setDisable(true);
        
        if (this.cbFilters != null)
        {
            for (CheckBox cb : this.cbFilters)
            {
                if (!cb.isSelected())
                {
                    for (int index = saleTableData.size() - 1; index >= 0; index--)
                    {
                        if (saleTableData.get(index).getValueFromString(cb.getId()).equals(cb.getText()))
                            saleTableData.remove(index);
                    } 
                }
            }   
        }
        
        this.tvData.setItems(FXCollections.observableArrayList(this.saleTableData));
    }
    
    //******************//
    // SEARCH FUNCTIONS //
    //******************//
    public void searchButtonClicked()
    {
        if (this.txtSearchInput.getText().isEmpty())
        {
           this.displayPopup("Please enter a keyword");
           
           return;
        }
        
        this.btnReset.setDisable(false);
        this.search.set(true);
        
        this.cbFilters.forEach(x -> {
            x.setSelected(true);
        });
        
        String textEntered = this.txtSearchInput.getText().toLowerCase();
        
        this.searchedSales = new LinkedList<Sales>();
        this.searchedSales = this.sales.stream().filter(x -> x.getRegion().toLowerCase().contains(textEntered) || x.getVehicle().toLowerCase().contains(textEntered)).collect(Collectors.toList());

        this.constructTableView();
    }
    
    public void resetButtonClicked()
    {
        this.txtSearchInput.clear();
        this.saleTableData.clear();
        this.saleTableData = FXCollections.observableArrayList(this.sales);
        this.constructTableView();
        this.btnReset.setDisable(true);
    }
    
    //************************//
    // POPUP WINDOW FUNCTIONS //
    //************************//
    private void displayPopup(String description)
    {
        Stage dialogStage = new Stage();
        Button btnOk = new Button("Ok");
        Text txtDescription = new Text(description);

        btnOk.setOnAction((event) -> {
            dialogStage.close();
        });

        dialogStage.setScene(new Scene(VBoxBuilder.create().children(txtDescription, btnOk).alignment(Pos.CENTER).padding(new Insets(10)).build()));
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.show();
    }
    
    //***********************//
    // APPLICATION FUNCTIONS //
    //***********************//
    public void closeApplication()
    {
        Stage dialogStage = new Stage();
        HBox hBoxCloseButtons = new HBox();
        Button btnCancel = new Button("Cancel");
        Button btnOk = new Button("Ok");
        Text txtDescription = new Text("Are you sure you want to exit?");

        btnCancel.setPadding(new Insets(10, 10, 10, 10));
        btnCancel.setOnAction((event) -> {
            dialogStage.close();
        });
        
        btnOk.setPadding(new Insets(10, 10, 10, 10));
        btnOk.setOnAction((event) -> {
             Platform.exit();
        });
        
        hBoxCloseButtons.setAlignment(Pos.CENTER);
        hBoxCloseButtons.setPadding(new Insets(10, 10, 10, 10));
        hBoxCloseButtons.getChildren().addAll(btnCancel, btnOk);

        dialogStage.setScene(new Scene(VBoxBuilder.create().children(txtDescription, hBoxCloseButtons).alignment(Pos.CENTER).padding(new Insets(30)).build()));
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.show();   
    }
    
    public void displayAboutInfo() throws IOException
    {
        Stage aboutStage = new Stage();
        FXMLLoader aboutLoader = new FXMLLoader(getClass().getResource("About.fxml"));
        Parent aboutRoot = aboutLoader.load();
        Scene aboutScene = new Scene(aboutRoot, 500, 200);
        AboutController aboutController = aboutLoader.getController();
        
        aboutStage.setScene(aboutScene);
        aboutStage.show();
        aboutController.setScene(aboutScene);
    }
    
    public void refresh()
    {
        System.out.println("Refresh");
    }
    
    //*********************//
    // DATE TIME FUNCTIONS //
    //*********************//
    private void setupDateTimeLabel()
    {
        DateFormat format = DateFormat.getInstance();
        
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
            Calendar calendar = Calendar.getInstance();
            
            lblDateTime.setText(format.format(calendar.getTime()));
        }));
        
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    //*****************//
    // IMAGE FUNCTIONS //
    //*****************//
    private void setupLogo() {
        Image logo = new Image(getClass().getResource("lotus.png").toExternalForm());
        
        this.ivLogo.setImage(logo);
    }

    //******************//
    // FOOTER FUNCTIONS //
    //******************//
    private void setupTop3() {
        List<LinkedHashMap<String, IntSummaryStatistics>> vehicleTop3 = this.calculateTop3Vehicles();
        List<LinkedHashMap<String, IntSummaryStatistics>> regionTop3 = this.calculateTop3Regions();
        
        for (int i = 0; i < 3; i++)
        {
            String imageFile = "medal";
            
            if (i == 0)
            {
                imageFile = "trophy";
            }
            
            // Vehicle top 3
            String vehicle = vehicleTop3.get(i).entrySet().stream().findFirst().get().getKey();
            IntSummaryStatistics statistics = vehicleTop3.get(i).entrySet().stream().findFirst().get().getValue();
            Label label = new Label(vehicle + " (" + statistics.getSum() + ")");
            Image trophy = new Image(getClass().getResource(imageFile + ".png").toExternalForm());
            ImageView ivTrophy = new ImageView(trophy);

            ivTrophy.setFitHeight(20);
            ivTrophy.setFitWidth(20);

            label.setPadding(new Insets(0, 0, 0, 30));
            
            this.gpTop3.add(ivTrophy, i + 1, 0);
            this.gpTop3.add(label, i + 1, 0);
            
            // Vehicle top 3
            String region = regionTop3.get(i).entrySet().stream().findFirst().get().getKey();
            IntSummaryStatistics regionStatistics = regionTop3.get(i).entrySet().stream().findFirst().get().getValue();
            Label labelRegion = new Label(region + " (" + regionStatistics.getSum() + ")");
            Image trophyRegion = new Image(getClass().getResource(imageFile + ".png").toExternalForm());
            ImageView ivTrophyRegion = new ImageView(trophyRegion);
            
            ivTrophyRegion.setFitHeight(20);
            ivTrophyRegion.setFitWidth(20);

            labelRegion.setPadding(new Insets(0, 0, 0, 30));
            
            this.gpTop3.add(ivTrophyRegion, i + 1, 1);
            this.gpTop3.add(labelRegion, i + 1, 1);
        }
    }
    
    private List<LinkedHashMap<String, IntSummaryStatistics>> calculateTop3Vehicles()
    {
        // Top vehicles
        Map<String, List<Sales>> vehicles =  this.sales.stream().collect(Collectors.groupingBy(x -> x.getVehicle()));
        List<LinkedHashMap<String, IntSummaryStatistics>> vehicleTop3 = new LinkedList<>();
        
        for (Entry<String, List<Sales>> vehicle : vehicles.entrySet())
        {
            LinkedHashMap<String, IntSummaryStatistics> map = new LinkedHashMap();
            
            map.put(vehicle.getKey(), vehicle.getValue().stream().collect(Collectors.summarizingInt(x -> x.getQuantity())));
            
            vehicleTop3.add(map);
        }
        
        Collections.sort(vehicleTop3, new Comparator<LinkedHashMap<String, IntSummaryStatistics>>()
        {

            @Override
            public int compare(LinkedHashMap<String, IntSummaryStatistics> o1, LinkedHashMap<String, IntSummaryStatistics> o2) {
                IntSummaryStatistics o1Summary = o1.entrySet().stream().findFirst().get().getValue();
                IntSummaryStatistics o2Summary = o2.entrySet().stream().findFirst().get().getValue();
                
                return (int)o2Summary.getSum() - (int)o1Summary.getSum();
            }
            
        });
        
        return vehicleTop3;
    }
    
    private List<LinkedHashMap<String, IntSummaryStatistics>> calculateTop3Regions()
    {
        // Top vehicles
        Map<String, List<Sales>> regions =  this.sales.stream().collect(Collectors.groupingBy(x -> x.getRegion()));
        List<LinkedHashMap<String, IntSummaryStatistics>> regionTop3 = new LinkedList<>();
        
        for (Entry<String, List<Sales>> vehicle : regions.entrySet())
        {
            LinkedHashMap<String, IntSummaryStatistics> map = new LinkedHashMap();
            
            map.put(vehicle.getKey(), vehicle.getValue().stream().collect(Collectors.summarizingInt(x -> x.getQuantity())));
            
            regionTop3.add(map);
        }
        
        Collections.sort(regionTop3, new Comparator<LinkedHashMap<String, IntSummaryStatistics>>()
        {

            @Override
            public int compare(LinkedHashMap<String, IntSummaryStatistics> o1, LinkedHashMap<String, IntSummaryStatistics> o2) {
                IntSummaryStatistics o1Summary = o1.entrySet().stream().findFirst().get().getValue();
                IntSummaryStatistics o2Summary = o2.entrySet().stream().findFirst().get().getValue();
                
                return (int)o2Summary.getSum() - (int)o1Summary.getSum();
            }
            
        });
        
        return regionTop3;
    }
}