package IHM;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MonController {
	@FXML
	Button quitter;
	@FXML
	Button affecter;
	@FXML
	Button exclureTutore;
	@FXML
	Button exclureTuteur;
	@FXML
	ListView<String> listeTutore;
	@FXML
	ListView<String> listeTuteur;
	@FXML
	ListView<String> listeTutorat;
	@FXML
	TextField rechercheTuteur;
	@FXML
	TextArea contenuTutore;
	@FXML
	TextArea contenuTuteur;
	@FXML
	TextArea boxCouple;

	public void initialize() {
		System.out.println("Initialisation...");
		listeTutore.getItems().addAll("Claude","Madeleine","Sabine","Hugues","Lucas","Alexandria","Anouk");
		listeTuteur.getItems().addAll("Hortense","David","Martin","Thomas","Guy","Emile","Guillaume");
		rechercheTuteur.getAccessibleText();

		listeTutore.getSelectionModel().getSelectedItems().addListener(new MonListChangeListener());

		listeTuteur.getSelectionModel().getSelectedItems().addListener(new MonListChangeListener2());

		listeTutorat.getSelectionModel().getSelectedItems().addListener(new MonListChangeListener3());

	}

	class MonListChangeListener implements ListChangeListener<String> {
		public void onChanged(Change<? extends String> report) {
			contenuTutore.setText("" + report.getList());
			String texte = contenuTutore.getText().substring(1, contenuTutore.getText().length()-1);
			contenuTutore.setText(texte);
		}
	}

	class MonListChangeListener2 implements ListChangeListener<String> {
		public void onChanged(Change<? extends String> report) {
			contenuTuteur.setText(""+ report.getList());
			String texte = contenuTuteur.getText().substring(1, contenuTuteur.getText().length()-1);
			contenuTuteur.setText(texte);
		}
	}

	class MonListChangeListener3 implements ListChangeListener<String> {
		public void onChanged(Change<? extends String> report) {
			boxCouple.setText(""+ report.getList());
			String texte = boxCouple.getText().substring(1, boxCouple.getText().length()-1);
			boxCouple.setText(texte);
		}
	}


	public void pressedButtonAffecter(ActionEvent event) {	
		//si rien selectionner on alerte l'utilisateur
		if(contenuTutore.getText().equals("Selectionner un tutore pour afficher ses détails") || 
				contenuTuteur.getText().equals("Selectionner un tuteur pour afficher ses détails.") ||
				contenuTuteur.getText().isBlank() ||
				contenuTutore.getText().isBlank() ||
				contenuTutore.getText().equals(contenuTuteur.getText())){
			Alert alert = new Alert(AlertType.ERROR, "selectionner au moins 2 etudiants pour les affecter !", ButtonType.OK);
			alert.showAndWait();
		}
		else {
			//ajout des 2 personnes dans la liste tutorat
			listeTutorat.getItems().add(""+contenuTutore.getText() + "-" + contenuTuteur.getText());

			//suppression des 2 personnes dans les listes d'origines
			listeTutore.getItems().remove(contenuTutore.getText());
			listeTuteur.getItems().remove(contenuTuteur.getText());
		}

	}


	public void pressedButtonSuppriner(ActionEvent event) {	
		//si rien selectionner on alerte l'utilisateur
		if((boxCouple.getText().equals(("Selectionner un couple pour afficher ses détails"))) || 
				(boxCouple.getText().equals(("[]")))){
			Alert alert = new Alert(AlertType.ERROR, "selectionner un couple d'etudiants pour les supprimer !", ButtonType.OK);
			alert.showAndWait();
		}
		else {
			int idx = 0;
			for(int i =0 ; i<boxCouple.getText().length();i++) {
				if(boxCouple.getText().charAt(i)== '-') {
					idx = i;
				}
			}

			//ajout des 2 personnes dans la liste tutorat   			//on prend substring pour separer le tutore du tuteur
			listeTutore.getItems().add(boxCouple.getText().substring(0, idx));
			listeTuteur.getItems().add(boxCouple.getText().substring(idx+1, boxCouple.getText().length()));
			//suppression des 2 personnes dans les listes d'origines
			listeTutorat.getItems().remove(boxCouple.getText());
		}	
	}



	public void pressedButtonExclureTutore(ActionEvent event) {
		if(listeTutore.getItems().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING, "la liste tutore est vide !", ButtonType.OK);
			alert.showAndWait();
		}else {
			listeTutore.getItems().remove(contenuTutore.getText());
		}
	}

	public void pressedButtonExclureTuteur(ActionEvent event) {
		if(listeTuteur.getItems().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING, "la liste tuteur est vide !", ButtonType.OK);
			alert.showAndWait();
		}else {
			listeTuteur.getItems().remove(contenuTuteur.getText());
		}
	}

	public void pressedButtonQuitter(ActionEvent event) {
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
}
