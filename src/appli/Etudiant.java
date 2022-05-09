package appli;

public abstract class Etudiant extends Utilisateur{
	int annee;
	double moyenne;
	
	public Etudiant(String identifiant, String prenom, String nom, double moyenne, int annee) {
		super(identifiant, nom, prenom);
		this.annee = annee;
		this.moyenne = moyenne;
		
	}
	
	
	@Override
	public String toString() {
		return super.toString() + "-> Etudiant [annee=" + annee + ", moyenne=" + moyenne + "]";
	}



	public abstract void inscription();
	
	public void setMoyenne(double moyenne) {
		this.moyenne = moyenne;
	}
	
	public double getMoyenne() {
		return this.moyenne;
	}
	
	public int getAnnee() {
		return this.annee;
	}
	
	public void setAnnee( int annee) {
		this.annee = annee;
	}
}
