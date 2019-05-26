
package view;
	


import Model.Application1;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	private static Application1 apli;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {

			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Start.fxml"));
			Scene scene = new Scene(root,600,400);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static Application1 getApli() {
		return apli;
	}
	
	
	
	public static void main(String[] args)  {
		
		apli = new Application1();
		launch(args);
	}
}