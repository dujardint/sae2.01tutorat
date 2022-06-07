package appli;

public class Utilisateur {
	String identifiant;
	String nom;
	String prenom;
	
	public Utilisateur(String identifiant, String nom, String prenom) {
		this.identifiant = identifiant;
		this.nom = nom;
		this.prenom = prenom;
	}

	@Override
	public String toString() {
		return this.prenom + "_" + this.nom;
	}
	
	public static void main(String[] args) {
		   Tuteur t1 = new Tuteur("tuteur_","François","Bertin",13.3,1, 2, '+');
		   System.out.println(t1);
		   System.out.println(t1.getPrenomNom());
		}

	public String getPrenom() {
		return this.prenom;
	}
	
	public String getNom() {
		return this.nom;
	}
}
