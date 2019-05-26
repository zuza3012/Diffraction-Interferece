package application;

import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;



import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
//import jxl.write.Number;


public class MainController  {

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
   	
   	
   	@FXML
   	private Slider mySlider;
   	
   	@FXML 
   	private Node rect1;
   	
   	@FXML
   	private Node rect2;
   	
   	@FXML 
   	private Node rect3;
   	
   	@FXML 
   	private Node rect4;
   	
   	@FXML 
   	private Node rect5;
   	
   	@FXML
   	private Button waveColorButton;
   	
   	@FXML
   	private  ColorPicker colorPicker = new ColorPicker(Color.BLACK);
   	


   	double phase, phase2;
	double width, min, submax;
	String widthParse, minParse, submaxParse;
	
	double[] tableY = new double[401];
	
	private RadioButton selectedRadioButton;
	private String toogleGroupValue;
	private Boolean dataEntered = false;
	
	//mine
	int excelCounter=0;
	
   	
    public void initialize()  { 
    	

    	animatePlainWave(rect1,0);
    	animatePlainWave(rect2,1.5);
    	animatePlainWave(rect3,3);
    	animatePlainWave(rect4,4.5);
    	animatePlainWave(rect5,6);
    	
    	//w zaleznosci od tego jak duzy bedzie obszar na ktorym to ma byc rysowane 
    	//to musi byc odpowiednio wiecej fal
    	

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
    
//    @Override
//	public void initialize(URL arg0, ResourceBundle arg1) {
//    	
//    	
//		
//	  	  
//	}
   //mo¿liwe ze taka jest potrzeban ta funkcja initialize, ale na razie wszystko dziala
    
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
	    		showDialog("Data Message", "Please, type data in every texfield!" + "\n" + "Use only whole numbers!");
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
    	System.out.println("Chart saving starts...");
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
    
    @FXML
    public void saveExcel() throws BiffException, IOException, RowsExceededException, WriteException
    {
    	boolean doWeHaveAllInts = true;
    	try 
    	{
    		Integer.parseInt(slitsDistance.getText());
    		Integer.parseInt(slitsNumber.getText());
    		Integer.parseInt(intensity.getText());
    		Integer.parseInt(lambda.getText());
    		Integer.parseInt(distanceScreen.getText());
    	}
    	catch (NumberFormatException e)
    	{
    		doWeHaveAllInts = false;
    	}
    	
    	if(doWeHaveAllInts == true) //jesli w zadnym polu nie ma stringa (jest to po to, zeby nie zapisywal sie plik excel, ktory jest zepsuty)
    	{
	    	try {
	    		System.out.println("Excel workbook saving starts...");
	        	WritableWorkbook myFirstWbook = null;
	        	File file = new File("resources/workbook"+excelCounter+".xls");
		    	myFirstWbook = Workbook.createWorkbook(file);	//jakby tu cos zaczelo wariowac to trzeba dac createWorkbook(new File("resources/workbook itd...))
		    	WritableSheet excelSheet = myFirstWbook.createSheet("Sheet1", 0);
		    	
		    	Label label = new Label(0, 0, "Distance between slits");
		    	jxl.write.Number number = new jxl.write.Number(0,1,Integer.parseInt(slitsDistance.getText()));
		        excelSheet.addCell(label);
		        excelSheet.addCell((WritableCell) number);
		        
		        label = new Label(1, 0, "Slits number");
		        number = new jxl.write.Number(1,1,Integer.parseInt(slitsNumber.getText()));
		        excelSheet.addCell(label);
		        excelSheet.addCell((WritableCell) number);
		        
		        label = new Label(2, 0, "Wave intensity");
		        number = new jxl.write.Number(2,1,Integer.parseInt(intensity.getText()));
		        excelSheet.addCell(label);
		        excelSheet.addCell((WritableCell) number);
		        
		        label = new Label(3, 0, "Wavelength");
		        number = new jxl.write.Number(3,1,Integer.parseInt(lambda.getText()));
		        excelSheet.addCell(label);
		        excelSheet.addCell((WritableCell) number);
		        
		        label = new Label(4, 0, "Distance to screen");
		        number = new jxl.write.Number(4,1,Integer.parseInt(distanceScreen.getText()));
		        excelSheet.addCell(label);
		    	excelSheet.addCell((WritableCell) number);
		    	
		        myFirstWbook.write();
		        myFirstWbook.close();
		        System.out.println("Saved in: " + file.getAbsolutePath());
	    	}
	    	catch (Exception e) //tutaj taki wyjatek a nie IOException, bo mozliwy wyjatek to RuntimeException, ktory sie pojawia jak chcemy zapisac stringa zamiast inta
	    	{
	        	System.out.println("Unable to save ");
	    		System.out.println("Catched: " + e);
	        }
	    	excelCounter++;
    	}
    	else
    	{
    		showDialog("Warning","Please enter only numerical values");
    	}
    }
    
    
    @FXML
    public void loadExcel() throws BiffException, IOException, RowsExceededException, WriteException
    {
    	
    	System.out.println("Excel workbook loading starts...");
    	Workbook workbook;
    	File file = null;
    	FileChooser fc = new FileChooser(); //mozna uzyc FileChoosera javyFX, ale trzeba by wtedy miec tu stage jakos
    	JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());		
    	int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) 
		{
			try
			{
				file = jfc.getSelectedFile();
				workbook = Workbook.getWorkbook(file); //tu byl jakis warning o static-accesie ale go jakos (?) naprawilem
					
				Sheet sheet = workbook.getSheet(0);
		        Cell cell0 = sheet.getCell(0, 1);
		        slitsDistance.setText((cell0.getContents()));
		        
		        Cell cell1 = sheet.getCell(1, 1);
		        slitsNumber.setText((cell1.getContents()));
		        
		        Cell cell2 = sheet.getCell(2, 1);  
		        intensity.setText((cell2.getContents()));
		        
		        Cell cell3 = sheet.getCell(3, 1);  
		        lambda.setText((cell3.getContents()));

		        Cell cell4 = sheet.getCell(4, 1);  
		        distanceScreen.setText((cell4.getContents()));	 
			}
			catch(Exception e)
			{
				System.out.println("Exception: " + e);	
			}
		}
		else
		{
			System.out.println("You didn't select a file");
		}
			
    }
    
    @FXML
    public void changeWaveColor()
    {    
    	((Rectangle) rect1).setFill(colorPicker.getValue());
    	((Rectangle) rect2).setFill(colorPicker.getValue());
    	((Rectangle) rect3).setFill(colorPicker.getValue());
    	((Rectangle) rect4).setFill(colorPicker.getValue());
    	((Rectangle) rect5).setFill(colorPicker.getValue());
    }
    
   
    
    

	
	public void animatePlainWave(Node rectangle, double seconds)
	{
		TranslateTransition rectangleTransition = new TranslateTransition();
	    rectangleTransition.setDuration(Duration.seconds(rectangle.getLayoutY()/100)); //czas jaki trwa animacja
	    rectangleTransition.setToY(650-rectangle.getLayoutY()); //koniec drogi
	    rectangleTransition.setAutoReverse(false);
	    rectangleTransition.setCycleCount(TranslateTransition.INDEFINITE); //zapetlenie
	    rectangleTransition.setNode(rectangle);
	    rectangleTransition.rateProperty().bind(Bindings.divide(1d, mySlider.valueProperty()));
	    rectangleTransition.setDelay(Duration.seconds(seconds));
	    rectangleTransition.play();

	}




}


//1. po co jest ta funkcja parsing i to dataEntered
//1. nie mozna po prostu wziac slitsNumber.getText()? tak jak w funkcji saveExcel?
//1. tak jak jest teraz, to trzeba enter za kazdym razem klikac


