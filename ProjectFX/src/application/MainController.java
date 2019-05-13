package application;
import java.io.IOException;
import java.text.ParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {

	//@FXML
	//private SecondController secondController;
	
    @FXML
    private Pane pane;

    @FXML
    private VBox vbox;

    @FXML
    private HBox hbox;

    @FXML
    private TextField slitsNumber, intensity, slitsDistance, lambda, distanceScreen; 

    @FXML 
    private LineChart<Number, Number> chart;

    @FXML 
    private NumberAxis xAxis;

    @FXML
    private  NumberAxis yAxis;

    @FXML
    private Button chartBtn, moreBtn;
    
    @FXML
    private  int slits;
    
    @FXML
    private MenuItem closeItem, saveItem, excelOpen, aboutProgram, moreURL, settings;
    
    @FXML
    private RadioButton chartPhase, chartTheta, chartY;
    
    @FXML
    private ToggleGroup group;
    
    @FXML
    private double d, theta, I0, L, waveLambda;
    
    @FXML
    private MenuBar menubar;
   	double max = 0;
   	
   	
   	double phase, phase2;
	double width, min, submax;
	String widthParse, minParse, submaxParse;
	
	double[] tableY = new double[401];
	
	public MainController() {
	   
   }
   	
    public void initialize() { 
    	//creating chart
    	xAxis.setAutoRanging(true);
   	 	//xAxis.setLowerBound(-200);
   	 	//xAxis.setUpperBound(200);
   	 	xAxis.setTickUnit(40);

    	yAxis.setAutoRanging(false);
    	yAxis.setLowerBound(0);
  	    yAxis.setTickUnit(10);
  	    
  	    //zamiast tych if-ów w wykresach funkcji ;)
  	    yAxis.setAutoRanging(true);
    	   
  	    //Adding radiobuttons to the Togglegroup
  	    group = new ToggleGroup();
  	    chartPhase.setToggleGroup(group);
  	    chartTheta.setToggleGroup(group);
  	    chartY.setToggleGroup(group);
  	    chartPhase.setSelected(true);
 
    }
    
   
    @FXML
    public boolean  parsing (ActionEvent e) throws ParseException  {
    	I0 = Double.parseDouble(intensity.getText());
		d = Double.parseDouble(slitsDistance.getText());
		L = Double.parseDouble(distanceScreen.getText());
		waveLambda = Double.parseDouble(lambda.getText());
		System.out.println("Io: " + I0 +"\n" + "d: " + d +"\n" + "L: " + L +"\n"+ "Y: " + "waveLambda: " + waveLambda +"\n");
		
		slits = Integer.parseInt(slitsNumber.getText());
		System.out.println("SLits number entered: " + slits);
		
		return true;
    }
   
    
    @FXML
    public void drawChart(ActionEvent e) {
    	
    	chart.getData().clear();
    	System.out.println("Drawing chart is begining...");
        xAxis.setLabel("Phi [rad]");
        yAxis.setLabel("Intensity [W/m^2]");
        
        XYChart.Series<Number, Number> data1 = new XYChart.Series<Number, Number>();
        
        double n = -200;
        double j = -200;
       
        tableY[0] = 0;
        
        if(chartPhase.isSelected()) {
        	System.out.println("Phase chart starts drawing...");
        	for(int i = 0; i < 401; i++) {
	        	tableY[i] = I0*(Math.sin(slits*0.5*n))*(Math.sin(slits*0.5*n))/((Math.sin(0.5*n))*(Math.sin(0.5*n)));
	        	n += 0.01;
	        	j++;
	        	//if (max < tableY[i]) 
	        	//	max = tableY[i];
	        	data1.getData().add(new XYChart.Data<>(j, tableY[i]));
        	}
        	
        }else if(chartTheta.isSelected()) {
        	System.out.println("Theta chart starts drawing...");
	        for(int i = 0; i < 401; i++) {
	        	tableY[i] = I0*(Math.sin(slits*d*Math.sin(n)*Math.PI/waveLambda))*(Math.sin(slits*d*Math.sin(n)*Math.PI/waveLambda))/((Math.sin(d*Math.sin(n)*Math.PI/waveLambda))*(Math.sin(d*Math.sin(n)*Math.PI/waveLambda)));
	        	n += 0.01;
	        	j++;
	        	//if (max < tableY[i]) 
	        	//	max = tableY[i];
	        	data1.getData().add(new XYChart.Data<>(j, tableY[i]));
	        }
        }else if (chartY.isSelected()) {
        	for(int i = 0; i < 401; i++) {
	        	tableY[i] = I0*(Math.sin(slits*d*n/(waveLambda*L)))*(Math.sin(slits*d*n/(waveLambda*L)))/((Math.sin(d*n/(waveLambda*L)))*(Math.sin(d*n/(waveLambda*L))));
	        	n += 0.01;
	        	j++;
	        	//if (max < tableY[i]) 
	        	//	max = tableY[i];
	        	data1.getData().add(new XYChart.Data<>(j, tableY[i]));
	        }
        }
        
        yAxis.setUpperBound(max*1.1);
        data1.setName(slitsNumber.getText());
        chart.getData().add(data1);
       	chart.setLegendVisible(true);
       	chart.setCreateSymbols(false);
    	
    }

    public void openWindow(ActionEvent e) throws IOException {
    	System.out.println("Trying to open a second window...");
	 	 
    	//Load second scene
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/SecondScreen.fxml"));
    	Parent root = loader.load();
	              
    	//Get controller of scene2
    	SecondController secondController = loader.getController();
    	//Pass whatever data you want. You can have multiple method calls here
    	this.calculate();
    	secondController.setCalculations(widthParse, minParse, submaxParse);
	  
    	//Show scene 2 in new window  
    	Stage newWindow = new Stage();
    	newWindow.setTitle("Calculations");
	        
    	Scene scene2 = new Scene(root, 640,480);
    	
    	newWindow.setScene(scene2);
    	scene2.getStylesheets().add("/application/application2.css");
    	
    	newWindow.show();
	          
    }

    public void calculate() {
    	phase = 360/slits;
    	
    	//theta in degrees
    	theta = phase*waveLambda/(d*360*1000000)*180/Math.PI;
    	width = 2*theta;
    	widthParse = Double.toString(width);
    	
    	//minimum w mm
    	min = phase*waveLambda*L/(d*360*1000);
    	minParse =Double.toString(min);
    	
    	//submax in degrees
    	try {
    		phase2 = 2*360/(slits-1);
        	submax = Math.asin(phase2*waveLambda/(d*360*1000000))*180/Math.PI;
        	submaxParse = Double.toString(submax);
    		
    	}catch (ArithmeticException e) {
    		System.out.println("N cannot be 1 when calculating subsidiary maximum!");
    		System.out.println("Catched: " + e);
    		if(slits == 1) {
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setTitle("Calculating subsidiary maximum error");
	    		alert.setHeaderText(null);
	    		alert.setContentText("2nd subsidiary maximum does not exist");
	    		alert.showAndWait();
    		}
    	
    	}
    }
	
}




