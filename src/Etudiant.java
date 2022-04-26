package SAE;

public abstract class Etudiant extends Utilisateur{
	int annee;
	double moyenne;
	int nbAbsence;
	
	public Etudiant(String identifiant, String nom, String prenom, int annee, double moyenne, int nbAbsence) {
		super(identifiant, nom, prenom);
		this.annee = annee;
		this.moyenne = moyenne;
		this.nbAbsence = nbAbsence;
	}
	
	public abstract void inscription();
	
}
