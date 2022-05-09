package appli;

public class Tutore extends Etudiant{

	public Tutore(String identifiant, String prenom, String nom, double moyenne) {
		super(identifiant, prenom,nom, moyenne,1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void inscription() {
		// TODO Auto-generated method stub
	}
	
	public static void main(String[] args) {
		Tutore a1 = new Tutore("001", "Barthe", "Madelaine", 6.9);
		
	}

}
