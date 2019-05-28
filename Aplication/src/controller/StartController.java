package controller;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import view.Main;

public class StartController implements Initializable {

	@FXML
    private Label textEfect;

    @FXML
    void addUser(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/view/NewUser.fxml"));
    	Parent viewCampo = loader.load();
    	Scene scene = new Scene(viewCampo);
    	Stage windowCampo = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	windowCampo.setScene(scene);
    	windowCampo.show();
    }

    @FXML
    void analyze(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/view/Analyze.fxml"));
    	Parent viewCampo = loader.load();
    	Scene scene = new Scene(viewCampo);
    	Stage windowCampo = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	windowCampo.setScene(scene);
    	windowCampo.show();
    }
    
    private void goStart() {
    	FadeTransition fadeTransition = new FadeTransition(Duration.millis(900));
    	fadeTransition.setNode(textEfect);
    	fadeTransition.setFromValue(1);
    	fadeTransition.setToValue(0);
    	fadeTransition.setAutoReverse(true);
    	fadeTransition.setCycleCount(FadeTransition.INDEFINITE);
    	fadeTransition.play();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		goStart();
		if(Main.getApli().getGraphHashtag().getVerticesL().size()==0){
			try {
				searchData("/Persistence/Users/BillGates");
				searchData("/Persistence/Users/DonaldJTrump");
				searchData("/Persistence/Users/ElonMusk");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void searchData(String link) throws IOException {
		try {
			Main.getApli().registerData(link);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}





}