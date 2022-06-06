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
			Tutorat etudiants = new Tutorat(ImportCSV.ExtractiongroupeTuteurCSV(myPath+sourceFileTuteur), ImportCSV.ExtractiongroupeTutoreCSV(myPath+sourceFileTutore));

			System.out.println("Bienvenue dans l'application officielle de tutorat !\nQue souhaitez-vous faire ? ");
			System.out.println(
					"0 pour calculer\n"
							+ "1 pour afficher la liste des etudiants inscrits\n"
							+ "2 pour vérifier la taille\n"
							+ "3 pour ajouter un candidat (tuteur ou tutore) \n"
							+ "4 pour supprimer un candidat\n"
							+ "5 pour rechercher un tuteur \n"
							+ "6 pour rechercher un tutoré\n"
							+ "7 pour quitter");
			System.out.print("Votre choix : ");
			String choix = scan.nextLine();
			System.out.println();
			if(choix.equals("0")) {
				System.out.println("Vous avez decidé de ne rien modifier. L'algorithme va s'exécuter.");

				ajouterSommetTutore(ImportCSV.ExtractiongroupeTutoreCSV(myPath+sourceFileTutore));
				ajouterSommetTuteur(ImportCSV.ExtractiongroupeTuteurCSV(myPath+sourceFileTuteur));
				ajouterArrete(ImportCSV.ExtractiongroupeTutoreCSV(myPath+sourceFileTutore), ImportCSV.ExtractiongroupeTuteurCSV(myPath+sourceFileTuteur));
				String affectation = calculAffectation(g, ImportCSV.ExtractiongroupeTutoreCSV(myPath+sourceFileTutore), ImportCSV.ExtractiongroupeTuteurCSV(myPath+sourceFileTuteur));

				System.out.println(affectation);

				System.out.println("Est-ce que l'affectation vous convient ? (=> o ou n)");
				String convaincu = "";
				while(scan.hasNext() || !convaincu.equals("o") || !convaincu.equals("oui") || !convaincu.equals("OUI")
					!convaincu.equals("n") || !convaincu("non") || !convaincu("NON")){
					convaincu = scan.nextLine();
					if (convaincu.equals("o") || convaincu.equals("oui") || convaincu.equals("OUI")){
						demande=false;
					} else if (convaincu.equals("n") || convaincu.equals("non") || convaincu.equals("NON")){
						demande=true;
					} else {
						System.out.println("La réponse n'est pas valide.");
						System.out.println("Est-ce que l'affectation vous convient ? (=> o ou n)");
					}
				}
			} else if(choix.equals("1")) {
				System.out.println(etudiants.toString());

			} else if(choix.equals("2")){
				System.out.println(etudiants.tailleEgale());

			} else if(choix.equals("3")) {
				String tuteurOuTutore = etudiants.ajoutCandidat();
				if (tuteurOuTutore.equals("tuteur")) {
					ImportCSV.writeToFileTuteur(etudiants.getListTuteur());
				} else if (tuteurOuTutore.equals("tutoré")) {
					ImportCSV.writeToFileTutore(etudiants.getListTutore());
				}				
			} else if(choix.equals("4")){
				etudiants.supprimeCandidat();
			} else if(choix.equals("5")) {
				etudiants.vuTuteur();
			} else if(choix.equals("6")) {
				etudiants.vuTutore();

			} else if(choix.equals("7")) {
				System.out.println("Vous avez demandé la fermeture du programme.");
				demande=false;
			} else {
				System.out.println("Nous n'avons pas compris votre choix, veuillez réessayer.");
			}
		}
		System.out.println("Merci d'avoir utilisé notre merveilleuse application ! ;-)");
		scan.close();
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