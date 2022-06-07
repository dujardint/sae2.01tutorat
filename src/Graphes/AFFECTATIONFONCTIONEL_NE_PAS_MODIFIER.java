package Graphes;

import fr.ulille.but.sae2_02.graphes.CalculAffectation ;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue ;
import java.util.* ;

import appli.Tuteur;
import appli.Tutorat;
import dev.ImportCSV;

public class AFFECTATIONFONCTIONEL_NE_PAS_MODIFIER {
	public static void main(String[] args) {
		GrapheNonOrienteValue<String> g = new GrapheNonOrienteValue<String>();
		System.out.println("Graphe permettant l'affectation normale avec les Donnï¿½esPourTester : ");
		//System.out.println(g);

		Tutorat tutorat = new Tutorat(ImportCSV.readFileTuteur(ImportCSV.FILEPATH_TUTEUR),
				ImportCSV.readFileTutore(ImportCSV.FILEPATH_TUTORE));
		
		for(int i=0; i<tutorat.getListTutore().size(); i++) {
			g.ajouterSommet(tutorat.getListTutore().get(i).getPrenomNom());
			System.out.println(tutorat.getListTutore().get(i).getPrenomNom() + " ajouter a la liste sommet");
		}
		
		for(int i=0; i<tutorat.getListTuteur().size(); i++) {
			g.ajouterSommet(tutorat.getListTuteur().get(i).getPrenomNom());
			System.out.println(tutorat.getListTuteur().get(i).getPrenomNom() + " ajouter a la liste sommet");
		}

		//PERMET DE VERIFIER SI LES 2 LISTES ONT LE MEME NOMBRES !!!!!!!!!!!
		System.out.println("\ntaille tuteur " + tutorat.getListTuteur().size());
		System.out.println("taille tutore " + tutorat.getListTutore().size()+"\n");

		//ARRETES
		for(int i=0; i<tutorat.getListTuteur().size(); i++) {
			for(int j=0; j<tutorat.getListTutore().size(); j++) {
				g.ajouterArete(tutorat.getListTuteur().get(i).getPrenomNom(), tutorat.getListTutore().get(j).getPrenomNom(), Tutorat.calculDistance(tutorat.getListTuteur().get(i), tutorat.getListTutore().get(j)));
				System.out.println("" + tutorat.getListTuteur().get(i).getPrenomNom() + " " + tutorat.getListTutore().get(j).getPrenomNom() + " distance : " + Tutorat.calculDistance(tutorat.getListTuteur().get(i), tutorat.getListTutore().get(j)));
			}
		}

		//System.out.println(g.toString());

		System.out.println();
		List<String> tuteurPrenomNom=new ArrayList<String>();
		for(int i=0; i < tutorat.getListTuteur().size(); i++) {
			tuteurPrenomNom.add(tutorat.getListTuteur().get(i).getPrenomNom());
			System.out.println("liste tuteur : " + tuteurPrenomNom.get(i));
		}
		System.out.println();
		ArrayList<String> tutorePrenomNom=new ArrayList<String>();
		for(int i=0; i < tutorat.getListTutore().size(); i++) {
			tutorePrenomNom.add(tutorat.getListTutore().get(i).getPrenomNom());
			System.out.println("liste tutore : "+tutorePrenomNom.get(i));
		}

		System.out.println();
		CalculAffectation<String> c = new CalculAffectation<>(g, tutorePrenomNom, tuteurPrenomNom);
		System.out.println("le cout minimal est de : " + c.getCout());
		System.out.println(c.getAffectation());
		for (int i=0; i < tutorat.getListTutore().size(); i++) {
			System.out.println(c.getAffectation().get(i).getExtremite1()+ " doit se mettre avec "+c.getAffectation().get(i).getExtremite2());
			//System.out.println("Cette tÃ¢che prendra "+g.getPoids(c.getAffectation().get(i).getExtremite1(),g.getAffectation().get(i).getExtremite2()) +" jours ");
		}

		System.out.println("\nL'affectation est terminée");
	}
}
