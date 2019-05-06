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
   	double max;
   	
   	
   	double phase, phase2;
	double width, min, submax;
	String widthParse, minParse, submaxParse;
	
	public MainController() {
	   
   }
   	
    public void initialize() {
    	//creating chart
    	xAxis.setAutoRanging(false);
   	 	xAxis.setLowerBound(-200);
   	 	xAxis.setUpperBound(200);
   	 	xAxis.setTickUnit(1);

    	yAxis.setAutoRanging(false);
    	yAxis.setLowerBound(0);
       //yAxis.setUpperBound(max);
  	    yAxis.setTickUnit(5);
    	   
  	    //Adding radiobuttons to the Togglegroup
  	    group = new ToggleGroup();
  	    chartPhase.setToggleGroup(group);
  	    chartTheta.setToggleGroup(group);
  	    chartY.setToggleGroup(group);
  	   //chartPhase.setSelected(true);
      
    }
    
   
    @FXML
    public boolean  parsing (ActionEvent e) throws ParseException  {
    	//Wszystko dzia³a, gdy po odtatnim textfield wciœniemy enter
    	//PLEASE!!! add exceptions and deal with slits parsing(in other function)
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
        
        XYChart.Series data1 = new XYChart.Series();
        
        if(chartPhase.isSelected()) {
        	   for(int i = -200; i < 200; i++) {
               	double n = i * 0.01;
               	double y = I0*(Math.sin(slits*0.5*n))*(Math.sin(slits*0.5*n))/((Math.sin(0.5*n))*(Math.sin(0.5*n)));
               	data1.getData().add(new XYChart.Data(i, y));
	               	if(n == 0.1) { 
	               		max = y;
	               		yAxis.setUpperBound(max*1.2 );
	               	}
               }     
        	data1.setName(slitsNumber.getText());
            chart.getData().add(data1);
           	chart.setLegendVisible(true);
           	chart.setCreateSymbols(false);
           	
           	
        }
        //chart.getData().clear(); Jak to bedzie tutaj, to pokazujemy jedna serie, nie wszystkie
        else if(chartTheta.isSelected()) {
        	for(int i = -200; i < 200; i++) {
               	double n = i * 0.01;
               	
               	double y = I0*(Math.sin(slits*d*Math.sin(n)*Math.PI/waveLambda))*(Math.sin(slits*d*Math.sin(n)*Math.PI/waveLambda))/((Math.sin(d*Math.sin(n)*Math.PI/waveLambda))*(Math.sin(d*Math.sin(n)*Math.PI/waveLambda)));
               	data1.getData().add(new XYChart.Data(i, y));
	               	if(n == 0.1) { 
	               		max = y;
	               		yAxis.setUpperBound(max*1.2 );
	               	}
        	}	
        	data1.setName(slitsNumber.getText());
            chart.getData().add(data1);
           	chart.setLegendVisible(true);
           	chart.setCreateSymbols(false);
           	
        }else if (chartY.isSelected()) {
        	for(int i = -200; i < 200; i++) {
               	double n = i * 0.01;
               	
               	double y = I0*(Math.sin(slits*d*n/(waveLambda*L)))*(Math.sin(slits*d*n/(waveLambda*L)))/((Math.sin(d*n/(waveLambda*L)))*(Math.sin(d*n/(waveLambda*L))));
               	data1.getData().add(new XYChart.Data(i, y));
	               	if(n == 0.1) { 
	               		max = y;
	               		yAxis.setUpperBound(max*1.2 );
	               	}
        	}	
        	data1.setName(slitsNumber.getText());
            chart.getData().add(data1);
           	chart.setLegendVisible(true);
           	chart.setCreateSymbols(false);
        }
        
        /*poprawiæ! wywala siê na if'ie z porównaniem tablicy
         * u¿ycie tablicy ma pomóæ ustaliæ maksymaln¹ watotœæ funkcji
        double n = -200;
        double max2 = 0;
        if(chartTheta.isSelected()) {
        	System.out.println("Checking");
        double[] tableY = new double[400];
        tableY[0] = 0;
        for(int i = 0; i < 400; i++) {
        	System.out.println("Checking2");
        	tableY[i] = I0*(Math.sin(slits*d*Math.sin(n)*Math.PI/waveLambda))*(Math.sin(slits*d*Math.sin(n)*Math.PI/waveLambda))/((Math.sin(d*Math.sin(n)*Math.PI/waveLambda))*(Math.sin(d*Math.sin(n)*Math.PI/waveLambda)));
        	n += 0.01;
        	if (tableY[i] > tableY[i-1]) {
        		max2 = tableY[i];
        	}
        	
        	data1.getData().add(new XYChart.Data(i, tableY[i]));
        }
       
        yAxis.setUpperBound(max2);
        data1.setName(slitsNumber.getText());
        chart.getData().add(data1);
       	chart.setLegendVisible(true);
       	chart.setCreateSymbols(false);
        
        }
    	*/
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
    	//TRY Catch??????
    	if(slits == 1) {
    		System.out.println("N cannot be 1 when calculating subsidiary maximum!");
    	}
    	phase2 = 2*360/(slits-1);
    	submax = Math.asin(phase2*waveLambda/(d*360*1000000))*180/Math.PI;
    	submaxParse = Double.toString(submax);
    	
    }

    	
    	
}




