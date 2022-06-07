package appli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Tutorat  {
	private List<Tutore> listeTutore;
	private List<Tuteur> listeTuteur;

	public Tutorat(List<Tuteur> listeTuteur, List<Tutore> listeTutore) {
		this.listeTutore = listeTutore;
		this.listeTuteur = listeTuteur;
		this.tailleEgale();
	}
	
	public List<Tutore> getListTutore(){
		return this.listeTutore;
	}

	public List<Tuteur> getListTuteur(){
		return this.listeTuteur;
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
	
	public void choixEnseignant() {
		//TODO
	}


	public boolean tailleEgale() {
		Tuteur tuteurFactice = new Tuteur("999999", "PrenomVide", "NomVide", 0.00, 1000, 2, '-');
		Tutore tutoreFactice = new Tutore("999999", "PrenomVide", "NomVide" , 20.0, 1000, 1, '-');
		while(this.listeTuteur.remove(tuteurFactice)) {}
		while(this.listeTutore.remove(tutoreFactice)) {}
		
		int difference = this.listeTuteur.size() - this.listeTutore.size();
		if (difference > 0) {
			System.out.println("Il y a plus de tuteurs que de tutores, nous procédons à un rééquilibrage pour la simulation.");
			return this.ajoutCandidat(false, difference);
		} else if (difference < 0) {
			System.out.println("Il y a plus de tutores que de tuteurs, nous procédons à un rééquilibrage pour la simulation.");
			return this.ajoutCandidat(false, difference);
		}
		return false;
	}

	public boolean ajoutCandidat(boolean manuel, int difference) {
		if (manuel) {
			// C'est l'utilisateur qui entre les paramètres du Candidat
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
				for(int i=0; i < listeTutore.size(); i++) {
					if(PrenomNom.equals(listeTutore.get(i).getPrenomNom())){
						System.out.println("Il existe deja ce tutore. Il n'y a pas de nÃ©cessitÃ© de l'ajouter.");	
						return false;
					}
				}
				return listeTutore.add(new Tutore("tutore_", nom, prenom, moyenne,nbAbsence, annee, motivation)); 
			} else {
				for(int i=0; i<listeTuteur.size(); i++) {
					if(PrenomNom.equals(listeTuteur.get(i).getPrenomNom())){
						System.out.println("il existe deja ce tuteur. Il n'y a pas de nÃ©cessitÃ© de l'ajouter.");	
						return false;
					}
				}
				return listeTuteur.add(new Tuteur("tuteur_", nom, prenom, moyenne, nbAbsence, annee, motivation));
			}
		} else {
			 // C'est le logiciel qui entre un candidat factice
			Tuteur tuteurFactice = new Tuteur("999999", "PrenomVide", "NomVide", 0.00, 1000, 2, '-');
			Tutore tutoreFactice = new Tutore("999999", "PrenomVide", "NomVide" , 20.0, 1000, 1, '-');
			if (difference > 0 ) {
				// + de tuteurs que de tutores
				for(int i = 0; i < difference; i++) {
					this.listeTutore.add(tutoreFactice);
				}
			} else if (difference < 0 ){
				// - de tuteurs que de tutores
				for(int i = 0; i < Math.abs(difference); i++) {
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
		System.out.println("Entrez le nom de l'étudiant Ã  supprimer :");
		String nom = scan.nextLine();

		System.out.println("Entrez le prÃ©nom de l'étudiant Ã  supprimer :");
		String prenom = scan.nextLine();

		nom = nom+"_"+prenom;
		System.out.println(nom);

		for(int i=0; i<listeTuteur.size(); i++) {
			if(nom.equals(listeTuteur.get(i).getPrenomNom())){
				listeTuteur.remove(i);
				System.out.println("Suppression rÃ©ussie.");
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

}

