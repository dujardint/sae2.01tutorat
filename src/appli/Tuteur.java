package appli;

//import com.sun.tools.javac.launcher.Main;

public class Tuteur extends Etudiant{

	public Tuteur(String identifiant, String prenom, String nom,double moyenne, int annee) {
		super(identifiant, prenom, nom, moyenne,annee);
	}

	@Override
	public void inscription() {
		// TODO Auto-generated method stub
	}
	
	public static void main(String[] args) {
		Tuteur t1 = new Tuteur("001", "Bertaint", "Francois", 13.3,2);
	}
	
	

	

}
