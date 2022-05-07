package tpsae;

import fr.ulille.but.sae2_02.graphes.CalculAffectation ;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue ;
import java.util.* ;

public class AffectationV2 {
 public static void main(String[] args) {
	GrapheNonOrienteValue<String> g = new GrapheNonOrienteValue<String>();
	System.out.println("Affichage de la liste vide : ");
	System.out.println("tu m'entends???");
	System.out.println(g);
	
	//1ÃˆRES ANNÃ‰ES
	g.ajouterSommet("Loïc"); //0,2 de moyenne
	g.ajouterSommet("Madeleine"); //6,9
	g.ajouterSommet("Claude"); //17,3
	g.ajouterSommet("Sabine"); // 12,7
	g.ajouterSommet("NULL");
	
	//3E ANNEE
	g.ajouterSommet("Edouart");
	g.ajouterSommet("Olivier");
	
	//2E ANNEE
	g.ajouterSommet("Francois");
	g.ajouterSommet("Martin");
	g.ajouterSommet("Joseph");
	
	//ARRETE = POIDS DANS LE TABLEAU AVEC CHACUN DES COUPLES POSSIBLES
	g.ajouterArete("Edouard", "Loïc", 7.05);
	g.ajouterArete("Edouard", "Madeleine", 10.4);
	g.ajouterArete("Edouard", "Claude", 11.85);
	g.ajouterArete("Edouard", "Sabine", 13.3);
	g.ajouterArete("Edouard", "NULL", 9999);
	
	g.ajouterArete("Olivier", "Loïc", 5.75);
	g.ajouterArete("Olivier", "Madeleine", 9.1);
	g.ajouterArete("Olivier", "Claude", 10.55);
	g.ajouterArete("Olivier", "Sabine", 12);
	g.ajouterArete("Olivier", "NULL", 9999);
	
	g.ajouterArete("François", "Loïc", 13.5);
	g.ajouterArete("François", "Madeleine", 20.2);
	g.ajouterArete("François", "Claude", 23.1);
	g.ajouterArete("François", "Sabine", 26);
	g.ajouterArete("François", "NULL", 9999);
	
	g.ajouterArete("Martin", "Loïc", 7.9);
	g.ajouterArete("Martin", "Madeleine", 14.6);
	g.ajouterArete("Martin", "Claude", 17.5);
	g.ajouterArete("Martin", "Sabine", 20.4);
	g.ajouterArete("Martin", "NULL", 9999);
	
	
	g.ajouterArete("Joseph", "Loïc", 11.2);
	g.ajouterArete("Joseph", "Madeleine", 17.9);
	g.ajouterArete("Joseph", "Claude", 20.8);
	g.ajouterArete("Joseph", "Sabine", 23.7);
	g.ajouterArete("Joseph", "NULL", 9999);
	

	//System.out.println(g.toString());
	
	List<String> partie1=new ArrayList<String>();
	partie1.add("Loïc");partie1.add("Madeleine");partie1.add("Claude");partie1.add("Sabine");partie1.add("NULL");
	System.out.println("tutorÃ© : " + partie1);
	
	List<String> partie2=new ArrayList<String>();
	partie2.add("Edouart");partie2.add("Olivier");partie2.add("Francois");partie2.add("Martin");partie2.add("Joseph");
	System.out.println("tuteurs" + partie2);
	
	CalculAffectation<String> c = new CalculAffectation<>(g, partie1, partie2);
	System.out.println(c.getCout());
	System.out.println(c.getAffectation());
	for (int i=0;i<=4;i++) {
		System.out.println(c.getAffectation().get(i).getExtremite1()+ " doit se mettre avec "+c.getAffectation().get(i).getExtremite2());
		//System.out.println("Cette tÃ¢che prendra "+g.getPoids(c.getAffectation().get(i).getExtremite1(),g.getAffectation().get(i).getExtremite2()) +" jours ");
	}
 }
}