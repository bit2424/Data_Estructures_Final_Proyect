package controller;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AnalyzeController {

    @FXML
    private ComboBox<?> ComboBoxOptions;

    @FXML
    private Label labelOptions;

    @FXML
    private ListView<?> ListResult;

    @FXML
    private AnchorPane nearGrade;

    @FXML
    private TextField pointsreferents;

    @FXML
    private ComboBox<?> ComboboxType;

    @FXML
    private ComboBox<?> ComboboxUsers;

    @FXML
    private Label labelListUsers;

    @FXML
    private Label labelType;

    @FXML
    private AnchorPane relation;

    @FXML
    private Label UserStarLabel;

    @FXML
    private ComboBox<?> ListUserCombobox;

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
}
