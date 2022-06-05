package dev;

import fr.ulille.but.sae2_02.graphes.CalculAffectation ;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue ;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.* ;
import appli.Tuteur;
import appli.Tutorat;
import appli.Tutore;

public class SenarioCSV {

	static String myPath =  System.getProperty("user.dir") + File.separator + "res" + File.separator;
	static String sourceFileTutore = "tutore.csv";
	static String sourceFileTuteur = "tuteur.csv";
	static GrapheNonOrienteValue<String> g;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean demande = true;

		while (demande) {
			g = new GrapheNonOrienteValue<String>();
			Tutorat etudiants = new Tutorat(ExtractiongroupeTuteurCSV(myPath+sourceFileTuteur), ExtractiongroupeTutoreCSV(myPath+sourceFileTutore));

			System.out.println("\nBienvenue dans l'application officielle de tutorat !\nQue voulez-vous faire ? ");
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

				ajouterSommetTutore(ExtractiongroupeTutoreCSV(myPath+sourceFileTutore));
				ajouterSommetTuteur(ExtractiongroupeTuteurCSV(myPath+sourceFileTuteur));
				ajouterArrete(ExtractiongroupeTutoreCSV(myPath+sourceFileTutore), ExtractiongroupeTuteurCSV(myPath+sourceFileTuteur));
				String affectation = calculAffectation(g, ExtractiongroupeTutoreCSV(myPath+sourceFileTutore), ExtractiongroupeTuteurCSV(myPath+sourceFileTuteur));

				System.out.println(affectation);
				//System.out.println("triage des tuteur : \n" + etudiants.triTuteur(groupeTuteur));
				//System.out.println("triage des tutore : \n" + etudiants.triTutore(groupeTutore));

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
				Tutorat.ajoutCandidat();
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
		System.out.println("Merci d'avoir utilis� notre merveilleuse application !");
		scan.close();
	}




	public static ArrayList<Tutore> ExtractiongroupeTutoreCSV(String sourceFile) {
		try {
			ArrayList<Tutore> groupeTutore = new ArrayList<>();
			Scanner scan = new Scanner(new File(sourceFile));
			int annee = 0;
			String nom;
			String prenom;
			double moyenne = 0;
			int abscence = 0;
			char motivation = 0;
			scan.nextLine();
			while (scan.hasNextLine()) {
				String[] mots = scan.nextLine().split(";");
				annee=Integer.parseInt(mots[0]);
				prenom=mots[1];
				nom=mots[2];
				moyenne=Double.parseDouble(mots[3]);
				abscence=Integer.parseInt(mots[4]);
				motivation=mots[5].charAt(0);
				groupeTutore.add(new Tutore("tutore_", prenom, nom, moyenne, abscence, annee, motivation));
			}
			return groupeTutore;
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier n'existe pas.");
			//}catch(IOException ioe) {
			//System.out.println("Problème à la fermeture du flux.");
		}
		return null;
	}



	public static ArrayList<Tuteur> ExtractiongroupeTuteurCSV(String sourceFile) {
		try {
			ArrayList<Tuteur> groupeTuteur = new ArrayList<>();
			Scanner scan = new Scanner(new File(sourceFile));
			int annee = 0;
			String nom;
			String prenom;
			double moyenne = 0;
			int abscence = 0;
			char motivation = 0;
			scan.nextLine();
			while (scan.hasNextLine()) {
				String[] mots = scan.nextLine().split(";");
				annee=Integer.parseInt(mots[0]);
				prenom=mots[1];
				nom=mots[2];
				moyenne=Double.parseDouble(mots[3]);
				abscence=Integer.parseInt(mots[4]);
				motivation=mots[5].charAt(0);
				groupeTuteur.add(new Tuteur("tuteur_", prenom, nom, moyenne, abscence, annee, motivation));
			}
			return groupeTuteur;
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier n'existe pas.");
			//}catch(IOException ioe) {
			//System.out.println("Problème à la fermeture du flux.");
		}
		return null;
	}


	public static void ajouterSommetTutore(ArrayList<Tutore> grTutore) {
		for(int i=0; i<grTutore.size(); i++) {
			g.ajouterSommet(grTutore.get(i).getPrenomNom());
			//System.out.println(grTutore.get(i).getPrenomNom() + " ajouter a la liste sommet");
		}
	}

	public static void ajouterSommetTuteur(ArrayList<Tuteur> grTuteur) {
		for(int i=0; i<grTuteur.size(); i++) {
			g.ajouterSommet(grTuteur.get(i).getPrenomNom());
			//	System.out.println(grTuteur.get(i).getPrenomNom() + " ajouter a la liste en tuteur sommet");
		}
	}


	public static void ajouterArrete(ArrayList<Tutore> grTutore, ArrayList<Tuteur> grTuteur) {
		for(int i=0; i<grTuteur.size(); i++) {
			for(int j=0; j<grTutore.size(); j++) {
				g.ajouterArete(grTuteur.get(i).getPrenomNom(), grTutore.get(j).getPrenomNom(), Tutorat.calculDistance(grTuteur.get(i), grTutore.get(j)));
				//System.out.println("" + groupeTuteur.get(i).getPrenomNom() + " " +  groupeTutore.get(j).getPrenomNom() + " distance : " + Tutorat.calculDistance(groupeTuteur.get(i), groupeTutore.get(j)));
			}
		}
	}


	public static String calculAffectation(GrapheNonOrienteValue<String> g2, ArrayList<Tutore> grTutore, ArrayList<Tuteur> grTuteur){
		System.out.println(grTutore.size() + " Tutore");
		System.out.println(grTuteur.size() + " Tuteur\n");
		ArrayList<String> tuteurPrenomNom=new ArrayList<String>();
		for(int i=0; i<grTuteur.size(); i++) {
			tuteurPrenomNom.add((grTuteur.get(i).getPrenomNom()));
		}

		ArrayList<String> tutorePrenomNom=new ArrayList<String>();
		for(int i=0; i<grTutore.size(); i++) {
			tutorePrenomNom.add(grTutore.get(i).getPrenomNom());
		}

		CalculAffectation<String> c = new CalculAffectation<>(g, tutorePrenomNom, tuteurPrenomNom);
		System.out.println("le cout minimal est de : " + c.getCout());
		String res="";
		for (int i=0;i<grTutore.size();i++) {
			res += c.getAffectation().get(i).getExtremite1()+ " doit se mettre avec "+c.getAffectation().get(i).getExtremite2() + "\n";
		}
		return res;
	}

}