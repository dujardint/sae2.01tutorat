package appli;

import java.util.List;

public class Enseignant extends Utilisateur {
	private List<Ressources> ressources;

	public Enseignant(String identifiant, String nom, String prenom, List<Ressources> ressources) {
		super(identifiant, nom, prenom);
		this.ressources = ressources;
	}
	
	 public boolean addResource(Ressources resource) {
	        return this.ressources.add(resource);
	    }

	public void imposerBinome() {
	}
	
	public void rejeterCandidatTutore() {
	}
	
	public List<Ressources> getRessources() {
		return ressources;
	}

	public void accepterTutore() {	 
	}
	
	public void rejeterCandidatTuteur() {		
	}
	
	public void accepterTuteur() {		 
	}
	
	@Override
	public String toString() {
		return "Enseignant" + this.nom + "[ressources=" + ressources + "]";
	}

}
