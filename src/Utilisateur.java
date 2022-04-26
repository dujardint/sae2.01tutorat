package SAE;

public abstract class Utilisateur {
	String identifiant;
	String nom;
	String prenom;
	
	public Utilisateur(String identifiant, String nom, String prenom) {
		this.identifiant = identifiant;
		this.nom = nom;
		this.prenom = prenom;
	}

}
