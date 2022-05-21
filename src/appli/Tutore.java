package appli;

public class Tutore extends Etudiant{

	public Tutore(String identifiant, String prenom, String nom, double moyenne, int absences , char motivation) throws IllegalArgumentException {
        super(identifiant, nom, nom, moyenne, absences, absences, motivation);
    }

	@Override
	public void inscription() {
	}
	
	public static void main(String[] args) {
		//Tutore a1 = new Tutore("001", "Barthe", "Madelaine", 6.9);	
	}

	@Override
	public int compareTo(Etudiant o) {

		return 0;
	}

	
}
