package tpsae;

import fr.ulille.but.sae2_02.graphes.CalculAffectation ;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue ;
import java.util.* ;

public class Affectation {
 public static void main(String[] args) {
	GrapheNonOrienteValue<String> g = new GrapheNonOrienteValue<String>();
	System.out.println(g);
	g.ajouterSommet("A");
	g.ajouterSommet("B");
	g.ajouterSommet("C");
	g.ajouterSommet("D");
	g.ajouterSommet("W");
	g.ajouterSommet("X");
	g.ajouterSommet("Y");
	g.ajouterSommet("Z");
	g.ajouterArete("A", "W", 13);
	g.ajouterArete("A", "X", 4);
	g.ajouterArete("A", "Y", 7);
	g.ajouterArete("A", "Z", 6);
	g.ajouterArete("B", "W", 1);
	g.ajouterArete("B", "X", 11);
	g.ajouterArete("B", "Y", 5);
	g.ajouterArete("B", "Z", 4);
	g.ajouterArete("C", "W", 6);
	g.ajouterArete("C", "X", 7);
	g.ajouterArete("C", "Y", 2);
	g.ajouterArete("C", "Z", 8);
	g.ajouterArete("D", "W", 1);
	g.ajouterArete("D", "X", 3);
	g.ajouterArete("D", "Y", 5);
	g.ajouterArete("D", "Z", 9);
	System.out.println(g);
	
	List<String> partie1=new ArrayList<String>();
	partie1.add("A");partie1.add("B");partie1.add("C");partie1.add("D");
	System.out.println(partie1);
	
	List<String> partie2=new ArrayList<String>();
	partie2.add("W");partie2.add("X");partie2.add("Y");partie2.add("Z");
	System.out.println(partie2);
	
	CalculAffectation<String> c = new CalculAffectation<>(g, partie1, partie2);
	System.out.println(c.getCout());
	System.out.println(c.getAffectation());
	for (int i=0;i<4;i++) {
		System.out.println(c.getAffectation().get(i).getExtremite1()+ " doit effectuer la tâche "+c.getAffectation().get(i).getExtremite2());
		//System.out.println("Cette tâche prendra "+g.getPoids(c.getAffectation().get(i).getExtremite1(),g.getAffectation().get(i).getExtremite2()) +" jours ");
	}
 }
}