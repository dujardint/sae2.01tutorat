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

public class ImportCSV1 {

	static String myPath =  System.getProperty("user.dir") + File.separator + "res" + File.separator;
	static String sourceFileTutore = "tutore.csv";
	static String sourceFileTuteur = "tuteur.csv";

	static GrapheNonOrienteValue<String> g = new GrapheNonOrienteValue<String>();
	public static String FILEPATH_TUTORE = myPath + sourceFileTutore;
	public static String FILEPATH_TUTEUR = myPath + sourceFileTuteur;

	/*
	public static void main(String[] args) throws IOException {	
		Tutorat tutorat = new Tutorat(ImportCSV.readFileTuteur(ImportCSV.FILEPATH_TUTEUR),
				ImportCSV.readFileTutore(ImportCSV.FILEPATH_TUTORE));
		
		ajouterSommetTutore(tutorat.getListTutore());
		ajouterSommetTuteur(tutorat.getListTuteur());
		ajouterArrete(tutorat.getListTutore(), tutorat.getListTuteur());
		String affectation = calculAffectation(g, tutorat.getListTutore(), tutorat.getListTuteur());

		System.out.println(affectation);
	}
	*/


	private static List<String> readCSVFileASStringList(String sourceFile) {
		List<String> lignes = new ArrayList<>();
		try {
			Scanner scan = new Scanner(new File(sourceFile));
			// Remove header
			scan.next();
			
			while (scan.hasNext()) {
				lignes.add(scan.next());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier n'existe pas.");
			System.err.print(e.getMessage());
		} catch(IOException ioe) {
			System.out.println("Problème à la fermeture du flux.");
			System.err.print(ioe.getMessage());
		}
		return lignes; 
	}
	
	public static List<Tutore> readFileTutore(String sourceFile){
		List<Tutore> tutores = new ArrayList<Tutore>();
		List<String> lignes = readCSVFileASStringList(sourceFile);
		String[] ligneSplit;
		Tutore tutoreTmp;
		int id = 0;
		for(String ligne : lignes) {
			ligneSplit = ligne.split(";");	
			tutoreTmp = new Tutore(id + "",
					ligneSplit[1],
					ligneSplit[2],
					Double.parseDouble(ligneSplit[3]),
					Integer.parseInt(ligneSplit[4]),
					Integer.parseInt(ligneSplit[0]), 
					ligneSplit[5].charAt(0));
			tutores.add(tutoreTmp);
			id = id +1;
		}
		return tutores;
	}
	
	public static List<Tuteur> readFileTuteur(String sourceFile){
		List<Tuteur> tuteurs = new ArrayList<Tuteur>();
		List<String> lignes = readCSVFileASStringList(sourceFile);
		String[] ligneSplit;
		Tuteur tuteurTmp;
		int id = 0;
		for(String ligne : lignes) {
			ligneSplit = ligne.split(";");
			System.out.println(ligne);
			tuteurTmp = new Tuteur(id + "",
					ligneSplit[1],
					ligneSplit[2],
					Double.parseDouble(ligneSplit[3]),
					Integer.parseInt(ligneSplit[4]),
					Integer.parseInt(ligneSplit[0]), 
					ligneSplit[5].charAt(0));
			tuteurs.add(tuteurTmp);
			id = id +1;
		}
		return tuteurs;
	}

	public static void ajouterSommetTutore(List<Tutore> list) {
		for(int i=0; i<list.size(); i++) {
			g.ajouterSommet(list.get(i).getPrenomNom());
			//System.out.println(grTutore.get(i).getPrenomNom() + " ajouter a la liste sommet");
		}
	}

	public static void ajouterSommetTuteur(List<Tuteur> grTuteur) {
		for(int i=0; i<grTuteur.size(); i++) {
			g.ajouterSommet(grTuteur.get(i).getPrenomNom());
			//	System.out.println(grTuteur.get(i).getPrenomNom() + " ajouter a la liste en tuteur sommet");
		}
	}


	private static void ajouterArrete(List<Tutore> grTutore, List<Tuteur> grTuteur) {
		for(int i=0; i<grTuteur.size(); i++) {
			for(int j=0; j<grTutore.size(); j++) {
				g.ajouterArete(grTuteur.get(i).getPrenomNom(), grTutore.get(j).getPrenomNom(), Tutorat.calculDistance(grTuteur.get(i), grTutore.get(j)));
				//System.out.println("" + groupeTuteur.get(i).getPrenomNom() + " " +  groupeTutore.get(j).getPrenomNom() + " distance : " + Tutorat.calculDistance(groupeTuteur.get(i), groupeTutore.get(j)));
			}
		}
	}


	public static String calculAffectation(GrapheNonOrienteValue<String> g2, List<Tutore> grTutore, List<Tuteur> grTuteur){
		ajouterSommetTutore(grTutore);
		ajouterSommetTuteur(grTuteur);
		ajouterArrete(grTutore, grTuteur);
		g2=new GrapheNonOrienteValue<String>();
		System.out.println(grTutore.size() + " Tutore");
		System.out.println(grTuteur.size() + " Tuteur\n");
		List<String> tuteurPrenomNom = new ArrayList<String>();
		for(int i=0; i<grTuteur.size(); i++) {
			tuteurPrenomNom.add((grTuteur.get(i).getPrenomNom()));
		}

		List<String> tutorePrenomNom = new ArrayList<String>();
		for(int i=0; i<grTutore.size(); i++) {
			tutorePrenomNom.add(grTutore.get(i).getPrenomNom());
		}
		System.out.println(tutorePrenomNom.size());
		System.out.println(tuteurPrenomNom.size());
		CalculAffectation<String> c = new CalculAffectation<>(g, tutorePrenomNom, tuteurPrenomNom);
		System.out.println("le cout minimal est de : " + c.getCout());
		String res="";
		for (int i=0;i<grTutore.size();i++) {
			res += c.getAffectation().get(i).getExtremite1()+ " doit se mettre avec "+c.getAffectation().get(i).getExtremite2() + "\n";
		}
		return res;
	}

	public static void writeToFileTutore(List<Tutore> tutores) {
		String path = myPath + sourceFileTutore;
		BufferedWriter out;
		String line;
		Tutore t;
		String sep = ";";
		try {
			out = new BufferedWriter(new FileWriter(path));
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
	
	public static void writeToFileTuteur(List<Tuteur> tuteurs) {
		String path = myPath + sourceFileTuteur;
		BufferedWriter out;
		String line;
		Tuteur t;
		String sep = ";";
		try {
			out = new BufferedWriter(new FileWriter(path));
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

	public static void writeToFiles(List<Tuteur> tuteurs, List<Tutore> tutores){
		ImportCSV1.writeToFileTuteur(tuteurs);
		ImportCSV1.writeToFileTutore(tutores);
	}

}