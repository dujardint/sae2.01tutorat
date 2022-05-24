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

	public void initialize() {
		System.out.println("Initialisation...");
		listeTutore.getItems().addAll("1","2","3","4","5","6","7");
		listeTuteur.getItems().addAll("1","2","3","4","5","6","7");
		rechercheTuteur.getAccessibleText();

		listeTutore.getSelectionModel().getSelectedItems().addListener(new MonListChangeListener());

		listeTuteur.getSelectionModel().getSelectedItems().addListener(new MonListChangeListener2());

	}

	class MonListChangeListener implements ListChangeListener<String> {
		public void onChanged(Change<? extends String> report) {
			contenuTutore.setText("" + report.getList());
		}
	}

	class MonListChangeListener2 implements ListChangeListener<String> {
		public void onChanged(Change<? extends String> report) {
			contenuTuteur.setText(""+ report.getList());
		}
	}


	public void pressedButtonAffecter(ActionEvent event) {	
		//si rien selectionner on alerte l'utilisateur
		if(contenuTutore.getText().equals("Selectionner un tutore pour afficher ses détails") || 
				contenuTuteur.getText().equals("Selectionner un tuteur pour afficher ses détails.") ||
				contenuTutore.getText().equals(contenuTuteur.getText())){
			Alert alert = new Alert(AlertType.ERROR, "selectionner au moins 2 etudiants pour les affecter !", ButtonType.OK);
			alert.showAndWait();
		}
		else {
			//ajout des 2 personnes dans la liste tutorat   			//on prend substring pour pas prendre les crochet [...]
			listeTutorat.getItems().add(""+contenuTutore.getText().substring(1, contenuTutore.getText().length()-1) + " - " + contenuTuteur.getText().substring(1, contenuTuteur.getText().length()-1));

			//suppression des 2 personnes dans les listes d'origines
			listeTutore.getItems().remove(contenuTutore.getText().substring(1, contenuTutore.getText().length()-1));
			listeTuteur.getItems().remove(contenuTuteur.getText().substring(1, contenuTuteur.getText().length()-1));
		}

	}

	public void pressedButtonExclureTutore(ActionEvent event) {
		listeTutore.getItems().remove(contenuTutore.getText().substring(1, contenuTutore.getText().length()-1));
		if(listeTutore.getItems().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING, "la liste tutore est vide !", ButtonType.OK);
			alert.showAndWait();
		}
	}

	public void pressedButtonExclureTuteur(ActionEvent event) {
		listeTuteur.getItems().remove(contenuTuteur.getText().substring(1, contenuTuteur.getText().length()-1));
		if(listeTuteur.getItems().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING, "la liste tuteur est vide !", ButtonType.OK);
			alert.showAndWait();
		}
	}

	public void pressedButtonQuitter(ActionEvent event) {
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
}
