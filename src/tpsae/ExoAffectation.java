package tpsae;

import java.util.ArrayList;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;

public class ExoAffectation{
	public static void main(String [] args){
		double [][] counts = new double [][]{

			new double [] {3.5,-2.1,1.2,-3.7,100,100,100}, //claude
			new double [] {6.4,0.8,4.1,-1.2,100,100,100}, //Madeleine


			ArrayList<String> tutorer = new ArrayList<>();
			tutorer.add("Claude");
			tutorer.add("Madeleine");


			ArrayList<String> tuteur = new ArrayList<>();
			tuteur.add("François");
			tuteur.add("Josphe");

			GrapheNonOrienteValue<String> graphe = new GrapheNonOrienteValue<>();
			for(String p : tutorer){
				graphe.ajouterSommet(p);
			}

			for(String t: tuteur){
				graphe.ajouterSommet(t);
			}

			for(int ip=0; ip<4; ip++){
				for(int it=0; it<4; it++){
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
