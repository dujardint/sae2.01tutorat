package dev;

import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue ;

import java.util.* ;
import appli.Tutorat;

public class SenarioCSVTho {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean demande = true;

		while (demande) {
			ImportCSV1.g=new GrapheNonOrienteValue<String>();

			Tutorat etudiants = new Tutorat(ImportCSV1.readFileTuteur(ImportCSV1.FILEPATH_TUTEUR),
					ImportCSV1.readFileTutore(ImportCSV1.FILEPATH_TUTORE));	
			
			System.out.println("Bienvenue dans l'application officielle de tutorat !\nQue voulez-vous faire ? ");
			System.out.println(
					("0 pour calculer\n"
							+ "1 pour afficher la liste des etudiants inscrits\n"
							+ "2 pour vérifier la taille\n"
							+ "3 pour ajouter un candidat (tuteur ou tutore) \n"
							+ "4 pour supprimer un candidat\n"
							+ "5 pour rechercher un tuteur \n"
							+ "6 pour rechercher un tutoré\n"
							+ "7 pour quitter"));
			System.out.print("Votre choix : ");
			String choix = scan.nextLine();
			System.out.println();
			if(choix.equals("0")) {
				System.out.println("Vous avez decidÃ© de ne rien modifier ! l'algo va s'executer !");
				CalculAffectation<String> affectations = etudiants.calculAffectation();
				

				System.out.println("Est ce que l'affectation vous va ? (=> o ou n)");
				String convaincu = scan.nextLine();
				if(convaincu.equals("o") || (convaincu.equals("oui")) || (convaincu.equals("OUI"))) {
					demande=false;
				}
			} else if(choix.equals("1")) {
				System.out.println(etudiants.toString());

			} else if(choix.equals("2")){
				System.out.println("Nombre de tuteurs : " + etudiants.getListTuteur().size());
				System.out.println("Nombre de tutores : " + etudiants.getListTutore().size());
				etudiants.purgeCandidatFactices();
				if (etudiants.getListTuteur().size() != etudiants.getListTutore().size()) {
					System.out.println("Les listes Tuteurs / Tutorés ne sont pas de taille égale. Ajout d'un candidat factice afin de pouvoir déterminer les couples possibles.");
					etudiants.tailleEgale();
				} else {
					System.out.println("Les listes Tuteurs / Tutorés sont de taille égale. Nous pouvons déterminer les couples possibles.");
				}
			} else if(choix.equals("3")) {
				etudiants.ajoutCandidat(true,1);
				ImportCSV1.writeToFiles(etudiants.getListTuteur(), etudiants.getListTutore());	
			} else if(choix.equals("4")){
				etudiants.supprimeCandidat();
				ImportCSV1.writeToFiles(etudiants.getListTuteur(), etudiants.getListTutore());	
				System.out.println("Le candidat est bien supprimé.");
			}
			else if(choix.equals("5")) {
				etudiants.vuTuteur();
			}
			else if(choix.equals("6")) {
				etudiants.vuTutore();

			}else if(choix.equals("7")) {
				System.out.println("Vous avez demandé la fermeture du programme.");
				demande=false;
			}
			else {
				System.out.println("Nous n'avons pas compris votre choix, veuillez reessayer.");
			}
		}
		System.out.println("Merci d'avoir utilisé notre merveilleuse application.");
		scan.close();
	}




}