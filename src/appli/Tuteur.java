package appli;

//import com.sun.tools.javac.launcher.Main;

public class Tuteur extends Etudiant{

	public Tuteur(String identifiant, String prenom, String nom,double moyenne, int annee, char motivation) 
			throws IllegalArgumentException {
		super(identifiant, prenom, nom, moyenne,motivation, annee, motivation);
		if (annee == 1) {
			throw new IllegalArgumentException("Tutor students cannot have a level of 1.");
		} else if (annee == 3) {
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

	public static void main(String[] args) {
		//Tuteur t1 = new Tuteur("001", "Bertaint", "Francois", 13.3,2);
	}

	@Override
	public int compareTo(Etudiant etudiant) {
		return 0;
	}





}
