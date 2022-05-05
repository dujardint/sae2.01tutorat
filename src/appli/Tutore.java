package appli;

public class Tutore extends Etudiant{

	public Tutore(String identifiant, String nom, String prenom, int annee, double moyenne, int nbAbsence) {
		super(identifiant, nom, prenom, annee, moyenne, nbAbsence);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void inscription() {
		// TODO Auto-generated method stub
	}
	
	public static void main(String[] args) {
		Tutore a1 = new Tutore("001", "Barthe", "Madelaine", 1, 6.9, 0);
		
	}

}
