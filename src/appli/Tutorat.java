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
}

