package appli;

//import com.sun.tools.javac.launcher.Main;

public class Tuteur extends Etudiant{

	public Tuteur(String identifiant, String nom, String prenom, int annee, double moyenne, int nbAbsence) {
		super(identifiant, nom, prenom, annee, moyenne, nbAbsence);
	}

	@Override
	public void inscription() {
		// TODO Auto-generated method stub
	}
	
	public static void main(String[] args) {
		Tuteur t1 = new Tuteur("001", "Bertaint", "Francois", 2, 13.3, 0);
	}
	
	

	

}
