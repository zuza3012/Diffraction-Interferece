package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;



public class MainController  {


	
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
   	private Node rect1,rect2, rect3, rect4, rect5;;

   	@FXML
   	private Button waveColorButton;
   	
   	@FXML
   	private  ColorPicker colorPicker = new ColorPicker(Color.BLUE);
   	  	
   	
   	@FXML
   	private Arc arc1, arc2, arc3, arc4, arc5, arc6, arc7, arc8, arc9, arc10, arc11;
   	
   	@FXML
   	private Arc arc12, arc22, arc32, arc42, arc52, arc62, arc72, arc82, arc92, arc102, arc112;
   	
   	@FXML
   	private Arc arc13, arc23, arc33, arc43, arc53, arc63, arc73, arc83, arc93, arc103, arc113;
   	
   	@FXML
   	private Arc arc14, arc24, arc34, arc44, arc54, arc64, arc74, arc84, arc94, arc104, arc114;
   	
   	@FXML
   	private Rectangle slitRectangle1, slitRectangle2, slitRectangle3, slitRectangle4, slitRectangle5, slitRectangle6, slitRectangle7, slitRectangle8, slitRectangle9, slitRectangle10, slitRectangle11;
   	
   	private ArrayList<Arc> circleList1 = new ArrayList<Arc>();
   	private ArrayList<Arc> circleList2 = new ArrayList<Arc>();
   	private ArrayList<Arc> circleList3 = new ArrayList<Arc>();
   	private ArrayList<Arc> circleList4 = new ArrayList<Arc>();
   	
   	private ArrayList<Rectangle> slitList = new ArrayList<Rectangle>();
   	
   	private ArrayList<Rectangle> rectangleList = new ArrayList<Rectangle>();

   	double phase, phase2;
	double width, min, submax;
	String widthParse, minParse, submaxParse;
	
	double[] tableY = new double[401];
	
	private RadioButton selectedRadioButton;
	private String toogleGroupValue;
	private Boolean dataEntered = false;
	
	
	int excelCounter=0; //do numeracji zapisanych plikow
	boolean startOrStopValue =false; //do startowania animacji
	int defaultStroke=1;
	
   	
    public void initialize() throws InterruptedException  { 
    		
    	rectangleList.add((Rectangle)rect1);
    	rectangleList.add((Rectangle)rect2);
    	rectangleList.add((Rectangle)rect3);
    	rectangleList.add((Rectangle)rect4);
    	rectangleList.add((Rectangle)rect5);
    	
    	animatePlainWave(rect1,0);
    	animatePlainWave(rect2,1.4);
    	animatePlainWave(rect3,2.8);
    	animatePlainWave(rect4,4.2);
    	animatePlainWave(rect5,5.6);
    	
    	circleList1.add(arc1);
    	circleList1.add(arc2);
    	circleList1.add(arc3);
    	circleList1.add(arc4);
    	circleList1.add(arc5);
    	circleList1.add(arc6);
    	circleList1.add(arc7);
    	circleList1.add(arc8);
    	circleList1.add(arc9);
    	circleList1.add(arc10);
    	circleList1.add(arc11);
    	
    	circleList2.add(arc12);
    	circleList2.add(arc22);
    	circleList2.add(arc32);
    	circleList2.add(arc42);
    	circleList2.add(arc52);
    	circleList2.add(arc62);
    	circleList2.add(arc72);
    	circleList2.add(arc82);
    	circleList2.add(arc92);
    	circleList2.add(arc102);
    	circleList2.add(arc112);
    	
    	circleList3.add(arc13);
    	circleList3.add(arc23);
    	circleList3.add(arc33);
    	circleList3.add(arc43);
    	circleList3.add(arc53);
    	circleList3.add(arc63);
    	circleList3.add(arc73);
    	circleList3.add(arc83);
    	circleList3.add(arc93);
    	circleList3.add(arc103);
    	circleList3.add(arc113);
    	
    	circleList4.add(arc14);
    	circleList4.add(arc24);
    	circleList4.add(arc34);
    	circleList4.add(arc44);
    	circleList4.add(arc54);
    	circleList4.add(arc64);
    	circleList4.add(arc74);
    	circleList4.add(arc84);
    	circleList4.add(arc94);
    	circleList4.add(arc104);
    	circleList4.add(arc114);
    	
    	slitList.add(slitRectangle1);
    	slitList.add(slitRectangle2);
    	slitList.add(slitRectangle3);
    	slitList.add(slitRectangle4);
    	slitList.add(slitRectangle5);
    	slitList.add(slitRectangle6);
    	slitList.add(slitRectangle7);
    	slitList.add(slitRectangle8);
    	slitList.add(slitRectangle9);
    	slitList.add(slitRectangle10);
    	slitList.add(slitRectangle11);
    	
    	slitList.forEach((n) -> n.setFill(Color.TRANSPARENT));  
    	rectangleList.forEach((n) -> n.setStrokeWidth(0));
    	rectangleList.forEach((n) -> n.setFill(Color.TRANSPARENT));
    	
    	circleList1.forEach((n) -> animateCircle(n,0)); 
    	circleList1.forEach((n) -> n.setFill(null)); 
    	circleList1.forEach((n) -> n.toBack());
    	circleList1.forEach((n) -> n.setStrokeWidth(0));

    	circleList2.forEach((n) -> animateCircle(n,1)); 
    	circleList2.forEach((n) -> n.setFill(null)); 
    	circleList2.forEach((n) -> n.toBack());
    	circleList2.forEach((n) -> n.setStrokeWidth(0));
    	
    	circleList3.forEach((n) -> animateCircle(n,2)); 
    	circleList3.forEach((n) -> n.setFill(null)); 
    	circleList3.forEach((n) -> n.toBack());
    	circleList3.forEach((n) -> n.setStrokeWidth(0));

    	circleList4.forEach((n) -> animateCircle(n,3)); 
    	circleList4.forEach((n) -> n.setFill(null)); 
    	circleList4.forEach((n) -> n.toBack());
    	circleList4.forEach((n) -> n.setStrokeWidth(0));


    	

  	//creating chart
  	chart.setTitle("Intensity Interference Pattern");
  	//chart.toFront();
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
	    		
	    		if(slitsCheck()==true && startOrStopValue == true)
	    		{
	    			activateSlits(slits);
	    		}
	    		
	    		
	    		dataEntered = true;
	    	} catch (NumberFormatException e) {
	    		showDialog("Data Entered Error Message", "Every textfield must me filled!" + "\n" + "Only numbers can be entered!");
	    		 
	    	}

    }
    
    //sprawdza czy liczba szczelin sie zgadza 
    public boolean slitsCheck()
    {
    	if(slits<1 || slits>11)
    	{
    		showDialog("Data Entered Error Message", "Slits number must be between 1 and 11");
    		return false;
    	}
    	else
    	{
    		return true;
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

    //obliczenia
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
    
    //zapisywanie wykresu
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
    
    public void info()
    {
    	
    }
    
    //powiadomienie
    public void showDialog(String title, String text) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.showAndWait();
    }
    
    //zapisywanie do excela
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
		        showDialog("Information", "Saved in: " + file.getAbsolutePath());
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
    
    //wczytywanie pliku excelowskiego 
    @FXML
    public void loadExcel() throws BiffException, IOException, RowsExceededException, WriteException
    {
    	
    	System.out.println("Excel workbook loading starts...");
    	Workbook workbook;
    	File file = null;
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
    
    //funkcja zmieniajaca kolor fal
    @FXML
    public void changeWaveColor()
    {    
    	rectangleList.forEach((n) -> n.setStroke(colorPicker.getValue()));
    	circleList1.forEach((n) -> n.setStroke(colorPicker.getValue())); //wlaczenie animacji
    	circleList2.forEach((n) -> n.setStroke(colorPicker.getValue()));
    	circleList3.forEach((n) -> n.setStroke(colorPicker.getValue())); //wlaczenie animacji
    	circleList4.forEach((n) -> n.setStroke(colorPicker.getValue()));
    	
    }

	//funkcja do fali plaskiej
	public void animatePlainWave(Node rectangle, double seconds)
	{
		TranslateTransition rectangleTransition = new TranslateTransition();
	    rectangleTransition.setDuration(Duration.seconds(rectangle.getLayoutY()/100)); //czas jaki trwa animacja
	    rectangleTransition.setToY(850-rectangle.getLayoutY()); //koniec drogi
	    rectangleTransition.setAutoReverse(false);
	    rectangleTransition.setCycleCount(TranslateTransition.INDEFINITE); //zapetlenie
	    rectangleTransition.setNode(rectangle);
	    rectangleTransition.rateProperty().bind(Bindings.divide(1d, mySlider.valueProperty()));
	    rectangleTransition.setDelay(Duration.seconds(seconds));
	    rectangleTransition.play();
	}
	
	

	 
	 @FXML
	 private void activateSlits(int n)
	 {
		 circleList1.forEach((m) -> m.setStrokeWidth(0));
		 circleList2.forEach((m) -> m.setStrokeWidth(0));
		 circleList3.forEach((m) -> m.setStrokeWidth(0));
		 circleList4.forEach((m) -> m.setStrokeWidth(0));
		 slitList.forEach((m)->m.setFill(Color.TRANSPARENT));
		 rectangleList.forEach((m)->m.setStrokeWidth(2));
		 
		 for(int i=0; i<n; i++)
		 {
			 
			 circleList1.get(i).setStrokeWidth(1);
			 circleList2.get(i).setStrokeWidth(1);
			 circleList3.get(i).setStrokeWidth(1);
			 circleList4.get(i).setStrokeWidth(1);
			 slitList.get(i).setFill(Color.WHITE);
			 
			 if(n%2==0)
			 {
				 //wylacza fale srodkowa
				 circleList1.get(0).setStrokeWidth(0);
				 circleList2.get(0).setStrokeWidth(0);
				 circleList3.get(0).setStrokeWidth(0);
				 circleList4.get(0).setStrokeWidth(0);
				 slitList.get(0).setFill(Color.TRANSPARENT);
				 
				 //dla np. n = 2 wlacza fale druga, ktora jest w liscie z indeksem 2
				 circleList1.get(n).setStrokeWidth(1);
				 circleList2.get(n).setStrokeWidth(1);
				 circleList3.get(n).setStrokeWidth(1);
				 circleList4.get(n).setStrokeWidth(1);
				 slitList.get(n).setFill(Color.WHITE);
			 }
				 
		 }

	 }
	 

	
	 
	 //ta funkcja obsluguje animacje fali kulistej
	 private void animateCircle(Arc arc,double seconds)
	 {
	        final Timeline timeline = new Timeline();
	        final KeyValue kv = new KeyValue(arc.radiusXProperty(), 500);
	        final KeyValue kv2 = new KeyValue(arc.radiusYProperty(),500);
	        final KeyFrame kf = new KeyFrame(Duration.seconds(6), kv);
	        final KeyFrame kf2 = new KeyFrame(Duration.seconds(6),kv2);
	        
	        timeline.setCycleCount(Timeline.INDEFINITE);
	        timeline.setAutoReverse(false);
	        timeline.setDelay(Duration.seconds(seconds));
	        timeline.rateProperty().bind(Bindings.divide(1d, mySlider.valueProperty()));
	        timeline.getKeyFrames().add(kf);
	        timeline.getKeyFrames().add(kf2);
	        
	        timeline.play();
	  }
	 
	 @FXML
	 private void startOrStopAnimation()
	 {
		 if(dataEntered == true)
		 {
			 if(startOrStopValue==true) //stop
			 {

				 rectangleList.forEach((n) -> n.setFill(Color.TRANSPARENT));
				 rectangleList.forEach((n) -> n.setStrokeWidth(0));
				 circleList1.forEach((m) -> m.setStrokeWidth(0));
				 circleList2.forEach((m) -> m.setStrokeWidth(0));
				 circleList3.forEach((m) -> m.setStrokeWidth(0));
				 circleList4.forEach((m) -> m.setStrokeWidth(0));
				 rectangleList.forEach((n) -> n.setStrokeWidth(0));
				 slitList.forEach((m)->m.setFill(Color.TRANSPARENT)); 
				 startOrStopValue=false;
				 
			 }
			 else
			 { 
				 if(slitsCheck()==true) //start
		    		{
		    			activateSlits(slits);
		    		}
				 startOrStopValue=true;	
			 }
		 }
		 else
		 {
			 showDialog("Error","No data");
		 }
		 
	 }
 

}




