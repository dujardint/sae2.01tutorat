package Graphes;

import fr.ulille.but.sae2_02.graphes.CalculAffectation ;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue ;
import java.util.* ;

public class AffectaEviter {
 public static void main(String[] args) {
	GrapheNonOrienteValue<String> g = new GrapheNonOrienteValue<String>();
	System.out.println("Graphe prmettant d'eviter des candidats : ");
	System.out.println(g);
	
	//1ÈRES ANNÉES
	g.ajouterSommet("Loic");
	g.ajouterSommet("Madeleine");
	g.ajouterSommet("Claude");
	g.ajouterSommet("Sabine");
	g.ajouterSommet("null");
	g.ajouterSommet("vide1");
	
	//3E ANNEE
	g.ajouterSommet("Edouart");
	g.ajouterSommet("Olivier");
	
	//2E ANNEE
	g.ajouterSommet("Francois");
	g.ajouterSommet("Martin");
	g.ajouterSommet("Joseph");
	g.ajouterSommet("vide2");

	
	//SOMMETS
	g.ajouterArete("Loic", "Edouart", 7.05 );
	g.ajouterArete("Loic", "Olivier", 5.75);
	g.ajouterArete("Loic", "Francois", 13.5);
	g.ajouterArete("Loic", "Martin", 7.9);
	g.ajouterArete("Loic", "Joseph", 11.2);
	g.ajouterArete("Loic", "vide2", 1000);
	
	g.ajouterArete("Madeleine", "Edouart", 10.4);
	g.ajouterArete("Madeleine", "Olivier", 9.1);
	g.ajouterArete("Madeleine", "Francois", 20.2);
	g.ajouterArete("Madeleine", "Martin", 14.6);
	g.ajouterArete("Madeleine", "Joseph", 17.9);
	g.ajouterArete("Madeleine", "vide2", 1000);
	
	g.ajouterArete("Claude", "Edouart", 11.85);
	g.ajouterArete("Claude", "Olivier", 10.55);
	g.ajouterArete("Claude", "Francois", 23.1);
	g.ajouterArete("Claude", "Martin", 17.5);
	g.ajouterArete("Claude", "Joseph", 20.8);
	g.ajouterArete("Claude", "vide2", 1000);
	
	g.ajouterArete("Sabine", "Edouart", 13.3);
	g.ajouterArete("Sabine", "Olivier", 12);
	g.ajouterArete("Sabine", "Francois", 26);
	g.ajouterArete("Sabine", "Martin", 20.4);
	g.ajouterArete("Sabine", "Joseph", 23.7);
	g.ajouterArete("Sabine", "vide2", 0);
	
	//onajoute null pour respecter la bipartie pour l'algo
	g.ajouterArete("null", "Edouart", 1000);
	g.ajouterArete("null", "Olivier", 1000);
	g.ajouterArete("null", "Francois", 1000);
	g.ajouterArete("null", "Martin", 1000);
	g.ajouterArete("null", "Joseph", 1000);
	g.ajouterArete("null", "vide2", 1000);
	
	g.ajouterArete("vide1", "Edouart", 1000);
	g.ajouterArete("vide1", "Olivier", 1000);
	g.ajouterArete("vide1", "Francois", 1000);
	g.ajouterArete("vide1", "Martin", 1000);
	g.ajouterArete("vide1", "Joseph", 0);
	g.ajouterArete("vide1", "vide2", 1000);

	System.out.println(g.toString());
	
	List<String> tutore=new ArrayList<String>();
	tutore.add("Loic");tutore.add("Madeleine");tutore.add("Claude");tutore.add("Sabine");tutore.add("vide1");tutore.add("null");
	System.out.println("tutoré : " + tutore);
	
	List<String> partie2=new ArrayList<String>();
	partie2.add("Francois");partie2.add("Joseph");partie2.add("Martin");partie2.add("Edouart");partie2.add("Olivier");partie2.add("vide2");
	System.out.println("tuteurs" + partie2);
	
	CalculAffectation<String> c = new CalculAffectation<>(g, tutore, partie2);
	System.out.println(c.getCout());
	System.out.println(c.getAffectation());
	for (int i=0;i<=4;i++) {
		System.out.println(c.getAffectation().get(i).getExtremite1()+ " doit se mettre avec "+c.getAffectation().get(i).getExtremite2());
		//System.out.println("Cette tâche prendra "+g.getPoids(c.getAffectation().get(i).getExtremite1(),g.getAffectation().get(i).getExtremite2()) +" jours ");
	}
 }
}