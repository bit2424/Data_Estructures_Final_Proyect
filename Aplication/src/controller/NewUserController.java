package controller;

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

public class NewUserController {

	@FXML
    private TextArea dataText;

    @FXML
    void add(ActionEvent event) {

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
