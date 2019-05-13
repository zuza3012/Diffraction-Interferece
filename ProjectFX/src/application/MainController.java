package application;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
	
	private RadioButton selectedRadioButton;
	private String toogleGroupValue;
	private Boolean dataEntered = false;
	
	public MainController() {
	   
   }
   	
    public void initialize() { 
    	//creating chart
    	chart.setTitle("Intensity Interference Pattern");
    	
    	xAxis.setAutoRanging(true);
   	 	//xAxis.setLowerBound(-200);
   	 	//xAxis.setUpperBound(200);
   	 	//xAxis.setTickUnit(5);
   	 	xAxis.setAutoRanging(true);
   	 	
   	 	//zamiast tych if-ów w wykresach funkcji ;)
    	yAxis.setAutoRanging(true);
    	yAxis.setLowerBound(0);
  	    yAxis.setTickUnit(10);
  	    
  	    //Adding radiobuttons to the Togglegroup
  	    group = new ToggleGroup();
  	    chartPhase.setToggleGroup(group);
  	    chartTheta.setToggleGroup(group);
  	    chartY.setToggleGroup(group);
  	    chartPhase.setSelected(true);

    }
    
   
    @FXML
    public void parsing () {
    
	    	try {
	    		I0 = Double.parseDouble(intensity.getText());
				System.out.println("Io: " + I0);
				
	    		d = Double.parseDouble(slitsDistance.getText());
	    		System.out.println("d: " + d);
	    		
	    		L = Double.parseDouble(distanceScreen.getText());
	    		System.out.println("L: " + L);
	    		
	    		waveLambda = Double.parseDouble(lambda.getText());
	    		System.out.println("waveLambda: " + waveLambda);

	    		slits = Integer.parseInt(slitsNumber.getText());
	    		System.out.println("Slits number entered: " + slits);
	    		
	    		dataEntered = true;
	    	} catch (NumberFormatException e) {
	    		showDialog("Data Entered Error Message", "Every textfield must me filled!" + "\n" + "Only numbers can be entered!");
	    		 
	    	}

    }
   
    
    @FXML
    public void drawChart(ActionEvent e) {
    	if(dataEntered) {
    		selectedRadioButton = (RadioButton) group.getSelectedToggle();
       	    toogleGroupValue = selectedRadioButton.getText();
        	chart.setTitle("Intensity Interference Pattern" + " " + toogleGroupValue );
        	
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
        	
	    	}else {
	    		System.out.println("Data entered missing");
	    		showDialog("Data Message", "Please, type data in every texfield!" + "\n" + "Use only numbers!");
	    	}
    	
    }

    public void openWindow() {
    	System.out.println("Trying to open a second window...");
	 	try { 
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
	 	}catch (IOException e){
	 		System.out.println("Data missing");
    		System.out.println("Catched: " + e);
    		showDialog("Data Missing Message", "Please, type data in every texfield!" + "\n" + "Use only numbers!");
	 	}      
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
    		if(slits == 1) 
    			showDialog("Calculating subsidiary maximum error","2nd subsidiary maximum does not exist");

    	}
    }
	
    @FXML
    public void closeProgram() {
    	Platform.exit();
    }
    
    @FXML
    public void saveAsPng() {
    	System.out.println("Saving starts...");
        WritableImage image =chart.snapshot(new SnapshotParameters(), null);
       
        File file = new File("resources/chart"+toogleGroupValue+".png");
        
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            System.out.println("Saved in: " + file.getAbsolutePath());
        } catch (IOException e) {
        	System.out.println("Unable to save ");
    		System.out.println("Catched: " + e);
        }
    }
    
    
    public void info() {
    	// cos co czyta z pliku np txt i wyswietal w nowym oknie dane o programie czy cos albo pokazywanie message dialog 
    }

    public void showDialog(String title, String text) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.showAndWait();
    }
}




