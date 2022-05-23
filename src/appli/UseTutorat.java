package appli;

import java.util.ArrayList;
import java.util.List;

public class UseTutorat extends Tutorat  {
	public UseTutorat(List<Tuteur> listeTuteur, List<Tutore> listeTutore) {
		super(listeTuteur, listeTutore);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		List<Tutore> tutore = new ArrayList<Tutore>();
		tutore.add(new Tutore("tutore_", "Claude", "Allard", 9.8,1,1,'+'));
		tutore.add(new Tutore("tutore_", "Madeleine", "Barre", 6.9,1,1,'+'));
		tutore.add(new Tutore("tutore_", "Sabine", "Besnard", 12.7,1,1,'+'));


		List<Tuteur> tuteur = new ArrayList<Tuteur>();
		tuteur.add(new Tuteur("tuteur_","François","Bertin",13.3,1,2,'+'));
		tuteur.add(new Tuteur("tuteur_","Joseph","Boyer",7.7,1,3,'+'));
		tuteur.add(new Tuteur("tuteur_","Martin","Delmas",11.0,1,2,'+'));


		Tutorat etu = new Tutorat(tuteur, tutore);
		System.out.println("liste avant tri : " + etu.toString());
		System.out.println("ON TRIE .... \n");

		for(int i=0; i<tuteur.size(); i++) {
			System.out.println(calculDistance(tuteur.get(i),tutore.get(i)));
		}

		System.out.println(etu.triTuteur(tuteur));
		System.out.println(etu.triTutore(tutore));

	}
}
