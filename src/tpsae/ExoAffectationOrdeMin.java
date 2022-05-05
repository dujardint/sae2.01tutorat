package tpsae;

import java.util.ArrayList;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;

public class ExoAffectationOrdeMin{
	public static void main(String [] args){
		//on a pris le binome qui a le min gagne

		double [][] counts = new double [][]{
//coeff 1ere annee = 10 et 2e annee =1
			new double [] {131,75,108,13.7,11.1},
			new double [] {64,8,41,7,4.4},
			new double [] {35,315,12,4.1,1.5},
			new double [] {6,750,255,1.2,21}, 
			new double [] {600,1440,945,51,90},
		};

		ArrayList<String> tutorer = new ArrayList<>();
		tutorer.add("huge");
		tutorer.add("Madeleine");
		tutorer.add("Claude");
		tutorer.add("Sabine");
		tutorer.add("vide");

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
