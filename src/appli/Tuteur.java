package appli;

//import com.sun.tools.javac.launcher.Main;

public class Tuteur extends Etudiant{

	int nbTutoresMax;
	int nbTutore;
	
	public Tuteur(String identifiant, String prenom, String nom,double moyenne,int absence , int annee, char motivation) 
			throws IllegalArgumentException {
		super(identifiant, prenom, nom, moyenne,absence, annee, motivation);
		if (annee == 1) {
			throw new IllegalArgumentException("Le tuteur ne peux pas etre en premi�re ann�e");
		} else if (annee == 2) {
			this.annee = annee;
			this.nbTutoresMax = 1;
			this.nbTutore = 0;
		} else if (annee == 3) {
			this.annee = annee;
			this.nbTutoresMax = 2;
			this.nbTutore = 0;
		}
	}
	
	
	@Override
	public void inscription() {

	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public char charAt(int index) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public CharSequence subSequence(int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
	   Tuteur t1 = new Tuteur("tuteur_","Francois","Bertin",13.3,1, 2, '+');
	   System.out.println(t1);
	   System.out.println(t1.getPrenomNom());
	}
}
