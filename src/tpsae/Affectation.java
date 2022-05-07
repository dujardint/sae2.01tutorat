package tpsae;

import fr.ulille.but.sae2_02.graphes.CalculAffectation ;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue ;
import java.util.* ;

public class Affectation {
 public static void main(String[] args) {
	GrapheNonOrienteValue<String> g = new GrapheNonOrienteValue<String>();
	System.out.println("Affichage de la liste vide : ");
	System.out.println(g);
	
	//1ÈRES ANNÉES
	g.ajouterSommet("Huges");
	g.ajouterSommet("Madeleine");
	g.ajouterSommet("Claude");
	g.ajouterSommet("Sabine");
	g.ajouterSommet("Lucas");
	
	
	//2E ANNEE
	g.ajouterSommet("Francois");
	g.ajouterSommet("Joseph");
	g.ajouterSommet("Martin");
	
	
	//3E ANNEE
	g.ajouterSommet("Edouart");
	g.ajouterSommet("Olivier");
	
	
	//SOMMETS
	g.ajouterArete("Huges", "Olivier", 11);
	g.ajouterArete("Huges", "Edouart", 13);
	g.ajouterArete("Huges", "Martin", 108);
	g.ajouterArete("Huges", "Joseph", 75);
	g.ajouterArete("Huges", "Francois", 131);
	
	g.ajouterArete("Madeleine", "Olivier", 4);
	g.ajouterArete("Madeleine", "Edouart", 7);
	g.ajouterArete("Madeleine", "Martin", 41);
	g.ajouterArete("Madeleine", "Joseph", 8);
	g.ajouterArete("Madeleine", "Francois", 64);
	
	g.ajouterArete("Claude", "Olivier", 1);
	g.ajouterArete("Claude", "Edouart", 4);
	g.ajouterArete("Claude", "Martin", 12);
	g.ajouterArete("Claude", "Joseph", 210);
	g.ajouterArete("Claude", "Francois", 35);
	
	g.ajouterArete("Sabine", "Olivier", 14);
	g.ajouterArete("Sabine", "Edouart", 1);
	g.ajouterArete("Sabine", "Martin", 170);
	g.ajouterArete("Sabine", "Joseph", 50);
	g.ajouterArete("Sabine", "Francois", 6);
	
	g.ajouterArete("Lucas", "Olivier", 60);
	g.ajouterArete("Lucas", "Edouart", 34);
	g.ajouterArete("Lucas", "Martin", 630);
	g.ajouterArete("Lucas", "Joseph", 960);
	g.ajouterArete("Lucas", "Francois", 400);

	System.out.println(g.toString());
	
	List<String> partie1=new ArrayList<String>();
	partie1.add("Huges");partie1.add("Madeleine");partie1.add("Claude");partie1.add("Sabine");partie1.add("Lucas");
	System.out.println("tutoré : " + partie1);
	
	List<String> partie2=new ArrayList<String>();
	partie2.add("Francois");partie2.add("Joseph");partie2.add("Martin");partie2.add("Edouart");partie2.add("Olivier");
	System.out.println("tuteurs" + partie2);
	
	CalculAffectation<String> c = new CalculAffectation<>(g, partie1, partie2);
	System.out.println(c.getCout());
	System.out.println(c.getAffectation());
	for (int i=0;i<=4;i++) {
		System.out.println(c.getAffectation().get(i).getExtremite1()+ " doit se mettre avec "+c.getAffectation().get(i).getExtremite2());
		//System.out.println("Cette tâche prendra "+g.getPoids(c.getAffectation().get(i).getExtremite1(),g.getAffectation().get(i).getExtremite2()) +" jours ");
	}
 }
}