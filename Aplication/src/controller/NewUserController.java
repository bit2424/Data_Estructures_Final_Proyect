package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.Main;

public class NewUserController {

	@FXML
    private TextArea dataText;

    @FXML
    void add(ActionEvent event) throws IOException {
    	File f;
    	FileWriter fw;
    	f = new File("./Persistence/Users/nuevo");
    	try {
			fw = new FileWriter(f);
		} catch (IOException e) {
			f = new File("./Aplication/Persistence/Users/nuevo");
			fw = new FileWriter(f);
		}
    	BufferedWriter bw = new BufferedWriter(fw);
    	bw.write(dataText.getText());
    	bw.close();
    	Main.getApli().registerUser();
//    	System.out.print("Termino");
    	System.out.println(Main.getApli().getGraphHashtag().getVerticesL().get(0).getValue().getName() +"   "+ Main.getApli().getGraphAt().getVerticesL().get(0).getValue().getName());
    }

    @FXML
    void clean(MouseEvent event) {
    	dataText.setText("");
    }

    @FXML
    void returnStart(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/view/Start.fxml"));
    	Parent viewCampo = loader.load();
    	Scene scene = new Scene(viewCampo);
    	Stage windowCampo = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	windowCampo.setScene(scene);
    	windowCampo.show();
    }

}
