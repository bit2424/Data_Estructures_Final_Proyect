
package view;
	


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
//	private static BVC reception;
	
	
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
//	public static BVC getReception() {
//		return reception;
//	}
//	
//	public static void setReception(BVC bvc) {
//		reception=bvc;
//	}
//	
	
	
	public static void main(String[] args)  {
		
//			reception = new BVC();
		launch(args);
	}
}