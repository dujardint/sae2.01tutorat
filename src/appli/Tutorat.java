package appli;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;


public class Tutorat  {
	private List<Tutore> listeTutore;
	private List<Tuteur> listeTuteur;
	private GrapheNonOrienteValue<String> graphe;

	public Tutorat(List<Tuteur> listeTuteur, List<Tutore> listeTutore) {
		this.listeTutore = listeTutore;
		this.listeTuteur = listeTuteur;
		this.tailleEgale();
		this.graphe= new GrapheNonOrienteValue<String>();
	}
	
	public List<Tutore> getListTutore(){
		return this.listeTutore;
	}
	
	public List<Tuteur> getListTuteur(){
		return this.listeTuteur;
	}
	
	public void setListTuteur(List<Tuteur> tuteurs) {
		this.listeTuteur = tuteurs;
	}
	
	public GrapheNonOrienteValue<String> getGraphe(){
		return this.graphe;
	}
	
	private Map<Tutore, Tuteur> forcedAssignments;
	
	private int getPositionMin(List<Tutore> listeTutore) {
		int position = -1;
		double valeur = 20.0;

		for(int i = 0; i<listeTutore.size(); i++) {
			if(listeTutore.get(i).getMoyenne()<= valeur) {
				valeur = listeTutore.get(i).getMoyenne();
				position = i;
			}
		}
		return position;
	}
	
	  public void addForcedAssignments(Tutore tutore, Tuteur tuteur) {
	        if (this.forcedAssignments.containsKey(tutore)){ throw new IllegalArgumentException("Cette association existe déja.");}
	        this.forcedAssignments.put(tutore, tuteur);
	    }
	  
	  
	  
	  public void removeForcedAssignment(Tutore tutore) {
	        if (!this.forcedAssignments.containsKey(tutore)){throw new IllegalArgumentException("Cette association n'existe pas!");}
	        this.forcedAssignments.remove(tutore);
	    }
	  
	  
	
	private int getPositionMax(List<Tuteur> listeTuteur) {
		int position = -1;
		double valeur = 0.0;
		double bonusMoyenne = 0.0;

		for(int i = 0; i<listeTuteur.size(); i++) {
			if(listeTuteur.get(i).getAnnee() == 3) {
				bonusMoyenne = 20.0;
			}
			if(listeTuteur.get(i).getMoyenne()+bonusMoyenne >= valeur) {
				position = i;
				valeur = listeTuteur.get(i).getMoyenne()+bonusMoyenne; 
			}
		}
		return position;
	}

	public void triTutore (){
		List<Tutore> listeTriee = new ArrayList<Tutore>();
		int positionMin;
		while(this.listeTutore.size() > 0) {
			positionMin = getPositionMin(this.listeTutore);
			listeTriee.add(this.listeTutore.get(positionMin));
			this.listeTutore.remove(positionMin);
		}
		this.listeTutore = listeTriee;
	}

	public void triTuteur (){
		List<Tuteur> listeTriee = new ArrayList<Tuteur>();
		int positionMax;
		while(this.listeTuteur.size() > 0) {
			positionMax = getPositionMax(this.listeTuteur);
			listeTriee.add(this.listeTuteur.get(positionMax));
			this.listeTuteur.remove(positionMax);
		}
		this.listeTuteur = listeTriee;
	}


	public double calculDistance(Tuteur tuteur, Tutore tutore) {
		double moyenneTuteur;
		if(tuteur.getAnnee() == 3) {
			moyenneTuteur = 40.0 + tuteur.getMoyenne();
		} else {
			moyenneTuteur = 20 + tuteur.getMoyenne();
		}
		return moyenneTuteur - tutore.getMoyenne();
	}
	
	public void choixEnseignant() {
		//TODO
	}

	public boolean purgeCandidatFactices() {
		Tuteur tuteurFactice = new Tuteur("999999", "PrenomVide", "NomVide", 0.00, 1000, 2, '-');
		Tutore tutoreFactice = new Tutore("999999", "PrenomVide", "NomVide" , 20.0, 1000, 1, '-');
		while(this.listeTuteur.remove(tuteurFactice)) {}
		while(this.listeTutore.remove(tutoreFactice)) {}
		return true;
	}
	public boolean tailleEgale() {
		purgeCandidatFactices();
		int difference = this.listeTuteur.size() - this.listeTutore.size();
		if (difference > 0) {
			//System.out.println("Il y a plus de tuteurs que de tutores, nous procï¿½dons ï¿½ un rï¿½ï¿½quilibrage pour la simulation.");
			return this.ajoutCandidat(false, difference);
		} else if (difference < 0) {
			//System.out.println("Il y a plus de tutores que de tuteurs, nous procï¿½dons ï¿½ un rï¿½ï¿½quilibrage pour la simulation.");
			return this.ajoutCandidat(false, difference);
		} 
		return true;
	}

	public boolean ajoutCandidat(boolean manuel, int difference) {
		if (manuel) {
			// C'est l'utilisateur qui entre les paramï¿½tres du Candidat
			Scanner scan = new Scanner(System.in);
			System.out.println("Entrez le nom pour ajouter un tutore");
			String nom = scan.nextLine();
			System.out.println("Entrez le prenom");
			String prenom = scan.nextLine();
			String prenomNom = prenom + "_" + nom;
			System.out.println(prenomNom);

			System.out.println("Entrez la moyenne");
			double moyenne = Double.parseDouble(scan.nextLine().replace(',', '.'));
			System.out.println("Entrez le nombre d'abscence(s)");
			int nbAbsence = scan.nextInt();
			System.out.println("Entrez l'annï¿½e");
			int annee = scan.nextInt();
			System.out.println("Entrez la motivation (+,-,=)");
			char motivation = scan.next().charAt(0);

			if(annee==1) {
				for(int i=0; i < listeTutore.size(); i++) {
					if(prenomNom.equals(listeTutore.get(i).getPrenomNom())){
						System.out.println("Il existe deja ce tutore. Il n'y a pas de nÃ©cessitÃ© de l'ajouter.");	
						return false;
					}
				}
				return listeTutore.add(new Tutore("tutore_", nom, prenom, moyenne,nbAbsence, annee, motivation)); 
			} else {
				for(int i=0; i<listeTuteur.size(); i++) {
					if(prenomNom.equals(listeTuteur.get(i).getPrenomNom())){
						System.out.println("il existe deja ce tuteur. Il n'y a pas de nÃ©cessitÃ© de l'ajouter.");	
						return false;
					}
				}
				return listeTuteur.add(new Tuteur("tuteur_", nom, prenom, moyenne, nbAbsence, annee, motivation));
			}
		} else {
			 // C'est le logiciel qui entre un candidat factice
			Random rand = new Random();
			Tuteur tuteurFactice;
			Tutore tutoreFactice;
			String prenomVide, nomVide;
			if (difference > 0 ) {
				// + de tuteurs que de tutores
				for(int i = 0; i < difference; i++) {
					prenomVide = "P_" + rand.nextInt(1000);
					nomVide = "N_" + rand.nextInt(1000);
					tutoreFactice = new Tutore("999999", prenomVide, nomVide , 20.0, 1000, 1, '-');
					this.listeTutore.add(tutoreFactice);
				}
			} else if (difference < 0 ){
				// - de tuteurs que de tutores
				for(int i = 0; i < Math.abs(difference); i++) {
					prenomVide = "PrenomVide" + rand.nextInt(2147483647);
					nomVide = "NomVide" + rand.nextInt(2147483647);
					tuteurFactice = new Tuteur("999999", prenomVide, nomVide, 0.00, 1000, 2, '-');
					this.listeTuteur.add(tuteurFactice);
				}
			} else {
				// impossible
				return false;
			}
			return true;
		}
	}

	public boolean supprimeCandidat() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Entrez le nom de l'ï¿½tudiant ï¿½ supprimer :");
		String nom = scan.nextLine();

		System.out.println("Entrez le prÃ©nom de l'ï¿½tudiant  supprimer :");
		String prenom = scan.nextLine();

		String prenomNom = nom+"_"+prenom;
		System.out.println(prenomNom);
		this.tailleEgale();
		return this.supprimeCandidat(prenomNom);
	}
	
	public boolean supprimeCandidat(String prenomNom) {
		for(int i=0; i<this.listeTuteur.size(); i++) {
			if(prenomNom.equals(this.listeTuteur.get(i).getPrenomNom())){
				listeTuteur.remove(i);
				System.out.println("Suppression du tuteur réussie.");
				return true;
			}
		}
		for(int i=0; i<this.listeTutore.size(); i++) {
			if(prenomNom.equals(this.listeTutore.get(i).getPrenomNom())){
				listeTutore.remove(i);
				System.out.println("Suppression du tutoré réussie.");
				return true;
			}
		}
		System.out.println("L'etudiant n'existe pas.");
		return false;
	}
	
	public boolean vuTuteur() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Entre le nom");
		String nom = scan.nextLine();
		
		System.out.println("Entre le prenom");
		String prenom = scan.nextLine();
		
		nom = nom+"_"+prenom;
		boolean trouve = false;
		for(int i=0; i<listeTuteur.size(); i++) {
			if(nom.equals(listeTuteur.get(i).getPrenomNom())){
				//listeTuteur.remove(i);
				System.out.println("annee :" + listeTuteur.get(i).getAnnee());
				System.out.println("abs :" + listeTuteur.get(i).absences);
				System.out.println("motivation :" + listeTuteur.get(i).motivation);
				System.out.println("moy :" + listeTuteur.get(i).moyenne);
				trouve = true;
			}
		}
		if(!trouve) {
			System.out.println("le tuteur n'est pas dans la liste");
		}
			
		return true;
	}
	
	public boolean vuTutore() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Entre le nom");
		String nom = scan.nextLine();
		
		System.out.println("Entre le prenom");
		String prenom = scan.nextLine();
		
		nom = nom+"_"+prenom;
		boolean trouve = false;
		for(int i=0; i<listeTutore.size(); i++) {
			if(nom.equals(listeTutore.get(i).getPrenomNom())){
				System.out.println("annee : " + listeTutore.get(i).getAnnee());
				System.out.println("abs : " + listeTutore.get(i).absences);
				System.out.println("motivation : " + listeTutore.get(i).motivation);
				System.out.println("moyenne : " + listeTutore.get(i).moyenne);
				trouve = true;
			}
		}
		if(!trouve) {
			System.out.println("Le tutore n'est pas dans la liste");
		}

		return true;
	}

	private void ajouterSommetsTutore() {
		for(Tutore t : this.listeTutore) {
			this.graphe.ajouterSommet(t.getPrenomNom());
		}
	}
	
	private void ajouterSommetsTuteur() {
		for(Tuteur t : this.listeTuteur) {
			this.graphe.ajouterSommet(t.getPrenomNom());
		}
	}
	
	private void ajouterAretes() {
		for(Tuteur tuteur : this.listeTuteur) {
			for(Tutore tutore : this.listeTutore) {
				this.graphe.ajouterArete(
						tuteur.getPrenomNom(), 
						tutore.getPrenomNom(), 
						this.calculDistance(tuteur, tutore));
			}
		}
	}
	
	public CalculAffectation<String> calculAffectation() {
		//egale des listes
		this.tailleEgale();
		//reinitialiation graphe 
		this.graphe=new GrapheNonOrienteValue<String>();
		// Ajouter Sommets au graphe
		this.ajouterSommetsTuteur();
		this.ajouterSommetsTutore();
		// Ajouter arete au graphe
		this.ajouterAretes();
		//String affectation = ImportCSV.calculAffectation(this.graphe, ImportCSV.ExtractiongroupeTutoreCSV(ImportCSV.tutoreCSV), ImportCSV.ExtractiongroupeTuteurCSV(ImportCSV.tuteurCSV));
		// Calcul affectation
		List<String> tuteurPrenomNom = new ArrayList<String>();
		List<String> tutorePrenomNom = new ArrayList<String>();
		
		for(Tuteur t : this.listeTuteur) {
			tuteurPrenomNom.add(t.getPrenomNom());
		}
		for(Tutore t : this.listeTutore) {
			tutorePrenomNom.add(t.getPrenomNom());
		}
		
		CalculAffectation<String> calculAffectation = new CalculAffectation<>(this.graphe, tutorePrenomNom, tuteurPrenomNom);
		
		//System.out.println("le cout minimal est de : " + c.getCout());
		
		return calculAffectation;
	}
	
	public String afficherResultatAffectation() {
		CalculAffectation<String> calcul = this.calculAffectation();
		String res = "";
		
		for(int i = 0; i < calcul.getAffectation().size(); i++) {
			res += calcul.getAffectation().get(i).getExtremite1() + " doit se mettre avec " + calcul.getAffectation().get(i).getExtremite2() + "\n";
		}
		
		return res;
	}

	public String toString() {
		this.purgeCandidatFactices();
		String retour = "TutorÃ©s : \n" + this.listeTutore.size() +"\n";
		for(Tutore t : this.listeTutore) {
			retour = retour + t.toString() + "\n";
		}
		retour = retour + "\nTuteurs : \n"+ this.listeTuteur.size()+"\n";
		for(Tuteur t : this.listeTuteur) {
			retour = retour + t.toString() + "\n";
		}
		this.tailleEgale();
		return retour;
	}
	
	public boolean ajouterTuteur(Tuteur t) {
		return this.listeTuteur.add(t);
	}
	
	public boolean ajouterTutore(Tutore t) {
		return this.listeTutore.add(t);
	}
	
	public Tuteur getTuteur(String prenomNom) {
		for(Tuteur t : this.listeTuteur) {
			if (t.getPrenomNom().equals(prenomNom)) {
				return t;
			}
		}
		// TODO - throw error
		return null;
	}
	
	public Tutore getTutore(String prenomNom) {
		for(Tutore t : this.listeTutore) {
			if (t.getPrenomNom().equals(prenomNom)) {
				return t;
			}
		}
		// TODO - throw error
		return null;
	}
	
	public boolean containsTuteurVide() {
		for(Tuteur t : this.listeTuteur) {
			if (t.getIdentifiant().equals("999999")) {
				return true;
			}
		}
		return false;
	}
	
	public boolean containsTutoreVide() {
		for(Tutore t : this.listeTutore) {
			if(t.getIdentifiant().equals("999999")) { 
				return true;
			}
		}
		return false;
	}
}

