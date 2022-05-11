package appli;

import java.util.ArrayList;
import java.util.List;

public class GroupeCandidats {
	List<Tuteur> groupeTuteur;
	List<Tutore> groupeTutore;

	
	public static void main(String[] args) {
		List<Tuteur> a = new ArrayList<Tuteur>();
		ArrayList<Tutore> b = new ArrayList<Tutore>();
		GroupeCandidats c = new GroupeCandidats(a, b);
		
		b.add(new Tutore("tutore_", "Claude", "Allard", 9.8));
		b.add(new Tutore("tutore_", "Madeleine", "Barre", 6.9));
		
		a.add(new Tuteur("tuteur_","François","Bertin",13.3,2));
		a.add(new Tuteur("tuteur_","Joseph","Boyer",7.7,2));
		
		System.out.println(a.get(0).getNom());
		
		System.out.println(c.calculDistance(a.get(0), b.get(1)));
	}
	
	

	public GroupeCandidats(List<Tuteur> groupeTuteur, List<Tutore> groupeTutore) {
		
		this.groupeTuteur = new ArrayList<Tuteur>();
		this.groupeTutore = new ArrayList<Tutore>();
		
	}
	
	
	//ancien constructeur
	/*
	 * 	public GroupeCandidats(List<Tuteur> groupeTuteur, List<Tutore> groupeTutore) {
		this.groupeTuteur = this.triTuteur(groupeTuteur);
		this.groupeTutore = this.triTutore(groupeTutore);
	}
	 */


	@Override
	public String toString() {
		
		String listeToString = "Candidats tuteurs : \n";
		for(int i = 0; i < groupeTuteur.size(); i++) {
			listeToString = listeToString + groupeTuteur.get(i).toString() + "\n";
		}
		listeToString= listeToString + "Candidats tutores : \n";
		for(int i = 0; i < groupeTutore.size(); i++) {
			listeToString = listeToString + groupeTutore.get(i).toString() + "\n";
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
	 * Cette fonction calcule l'�cart entre 2 notes d'�tudiants
	 * elle retourne la distance tuteur, tutor�.
	 
	 */
	public double calculDistance(Tuteur tuteur, Tutore tutore) {
		double moyenneTuteur;
		if(tuteur.getAnnee() == 3) {
			moyenneTuteur = 40.0 + tuteur.getMoyenne();
		} else {
			moyenneTuteur = 20 + tuteur.getMoyenne();
		}
		return moyenneTuteur - tutore.getMoyenne();
	}
	
	//* Elle retourne un tableau avec le nom du tuteur / tutor� ainsi que les �carts
	
	public List<PaireCandidats> tableauEcartMoyenne () {
		List<PaireCandidats> paireCandidats = new ArrayList<PaireCandidats>();
		for(int i = 0; i < this.groupeTutore.size(); i++) {
			for(int j= 0; j < this.groupeTuteur.size(); j++) {
				paireCandidats.add(new PaireCandidats(
						this.groupeTuteur.get(j).getNom(),
						this.groupeTutore.get(i).getNom(),
						calculDistance(this.groupeTuteur.get(j), this.groupeTutore.get(i))));
			}
		}
		return paireCandidats;
	}
	
	public String groupeCandidatsToString(List<PaireCandidats> groupeCandidats) {
		String retour = "";
		for (int i = 0; i < groupeCandidats.size(); i++) {
			retour = retour + groupeCandidats.get(i).toString() + "\n";
		}
		return retour;
	}
}
