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
import appli.Tutore;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;

public class ImportCSV {

	static String myPath =  System.getProperty("user.dir") + File.separator + "res" + File.separator;
	static String sourceFileTutore = "tutore.csv";
	static String sourceFileTuteur = "tuteur.csv";

	public static GrapheNonOrienteValue<String> g = new GrapheNonOrienteValue<String>();
	public static String FILEPATH_TUTORE = myPath + sourceFileTutore;
	public static String FILEPATH_TUTEUR = myPath + sourceFileTuteur;

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
			System.out.println("Probleme a la fermeture du flux.");
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

	public static void writeToFileTutore(List<Tutore> tutores) {
		String path = myPath + sourceFileTutore;
		BufferedWriter out;
		String line;
		Tutore t;
		String sep = ";";
		try {
			out = new BufferedWriter(new FileWriter(path));
			out.write("annee;prenom;nom;moyenne;absence;motivation");
			out.newLine();
			for(int i = 0; i < tutores.size(); i++) {
				t = tutores.get(i);
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
			out.write("annee;prenom;nom;moyenne;absence;motivation");
			out.newLine();
			for(int i = 0; i < tuteurs.size(); i++) {
				t = tuteurs.get(i);
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
		ImportCSV.writeToFileTuteur(tuteurs);
		ImportCSV.writeToFileTutore(tutores);
	}

}