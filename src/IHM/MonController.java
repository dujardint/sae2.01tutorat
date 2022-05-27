package IHM;

import java.util.ArrayList;
import java.util.List;

import appli.Tuteur;
import appli.Tutorat;
import appli.Tutore;
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

	ArrayList<Tutore> groupeTutore = new ArrayList<>();
	ArrayList<Tuteur> groupeTuteur = new ArrayList<>();
	ArrayList<Object> groupeTutorat = new ArrayList<>();



	public void initialize() {
		System.out.println("Initialisation...");
		int nbAbscenceDefaut = 1;
		int annee = 1;
		char motivation = '+';  //+ - ou =

		int second = 2;
		int troisieme = 3;
		
		groupeTutore.add(new Tutore("tutore_", "Claude", "Allard", 9.8, nbAbscenceDefaut, annee, motivation));
		groupeTutore.add(new Tutore("tutore_", "Madeleine", "Barre", 6.9, nbAbscenceDefaut, annee, motivation));
		groupeTutore.add(new Tutore("tutore_", "Sabine", "Besnard", 12.7, nbAbscenceDefaut, annee, motivation));
		groupeTutore.add(new Tutore("tutore_", "Hugues", "Bigot", 0.2, nbAbscenceDefaut, annee, motivation));
		groupeTutore.add(new Tutore("tutore_", "Lucas", "Bouchet", 17.3, nbAbscenceDefaut, annee, motivation));
		
		for(int i=0; i<groupeTutore.size(); i++) {
			listeTutore.getItems().add(groupeTutore.get(i).getPrenomNom());
		}
		
		groupeTuteur.add(new Tuteur("tuteur_","FranÃ§ois","Bertin",13.3,nbAbscenceDefaut, second, motivation));
		groupeTuteur.add(new Tuteur("tuteur_","Joseph","Boyer",7.7,nbAbscenceDefaut, second, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Martin","Delmas",11.0,nbAbscenceDefaut, second, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Maurice","Fernandez",5.7,nbAbscenceDefaut, second, motivation));
		groupeTuteur.add(new Tuteur("tuteur_","ThÃ©rÃ¨se","Gay",11.5,nbAbscenceDefaut, second, motivation)); 


		for(int i=0; i<groupeTuteur.size(); i++) {
			listeTuteur.getItems().add(groupeTuteur.get(i).getPrenomNom());
		}


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
			
			
			for(int i=0; i<groupeTutore.size(); i++) {
				if(contenuTutore.getText().equals(groupeTutore.get(i).getPrenomNom())) {
					groupeTutorat.add(groupeTutore.get(i));
				}
			}
			
			for(int i=0; i<groupeTuteur.size(); i++) {
				if(contenuTuteur.getText().equals(groupeTuteur.get(i).getPrenomNom())) {
					groupeTutorat.add(groupeTuteur.get(i));
				}
			}
			
			listeTutorat.getItems().add(""+contenuTutore.getText() + "-" + contenuTuteur.getText());

			//suppression des 2 personnes dans les listes d'origines
			for(int i=0; i<groupeTutore.size(); i++) {
				if(contenuTutore.getText().equals(groupeTutore.get(i).getPrenomNom())) {
					groupeTutore.remove(i);
				}
			}
			listeTutore.getItems().remove(contenuTutore.getText());

			for(int i=0; i<groupeTuteur.size(); i++) {
				if(contenuTuteur.getText().equals(groupeTuteur.get(i).getPrenomNom())) {
					groupeTuteur.remove(i);
				}
			}
			listeTuteur.getItems().remove(contenuTuteur.getText());
		}

	}


	public void pressedButtonSuppriner(ActionEvent event) {	
		//si rien selectionner on alerte l'utilisateur
		if((boxCouple.getText().equals(("Selectionner un couple pour afficher ses détails"))) || 
				(boxCouple.getText().equals(("[]")) ||
						(boxCouple.getText().isBlank()))){
			Alert alert = new Alert(AlertType.ERROR, "selectionner un couple d'etudiants pour les supprimer !", ButtonType.OK);
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

			//ajout des 2 personnes dans la liste tutorat   			//on prend substring pour separer le tutore du tuteur
			
			for(int i=0; i<groupeTutore.size(); i++) {
				if(boxCouple.getText().substring(0, idx).equals(groupeTutore.get(i).getPrenomNom())) {
					groupeTutore.remove(i);
				}
			}
			
			listeTutore.getItems().add(boxCouple.getText().substring(0, idx));
			
			for(int i=0; i<groupeTuteur.size(); i++) {
				if(boxCouple.getText().substring(idx+1, boxCouple.getText().length()).equals(groupeTuteur.get(i).getPrenomNom())) {
					groupeTuteur.remove(i);
				}
			}
			
			listeTuteur.getItems().add(boxCouple.getText().substring(idx+1, boxCouple.getText().length()));
			//suppression des 2 personnes dans les listes d'origines
			
			
			for(int i=0; i<groupeTuteur.size(); i++) {
				if(contenuTuteur.getText().equals(groupeTuteur.get(i).getPrenomNom())) {
					//groupeTuteur.remove(i);
				}
			}
			listeTutorat.getItems().remove(boxCouple.getText());
		}	
	}



	public void pressedButtonExclureTutore(ActionEvent event) {
		if(listeTutore.getItems().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING, "la liste tutore est vide !", ButtonType.OK);
			alert.showAndWait();
		}else {
			for(int i=0; i<groupeTutore.size(); i++) {
				if(contenuTutore.getText().equals(groupeTutore.get(i).getPrenomNom())) {
					groupeTutore.remove(i);
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
			for(int i=0; i<groupeTuteur.size(); i++) {
				if(contenuTuteur.getText().equals(groupeTuteur.get(i).getPrenomNom())) {
					groupeTuteur.remove(i);
				}
			}
			listeTuteur.getItems().remove(contenuTuteur.getText());
		}
	}


	public void pressedButtonCalculer(ActionEvent event) {
		GrapheNonOrienteValue<String> g = new GrapheNonOrienteValue<String>();
		System.out.println("\ntaille tuteur " + groupeTuteur.size());
		System.out.println("taille tutore " + groupeTutore.size()+"\n");
		for(int i=0; i<groupeTutore.size(); i++) {
			g.ajouterSommet(groupeTutore.get(i).getPrenomNom());
			System.out.println(groupeTutore.get(i).getPrenomNom() + " ajouter a la liste sommet");
		}
		
		for(int i=0; i<groupeTuteur.size(); i++) {
			g.ajouterSommet(groupeTuteur.get(i).getPrenomNom());
			System.out.println(groupeTuteur.get(i).getPrenomNom() + " ajouter a la liste sommet");
		}
		
		
		for(int i=0; i<groupeTuteur.size(); i++) {
			for(int j=0; j<groupeTutore.size(); j++) {
				g.ajouterArete(groupeTuteur.get(i).getPrenomNom(), groupeTutore.get(j).getPrenomNom(), Tutorat.calculDistance(groupeTuteur.get(i), groupeTutore.get(j)));
				System.out.println("" + groupeTuteur.get(i).getPrenomNom() + " " +  groupeTutore.get(j).getPrenomNom() + " distance : " + Tutorat.calculDistance(groupeTuteur.get(i), groupeTutore.get(j)));
			}
		}
		
		System.out.println();
		List<String> tuteurPrenomNom=new ArrayList<String>();
		for(int i=0; i<groupeTuteur.size(); i++) {
			tuteurPrenomNom.add((groupeTuteur.get(i).getPrenomNom()));
			System.out.println("liste tuteur : " + tuteurPrenomNom.get(i));
		}
		System.out.println();
		ArrayList<String> tutorePrenomNom=new ArrayList<String>();
		for(int i=0; i<groupeTutore.size(); i++) {
			tutorePrenomNom.add(groupeTutore.get(i).getPrenomNom());
			System.out.println("liste tutore : "+tutorePrenomNom.get(i));
		}

		System.out.println();
		CalculAffectation<String> c = new CalculAffectation<>(g, tutorePrenomNom, tuteurPrenomNom);
		System.out.println("le cout minimal est de : " + c.getCout());
		System.out.println(c.getAffectation());
		for (int i=0;i<groupeTutore.size();i++) {
			System.out.println(c.getAffectation().get(i).getExtremite1()+ " doit se mettre avec "+c.getAffectation().get(i).getExtremite2());
			
			groupeTutorat.add(c.getAffectation().get(i).getExtremite1()+ " doit se mettre avec "+c.getAffectation().get(i).getExtremite2());
			listeTutorat.getItems().add(c.getAffectation().get(i).getExtremite1()+ "-"+c.getAffectation().get(i).getExtremite2());
			
			//System.out.println("Cette tÃ¢che prendra "+g.getPoids(c.getAffectation().get(i).getExtremite1(),g.getAffectation().get(i).getExtremite2()) +" jours ");
		}

		System.out.println("\nL'affectation est terminÃ© !!");

		
		
		
		
		
	}












	public void pressedButtonQuitter(ActionEvent event) {
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
}
