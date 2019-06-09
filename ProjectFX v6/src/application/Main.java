package application;
	

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;


public class Main extends Application {
	
	static Stage window;
	@Override
	public void start(Stage primaryStage) {
		try {

			 window = primaryStage;
			 Parent root = FXMLLoader.load(getClass().getResource("/MainScreen.fxml"));
			  
		     Scene scene = new Scene(root);
		     scene.getStylesheets().add("/application/application.css");
		     window.setTitle("Diffraction and Interference");
		     Main.window.setResizable(false);
		     window.setScene(scene);
		     window.show();
	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
