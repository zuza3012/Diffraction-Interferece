package application;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ThirdController implements Initializable {

    @FXML
    private WebView webView;

    @FXML
    private Button reloabBtn;
    
    private WebEngine engine;
    
    

    public void initialize(URL location, ResourceBundle resources) {
    	engine = webView.getEngine();
    }
    
    public void loadURL(ActionEvent event) {
    	System.out.println("Opening page...");
    	engine.load("https://opentextbc.ca/physicstestbook2/chapter/multiple-slit-diffraction/");
    }
}
