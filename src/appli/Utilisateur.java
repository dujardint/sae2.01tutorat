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
		return "Utilisateur [identifiant=" + identifiant + ", nom=" + nom + ", prenom=" + prenom + "]";
	}

}
