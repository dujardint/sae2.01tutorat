package appli;

public abstract class Utilisateur {
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getPrenomNom() {
		return prenom+"_"+nom;
	}
	
}
