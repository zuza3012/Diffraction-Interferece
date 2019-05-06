package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	Stage window;
	@Override
	public void start(Stage primaryStage) {
		try {
			window = primaryStage;
			 Parent root = FXMLLoader.load(getClass().getResource("/MainScreen.fxml"));
			  
		     Scene scene = new Scene(root);
		     scene.getStylesheets().add("/application/application.css");
		     window.setTitle("Diffraction and Interference");
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
