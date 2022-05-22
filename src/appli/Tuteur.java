package appli;

//import com.sun.tools.javac.launcher.Main;

public class Tuteur extends Etudiant{

	public Tuteur(String identifiant, String prenom, String nom,double moyenne, int annee, char motivation) 
			throws IllegalArgumentException {
		super(identifiant, prenom, nom, moyenne,motivation, annee, motivation);
		if (annee == 1) {
			throw new IllegalArgumentException("Le tuteur ne peux pas etre en première année");
		} else if (annee >= 2) {
			this.annee = annee;
		}
	}
	
	
	@Override
	public void inscription() {

	}
	
	@Override
	public String toString() {
		return "Tuteur []";
	}
	






}
