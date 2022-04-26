package SAE;

import java.util.ArrayList;
import java.util.List;

import fr.ulille.but.sae2_02.graphes.CalculAffectation;

public class Ressources {
	String nom;
	int nbPlaces;
	Enseignant enseignant;
	List<Tutorat> tutorats;
	
	public Ressources(String nom, int nbPlaces, Enseignant enseignant, List<Tutorat> tutorats) {
		this.nom = nom;
		this.nbPlaces = nbPlaces;
		this.enseignant = enseignant;
		this.tutorats = tutorats;
	}
	
	public static void main (String[] args) {
		
	}
}
