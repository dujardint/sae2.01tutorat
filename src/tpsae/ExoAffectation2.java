package tpsae;

import java.util.ArrayList;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;

public class ExoAffectation2{
	public static void main(String [] args){
		double [][] counts = new double [][]{

			new double [] {5,100,10,15,0},
			new double [] {5,50,10,100,0},
			new double [] {0,50,5,100,10}, //Madeleine
			new double [] {0,50,5,100,10}, //huge
			new double [] {0,50,5,100,10}}; //lucas
			//new double [] {0.8,-4.8,-1.5,-6.8,100,100,100}, //david
			//new double [] {2.8,-2.8,0.5,-4.8,100,100,100}}; //amelie


			ArrayList<String> tutorer = new ArrayList<>();
			tutorer.add("huge");
			tutorer.add("Madeleine");
			tutorer.add("Alexandria");
			tutorer.add("Sabine");
			tutorer.add("Lucas");
			//tutorer.add("lucas");
			//tutorer.add("david");
			//tutorer.add("amelie");

			ArrayList<String> tuteur = new ArrayList<>();
			tuteur.add("François");
			tuteur.add("Josphe");
			tuteur.add("Martin");
			
			tuteur.add("Therese");
			tuteur.add("maurice");
			//tuteur.add("nul");
			//tuteur.add("nul1");
			//tuteur.add("nul2");
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
