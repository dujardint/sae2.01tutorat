package appli;

import java.util.List;

public class Tutorat extends Ressources {
	Tuteur tuteur;
	Tutore tutore;
	
	public Tutorat(String nom, int nbPlaces, Enseignant enseignant, List<Tutorat> tutorats) {
		super(nom, nbPlaces, enseignant, tutorats);
	}
	
	public void choixEnseignant() {
		//TODO
	}
}
