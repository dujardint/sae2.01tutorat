package dev;

import fr.ulille.but.sae2_02.graphes.CalculAffectation ;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue ;
import java.util.* ;

import appli.Tuteur;
import appli.Tutorat;
import appli.Tutore;

public class Scenario {

	public static void main(String[] args) {
		GrapheNonOrienteValue<String> g = new GrapheNonOrienteValue<String>();
		//System.out.println(g);
		//1ÈRES ANNÉES
		// On initialise le groupe des tutorés
		Tutorat tutorat = new Tutorat(ImportCSV1.readFileTuteur(ImportCSV1.FILEPATH_TUTEUR),
				ImportCSV1.readFileTutore(ImportCSV1.FILEPATH_TUTORE));

		Scanner scan = new Scanner(System.in);

		boolean demande = true;

		while (demande) {
			System.out.println("\nBienvenue dans l'application officielle de tutorat !\nQue voulez-vous faire ? ");

			System.out.println(("0 pour quitter\n1 pour v�rifier la taille\n2 pour supprimer un candidat\n3 pour ajouter un candidat (tuteur ou tutore) \n4 pour calculer \n5 pour voir un tuteur \n6 pour voir un tutor�"));
			String choix = scan.nextLine();
			if(choix.equals("0")) {
				System.out.println("Vous avez demander la fermeture du programme.");
				demande=false;
			}
			else if(choix.equals("1")) {
				System.out.println(tutorat.getListTuteur().size() + " tutor�s : " + tutorat.getListTutore().size());
				System.out.println(tutorat.tailleEgale());
			}
			else if(choix.equals("2")){
				tutorat.supprimeCandidat();
			}
			else if(choix.equals("3")) {
				tutorat.ajoutCandidat(true, 1);
			}
			else if(choix.equals("4")){
				//demande=false;
				System.out.println("Vous avez decidé de ne rien modifier ! l'algo va s'executer !");

				for(int i=0; i<tutorat.getListTutore().size(); i++) {
					g.ajouterSommet(tutorat.getListTutore().get(i).getPrenomNom());
					//System.out.println(tutorat.getListTutore()get(i).getPrenomNom() + " ajouter a la liste sommet");
				}

				for(int i=0; i<tutorat.getListTuteur().size(); i++) {
					g.ajouterSommet(tutorat.getListTuteur().get(i).getPrenomNom());
					//	System.out.println(tutorat.getListTuteur().get(i).getPrenomNom() + " ajouter a la liste sommet");
				}

				//ARRETES
				for(int i=0; i<tutorat.getListTuteur().size(); i++) {
					for(int j=0; j<tutorat.getListTutore().size(); j++) {
						g.ajouterArete(tutorat.getListTuteur().get(i).getPrenomNom(), tutorat.getListTutore().get(j).getPrenomNom(), Tutorat.calculDistance(tutorat.getListTuteur().get(i), tutorat.getListTutore().get(j)));
						//System.out.println("" + tutorat.getListTuteur().get(i).getPrenomNom() + " " +  tutorat.getListTutore()get(j).getPrenomNom() + " distance : " + Tutorat.calculDistance(tutorat.getListTuteur().get(i), tutorat.getListTutore()get(j)));
					}
				}

				//System.out.println(g.toString());

				System.out.println();
				List<String> tuteurPrenomNom=new ArrayList<String>();
				for(int i=0; i<tutorat.getListTuteur().size(); i++) {
					tuteurPrenomNom.add(tutorat.getListTuteur().get(i).getPrenomNom());
					System.out.println("liste tuteur : " + tuteurPrenomNom.get(i));
				}
				System.out.println();
				ArrayList<String> tutorePrenomNom=new ArrayList<String>();
				for(int i=0; i<tutorat.getListTutore().size(); i++) {
					tutorePrenomNom.add(tutorat.getListTutore().get(i).getPrenomNom());
					System.out.println("liste tutore : "+tutorePrenomNom.get(i));
				}

				System.out.println();
				CalculAffectation<String> c = new CalculAffectation<>(g, tutorePrenomNom, tuteurPrenomNom);
				//	System.out.println("le cout minimal est de : " + c.getCout());
				//System.out.println(c.getAffectation());
				for (int i=0;i<tutorat.getListTutore().size();i++) {
					System.out.println(c.getAffectation().get(i).getExtremite1()+ " doit se mettre avec "+c.getAffectation().get(i).getExtremite2());
					String res = c.getAffectation().get(i).getExtremite1()+ " doit se mettre avec "+c.getAffectation().get(i).getExtremite2();
					
					//System.out.println("Cette tâche prendra "+g.getPoids(c.getAffectation().get(i).getExtremite1(),g.getAffectation().get(i).getExtremite2()) +" jours ");
				}

				System.out.println("\nL'affectation est terminé !!");


				//System.out.println("triage des tuteur : \n" + etudiants.triTuteur(tutorat.getListTuteur()));
				//System.out.println("triage des tutore : \n" + etudiants.triTutore(groupeTutore));
			}
			else if(choix.equals("5")) {
				tutorat.vuTuteur();
			}
			else if(choix.equals("6")) {
				tutorat.vuTutore();
				
			}else if(choix.equals("7")) {
				
			}
			
			else {
				System.out.println("Nous n'avons pas compris votre choix, veuillez ressayer merci !");
			}
		}
		scan.close();
	}
}
