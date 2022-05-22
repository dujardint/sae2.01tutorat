package appli;

public class UseEtudiant {
	 public static void main(String[] args) {
	        Tuteur t1 = new Tuteur("tuteur_","François","Bertin",13.3,2,'+');
	        Tuteur t2 = new Tuteur("tuteur_","François","Bertin",13.3,2,'+');
	        Tuteur t3 = new Tuteur("tuteur_","François","Bertin",13.3,2,'+');
	        Tuteur t4 = new Tuteur("tuteur_","François","Bertin",13.3,2,'+');
	        Tuteur t5 = new Tuteur("tuteur_","François","Bertin",13.3,2,'+');
	        // Tutor�s : forment l'ensemble U
	        Tutore u1 = new Tutore("tutore_", "Claude", "Allard", 9.8,1,'+');
	        Tutore u2 = new Tutore("tutore_", "Claude", "Allard", 9.8,1,'+');
	        Tutore u3 = new Tutore("tutore_", "Claude", "Allard", 9.8,1,'+');
	        Tutore u4 =new Tutore("tutore_", "Claude", "Allard", 9.8,1,'+');
	        Tutore u5 =new Tutore("tutore_", "Claude", "Allard", 9.8,1,'+');
	        Tutore u6 = new Tutore("tutore_", "Claude", "Allard", 9.8,1,'+');
	        Tutore u7 = new Tutore("tutore_", "Claude", "Allard", 9.8,1,'+');

	        dpt.addEtudiant(Ressources.R201, t1);
	        dpt.addEtudiant(Ressources.R201, t3);
	        dpt.addEtudiant(Ressources.R201, t4);

	        dpt.addEtudiant(Ressources.R201, u1);
	        dpt.addEtudiant(Ressources.R201, u3);
	        dpt.addEtudiant(Ressources.R201, u5);
	        dpt.addEtudiant(Ressources.R201, u7);

	        

	        System.out.println(dpt.toString());
	    }
}
