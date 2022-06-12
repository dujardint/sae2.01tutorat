package IHM;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import appli.Tuteur;
import appli.Tutorat;
import appli.Tutore;
import dev.ImportCSV;
import fr.ulille.but.sae2_02.graphes.Arete;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
	TextField rechercheTutore;
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

	@FXML
	TextField absenceTutore;
	@FXML
	TextField motivationTutore;

	@FXML
	TextField absenceTuteur;
	@FXML
	TextField motivationTuteur;


	@FXML
	TextField effectifTuteur;
	@FXML
	TextField effectifTutore;
	@FXML
	TextField effectifTutorat;

	final String messageContenuTuteur = "Sélectionnez un tuteur";
	final String messageContenuTutore = "Sélectionnez un tutoré";
	final String messageBoxCouple = "Sélectionnez un couple";


	Tutorat etudiants, etudiantsCopie;
	//ArrayList<Tutore> groupeTutore = new ArrayList<>();
	//ArrayList<Tuteur> groupeTuteur = new ArrayList<>();
	//ArrayList<Object> groupeTutorat = new ArrayList<>();
	Map<Tutore, Tuteur> groupeTutorat;


	public void initialize() {
		System.out.println("Initialisation...");

		contenuTuteur.setText(messageContenuTuteur);
		contenuTutore.setText(messageContenuTutore);
		boxCouple.setText(messageBoxCouple);

		this.groupeTutorat = new HashMap<>();



		etudiants = new Tutorat(ImportCSV.readFileTuteur(ImportCSV.FILEPATH_TUTEUR),
				ImportCSV.readFileTutore(ImportCSV.FILEPATH_TUTORE));

		etudiantsCopie = new Tutorat(ImportCSV.readFileTuteur(ImportCSV.FILEPATH_TUTEUR),
				ImportCSV.readFileTutore(ImportCSV.FILEPATH_TUTORE));

		for(int i=0; i<etudiants.getListTutore().size(); i++) {
			listeTutore.getItems().add(etudiants.getListTutore().get(i).getPrenomNom());
		}

		for(int i=0; i<etudiants.getListTuteur().size(); i++) {
			listeTuteur.getItems().add(etudiants.getListTuteur().get(i).getPrenomNom());
		}


		rechercheTutore.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				boolean trouve=false;
				for(int i=0; i<listeTutore.getItems().size(); i++) {
					if (rechercheTutore.getText().equals(etudiants.getListTutore().get(i).getNom()) ||  
							(rechercheTutore.getText().equals(etudiants.getListTutore().get(i).getPrenom()))) {
						System.out.println("tutore trouve");
						trouve=true;
						Tutore temp=null;
						temp=etudiants.getListTutore().get(i);
						contenuTutore.setText(""+temp.getPrenomNom());
						prenomTutore.setText(temp.getPrenom());
						nomTutore.setText(temp.getNom());
						moyTutore.setText(""+temp.getMoyenne());
						anneeTutore.setText(""+temp.getAnnee());
						absenceTutore.setText(""+temp.getAbsences());
						motivationTutore.setText(""+temp.getMotivation());
					}
				}
				if (!trouve) {
					Alert alert = new Alert(AlertType.INFORMATION, "Le tutoré que vous recherchez n'existe pas dans sa liste ou alors a déja été attribué !\n\nAvez-vous bien écrit son nom ou son prénom ?", ButtonType.OK);
					alert.showAndWait();
				}
			}});


		rechercheTuteur.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				boolean trouve=false;
				for(int i=0; i<listeTuteur.getItems().size(); i++) {
					if (rechercheTuteur.getText().equals(etudiants.getListTuteur().get(i).getNom()) ||  
							(rechercheTuteur.getText().equals(etudiants.getListTuteur().get(i).getPrenom()))) {
						System.out.println("tuteur trouve");
						trouve=true;
						Tuteur temp1=null;
						temp1=etudiants.getListTuteur().get(i);
						contenuTuteur.setText(""+temp1.getPrenomNom());
						prenomTuteur.setText(temp1.getPrenom());
						nomTuteur.setText(temp1.getNom());
						moyTuteur.setText(""+temp1.getMoyenne());
						anneeTuteur.setText(""+temp1.getAnnee());
						absenceTuteur.setText(""+temp1.getAbsences());
						motivationTuteur.setText(""+temp1.getMotivation());
					}
				}
				if (!trouve) {
					Alert alert = new Alert(AlertType.INFORMATION, "Le tuteur que vous recherchez n'existe pas dans sa liste ou alors a déja été attribué !\\n\\nAvez-vous bien écrit son nom ou son prénom ?", ButtonType.OK);
					alert.showAndWait();
				}
			}});


		listeTutore.getSelectionModel().getSelectedItems().addListener(new MonListChangeListener());

		listeTuteur.getSelectionModel().getSelectedItems().addListener(new MonListChangeListener2());

		listeTutorat.getSelectionModel().getSelectedItems().addListener(new MonListChangeListener3());

		miseAjourTaille();
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

					prenomTutore.setText(temp.getPrenom());
					nomTutore.setText(temp.getNom());
					moyTutore.setText(""+temp.getMoyenne());
					anneeTutore.setText(""+temp.getAnnee());
					absenceTutore.setText(""+temp.getAbsences());
					motivationTutore.setText(""+temp.getMotivation());
				}
			}
			miseAjourTaille();
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

					prenomTuteur.setText(temp1.getPrenom());
					nomTuteur.setText(temp1.getNom());
					moyTuteur.setText(""+temp1.getMoyenne());
					anneeTuteur.setText(""+temp1.getAnnee());
					absenceTuteur.setText(""+temp1.getAbsences());
					motivationTuteur.setText(""+temp1.getMotivation());
				}
			}
			miseAjourTaille();
		}
	}

	class MonListChangeListener3 implements ListChangeListener<String> {
		public void onChanged(Change<? extends String> report) {
			boxCouple.setText(""+ report.getList());
			boxCouple.setText(boxCouple.getText().substring(1, boxCouple.getText().length()-1));

			int id=0;
			while(boxCouple.getText().charAt(id)!='-') {
				id++;
			}
			String tutoreNom = boxCouple.getText().substring(0, id);
			String tuteurNom = boxCouple.getText().substring(id+1);

			contenuTutore.setText(tutoreNom);
			contenuTuteur.setText(tuteurNom);

			for(int i=0; i<etudiantsCopie.getListTutore().size(); i++) {
				if(etudiantsCopie.getListTutore().get(i).getPrenomNom().equals(tutoreNom)) {
					Tutore temp=null;
					temp=etudiantsCopie.getListTutore().get(i);
					prenomTutore.setText(temp.getPrenom());
					nomTutore.setText(temp.getNom());
					moyTutore.setText(""+temp.getMoyenne());
					anneeTutore.setText(""+temp.getAnnee());
					absenceTutore.setText(""+temp.getAbsences());
					motivationTutore.setText(""+temp.getMotivation());
				}
			}

			for(int i=0; i<etudiantsCopie.getListTuteur().size(); i++) {
				if(etudiantsCopie.getListTuteur().get(i).getPrenomNom().equals(tuteurNom)){
					Tuteur temp1=null;
					temp1=etudiantsCopie.getListTuteur().get(i);

					prenomTuteur.setText(temp1.getPrenom());
					nomTuteur.setText(temp1.getNom());
					moyTuteur.setText(""+temp1.getMoyenne());
					anneeTuteur.setText(""+temp1.getAnnee());
					absenceTuteur.setText(""+temp1.getAbsences());
					motivationTuteur.setText(""+temp1.getMotivation());
				}
			}

			miseAjourTaille();
		}
	}


	public void pressedButtonAffecter(ActionEvent event) {	
		Tuteur tuteurSelectionne = null;
		Tutore tutoreSelectionne = null ;

		//si rien selectionner on alerte l'utilisateur
		if(contenuTutore.getText().equals(messageContenuTutore) || 
				contenuTuteur.getText().equals(messageContenuTuteur) ||
				contenuTuteur.getText().isBlank() ||
				contenuTutore.getText().isBlank() ||
				contenuTutore.getText().equals(contenuTuteur.getText())){
			Alert alert = new Alert(AlertType.ERROR, "selectionner au moins 2 etudiants pour les affecter !", ButtonType.OK);
			alert.showAndWait();
		}

		else if(listeTutorat.getItems().contains(contenuTutore.getText() + "-" +contenuTuteur.getText())){
			Alert alert = new Alert(AlertType.ERROR, "le couple existe deja!", ButtonType.OK);
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

			miseAjourTaille();
		}

	}

	public void pressedButtonSuppriner(ActionEvent event) {	
		Tuteur tuteurSelectionne = null;
		Tutore tutoreSelectionne = null ;

		//si rien selectionner on alerte l'utilisateur
		if((boxCouple.getText().equals((messageBoxCouple))) || 
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
				if (entry.getKey().getPrenomNom().equals(boxCouple.getText().substring(0, idx))) {
					tuteurSelectionne=entry.getValue();
					tutoreSelectionne=entry.getKey();

					System.out.println("tuteurSelectionne : " + tuteurSelectionne);
					System.out.println("tutoreSelectionne : " + tutoreSelectionne);

					// On remet les tuteurs et tutores dans leur listes initiales candidat en mémoire

					etudiants.ajouterTutore(tutoreSelectionne);
					etudiants.ajouterTuteur(tuteurSelectionne);
					System.out.println("Key : " + entry.getKey() + " value : " + entry.getValue());

					// on remet les tuteurs et tutores dans leur listes initiales candidat en IHM
					listeTutore.getItems().add(""+tutoreSelectionne.getPrenomNom());
					listeTuteur.getItems().add(""+tuteurSelectionne.getPrenomNom());

					groupeTutorat.remove(entry.getKey());
					listeTutorat.getItems().remove(boxCouple.getText());

					miseAjourTaille();
				}
			}
		}	
	}


	public void pressedButtonExclureTutore(ActionEvent event) {
		if(listeTutore.getItems().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING, "la liste tutore est vide !", ButtonType.OK);
			alert.showAndWait();
		}else if(contenuTutore.getText().equals(messageContenuTutore)) {
			Alert alert = new Alert(AlertType.WARNING, "Selectionner un tutore pour afficher ses details", ButtonType.OK);
			alert.showAndWait();
		}else {
			for(int i=0; i<etudiants.getListTutore().size(); i++) {
				if(contenuTutore.getText().equals(etudiants.getListTutore().get(i).getPrenomNom())) {
					etudiants.getListTutore().remove(i);
				}
			}
			listeTutore.getItems().remove(contenuTutore.getText());

			miseAjourTaille();
		}
	}

	public void pressedButtonExclureTuteur(ActionEvent event) {
		if(listeTuteur.getItems().isEmpty()){
			Alert alert = new Alert(AlertType.WARNING, "la liste tuteur est vide !", ButtonType.OK);
			alert.showAndWait();
		}else if(contenuTuteur.getText().equals(messageContenuTuteur)) {
			Alert alert = new Alert(AlertType.WARNING, "Selectionner un tuteur pour afficher ses details", ButtonType.OK);
			alert.showAndWait();
		}else {
			for(int i=0; i<etudiants.getListTuteur().size(); i++) {
				if(contenuTuteur.getText().equals(etudiants.getListTuteur().get(i).getPrenomNom())) {
					etudiants.getListTuteur().remove(i);
				}
			}
			listeTuteur.getItems().remove(contenuTuteur.getText());

			miseAjourTaille();
		}
	}


	public void pressedButtonCalculer(ActionEvent event) {		

		if( (etudiants.getListTutore().isEmpty()) && (etudiants.getListTuteur().isEmpty()) ) {
			Alert alert = new Alert(AlertType.ERROR, "L'affectation est termine, il n'y a plus de tuteur a affecter avec de tutore !", ButtonType.OK);
			alert.showAndWait();
		}
		else {

			CalculAffectation<String>  affectation = etudiants.calculAffectation();

			for(Arete<String> couple : affectation.getAffectation()) {
				listeTutorat.getItems().add(couple.getExtremite2() + "-" + couple.getExtremite1());
				groupeTutorat.put(etudiants.getTutore(couple.getExtremite2()), etudiants.getTuteur(couple.getExtremite1()));
			}

			listeTutore.getItems().clear();
			listeTuteur.getItems().clear();

			etudiants.getListTutore().clear();
			etudiants.getListTuteur().clear();

			miseAjourTaille();

			System.out.println("L'affectation est terminee.");
		}
	}

	private void miseAjourTaille() {
		effectifTuteur.setText(""+listeTuteur.getItems().size());
		effectifTutore.setText(""+listeTutore.getItems().size());
		effectifTutorat.setText(""+groupeTutorat.size());
	}


	public void pressedButtonReinitialiser(ActionEvent event) {
		listeTutore.getItems().clear();
		listeTuteur.getItems().clear();
		listeTutorat.getItems().clear();

		etudiants.getListTutore().clear();
		etudiants.getListTuteur().clear();
		groupeTutorat.clear();

		initialize();
	}


	public void pressedButtonSave(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Sauvegarder ?");
		alert.setHeaderText("Voulez-vous sauvegarder la liste du tutorat ?");
		alert.setContentText("le fichier s'appellera : tutorat.csv\nIl sera placé dans la racine de l'application");

		ButtonType oui = new ButtonType("Oui");
		ButtonType non = new ButtonType("Non");

		alert.getButtonTypes().clear();

		alert.getButtonTypes().addAll(oui, non);

		Optional<ButtonType> option = alert.showAndWait();

		if (option.get() == null) {
			System.out.println("null");
		} else if (option.get() == oui) {
			System.out.println("sauvegarde en cours...");

			FileWriter file = null;
			try
			{
				String DELIMITER = ",";
				String SEPARATOR = "\n";  
				file = new FileWriter("tutorat.csv");
				//Ajouter l'en-tête
				file.append("Annee,Nom,Prenom,Moyenne,Nombres d'Absences,Motivation, ,Annee2,Nom2,Prenom2,Moyenne2,Nombres d'Absences2,Motivation2");
				//Ajouter une nouvelle ligne après l'en-tête
				file.append(SEPARATOR);

				for (Map.Entry<Tutore, Tuteur> entry : groupeTutorat.entrySet()) {
					Tutore key = entry.getKey();
					Tuteur value = entry.getValue();
					//	Tuteur tuteur = (Tuteur) it.next();
					file.append(""+key.getAnnee());
					file.append(DELIMITER);
					file.append(key.getNom());
					file.append(DELIMITER);
					file.append(key.getPrenom());
					file.append(DELIMITER);
					file.append(""+key.getMoyenne());
					file.append(DELIMITER);
					file.append(""+key.getAbsences());
					file.append(DELIMITER);
					file.append(""+key.getMotivation());
					file.append(DELIMITER);
					file.append(DELIMITER);

					file.append(""+value.getAnnee());
					file.append(DELIMITER);
					file.append(value.getNom());
					file.append(DELIMITER);
					file.append(value.getPrenom());
					file.append(DELIMITER);
					file.append(""+value.getMoyenne());
					file.append(DELIMITER);
					file.append(""+value.getAbsences());
					file.append(DELIMITER);
					file.append(""+value.getMotivation());
					file.append(SEPARATOR);
				}
				file.close();
				System.out.println("sauvegarde terminé.");


				//alerter l'utilisateur si c'estbien enregistré
				/*if(file.) {
						Alert alert1 = new Alert(AlertType.ERROR, "enregistrement fait !", ButtonType.OK);
						alert1.showAndWait();
					}
				 */
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

		}else if (option.get() == non) {
			System.out.println("sauvegarde refuser");
		} else {
			System.out.println("rien");
		}
	}

	/*
	 * Methode non utilise car plus de bouton qui permettent de quitter l'appli
	public void pressedButtonQuitter(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Souhaitez-vous sauvegarder puis quitter ?", ButtonType.OK);
		alert.showAndWait();
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
	 */
}

