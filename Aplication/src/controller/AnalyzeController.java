package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.scene.control.CheckBox;
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

import javax.swing.*;

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

	@FXML
	private CheckBox sport;

	@FXML
	private CheckBox tecnology;

	@FXML
	private CheckBox politics;

	@FXML
	private Label resulLabel;


	private int option;
	private ArrayList<String> listOptions;
	private ArrayList<String> types;
	private ArrayList<String> nameUsers;
	private int[] sendAndReceide;
	private int selecCategory=-1;
	private int selecUser;


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
		if(ComboboxUsers.getSelectionModel().getSelectedIndex()>-1) {
			selecUser = ComboboxUsers.getSelectionModel().getSelectedIndex();
			labelListUsers.setText(nameUsers.get(selecUser));
		}
	}

	@FXML
	void add(ActionEvent event) {

		switch (option) {
			case 0:
				listUsersPoints();
				break;
			case 1:
				listUserHash();
				break;
			case 2:
				listUserAt();
				break;
			case 3:
				listfiler();
				break;
			case 4:
				futureRelation();
				break;
			case 5:
				resultGrup();
				break;
			default:
				break;
		}
	}

	private void futureRelation() {
		if(selecUser!=-1){
			User selec = Main.getApli().getGraphHashtag().getVerticesL().get(selecUser).getValue();
			resulLabel.setText(Main.getApli().getNextProbableRelation(selec).getName());
		}else{
			JOptionPane.showMessageDialog(null,"Seleccione un usuario");
		}
		selecUser= -1;
	}

	private void listfiler() {
		if(selecUser!=-1&&selecCategory!=-1&&!pointsreferents.getText().equals("")){
			int puntaje = Integer.parseInt(pointsreferents.getText());
			User start = Main.getApli().getGraphHashtag().getVerticesL().get(selecUser).getValue();
			HashMap<User,Integer> resul = Main.getApli().usersUpScore(puntaje,start,selecCategory);
			ArrayList<String> data = new ArrayList<>();
			for(User u : resul.keySet()){
				data.add(u.getName());
			}
			int j =0;
			for(Integer i : resul.values()){
				data.set(j,data.get(j)+"  Grado de cercania:"+ i);
				j++;
			}
			ListResult.getItems().addAll(data);
			ListResult.setVisible(true);
			nearGrade.setVisible(false);
		}else{
			JOptionPane.showMessageDialog(null,"Faltan datos requeridos");
		}
		selecCategory=-1;
		selecUser=-1;

	}


	private void listUserAt() {
		ArrayList<String> n  = new ArrayList<>();
		ArrayList<ArrayList<User>> u  = Main.getApli().getArCoincidentUsers();
		for(int i=0; i<u.size();i++){
			n.add("Grupo #"+(i+1));
			for(int j=0; j< u.get(i).size();j++){
				n.add(u.get(i).get(j).getName());
			}
		}
		ListResult.getItems().addAll(n);
	}

	private void listUserHash() {
		ArrayList<String> n  = new ArrayList<>();
		ArrayList<ArrayList<User>> u  = Main.getApli().getHashCoincidentUsers();
		for(int i=0; i<u.size();i++){
			n.add("Grupo #"+(i+1));
			for(int j=0; j< u.get(i).size();j++){
				n.add(u.get(i).get(j).getName());
			}
		}
		ListResult.getItems().addAll(n);
	}

	private void listUsersPoints() {
		if(selecCategory != -1){
			HashMap<User,Integer> data = Main.getApli().getClasificatedUsers(selecCategory);
			ArrayList<String> n  = new ArrayList<>();
			int i = 0;
			for ( User key : data.keySet() ) {
				n.add(key.getName()+"  Puntaje: "+ key.getPoints()[selecCategory]);
			}
			politics.setDisable(false);
			politics.setSelected(false);
			sport.setDisable(false);
			sport.setSelected(false);
			tecnology.setDisable(false);
			tecnology.setSelected(false);
			ListResult.getItems().addAll(n);
		}else{
			JOptionPane.showMessageDialog(null,"Seleccione una categoria");
		}
		selecCategory=-1;
	}

	private void resultGrup() {
		User UserSend = Main.getApli().getGraphHashtag().getVerticesL().get(sendAndReceide[0]).getValue();
		User userReceive = Main.getApli().getGraphHashtag().getVerticesL().get(sendAndReceide[1]).getValue();
		ArrayList<String> names = new ArrayList<>();
		ArrayList<User> u = Main.getApli().getDifusionGroup(UserSend,userReceive );
		if(u!=null){
			for (int i  =0; i< u.size();i++){
				names.add(u.get(i).getName());
			}
		}else{
			names.add("No existe grupo de relacion");
		}

		ListResult.getItems().addAll(names);
		ListResult.setVisible(true);
		grup.setVisible(false);
	}

	@FXML
	void listUser(ActionEvent event) {
		if(ListUserCombobox.getSelectionModel().getSelectedIndex()>-1) {
			selecUser = ListUserCombobox.getSelectionModel().getSelectedIndex();
			UserStarLabel.setText(nameUsers.get(selecUser));
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
		ListResult.getItems().clear();
		ListResult.setVisible(false);
		nearGrade.setVisible(false);
		relation.setVisible(false);
		grup.setVisible(false);
		sport.setVisible(false);
		politics.setVisible(false);
		tecnology.setVisible(false);
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
		sport.setVisible(true);
		politics.setVisible(true);
		tecnology.setVisible(true);
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
			selecCategory = ComboboxType.getSelectionModel().getSelectedIndex();
			labelType.setText(types.get(selecCategory));
		}
	}

	@FXML
	void categoryPolitics(ActionEvent event) {
		selecCategory = 2;
		ListResult.getItems().clear();
		tecnology.setDisable(true);
		sport.setDisable(true);
	}

	@FXML
	void categorySport(ActionEvent event) {
		selecCategory = 1;
		ListResult.getItems().clear();
		tecnology.setDisable(true);
		politics.setDisable(true);
	}

	@FXML
	void categoryTecnology(ActionEvent event) {
		selecCategory = 0;
		ListResult.getItems().clear();
		sport.setDisable(true);
		politics.setDisable(true);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String[] a = {"Consultar usuarios","Coinsidencia de #","Coinsidencia de @","Filtrar consultas","Probabilidad de relacion","Grupo de puente"};
		listOptions = new ArrayList<>(Arrays.asList(a));
		ComboBoxOptions.getItems().addAll(listOptions);
	}
}
