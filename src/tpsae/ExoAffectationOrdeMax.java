package tpsae;

import java.util.ArrayList;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;

public class ExoAffectationOrdeMax{
	public static void main(String [] args){
		//on a pris le binome qui a le max gagne
		
		double [][] counts = new double [][]{
		

			new double [] {67,39,55.5,70,57},
			new double [] {83.75,55.75,72.25,86.75,73.75},
			new double [] {91,63,79.5,94,81},
			new double [] {98.25,70.25,86.75,101.25,88.25}, 
			new double [] {109.75,81.75,98.25,112.75,99.75}};
			

			ArrayList<String> tutorer = new ArrayList<>();
			tutorer.add("huge");
			tutorer.add("Madeleine");
			tutorer.add("Claude");
			tutorer.add("Sabine");
			tutorer.add("Lucas");


			ArrayList<String> tuteur = new ArrayList<>();
			tuteur.add("François");
			tuteur.add("Josphe");
			tuteur.add("Martin");
			tuteur.add("Edouard");
			tuteur.add("Olivier");

			
			GrapheNonOrienteValue<String> graphe = new GrapheNonOrienteValue<>();
			for(String p : tutorer){
				graphe.ajouterSommet(p);
			}

			for(String t: tuteur){
				graphe.ajouterSommet(t);
			}

			for(int ip=0; ip<5; ip++){
				for(int it=0; it<5; it++){
					graphe.ajouterArete(tutorer.get(ip), tuteur.get(it), counts[ip][it]);
				}
			}

			//calcul de l'affectation cout minimal
			CalculAffectation<String> aff = new CalculAffectation<>(graphe,tutorer,tuteur);

			//affichage de l'affectation dans ordre alphabetique
			System.out.println(aff.getAffectation());
			System.out.println(aff.getCout());

	}
}
