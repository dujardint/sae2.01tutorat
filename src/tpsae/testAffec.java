package tpsae;

import fr.ulille.but.sae2_02.graphes.CalculAffectation ;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue ;
import java.util.* ;

public class testAffec {
 public static void main(String[] args) {
	GrapheNonOrienteValue<String> g = new GrapheNonOrienteValue<String>();
	System.out.println("Affichage de la liste vide : ");
	System.out.println("tu m'entends???");
	System.out.println(g);
	
	g.ajouterSommet("a");
	g.ajouterSommet("b");
	g.ajouterSommet("c");
	g.ajouterSommet("d");
	
	g.ajouterArete("a", "b", 1);
	g.ajouterArete("a", "c", 2);
	g.ajouterArete("a", "d", 3);
	
	g.ajouterArete("b", "a", 4);
	g.ajouterArete("b", "c", 5);
	g.ajouterArete("b", "d", 6);
	
	g.ajouterArete("c", "a", 7);
	g.ajouterArete("c", "b", 8);
	g.ajouterArete("c", "d", 9);
	
	g.ajouterArete("d", "a", 10);
	g.ajouterArete("d", "b", 11);
	g.ajouterArete("d", "c", 12);
	
	List<String> partie1=new ArrayList<String>();
	partie1.add("a");partie1.add("d");
	System.out.println("tutoré : " + partie1);
	
	List<String> partie2=new ArrayList<String>();
	partie2.add("b");partie2.add("c");
	System.out.println("tuteurs" + partie2);
	
	CalculAffectation<String> c = new CalculAffectation<>(g, partie1, partie2);
	System.out.println(c.getCout());
	System.out.println(c.getAffectation());
	for (int i=0;i<3;i++) {
		System.out.println(c.getAffectation().get(i).getExtremite1()+ " doit se mettre avec "+c.getAffectation().get(i).getExtremite2());
		//System.out.println("Cette tâche prendra "+g.getPoids(c.getAffectation().get(i).getExtremite1(),g.getAffectation().get(i).getExtremite2()) +" jours ");
	}
 }
}
