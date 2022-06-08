package IHM;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	TextField annneTutore;
	@FXML
	TextField prenomTutore;
	@FXML
	TextField nomTutore;
	@FXML
	TextField moyTuteur;
	@FXML
	TextField annneTuteur;
	@FXML
	TextField prenomTuteur;
	@FXML
	TextField nomTuteur;


	Tutorat etudiants;
	//ArrayList<Tutore> groupeTutore = new ArrayList<>();
	//ArrayList<Tuteur> groupeTuteur = new ArrayList<>();
	//ArrayList<Object> groupeTutorat = new ArrayList<>();
	Map<Tuteur, Tutore> groupeTutorat;
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

	class MonListChangeListener implements ListChangeListener<String> {
		public void onChanged(Change<? extends String> report) {
			contenuTutore.setText("" + report.getList());
			contenuTutore.setText(contenuTutore.getText().substring(1, contenuTutore.getText().length()-1));
			prenomTutore.setText(contenuTutore.getText().substring(0, recherche(contenuTutore.getText())));
			nomTutore.setText(contenuTutore.getText().substring(recherche(contenuTutore.getText())+1,contenuTutore.getText().length()));
		}
	}

	class MonListChangeListener2 implements ListChangeListener<String> {
		public void onChanged(Change<? extends String> report) {
			contenuTuteur.setText(""+ report.getList());
			contenuTuteur.setText(contenuTuteur.getText().substring(1, contenuTuteur.getText().length()-1));
			prenomTuteur.setText(contenuTuteur.getText().substring(0, recherche(contenuTuteur.getText())));
			nomTuteur.setText(contenuTuteur.getText().substring(recherche(contenuTuteur.getText())+1,contenuTuteur.getText().length()));
		}
	}

	class MonListChangeListener3 implements ListChangeListener<String> {
		public void onChanged(Change<? extends String> report) {
			boxCouple.setText(""+ report.getList());
			boxCouple.setText(boxCouple.getText().substring(1, boxCouple.getText().length()-1));
		}
	}

	public int recherche(String nom) {
		for (int i=0; i<nom.length();i++) {
			if(nom.charAt(i)=='_') {
				return i;
			}
		}
		return 0;
	}

	public void pressedButtonAffecter(ActionEvent event) {	
		Tuteur tuteurSelectionne = null;
		Tutore tutoreSelectionne = null ;

		//si rien selectionner on alerte l'utilisateur
		if(contenuTutore.getText().equals("Selectionner un tutore pour afficher ses d�tails") || 
				contenuTuteur.getText().equals("Selectionner un tuteur pour afficher ses d�tails.") ||
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

			groupeTutorat.put(tuteurSelectionne, tutoreSelectionne);

			listeTutorat.getItems().add(""+contenuTutore.getText() + "-" + contenuTuteur.getText());

			etudiants.supprimeCandidat(contenuTutore.getText());
			etudiants.supprimeCandidat(contenuTuteur.getText());

			listeTutore.getItems().remove(contenuTutore.getText());
			listeTuteur.getItems().remove(contenuTuteur.getText());
		}

	}


	public void pressedButtonSuppriner(ActionEvent event) {	
		//si rien selectionner on alerte l'utilisateur
		if((boxCouple.getText().equals(("Sélectionner un couple pour afficher ses détails"))) || 
				(boxCouple.getText().equals(("[]")) ||
						(boxCouple.getText().isBlank()))){
			Alert alert = new Alert(AlertType.ERROR, "Sélectionner un couple d'étudiants pour les supprimer.", ButtonType.OK);
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

			for (Map.Entry<Tuteur, Tutore> entry : groupeTutorat.entrySet()) {
				System.out.println("avant if Key : " + entry.getKey() + " value : " + entry.getValue());

				System.out.println(boxCouple.getText().substring(idx + 1));

				if (entry.getKey().getPrenomNom().equals(boxCouple.getText().substring(0, idx))) {
					// On remet les tuteurs et tutores dans leur listes initiales candidat en mémoire
					etudiants.ajouterTuteur(entry.getKey());
					etudiants.ajouterTutore(entry.getValue());
					System.out.println("Key : " + entry.getKey() + " value : " + entry.getValue());

					// on remet les tuteurs et tutores dans leur listes initiales candidat en IHM
					listeTutore.getItems().add(entry.getValue().getPrenomNom());
					listeTuteur.getItems().add(entry.getKey().getPrenomNom());

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
		if(listeTuteur.getItems().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING, "la liste tuteur est vide !", ButtonType.OK);
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

		// 1er brassage
		for(Arete<String> couple : affectation.getAffectation()) {
			listeTutorat.getItems().add(couple.getExtremite1() + "-" + couple.getExtremite2());
			groupeTutorat.put(etudiants.getTuteur(couple.getExtremite1()), 
					etudiants.getTutore(couple.getExtremite2()));
		}

		listeTutore.getItems().clear();
		listeTuteur.getItems().clear();

		etudiants.getListTutore().clear();
		etudiants.getListTuteur().clear();

		/*
		// 2ème brassage
		List<Tuteur> troisiemeAnnees = new ArrayList<Tuteur>();
		for (Map.Entry<Tuteur, Tutore> entry : groupeTutorat.entrySet()) {
			System.out.println("avant if Key : " + entry.getKey() + " value : " + entry.getValue());
			if(entry.getKey().getNom().contains("N_") && entry.getKey().getPrenom().contains("P_")) {
				etudiants.getListTutore().add(entry.getValue());
				listeTutore.getItems().add(entry.getValue().getPrenomNom());
				// on supprime du tutorat
				listeTutorat.getItems().remove(entry.getKey() + "-" + entry.getValue());
				groupeTutorat.remove(entry.getKey());
			} else if (entry.getKey().getAnnee() == 3) {
				// On ajoute tous les 3ème années dans une List<Tuteur> troisiemeAnnees
				troisiemeAnnees.add(entry.getKey());
			}
		}
		if (etudiants.getListTutore().size()  > 0) {
			// Cela veut dire qu'il y a plus de tutorés que de tuteurs
			etudiants.setListTuteur(troisiemeAnnees);
			etudiants.triTuteur();
			List<Tuteur> tuteursConserves = new ArrayList<>();
			for(int i = 0; i < etudiants.getListTutore().size(); i++) {
				if(i < etudiants.getListTuteur().size()) {
					// On ajoute tous les 3èmes années nécessaires ET disponibles
					tuteursConserves.add(etudiants.getListTuteur().get(i));
				}
			}
			etudiants.setListTuteur(tuteursConserves);
			
			affectation = etudiants.calculAffectation();

			// 1er brassage
			for(Arete<String> couple : affectation.getAffectation()) {
				listeTutorat.getItems().add(couple.getExtremite1() + "-" + couple.getExtremite2());
				groupeTutorat.put(etudiants.getTuteur(couple.getExtremite1()), 
						etudiants.getTutore(couple.getExtremite2()));
			}

			listeTutore.getItems().clear();
			listeTuteur.getItems().clear();

			etudiants.getListTutore().clear();
			etudiants.getListTuteur().clear();
		}*/
		System.out.println("L'affectation est terminée.");
	}

	public void pressedButtonQuitter(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Souhaitez-vous sauvegarder puis quitter ?", ButtonType.OK);
		alert.showAndWait();
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
}
