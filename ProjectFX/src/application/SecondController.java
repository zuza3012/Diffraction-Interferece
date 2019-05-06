package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SecondController {
	
	@FXML
	private Label centralMax;
	@FXML
	private Label firstMin;
	@FXML
	private Label subMax;
	
	@FXML
	private Label centralMaxLabel;
	
	@FXML
	private Label firstMinLabel;
	
	@FXML
	private Label subMaxLabel;
	
	public void initialize() {
		
	}
	public void setCalculations(String centralmax, String firstmin, String submax){
		centralMaxLabel.setText(centralmax);
		firstMinLabel.setText(firstmin);
		subMaxLabel.setText(submax);
	}
}
