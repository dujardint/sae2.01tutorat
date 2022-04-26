package Graphes;

import java.util.ArrayList;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;

public class Affectation{
    public static void main(String [] args){
        double [][] counts = new double [][]{
            new double [] {0,0,2,1}, //Anne
            new double [] {1,0,1,0}, //Bob
            new double [] {0,2,2,0}, //Carl
            new double [] {1,42,2,2}}; //Anne

        ArrayList<String> personnes = new ArrayList<>();
        personnes.add("Anne");
        personnes.add("Bob");
        personnes.add("Carl");
        personnes.add("Diana");

        ArrayList<String> taches = new ArrayList<>();
        taches.add("animaux");
        taches.add("ranger");
        taches.add("courses");
        taches.add("valise");

        GrapheNonOrienteValue<String> graphe = new GrapheNonOrienteValue<>();
        for(String p : personnes){
            graphe.ajouterSommet(p);
        }

        for(String t: taches){
            graphe.ajouterSommet(t);
        }

        for(int ip=0; ip<4; ip++){
            for(int it=0; it<4; it++){
                graphe.ajouterArete(personnes.get(ip), taches.get(it), counts[ip][it]);
            }
        }

        //calcul de l'affectation cout minimal
        CalculAffectation<String> aff = new CalculAffectation<>(graphe,personnes,taches);

        //affichage de l'affectation dans ordre alphabetique
        System.out.println(aff.getAffectation());
        System.out.println(aff.getCout());

    }
}