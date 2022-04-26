package tpsae;

import java.util.ArrayList;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;

public class ExoAffectation{
	public static void main(String [] args){
		double [][] counts = new double [][]{

			new double [] {3.5,-2.1,1.2,-3.7,100,100,100}, //claude
			new double [] {6.4,0.8,4.1,-1.2,100,100,100}, //Madeleine
			new double [] {0.6,-5,-1.7,-7,100,100,100}, //Saline
			new double [] {5.1,-0.5,2.8,-2.5,100,100,100}, //huge
			new double [] {-4,-9.6,-6.3,-11.6,100,100,100}, //lucas
			new double [] {0.8,-4.8,-1.5,-6.8,100,100,100}, //david
			new double [] {2.8,-2.8,0.5,-4.8,100,100,100}}; //amelie


			ArrayList<String> tutorer = new ArrayList<>();
			tutorer.add("Claude");
			tutorer.add("Madeleine");
			tutorer.add("Saline");
			tutorer.add("huge");
			tutorer.add("lucas");
			tutorer.add("david");
			tutorer.add("amelie");

			ArrayList<String> tuteur = new ArrayList<>();
			tuteur.add("François");
			tuteur.add("Josphe");
			tuteur.add("Martin");
			tuteur.add("maurice");
			tuteur.add("nul");
			tuteur.add("nul1");
			tuteur.add("nul2");
			GrapheNonOrienteValue<String> graphe = new GrapheNonOrienteValue<>();
			for(String p : tutorer){
				graphe.ajouterSommet(p);
			}

			for(String t: tuteur){
				graphe.ajouterSommet(t);
			}

			for(int ip=0; ip<7; ip++){
				for(int it=0; it<7; it++){
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
