package appli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;


public class Tutorat  {
	private static List<Tutore> listeTutore;
	private static List<Tuteur> listeTuteur;
	private List<Paire> listePaire;

	public Tutorat(List<Tuteur> listeTuteur, List<Tutore> listeTutore) {
		Tutorat.listeTutore = listeTutore;
		Tutorat.listeTuteur = listeTuteur;
		//this.listePaire  = this.genererPaires();
	}
	public List<Paire> getListePaire(){
		return this.listePaire;
	}

	private class Paire {
		private String tuteur;
		private String tutore;
		private double distance;

		Paire(String tuteur, String tutore, double distance) {
			this.tuteur = tuteur;
			this.tutore = tutore;
			this.distance = distance;
		}

		public String getTuteur() {
			return tuteur;
		}
		public void setTuteur(String tuteur) {
			this.tuteur = tuteur;
		}
		public String getTutore() {
			return tutore;
		}
		public void setTutore(String tutore) {
			this.tutore = tutore;
		}
		public double getDistance() {
			return distance;
		}
		public void setDistance(double distance) {
			this.distance = distance;
		}	
	}
	public String toString() {
		String listeToString = "Candidats tuteurs : \n";
		for(int i = 0; i < listeTuteur.size(); i++) {
			listeToString = listeToString + listeTuteur.get(i).toString() + "\n";
			System.out.println(" \n");
		}
		listeToString= listeToString + "Candidats tutores : \n";
		for(int i = 0; i < listeTutore.size(); i++) {
			listeToString = listeToString + listeTutore.get(i).toString() + "\n";
			System.out.println(" \n");
		}

		return listeToString;
	}

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

	public List<Tutore> triTutore (List<Tutore> listeNonTriee){
		List<Tutore> listeTriee = new ArrayList<Tutore>();
		int positionMin;
		while(listeNonTriee.size() > 0) {
			positionMin = getPositionMin(listeNonTriee);
			listeTriee.add(listeNonTriee.get(positionMin));
			listeNonTriee.remove(positionMin);
		}

		return listeTriee;
	}

	public List<Tuteur> triTuteur (List<Tuteur> listeNonTriee){
		List<Tuteur> listeTriee = new ArrayList<Tuteur>();
		int positionMax;
		while(listeNonTriee.size() > 0) {
			positionMax = getPositionMax(listeNonTriee);
			listeTriee.add(listeNonTriee.get(positionMax));
			listeNonTriee.remove(positionMax);
		}

		return listeTriee;
	}


	public static double calculDistance(Tuteur tuteur, Tutore tutore) {
		double moyenneTuteur;
		if(tuteur.getAnnee() == 3) {
			moyenneTuteur = 40.0 + tuteur.getMoyenne();
		} else {
			moyenneTuteur = 20 + tuteur.getMoyenne();
		}
		return moyenneTuteur - tutore.getMoyenne();
	}

	public String paireToString() {
		String retour = "";
		for (int i = 0; i < this.listePaire.size(); i++) {
			retour = retour + this.listePaire.get(i).getTutore() + " " +this.listePaire.get(i).getTuteur() + " " +this.listePaire.get(i).getDistance() + "\n";
		}
		return retour;
	}

	public void choixEnseignant() {
		//TODO
	}


	public boolean tailleEgale() {
		System.out.println("Il y a " + listeTuteur.size() + " Tuteurs et " + listeTutore.size() + " tutores.");
		while(listeTuteur.size()!=listeTutore.size()) {
			if((listeTuteur.size()>listeTutore.size())){
				System.out.println("\ntaille tuteur " + listeTuteur.size());
				System.out.println("taille tutore " + listeTutore.size()+"\n");
				System.out.println("il y a plus de tuteur que de tutore !");
				Tutorat.ajoutCandidat();
			}
			else {
				System.out.println("\ntaille tuteur " + listeTuteur.size());
				System.out.println("taille tutore " + listeTutore.size()+"\n");
				System.out.println("il y a plus de tutore que de tuteur !");
				Tutorat.ajoutCandidat();
			}
		}
		System.out.println("Il y a autant de tueur que de tutoren l'algorithme peut s'executer !");
		return true;
	}


	public static boolean ajoutCandidat() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Entre le nom pour ajouter un tutore");
		String nom = scan.nextLine();
		System.out.println("Entre le prenom");
		String prenom = scan.nextLine();
		String PrenomNom = prenom + "_" + nom;
		System.out.println(PrenomNom);

		System.out.println("Entre la moyenne");
		double moyenne = scan.nextDouble();
		System.out.println("Entre le nombre d'abscence");
		int nbAbsence = scan.nextInt();
		System.out.println("Entre l'annee");
		int annee = scan.nextInt();
		System.out.println("Entre la motivation (+,-,=)");
		char motivation = scan.next().charAt(0);

		if(annee==1) {
			for(int i=0; i<listeTutore.size(); i++) {
				if(PrenomNom.equals(listeTutore.get(i).getPrenomNom())){
					System.out.println("il existe deja en tutore !!");	
					return false;
				}
			}
			return listeTutore.add(new Tutore("tutore_", nom, prenom, moyenne,nbAbsence, annee, motivation)); 
		}
		else {
			for(int i=0; i<listeTuteur.size(); i++) {
				if(PrenomNom.equals(listeTuteur.get(i).getPrenomNom())){
					System.out.println("il existe deja en tuteur !!");	
					return false;
				}
			}
			return listeTuteur.add(new Tuteur("tutore_", nom, prenom, moyenne, nbAbsence, annee, motivation));
		}
	}

	public boolean supprimeCandidat() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Entre le nom pour le supprimer");
		String nom = scan.nextLine();

		System.out.println("Entre le prenom pour le supprimer");
		String prenom = scan.nextLine();

		nom = nom+"_"+prenom;
		System.out.println(nom);

		for(int i=0; i<listeTuteur.size(); i++) {
			if(nom.equals(listeTuteur.get(i).getPrenomNom())){
				listeTuteur.remove(i);
				System.out.println("suppression reussit !");
				tailleEgale();
			}
		}
		return true;
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
				//listeTuteur.remove(i);
				System.out.println("annee :" + listeTutore.get(i).getAnnee());
				System.out.println("abs :" + listeTutore.get(i).absences);
				System.out.println("motivation :" + listeTutore.get(i).motivation);
				System.out.println("moy :" + listeTutore.get(i).moyenne);
				trouve = true;
			}
		}
		if(!trouve) {
			System.out.println("le tutore n'est pas dans la liste");
		}

		return true;
		
	}

}

