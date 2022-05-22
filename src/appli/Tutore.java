package appli;

public class Tutore extends Etudiant{

	public Tutore(String identifiant, String prenom, String nom, double moyenne, int absences , char motivation) throws IllegalArgumentException {
        super(identifiant, nom, nom, moyenne, absences, absences, motivation);
    }

	@Override
	public void inscription() {
	}
	
	@Override
	public int compareTo(Etudiant o) {

		return 0;
	}

	
}
