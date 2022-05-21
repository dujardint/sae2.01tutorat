package appli;

import java.util.ArrayList;
import java.util.List;

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

	/*
	public Tutorat(List<Tuteur> listeTuteur, List<Tutore> listeTutore) {
		this.listeTutore = this.triTutore(listeTutore);
		this.listeTuteur = this.triTuteur(listeTuteur);
		this.listePaire  = this.genererPaires();
	}
	 */

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
	/*public boolean addStudent(Etudiant e) {
	        if (this.Tutore.contains(e) || this.tutors.contains(e)) {
	            return false;
	        }
	        if (e.getAnnee() == 1) {
	            return this.tutore.add((Tutore) e);
	        } else {
	            return this.tuteur.add((Tuteur) e);
	        }
	    }*/

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

	public static void main(String[] args) {
		List<Tutore> tutore = new ArrayList<Tutore>();
		tutore.add(new Tutore("tutore_", "Claude", "Allard", 9.8,1,'+'));
		tutore.add(new Tutore("tutore_", "Madeleine", "Barre", 6.9,1,'+'));
		tutore.add(new Tutore("tutore_", "Sabine", "Besnard", 12.7,1,'+'));


		List<Tuteur> tuteur = new ArrayList<Tuteur>();
		tuteur.add(new Tuteur("tuteur_","François","Bertin",13.3,2,'+'));
		tuteur.add(new Tuteur("tuteur_","Joseph","Boyer",7.7,3,'+'));
		tuteur.add(new Tuteur("tuteur_","Martin","Delmas",11.0,2,'+'));


		Tutorat etu = new Tutorat(tuteur, tutore);
		System.out.println("liste avant tri : " + etu.toString());
		System.out.println("ON TRIE .... \n");

		for(int i=0; i<tuteur.size(); i++) {
			System.out.println(calculDistance(tuteur.get(i),tutore.get(i)));
		}

		//	System.out.println(genererPaires());

		System.out.println(etu.triTuteur(tuteur));
		System.out.println(etu.triTutore(tutore));
		//System.out.println("liste trie : " + etu.toString());




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

	/**
	 * Cette fonction calcule l'ï¿½cart entre 2 notes d'ï¿½tudiants
	 * elle retourne la distance tuteur, tutorï¿½.

	 */
	public static double calculDistance(Tuteur tuteur, Tutore tutore) {
		double moyenneTuteur;
		if(tuteur.getAnnee() == 3) {
			moyenneTuteur = 40.0 + tuteur.getMoyenne();
		} else {
			moyenneTuteur = 20 + tuteur.getMoyenne();
		}
		return moyenneTuteur - tutore.getMoyenne();
	}

	//* Elle retourne un tableau avec le nom du tuteur / tutorï¿½ ainsi que les ï¿½carts

	/*
	public static List<Paire> genererPaires () {
		List<Paire> paireCandidats = new ArrayList<Paire>();
		for(int i = 0; i < listeTutore.size(); i++) {
			for(int j= 0; j < listeTuteur.size(); j++) {
				paireCandidats.add(new Paire(
						listeTuteur.get(j).getPrenom(),
						listeTutore.get(i).getPrenom(),
						calculDistance(listeTuteur.get(j), listeTutore.get(i))));

			}
		}
		return paireCandidats;
	}

	 */

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
	/*
	public GrapheNonOrienteValue<String> genererGrapheNonOrienteValue(){
		GrapheNonOrienteValue<String> g = new GrapheNonOrienteValue<String>();
		// Sommets tuteurs
		for(int i = 0; i < this.listeTuteur.size(); i++) {
			System.out.println(this.listeTuteur.get(i).getPrenom());
			g.ajouterSommet(this.listeTuteur.get(i).getPrenom());
		}
		System.out.println("    ");
		// Sommets tutores
		for(int i = 0; i < this.listeTutore.size(); i++) {
			System.out.println(this.listeTutore.get(i).getPrenom());
			g.ajouterSommet(this.listeTutore.get(i).getPrenom());
		}

		// Aretes
		for(int i = 0; i < this.listePaire.size(); i++) {
			System.out.println(this.listePaire.get(i).getTuteur() + " " + this.listePaire.get(i).getTutore() + " " + this.listePaire.get(i).getDistance());
			g.ajouterArete(this.listePaire.get(i).getTuteur(),
					this.listePaire.get(i).getTutore(),
					this.listePaire.get(i).getDistance());
		}
		return g;
	}

	public List<String> getPrenomsTuteurs(){
		List<String> prenomsTuteurs = new ArrayList<>();
		for(int i = 0; i < listeTuteur.size(); i++) {
			prenomsTuteurs.add(listeTuteur.get(i).getPrenom());
		}
		return prenomsTuteurs;
	}

	public List<String> getPrenomsTutores(){
		List<String> prenomsTutores = new ArrayList<>();
		for(int i = 0; i < listeTutore.size(); i++) {
			prenomsTutores.add(listeTuteur.get(i).getPrenom());
		}
		return prenomsTutores;
	}
	 */
}

