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
							+ "2 pour v�rifier la taille\n"
							+ "3 pour ajouter un candidat (tuteur ou tutore) \n"
							+ "4 pour supprimer un candidat\n"
							+ "5 pour rechercher un tuteur \n"
							+ "6 pour rechercher un tutor�\n"
							+ "7 pour quitter"));
			System.out.print("Votre choix : ");
			String choix = scan.nextLine();
			System.out.println();
			if(choix.equals("0")) {
				System.out.println("Vous avez decidé de ne rien modifier ! l'algo va s'executer !");
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
					System.out.println("Les listes Tuteurs / Tutor�s ne sont pas de taille �gale. Ajout d'un candidat factice afin de pouvoir d�terminer les couples possibles.");
					etudiants.tailleEgale();
				} else {
					System.out.println("Les listes Tuteurs / Tutor�s sont de taille �gale. Nous pouvons d�terminer les couples possibles.");
				}
			} else if(choix.equals("3")) {
				etudiants.ajoutCandidat(true,1);
				ImportCSV1.writeToFiles(etudiants.getListTuteur(), etudiants.getListTutore());	
			} else if(choix.equals("4")){
				etudiants.supprimeCandidat();
				ImportCSV1.writeToFiles(etudiants.getListTuteur(), etudiants.getListTutore());	
				System.out.println("Le candidat est bien supprim�.");
			}
			else if(choix.equals("5")) {
				etudiants.vuTuteur();
			}
			else if(choix.equals("6")) {
				etudiants.vuTutore();

			}else if(choix.equals("7")) {
				System.out.println("Vous avez demand� la fermeture du programme.");
				demande=false;
			}
			else {
				System.out.println("Nous n'avons pas compris votre choix, veuillez reessayer.");
			}
		}
		System.out.println("Merci d'avoir utilis� notre merveilleuse application.");
		scan.close();
	}




}