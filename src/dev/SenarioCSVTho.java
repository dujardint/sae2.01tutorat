package dev;

import fr.ulille.but.sae2_02.graphes.CalculAffectation ;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue ;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.* ;
import appli.Tuteur;
import appli.Tutorat;
import appli.Tutore;

public class SenarioCSVTho {

	static String myPath =  System.getProperty("user.dir") + File.separator + "res" + File.separator;
	static String sourceFileTutore = "tutore.csv";
	static String sourceFileTuteur = "tuteur.csv";

	static String tuteurCSV = ""+ myPath +sourceFileTuteur;
	static String tutoreCSV = ""+ myPath +sourceFileTutore;


	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean demande = true;

		while (demande) {
			ImportCSV.g=new GrapheNonOrienteValue<String>();
			//writeToFiles()
			Tutorat etudiants = new Tutorat(ImportCSV.ExtractiongroupeTuteurCSV(tuteurCSV), ImportCSV.ExtractiongroupeTutoreCSV(tutoreCSV));

			System.out.println("\nBienvenue dans l'application officielle de tutorat !\nQue voulez-vous faire ? ");
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
				String affectation = Tutorat.calculAffectation();
				System.out.println(affectation);

				System.out.println("Est ce que l'affectation vous va ? (=> o ou n)");
				String convaincu = scan.nextLine();
				if(convaincu.equals("o") || (convaincu.equals("oui")) || (convaincu.equals("OUI"))) {
					demande=false;
				}
			}
			else if(choix.equals("1")) {
				System.out.println(etudiants.toString());

			}
			else if(choix.equals("2")){
				System.out.println(etudiants.tailleEgale());

			}
			else if(choix.equals("3")) {
				etudiants.ajoutCandidat(true,1);
				//METTRE A JOUR LE CSV QUAND ON AJOUTE OU SUPPRIME UN CANDIDAT !

			}
			else if(choix.equals("4")){
				etudiants.supprimeCandidat();
			}
			else if(choix.equals("5")) {
				etudiants.vuTuteur();
			}
			else if(choix.equals("6")) {
				etudiants.vuTutore();

			}else if(choix.equals("7")) {
				System.out.println("Vous avez demander la femeture du programme !");
				demande=false;
			}
			else {
				System.out.println("Nous n'avons pas compris votre choix, veuillez ressayer merci !");
			}
		}
		System.out.println("Merci d'avoir utilisé notre merveilleuse application !");
		scan.close();
	}




}