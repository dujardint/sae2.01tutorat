package appli;

public class Tutore extends Etudiant{

	public Tutore(String identifiant, String prenom, String nom, double moyenne, int absences , int annee ,char motivation) throws IllegalArgumentException {
        super(identifiant, prenom, nom, moyenne, absences, annee, motivation);
    }

	@Override
	public void inscription() {
	}
	
	@Override
	public int compareTo(Etudiant o) {

		return 0;
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

	public String getPrenomNom() {
		
		return this.prenom +"_"+this.nom;
	}

	
}
