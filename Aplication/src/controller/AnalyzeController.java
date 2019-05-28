package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import Model.User;
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
import view.Main;

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
	@FXML
	private AnchorPane grup;

	@FXML
	private ComboBox<String> ListSendComboBox;

	@FXML
	private Label sendLabel;

	@FXML
	private ComboBox<String> ListReciveComboBox;

	@FXML
	private Label receiveLabel;

	private int option;
	private ArrayList<String> listOptions;
	private ArrayList<String> types;
	private ArrayList<String> nameUsers;
	private int[] sendAndReceide;


	@FXML
	void ListRecive(ActionEvent event) {
		if(ListReciveComboBox.getSelectionModel().getSelectedIndex()>-1) {
			sendAndReceide[1] = ListReciveComboBox.getSelectionModel().getSelectedIndex();
			receiveLabel.setText(nameUsers.get(sendAndReceide[1]));
			User selec = Main.getApli().getGraphHashtag().getVerticesL().get(option).getValue();
//			String name = Main.getApli().getNextProbableRelation(selec)..getName();
		}
	}

	@FXML
	void ListSend(ActionEvent event) {
		if(ListSendComboBox.getSelectionModel().getSelectedIndex()>-1) {
			sendAndReceide[0] = ListSendComboBox.getSelectionModel().getSelectedIndex();
			sendLabel.setText(nameUsers.get(sendAndReceide[0]));
			User selec = Main.getApli().getGraphHashtag().getVerticesL().get(option).getValue();
//			String name = Main.getApli().getNextProbableRelation(selec)..getName();
		}
	}
	@FXML
	void ListUsers(ActionEvent event) {
	}

	@FXML
	void add(ActionEvent event) {
		switch (option) {
			case 6:
				resultGrup();
				break;
			default:
				break;
		}
	}

	private void resultGrup() {
		User UserSend = Main.getApli().getGraphHashtag().getVerticesL().get(sendAndReceide[0]).getValue();
		User userReceive = Main.getApli().getGraphHashtag().getVerticesL().get(sendAndReceide[1]).getValue();
		ArrayList<String> names = new ArrayList<>();
		for (int i  =0; i< Main.getApli().getDifusionGroup(UserSend,userReceive ).size();i++){
			names.add(Main.getApli().getDifusionGroup(UserSend,userReceive ).get(i).getName());
		}
		ListResult.getItems().addAll(names);
		ListResult.setVisible(true);
	}

	@FXML
	void listUser(ActionEvent event) {
		if(ListUserCombobox.getSelectionModel().getSelectedIndex()>-1) {
			int a = ListUserCombobox.getSelectionModel().getSelectedIndex();
			UserStarLabel.setText(nameUsers.get(a));
			User selec = Main.getApli().getGraphHashtag().getVerticesL().get(option).getValue();
//			String name = Main.getApli().getNextProbableRelation(selec)..getName();
		}
	}

	@FXML
	void options(ActionEvent event) {
		if(ComboBoxOptions.getSelectionModel().getSelectedIndex()>-1) {
			option = ComboBoxOptions.getSelectionModel().getSelectedIndex();
			labelOptions.setText(listOptions.get(option));
			fresh();
			goOption();
		}
	}

	private void fresh() {
		ListResult.setVisible(false);
		nearGrade.setVisible(false);
		relation.setVisible(false);
		grup.setVisible(false);
	}

	private void goOption() {
		switch (option+1) {
			case 1:goListUsersCategory();
				break;
			case 2: ListRelationHashtag();
				break;
			case 3: ListRelationAt();
				break;
			case 4: goFilter();
				break;
			case 5: probabilityRelation();
			break;
			case 6: probabilityGrup();
				break;
			default:
				break;
		}
	}

	private void probabilityGrup() {
		nameUsers = new ArrayList<>();
		for (int i = 0; i < Main.getApli().getGraphHashtag().getVerticesL().size(); i++) {
			nameUsers.add(Main.getApli().getGraphHashtag().getVerticesL().get(i).getValue().getName());
		}
		ListReciveComboBox.getItems().addAll(nameUsers);
		ListSendComboBox.getItems().addAll(nameUsers);
		grup.setVisible(true);
		sendAndReceide = new int[2];
	}

	private void probabilityRelation() {
		nameUsers = new ArrayList<>();
		for (int i = 0; i < Main.getApli().getGraphHashtag().getVerticesL().size(); i++) {
			nameUsers.add(Main.getApli().getGraphHashtag().getVerticesL().get(i).getValue().getName());
		}
		ListUserCombobox.getItems().addAll(nameUsers);
		relation.setVisible(true);
	}

	private void goFilter() {
		String[] a = {"Tecnologia","Deportes","Politica"};
		types = new ArrayList<>(Arrays.asList(a));
		ComboboxType.getItems().addAll(types);
		nameUsers = new ArrayList<>();
		for (int i = 0; i < Main.getApli().getGraphHashtag().getVerticesL().size(); i++) {
			nameUsers.add(Main.getApli().getGraphHashtag().getVerticesL().get(i).getValue().getName());
		}
		ComboboxUsers.getItems().addAll(nameUsers);
		nearGrade.setVisible(true);
	}

	private void ListRelationAt() {
		ListResult.setVisible(true);
	}

	private void ListRelationHashtag() {
		ListResult.setVisible(true);
	}

	private void goListUsersCategory() {
		ListResult.setVisible(true);
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
		if(ComboBoxOptions.getSelectionModel().getSelectedIndex()>-1) {
			int a = ComboboxType.getSelectionModel().getSelectedIndex();
			labelType.setText(types.get(a));
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String[] a = {"Consultar usuarios","Coinsidencia de #","Coinsidencia de @","Filtrar consultas","Probabilidad de relacion","Grupo de puente"};
		listOptions = new ArrayList<>(Arrays.asList(a));
		ComboBoxOptions.getItems().addAll(listOptions);
	}
}
