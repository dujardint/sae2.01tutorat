package tpsae;

import fr.ulille.but.sae2_02.graphes.CalculAffectation ;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue ;
import java.util.* ;

public class Affecta {
 public static void main(String[] args) {
	GrapheNonOrienteValue<String> g = new GrapheNonOrienteValue<String>();
	System.out.println("Affichage de la liste vide : ");
	System.out.println(g);
	
	//1ÈRES ANNÉES
	g.ajouterSommet("Loic");
	g.ajouterSommet("Madeleine");
	g.ajouterSommet("Claude");
	g.ajouterSommet("Sabine");
	g.ajouterSommet("null");
	
	//3E ANNEE
	g.ajouterSommet("Edouart");
	g.ajouterSommet("Olivier");
	
	//2E ANNEE
	g.ajouterSommet("Francois");
	g.ajouterSommet("Martin");
	g.ajouterSommet("Joseph");

	
	//SOMMETS
	g.ajouterArete("Loic", "Edouart", 7.05);
	g.ajouterArete("Loic", "Olivier", 5.75);
	g.ajouterArete("Loic", "Francois", 13.5);
	g.ajouterArete("Loic", "Martin", 7.9);
	g.ajouterArete("Loic", "Joseph", 11.2);
	
	g.ajouterArete("Madeleine", "Edouart", 10.4);
	g.ajouterArete("Madeleine", "Olivier", 9.7);
	g.ajouterArete("Madeleine", "Francois", 20.2);
	g.ajouterArete("Madeleine", "Martin", 14.6);
	g.ajouterArete("Madeleine", "Joseph", 17.9);
	
	g.ajouterArete("Claude", "Edouart", 11.85);
	g.ajouterArete("Claude", "Olivier", 10.55);
	g.ajouterArete("Claude", "Francois", 23.1);
	g.ajouterArete("Claude", "Martin", 17.5);
	g.ajouterArete("Claude", "Joseph", 20.8);
	
	g.ajouterArete("Sabine", "Edouart", 13.3);
	g.ajouterArete("Sabine", "Olivier", 12);
	g.ajouterArete("Sabine", "Francois", 26);
	g.ajouterArete("Sabine", "Martin", 20.4);
	g.ajouterArete("Sabine", "Joseph", 23.7);
	
	g.ajouterArete("null", "Edouart", 1000.1);
	g.ajouterArete("null", "Olivier", 1000.2);
	g.ajouterArete("null", "Francois", 1000.3);
	g.ajouterArete("null", "Martin", 1000.4);
	g.ajouterArete("null", "Joseph", 1000.5);

	System.out.println(g.toString());
	
	List<String> tutore=new ArrayList<String>();
	tutore.add("Loic");tutore.add("Madeleine");tutore.add("Claude");tutore.add("Sabine");tutore.add("null");
	System.out.println("tutoré : " + tutore);
	
	List<String> partie2=new ArrayList<String>();
	partie2.add("Francois");partie2.add("Joseph");partie2.add("Martin");partie2.add("Edouart");partie2.add("Olivier");
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