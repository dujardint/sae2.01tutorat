package Graphes;

import fr.ulille.but.sae2_02.graphes.CalculAffectation ;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue ;
import java.util.* ;

import appli.Tuteur;
import appli.Tutorat;
import appli.Tutore;

public class AffectationAdd {

	public static void main(String[] args) {
		GrapheNonOrienteValue<String> g = new GrapheNonOrienteValue<String>();
		System.out.println("Graphe permettant l'affectation normale avec les Donn�esPourTester : ");
		//System.out.println(g);

		System.out.println("\n1ere annee :");
		//1ÈRES ANNÉES
		// On initialise le groupe des tutorés
		ArrayList<Tutore> groupeTutore = new ArrayList<>();

		int nbAbscenceDefaut = 1;
		int annee = 1;
		char motivation = '+';  //+ - ou =

		int second = 2;
		int troisieme = 3;

		//	         Tutore(String identifiant, String prenom, String nom, double moyenne, int absences , int annee ,char motivation)

		groupeTutore.add(new Tutore("tutore_", "Claude", "Allard", 9.8, nbAbscenceDefaut, annee, motivation));
		groupeTutore.add(new Tutore("tutore_", "Madeleine", "Barre", 6.9, nbAbscenceDefaut, annee, motivation));
		groupeTutore.add(new Tutore("tutore_", "Sabine", "Besnard", 12.7, nbAbscenceDefaut, annee, motivation));
		groupeTutore.add(new Tutore("tutore_", "Hugues", "Bigot", 0.2, nbAbscenceDefaut, annee, motivation));
		groupeTutore.add(new Tutore("tutore_", "Lucas", "Bouchet", 17.3, nbAbscenceDefaut, annee, motivation));
		groupeTutore.add(new Tutore("tutore_", "Alexandria", "Boulay", 12.5, nbAbscenceDefaut, annee, motivation)); 
		groupeTutore.add(new Tutore("tutore_", "Anouk", "Brun", 10.5, nbAbscenceDefaut, annee, motivation));
		groupeTutore.add(new Tutore("tutore_", "Hortense", "Chauveau", 9.1,nbAbscenceDefaut, annee, motivation));  
		groupeTutore.add(new Tutore("tutore_", "David", "Colin", 7.0,nbAbscenceDefaut, annee, motivation));
		groupeTutore.add(new Tutore("tutore_", "Amélie", "Dijoux", 9.7,nbAbscenceDefaut, annee, motivation)); 
		groupeTutore.add(new Tutore("tutore_", "Martin", "Dijoux", 10.1,nbAbscenceDefaut, annee, motivation)); 
		groupeTutore.add(new Tutore("tutore_", "Thomas", "Evrard", 8.9,nbAbscenceDefaut, annee, motivation));  
		groupeTutore.add(new Tutore("tutore_", "Guy", "Goncalves", 0.5,nbAbscenceDefaut, annee, motivation));  
		groupeTutore.add(new Tutore("tutore_", "Emile", "Guerin", 4.8,nbAbscenceDefaut, annee, motivation));
		groupeTutore.add(new Tutore("tutore_", "Eléonore", "Guillou", 13.1,nbAbscenceDefaut, annee, motivation));  
		groupeTutore.add(new Tutore("tutore_", "Guillaume", "Hamel", 5.5,nbAbscenceDefaut, annee, motivation));  
		groupeTutore.add(new Tutore("tutore_", "ValÃ©rie", "Jacob", 4.7,nbAbscenceDefaut, annee, motivation)); 
		groupeTutore.add(new Tutore("tutore_", "Odette", "Lacroix", 15.7,nbAbscenceDefaut, annee, motivation));  
		groupeTutore.add(new Tutore("tutore_", "Jacques", "Marchand", 7.1,nbAbscenceDefaut, annee, motivation)); 
		groupeTutore.add(new Tutore("tutore_", "HonorÃ©", "Martel", 11.7,nbAbscenceDefaut, annee, motivation));   
		groupeTutore.add(new Tutore("tutore_", "Thierry", "Moreno", 1.4,nbAbscenceDefaut, annee, motivation));  
		groupeTutore.add(new Tutore("tutore_", "Paulette", "Muller", 7.5,nbAbscenceDefaut, annee, motivation));   
		groupeTutore.add(new Tutore("tutore_", "Camille", "Pascal", 12.8,nbAbscenceDefaut, annee, motivation));   
		groupeTutore.add(new Tutore("tutore_", "Théodore", "Payet", 2.1,nbAbscenceDefaut, annee, motivation));  
		groupeTutore.add(new Tutore("tutore_", "Caroline", "Perrier", 6.4,nbAbscenceDefaut, annee, motivation));  
		groupeTutore.add(new Tutore("tutore_", "Rdith", "Pichon", 8.8,nbAbscenceDefaut, annee, motivation));   
		groupeTutore.add(new Tutore("tutore_", "Agnès", "Renard", 13.2,nbAbscenceDefaut, annee, motivation));   
		groupeTutore.add(new Tutore("tutore_", "Marine", "Roux", 9.1,nbAbscenceDefaut, annee, motivation)); 
		groupeTutore.add(new Tutore("tutore_", "Aurore", "Schmitt", 9.9,nbAbscenceDefaut, annee, motivation)); 







		System.out.println("\n2e et 3 annee :");
		//2E et 3e ANNEE
		ArrayList<Tuteur> groupeTuteur = new ArrayList<>();
		groupeTuteur.add(new Tuteur("tuteur_","François","Bertin",13.3,nbAbscenceDefaut, second, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Joseph","Boyer",7.7,nbAbscenceDefaut, second, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Martin","Delmas",11.0,nbAbscenceDefaut, second, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Maurice","Fernandez",5.7,nbAbscenceDefaut, second, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Thérèse","Gay",11.5,nbAbscenceDefaut, second, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","daisy","Jacques",11.9,nbAbscenceDefaut, second, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Georges","Lefebvre",10.4,nbAbscenceDefaut, second, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Sabine","Leleu",15.8,nbAbscenceDefaut, second, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","William","Letellier",11.8,nbAbscenceDefaut, second, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Gabriel","Marin",12.3,nbAbscenceDefaut, second, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Nathalie","Marion",12.1,nbAbscenceDefaut, second, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Christophe","Millet",14.2,nbAbscenceDefaut, second, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Henri","Moreno",10.8,nbAbscenceDefaut, second, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Vincent","Muller",9.3,nbAbscenceDefaut, second, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Jacqueline","Pons",13.2,nbAbscenceDefaut, second, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Pénélope","Renault",13.2,nbAbscenceDefaut, second, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Nicolas","Roche",13.1,nbAbscenceDefaut, second, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Juliette","Traore",9.8,nbAbscenceDefaut, second, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Sophie","Vallee",15.8,nbAbscenceDefaut, second, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Edouard","Auger",13.9,nbAbscenceDefaut, troisieme, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Olivier","Gallet",11.3,nbAbscenceDefaut, troisieme, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","InÃ¨s","Gautier",9.3,nbAbscenceDefaut, troisieme, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Franck","Hebert",11.9,nbAbscenceDefaut, troisieme, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Daniel","Lefebvre",12.9,nbAbscenceDefaut, troisieme, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Charles","Letellier",12.7,nbAbscenceDefaut, troisieme, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Alex","Marchand",8.7,nbAbscenceDefaut, troisieme, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Josette","Nicolas",12.5,nbAbscenceDefaut, troisieme, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Paul","Sanchez",12.0,nbAbscenceDefaut, troisieme, motivation)); 
		groupeTuteur.add(new Tuteur("tuteur_","Nathan","Accart",2,nbAbscenceDefaut, troisieme, motivation)); 

		Tutorat etudiants = new Tutorat(groupeTuteur, groupeTutore);


		Scanner scan = new Scanner(System.in);


		boolean demande = true;

		while (demande) {


			System.out.println(("0 pour quitter\n1 pour verifier la taille\n2 pour supprimer un candidat\n3 pour ajouter un tuteur\n4 pour ajouter un tutore\n5 pour calculer"));
			int choix = scan.nextInt();
			if(choix==0) {
				System.out.println("Vous avez demander la femeture du programme !");
				demande=false;
			}
			if(choix==1) {
				System.out.println(etudiants.tailleEgale());
			}
			else if(choix==2){
				etudiants.supprimeCandidat();
			}
			else if(choix==3) {
				Tutorat.ajoutTuteur();
			}
			else if(choix==4) {
				Tutorat.ajoutTutore();
			}
			else if(choix==5){
				System.out.println("Vous avez decidé de ne rien modifier ! l'algo va s'executer !");
		


				for(int i=0; i<groupeTutore.size(); i++) {
					g.ajouterSommet(groupeTutore.get(i).getPrenomNom());
					//System.out.println(groupeTutore.get(i).getPrenomNom() + " ajouter a la liste sommet");
				}

				for(int i=0; i<groupeTuteur.size(); i++) {
					g.ajouterSommet(groupeTuteur.get(i).getPrenomNom());
					//	System.out.println(groupeTuteur.get(i).getPrenomNom() + " ajouter a la liste sommet");
				}

				//ARRETES
				for(int i=0; i<groupeTuteur.size(); i++) {
					for(int j=0; j<groupeTutore.size(); j++) {
						g.ajouterArete(groupeTuteur.get(i).getPrenomNom(), groupeTutore.get(j).getPrenomNom(), Tutorat.calculDistance(groupeTuteur.get(i), groupeTutore.get(j)));
						//System.out.println("" + groupeTuteur.get(i).getPrenomNom() + " " +  groupeTutore.get(j).getPrenomNom() + " distance : " + Tutorat.calculDistance(groupeTuteur.get(i), groupeTutore.get(j)));
					}
				}

				//System.out.println(g.toString());

				System.out.println();
				List<String> tuteurPrenomNom=new ArrayList<String>();
				for(int i=0; i<groupeTuteur.size(); i++) {
					tuteurPrenomNom.add((groupeTuteur.get(i).getPrenomNom()));
					System.out.println("liste tuteur : " + tuteurPrenomNom.get(i));
				}
				System.out.println();
				ArrayList<String> tutorePrenomNom=new ArrayList<String>();
				for(int i=0; i<groupeTutore.size(); i++) {
					tutorePrenomNom.add(groupeTutore.get(i).getPrenomNom());
					System.out.println("liste tutore : "+tutorePrenomNom.get(i));
				}

				System.out.println();
				CalculAffectation<String> c = new CalculAffectation<>(g, tutorePrenomNom, tuteurPrenomNom);
				//	System.out.println("le cout minimal est de : " + c.getCout());
				//System.out.println(c.getAffectation());
				for (int i=0;i<groupeTutore.size();i++) {
					System.out.println(c.getAffectation().get(i).getExtremite1()+ " doit se mettre avec "+c.getAffectation().get(i).getExtremite2());
					//System.out.println("Cette tâche prendra "+g.getPoids(c.getAffectation().get(i).getExtremite1(),g.getAffectation().get(i).getExtremite2()) +" jours ");
				}

				System.out.println("\nL'affectation est terminé !!");


				//System.out.println("triage des tuteur : \n" + etudiants.triTuteur(groupeTuteur));
				//System.out.println("triage des tutore : \n" + etudiants.triTutore(groupeTutore));
			}
		}
	}
}
