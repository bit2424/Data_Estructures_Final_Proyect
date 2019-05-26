package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AnalyzeController implements Initializable {

    @FXML
    private ComboBox<String> ComboBoxOptions;

    @FXML
    private Label labelOptions;

    @FXML
    private ListView<String> ListResult;

    @FXML
    private AnchorPane nearGrade;

    @FXML
    private TextField pointsreferents;

    @FXML
    private ComboBox<String> ComboboxType;

    @FXML
    private ComboBox<String> ComboboxUsers;

    @FXML
    private Label labelListUsers;

    @FXML
    private Label labelType;

    @FXML
    private AnchorPane relation;

    @FXML
    private Label UserStarLabel;

    @FXML
    private ComboBox<String> ListUserCombobox;

    private int option;
    private ArrayList<String> listOptions;
    
    @FXML
    void ListUsers(ActionEvent event) {

    }

    @FXML
    void add(ActionEvent event) {

    }

    @FXML
    void listUser(ActionEvent event) {

    }

    @FXML
    void options(ActionEvent event) {
    	if(ComboBoxOptions.getSelectionModel().getSelectedIndex()>-1) {
    		option = ComboBoxOptions.getSelectionModel().getSelectedIndex();
    		labelOptions.setText(listOptions.get(option));
    	}
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

    @FXML
    void type(ActionEvent event) {
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String[] a = {"Consultar usuarios","Coinsidencia de #","Coinsidencia de @","Filtrar consultas","Probabilidad de relacion"};
		listOptions = new ArrayList<>(Arrays.asList(a));
		ComboBoxOptions.getItems().addAll(listOptions);
	}
}
