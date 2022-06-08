package dev;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import appli.Tuteur;
import appli.Tutorat;
import appli.Tutore;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;

public class ImportCSV {

	static String myPath =  System.getProperty("user.dir") + File.separator + "res" + File.separator;
	static String sourceFileTutore = "tutore.csv";
	static String sourceFileTuteur = "tuteur.csv";
	
	public static String tuteurCSV = ""+ myPath +sourceFileTuteur;
	public static String tutoreCSV = ""+ myPath +sourceFileTutore;

	static GrapheNonOrienteValue<String> g = new GrapheNonOrienteValue<String>();

	
	public static void writeToFiles(List<Tuteur> newTuteurs, List<Tutore> newTutores){
		ImportCSV.writeToFileTuteur(newTuteurs, tuteurCSV);
		ImportCSV.writeToFileTutore(newTutores, tutoreCSV);
	}

	public static ArrayList<Tutore> ExtractiongroupeTutoreCSV(String sourceFile) {
		try {
			ArrayList<Tutore> groupeTutore = new ArrayList<>();
			Scanner scan = new Scanner(new File(sourceFile));
			ArrayList<String> mot0 = new ArrayList<String>();
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
		}catch(IOException ioe) {
			System.out.println("Problème à la fermeture du flux.");
		}
		return null;
	}

	public static ArrayList<Tuteur> ExtractiongroupeTuteurCSV(String sourceFile) {
		try {
			ArrayList<Tuteur> groupeTuteur = new ArrayList<>();
			Scanner scan = new Scanner(new File(sourceFile));
			ArrayList<String> mot0 = new ArrayList<String>();
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
		}catch(IOException ioe) {
			System.out.println("Problème à la fermeture du flux.");
		}
		return null;
	}





	public static void writeToFileTutore(List<Tutore> tutores, String file) {
	//	String path = myPath + sourceFileTutore;
		BufferedWriter out;
		String line;
		Tutore t;
		String sep = ";";
		try {
			out = new BufferedWriter(new FileWriter(file));
			for(int i = 0; i < tutores.size(); i++) {
				t = tutores.get(i);
				// annee;prenom;nom;moyenne;absence;motivation
				line = t.getAnnee()
						+ sep + t.getPrenom()
						+ sep + t.getNom()
						+ sep + t.getMoyenne()
						+ sep + t.getAbsences()
						+ sep + t.getMotivation();
				out.write(line);
				out.newLine();
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeToFileTuteur(List<Tuteur> tuteurs, String file) {
		//String path = myPath + sourceFileTuteur;
		BufferedWriter out;
		String line;
		Tuteur t;
		String sep = ";";
		try {
			out = new BufferedWriter(new FileWriter(file));
			for(int i = 0; i < tuteurs.size(); i++) {
				t = tuteurs.get(i);
				// annee;prenom;nom;moyenne;absence;motivation
				line = t.getAnnee()
						+ sep + t.getPrenom()
						+ sep + t.getNom()
						+ sep + t.getMoyenne()
						+ sep + t.getAbsences()
						+ sep + t.getMotivation();
				out.write(line);
				out.newLine();
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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


	public static String calculAffectation(GrapheNonOrienteValue<String> g2, List<Tutore> grTutore, List<Tuteur> grTuteur){
		System.out.println(grTutore.size() + " Tutore");
		System.out.println(grTuteur.size() + " Tuteur\n");
		List<String> tuteurPrenomNom=new ArrayList<String>();
		for(int i=0; i<grTuteur.size(); i++) {
			tuteurPrenomNom.add((grTuteur.get(i).getPrenomNom()));
		}
		System.out.println("taille liste prenom tuteur " +tuteurPrenomNom.size());

		ArrayList<String> tutorePrenomNom=new ArrayList<String>();
		for(int i=0; i<grTutore.size(); i++) {
			tutorePrenomNom.add(grTutore.get(i).getPrenomNom());
		}
		System.out.println("taille liste prenom tutore " +tutorePrenomNom.size());

		CalculAffectation<String> c = new CalculAffectation<>(g2, tutorePrenomNom, tuteurPrenomNom);
		System.out.println("le cout minimal est de : " + c.getCout());
		String res="";
		for (int i=0;i<grTutore.size();i++) {
			res += c.getAffectation().get(i).getExtremite1()+ " doit se mettre avec "+c.getAffectation().get(i).getExtremite2() + "\n";
		}
		return res;
	}
	
	
	
	
	
	
	/*
	 * public static void main(String[] args) throws IOException {		
		ajouterSommetTutore(ExtractiongroupeTutoreCSV(myPath+sourceFileTutore));
		ajouterSommetTuteur(ExtractiongroupeTuteurCSV(myPath+sourceFileTuteur));
		ajouterArrete(ExtractiongroupeTutoreCSV(myPath+sourceFileTutore), ExtractiongroupeTuteurCSV(myPath+sourceFileTuteur));
		String affectation = calculAffectation(g, ExtractiongroupeTutoreCSV(myPath+sourceFileTutore), ExtractiongroupeTuteurCSV(myPath+sourceFileTuteur));

		System.out.println(affectation);
	}
	 */
	
	

}