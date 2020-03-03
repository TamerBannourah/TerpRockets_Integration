import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import javafx.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.text.SimpleDateFormat;
import java.io.*;
public class TestStandUI {
	
	private Scene standScene;
	private BorderPane standBorder;
	private HBox mainGraphHBox;
	private VBox leftGraphVBox;
	private VBox rightGraphVBox;
	private HBox topHBox;
	private HBox bottomHBox;
	private Button switchToRocketButton;
	Series<String, Number> pressureSeries = new XYChart.Series<>();
	LineChart<String, Number> pressureChart;
	
	/*
	 * Have start stop and switch buttons
	 * 
	 * stop button should save to csv file and close the port
	 * 
	 * start should create a new portModule everytime
	 * 
	 * 
	 * 
	 */
	
	/**
	 * Test Stand UI Constructor
	 * 	creates the borderpane and fills it with 
	 * 	H and VBoxes
	 */
	public TestStandUI() {
		
		//set up borderpanes and Boxes
		 
		 standBorder = new BorderPane();
		 mainGraphHBox = new HBox();
		 leftGraphVBox = new VBox();
		 rightGraphVBox = new VBox();
		 topHBox = new HBox();
		 bottomHBox = new HBox();
		 standScene = new Scene(standBorder, 1920, 1080);
		 
		 switchToRocketButton = new Button();
		 //create charts
		 pressureChart = makePressureGraph();
		 
		 mainGraphHBox.setStyle("-fx-background-color: #42f563");
		 topHBox.setStyle("-fx-background-color: #643433");
		 leftGraphVBox.setStyle("-fx-background-color: #893764");
		 rightGraphVBox.setStyle("-fx-background-color: #12EAD1");
		 BuildUI();
		 switchButtonSettings();
	}
	
	
	
	public BorderPane getstandBorder() {
		return standBorder;
	}
	
	public Scene getScene() {
		return standScene;
	}
	
	public void pushToPressureSeries(String xAxisText, int seriesValue) {
		pressureSeries.getData().
			add(new XYChart.Data<>(xAxisText, seriesValue));
	}
	
	private LineChart<String, Number> makePressureGraph(){
		final CategoryAxis  xAxis = new CategoryAxis(); 
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time (s)");
        xAxis.setAnimated(true); 
        yAxis.setLabel("Pressure (PSI)");
        yAxis.setAnimated(true); 
        LineChart<String, Number> pressureChart = new LineChart(xAxis, yAxis);
        pressureChart.setTitle("Tank Pressure");
        pressureChart.setAnimated(false);
        pressureSeries.setName("Tank Pressure Data");
        pressureChart.getData().add(pressureSeries);
        pressureSeries.getData().add(new XYChart.Data<>("0", 0));
        return pressureChart;
		
	}
	
	private void switchButtonSettings() {
		switchToRocketButton.minHeight(70);
		switchToRocketButton.minWidth(150);
		switchToRocketButton.setText("Switch to Rocket UI");
		
		//switchToRocketButton.setDisable(true); use this to disable when data is comming in.
	}
	
	public Button getSwitchToRocketButton() {
		return switchToRocketButton;
	}
	
	private void BuildUI() {
		standBorder.setTop(topHBox);
		standBorder.setBottom(bottomHBox);
		standBorder.setCenter(mainGraphHBox);
		mainGraphHBox.getChildren().add(leftGraphVBox);
		mainGraphHBox.getChildren().add(rightGraphVBox);
		
		leftGraphVBox.setMinWidth(100);
		rightGraphVBox.setMinWidth(100);
		
		
		//then add graphs and other boxes 
		leftGraphVBox.getChildren().add(pressureChart);
		topHBox.getChildren().add(switchToRocketButton);
		
	}
	
}
