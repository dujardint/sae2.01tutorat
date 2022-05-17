package Graphes;

import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue ;
import java.util.* ;

import appli.Tuteur;
import appli.Tutorat;
import appli.Tutore;

public class AffectaCritere {


public static void main(String[] args) {
	GrapheNonOrienteValue<String> g = new GrapheNonOrienteValue<String>();
	System.out.println("Graphe permettant l'affectation normale : ");
	System.out.println(g);



	// On initialise le groupe des tutorés
	List<Tutore> groupeTutore = new ArrayList<>();
	groupeTutore.add(new Tutore("tutore_", "Claude", "Allard", 9.8));
	groupeTutore.add(new Tutore("tutore_", "Madeleine", "Barre", 6.9));
	groupeTutore.add(new Tutore("tutore_", "Sabine", "Besnard", 12.7));
	groupeTutore.add(new Tutore("tutore_", "Hugues", "Bigot", 0.2));
	groupeTutore.add(new Tutore("tutore_", "Lucas", "Bouchet", 17.3));
	groupeTutore.add(new Tutore("tutore_", "Alexandria", "Boulay", 12.5));  
	groupeTutore.add(new Tutore("tutore_", "Anouk", "Brun", 10.5));
	groupeTutore.add(new Tutore("tutore_", "Hortense", "Chauveau", 9.1));  
	groupeTutore.add(new Tutore("tutore_", "David", "Colin", 7.0));
	groupeTutore.add(new Tutore("tutore_", "Amélie", "Dijoux", 9.7));  
	groupeTutore.add(new Tutore("tutore_", "Martin", "Dijoux", 10.1));  
	groupeTutore.add(new Tutore("tutore_", "Thomas", "Evrard", 8.9));  
	groupeTutore.add(new Tutore("tutore_", "Guy", "Goncalves", 0.5));  
	groupeTutore.add(new Tutore("tutore_", "Emile", "Guerin", 4.8));
	groupeTutore.add(new Tutore("tutore_", "Eléonore", "Guillou", 13.1));  
	groupeTutore.add(new Tutore("tutore_", "Guillaume", "Hamel", 5.5));  
	groupeTutore.add(new Tutore("tutore_", "ValÃ©rie", "Jacob", 4.7));  
	groupeTutore.add(new Tutore("tutore_", "Odette", "Lacroix", 15.7));  
	groupeTutore.add(new Tutore("tutore_", "Jacques", "Marchand", 7.1));
	groupeTutore.add(new Tutore("tutore_", "HonorÃ©", "Martel", 11.7));  
	groupeTutore.add(new Tutore("tutore_", "Thierry", "Moreno", 1.4));  
	groupeTutore.add(new Tutore("tutore_", "Paulette", "Muller", 7.5));  
	groupeTutore.add(new Tutore("tutore_", "Camille", "Pascal", 12.8));  
	groupeTutore.add(new Tutore("tutore_", "Théodore", "Payet", 2.1));  
	groupeTutore.add(new Tutore("tutore_", "Caroline", "Perrier", 6.4));  
	groupeTutore.add(new Tutore("tutore_", "Rdith", "Pichon", 8.8));  
	groupeTutore.add(new Tutore("tutore_", "Agnès", "Renard", 13.2));  
	groupeTutore.add(new Tutore("tutore_", "Marine", "Roux", 9.1));
	groupeTutore.add(new Tutore("tutore_", "Aurore", "Schmitt", 9.9));


	for(int i=0; i<groupeTutore.size(); i++) {
		g.ajouterSommet(groupeTutore.get(i).getPrenom());
	}


	// On initialise le groupe des tuteurs
	List<Tuteur> groupeTuteur = new ArrayList<>();
	groupeTuteur.add(new Tuteur("tuteur_","François","Bertin",13.3,2));
	groupeTuteur.add(new Tuteur("tuteur_","Joseph","Boyer",7.7,2));
	groupeTuteur.add(new Tuteur("tuteur_","Martin","Delmas",11.0,2));
	groupeTuteur.add(new Tuteur("tuteur_","Maurice","Fernandez",5.7,2));
	groupeTuteur.add(new Tuteur("tuteur_","Thérèse","Gay",11.5,2));
	groupeTuteur.add(new Tuteur("tuteur_","daisy","Jacques",11.9,2));
	groupeTuteur.add(new Tuteur("tuteur_","Georges","Lefebvre",10.4,2));
	groupeTuteur.add(new Tuteur("tuteur_","Sabine","Leleu",15.8,2));
	groupeTuteur.add(new Tuteur("tuteur_","William","Letellier",11.8,2));
	groupeTuteur.add(new Tuteur("tuteur_","Gabriel","Marin",12.3,2));
	groupeTuteur.add(new Tuteur("tuteur_","Nathalie","Marion",12.1,2));
	groupeTuteur.add(new Tuteur("tuteur_","Christophe","Millet",14.2,2));
	groupeTuteur.add(new Tuteur("tuteur_","Henri","Moreno",10.8,2));
	groupeTuteur.add(new Tuteur("tuteur_","Vincent","Muller",9.3,2));
	groupeTuteur.add(new Tuteur("tuteur_","Jacqueline","Pons",13.2,2));
	groupeTuteur.add(new Tuteur("tuteur_","Pénélope","Renault",13.2,2));
	groupeTuteur.add(new Tuteur("tuteur_","Nicolas","Roche",13.1,2));
	groupeTuteur.add(new Tuteur("tuteur_","Juliette","Traore",9.8,2));
	groupeTuteur.add(new Tuteur("tuteur_","Sophie","Vallee",15.8,2));
	groupeTuteur.add(new Tuteur("tuteur_","Edouard","Auger",13.9,3));
	groupeTuteur.add(new Tuteur("tuteur_","Olivier","Gallet",11.3,3));
	groupeTuteur.add(new Tuteur("tuteur_","InÃ¨s","Gautier",9.3,3));
	groupeTuteur.add(new Tuteur("tuteur_","Franck","Hebert",11.9,3));
	groupeTuteur.add(new Tuteur("tuteur_","Daniel","Lefebvre",12.9,3));
	groupeTuteur.add(new Tuteur("tuteur_","Charles","Letellier",12.7,3));
	groupeTuteur.add(new Tuteur("tuteur_","Alex","Marchand",8.7,3));
	groupeTuteur.add(new Tuteur("tuteur_","Josette","Nicolas",12.5,3));
	groupeTuteur.add(new Tuteur("tuteur_","Paul","Sanchez",12.0,3));
	//groupeTuteur.add(new Tuteur("tuteur_","null","null",-40,2));

	for(int i=0; i<groupeTuteur.size(); i++) {
		g.ajouterSommet(groupeTuteur.get(i).getPrenom());
	}

	System.out.println("nb tutore : " + groupeTutore.size());
	System.out.println("nb tuteur : "+ groupeTuteur.size());

	List<String> tuteur=new ArrayList<String>();
	for(int i=0; i<groupeTuteur.size(); i++) {
		tuteur.add((groupeTuteur.get(i).getPrenom()));
	}

	List<String> tutore=new ArrayList<String>();
	for(int i=0; i<groupeTutore.size(); i++) {
		g.ajouterSommet(groupeTutore.get(i).getPrenom());
	}



	Tutorat cand = new Tutorat(groupeTuteur, groupeTutore);
	System.out.println(cand);


	CalculAffectation<String> c = new CalculAffectation<>(g, tuteur, tutore);
	System.out.println(c.getCout());
	System.out.println(c.getAffectation());
	for (int i=0;i<=(groupeTutore.size()+groupeTuteur.size());i++) {
		System.out.println(c.getAffectation().get(i).getExtremite1()+ " doit se mettre avec "+c.getAffectation().get(i).getExtremite2());
		//System.out.println("Cette tâche prendra "+g.getPoids(c.getAffectation().get(i).getExtremite1(),g.getAffectation().get(i).getExtremite2()) +" jours ");
	}
}
}
