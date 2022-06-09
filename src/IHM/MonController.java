package IHM;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import appli.Tuteur;
import appli.Tutorat;
import appli.Tutore;
import dev.ImportCSV;
import fr.ulille.but.sae2_02.graphes.Arete;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;
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
	@FXML
	TextField moyTutore;
	@FXML
	TextField anneeTutore;
	@FXML
	TextField prenomTutore;
	@FXML
	TextField nomTutore;
	@FXML
	TextField moyTuteur;
	@FXML
	TextField anneeTuteur;
	@FXML
	TextField prenomTuteur;
	@FXML
	TextField nomTuteur;


	Tutorat etudiants;
	//ArrayList<Tutore> groupeTutore = new ArrayList<>();
	//ArrayList<Tuteur> groupeTuteur = new ArrayList<>();
	//ArrayList<Object> groupeTutorat = new ArrayList<>();
	Map<Tutore, Tuteur> groupeTutorat;
	int val = 0; 



	public void initialize() {
		System.out.println("Initialisation...");
		this.groupeTutorat = new HashMap<>();

		etudiants = new Tutorat(ImportCSV.readFileTuteur(ImportCSV.FILEPATH_TUTEUR),
				ImportCSV.readFileTutore(ImportCSV.FILEPATH_TUTORE));

		for(int i=0; i<etudiants.getListTutore().size(); i++) {
			listeTutore.getItems().add(etudiants.getListTutore().get(i).getPrenomNom());
		}

		for(int i=0; i<etudiants.getListTuteur().size(); i++) {
			listeTuteur.getItems().add(etudiants.getListTuteur().get(i).getPrenomNom());
		}

		rechercheTuteur.getAccessibleText();

		listeTutore.getSelectionModel().getSelectedItems().addListener(new MonListChangeListener());

		listeTuteur.getSelectionModel().getSelectedItems().addListener(new MonListChangeListener2());

		listeTutorat.getSelectionModel().getSelectedItems().addListener(new MonListChangeListener3());
	}

	public int recherche(String nom) {
		for (int i=0; i<nom.length();i++) {
			if(nom.charAt(i)=='_') {
				return i;
			}
		}
		return 0;
	}

	class MonListChangeListener implements ListChangeListener<String> {
		public void onChanged(Change<? extends String> report) {
			//on recupere son nom sur l'eleement clique dans la liste
			contenuTutore.setText("" + report.getList());
			//on enleve les crochet
			contenuTutore.setText(contenuTutore.getText().substring(1, contenuTutore.getText().length()-1));
			Tutore temp=null;
			for(int i=0; i<etudiants.getListTutore().size(); i++) {
				if(contenuTutore.getText().equals(etudiants.getListTutore().get(i).getPrenomNom())){
					temp=etudiants.getListTutore().get(i);
				}
			}
			prenomTutore.setText(temp.getPrenom());
			nomTutore.setText(temp.getNom());
			moyTutore.setText(""+temp.getMoyenne());
			anneeTutore.setText(""+temp.getAnnee());
		}
	}

	class MonListChangeListener2 implements ListChangeListener<String> {
		public void onChanged(Change<? extends String> report) {
			contenuTuteur.setText(""+ report.getList());
			contenuTuteur.setText(contenuTuteur.getText().substring(1, contenuTuteur.getText().length()-1));

			Tuteur temp1=null;
			for(int i=0; i<etudiants.getListTuteur().size(); i++) {
				if(contenuTuteur.getText().equals(etudiants.getListTuteur().get(i).getPrenomNom())){
					temp1=etudiants.getListTuteur().get(i);
				}
			}
			prenomTuteur.setText(temp1.getPrenom());
			nomTuteur.setText(temp1.getNom());
			moyTuteur.setText(""+temp1.getMoyenne());
			anneeTuteur.setText(""+temp1.getAnnee());
		}
	}

	class MonListChangeListener3 implements ListChangeListener<String> {
		public void onChanged(Change<? extends String> report) {
			boxCouple.setText(""+ report.getList());
			boxCouple.setText(boxCouple.getText().substring(1, boxCouple.getText().length()-1));
		}
	}


	public void pressedButtonAffecter(ActionEvent event) {	
		Tuteur tuteurSelectionne = null;
		Tutore tutoreSelectionne = null ;

		//si rien selectionner on alerte l'utilisateur
		if(contenuTutore.getText().equals("Selectionner un tutore pour afficher ses details") || 
				contenuTuteur.getText().equals("Selectionner un tuteur pour afficher ses details.") ||
				contenuTuteur.getText().isBlank() ||
				contenuTutore.getText().isBlank() ||
				contenuTutore.getText().equals(contenuTuteur.getText())){
			Alert alert = new Alert(AlertType.ERROR, "selectionner au moins 2 etudiants pour les affecter !", ButtonType.OK);
			alert.showAndWait();
		}
		else {
			//ajout des 2 personnes dans la liste tutorat


			for(int i=0; i<etudiants.getListTutore().size(); i++) {
				if(contenuTutore.getText().equals(etudiants.getListTutore().get(i).getPrenomNom())) {
					tutoreSelectionne = etudiants.getListTutore().get(i);
				}
			}

			for(int i=0; i<etudiants.getListTuteur().size(); i++) {
				if(contenuTuteur.getText().equals(etudiants.getListTuteur().get(i).getPrenomNom())) {
					tuteurSelectionne = etudiants.getListTuteur().get(i);
				}
			}

			groupeTutorat.put(tutoreSelectionne, tuteurSelectionne);

			listeTutorat.getItems().add(""+contenuTutore.getText() + "-" + contenuTuteur.getText());
			//listeTutorat.setStyle("-fx-text-inner-color: red;");

			etudiants.supprimeCandidat(contenuTutore.getText());
			etudiants.supprimeCandidat(contenuTuteur.getText());

			listeTutore.getItems().remove(contenuTutore.getText());
			listeTuteur.getItems().remove(contenuTuteur.getText());
		}

	}


	public void pressedButtonSuppriner(ActionEvent event) {	
		Tuteur tuteurSelectionne = null;
		Tutore tutoreSelectionne = null ;

		//si rien selectionner on alerte l'utilisateur
		if((boxCouple.getText().equals(("Selectionner un couple pour afficher ses details"))) || 
				(boxCouple.getText().equals(("[]")) ||
						(boxCouple.getText().isBlank()))){
			Alert alert = new Alert(AlertType.ERROR, "Selectionner un couple d'etudiants pour les supprimer.", ButtonType.OK);
			alert.showAndWait();
		}
		else {
			int idx = 0;
			System.out.println(boxCouple.getText());
			for(int i =0 ; i<boxCouple.getText().length();i++) {
				if(boxCouple.getText().charAt(i)== '-') {
					idx = i;
				}
			}

			for (Map.Entry<Tutore, Tuteur> entry : groupeTutorat.entrySet()) {
				//System.out.println("avant if Key : " + entry.getKey() + " value : " + entry.getValue());

				//System.out.println("on affiche blablabla " + boxCouple.getText().substring(idx + 1));

				if (entry.getKey().getPrenomNom().equals(boxCouple.getText().substring(0, idx))) {
					tuteurSelectionne=entry.getValue();
					tutoreSelectionne=entry.getKey();
					
					System.out.println("tuteurSelectionne : " + tuteurSelectionne);
					System.out.println("tutoreSelectionne : " + tutoreSelectionne);
					
					// On remet les tuteurs et tutores dans leur listes initiales candidat en mémoire
					/*
					etudiants.ajouterTutore(entry.getKey());
					etudiants.ajouterTuteur(entry.getValue());
					System.out.println("Key : " + entry.getKey() + " value : " + entry.getValue());

					// on remet les tuteurs et tutores dans leur listes initiales candidat en IHM
					System.out.println("reussite : " + listeTutore.getItems().add(""+entry.getKey().getPrenomNom()));
					listeTuteur.getItems().add(""+entry.getValue().getPrenomNom());

					groupeTutorat.remove(entry.getKey());
					listeTutorat.getItems().remove(boxCouple.getText());
					*/
					
					
					etudiants.ajouterTutore(tutoreSelectionne);
					etudiants.ajouterTuteur(tuteurSelectionne);
					System.out.println("Key : " + entry.getKey() + " value : " + entry.getValue());

					// on remet les tuteurs et tutores dans leur listes initiales candidat en IHM
					listeTutore.getItems().add(""+tutoreSelectionne.getPrenomNom());
					listeTuteur.getItems().add(""+tuteurSelectionne.getPrenomNom());

					groupeTutorat.remove(entry.getKey());
					listeTutorat.getItems().remove(boxCouple.getText());
				}
			}

			
			/*	//ajout des 2 personnes dans la liste tutorat   			
			//on prend substring pour separer le tutore du tuteur
			for(int i=0; i<groupeTutore.size(); i++) {
				if(boxCouple.getText().substring(0, idx).equals(groupeTutore.get(i).getPrenomNom())) {
					//groupeTutore.remove(i);
				}
			}

			for(int i=0; i<groupeTuteur.size(); i++) {
				if(boxCouple.getText().substring(idx+1, boxCouple.getText().length()).equals(groupeTuteur.get(i).getPrenomNom())) {
					groupeTuteur.remove(i);
				}
			}
			 */

			//suppression des 2 personnes dans les listes d'origines


			/*for(int i=0; i<groupeTuteur.size(); i++) {
				//if(contenuTuteur.getText().equals(groupeTuteur.get(i).getPrenomNom())) {
					//groupeTuteur.remove(i);
				}
			}
			//listeTutorat.getItems().remove(boxCouple.getText());*/
		}	
	}



	public void pressedButtonExclureTutore(ActionEvent event) {
		if(listeTutore.getItems().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING, "la liste tutore est vide !", ButtonType.OK);
			alert.showAndWait();
		}else if(contenuTutore.getText().equals("Selectionner un tutore pour afficher ses details")) {
			Alert alert = new Alert(AlertType.WARNING, "Selectionner un tutore pour afficher ses details", ButtonType.OK);
			alert.showAndWait();
		}else {
			for(int i=0; i<etudiants.getListTutore().size(); i++) {
				if(contenuTutore.getText().equals(etudiants.getListTutore().get(i).getPrenomNom())) {
					etudiants.getListTutore().remove(i);
				}
			}
			listeTutore.getItems().remove(contenuTutore.getText());

		}
	}

	public void pressedButtonExclureTuteur(ActionEvent event) {
		if(listeTuteur.getItems().isEmpty()){
			Alert alert = new Alert(AlertType.WARNING, "la liste tuteur est vide !", ButtonType.OK);
			alert.showAndWait();
		}else if(contenuTuteur.getText().equals("Selectionner un tuteur pour afficher ses details")) {
			Alert alert = new Alert(AlertType.WARNING, "Selectionner un tuteur pour afficher ses details", ButtonType.OK);
			alert.showAndWait();
		}else {
			for(int i=0; i<etudiants.getListTuteur().size(); i++) {
				if(contenuTuteur.getText().equals(etudiants.getListTuteur().get(i).getPrenomNom())) {
					etudiants.getListTuteur().remove(i);
				}
			}
			listeTuteur.getItems().remove(contenuTuteur.getText());
		}
	}


	public void pressedButtonCalculer(ActionEvent event) {		
		CalculAffectation<String>  affectation = etudiants.calculAffectation();


		for(Arete<String> couple : affectation.getAffectation()) {
			listeTutorat.getItems().add(couple.getExtremite2() + "-" + couple.getExtremite1());
			groupeTutorat.put(etudiants.getTutore(couple.getExtremite2()), etudiants.getTuteur(couple.getExtremite1()));
		}

		listeTutore.getItems().clear();
		listeTuteur.getItems().clear();

		etudiants.getListTutore().clear();
		etudiants.getListTuteur().clear();

		System.out.println("L'affectation est terminée.");
	}


	public void pressedButtonQuitter(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Souhaitez-vous sauvegarder puis quitter ?", ButtonType.OK);
		alert.showAndWait();
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
}
