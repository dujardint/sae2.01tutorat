package dev;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import appli.Tuteur;
import appli.Tutore;

public class ImportCSV {

	static String myPath =  System.getProperty("user.dir") +
			File.separator + "res" + File.separator;
	static String sourceFileTutore = "tutore.csv";
	static String sourceFileTuteur = "groupeTuteur.csv";

	static ArrayList<Tutore> groupeTutore = new ArrayList<>();
	static ArrayList<Tuteur> groupeTuteur = new ArrayList<>();


	
	public static void main(String[] args) throws IOException {
		System.out.println("Voici le fichier situe a l'emplacement " + myPath+sourceFileTutore + "\n");
		sysoreadAndDisplayFileTutore(myPath+sourceFileTutore);
		readAndDisplayFileTuteur(myPath+sourceFileTuteur);
	}

	
	public ArrayList<Tutore> readAndDisplayFileTutore(String sourceFile) {
		try {
			Scanner scan = new Scanner(new File(sourceFile));
			
			ArrayList<String> mot0 = new ArrayList<String>();
			
			int annee = 0;
			String nom;
			String prenom;
			double moyenne = 0;
			int abscence = 0;
			char motivation = 0;
			
			scan.nextLine();
			System.out.println(mot0);
			while (scan.hasNextLine()) {
				String[] mots = scan.nextLine().split(";");
				System.out.println("on affiche l'annee");
				System.out.println(mots[0]);
				annee=Integer.parseInt(mots[0]);
		
				System.out.println("on affiche les prenoms");
				System.out.println(mots[1]);
				prenom=mots[1];
				System.out.println("on affiche les noms");
				System.out.println(mots[2]);
				nom=mots[2];
				System.out.println("on affiche les moyennes");
				System.out.println(mots[3]);
				moyenne=Double.parseDouble(mots[3]);
				System.out.println("on affiche les absences");
				System.out.println(mots[4]);
				abscence=Integer.parseInt(mots[4]);
				System.out.println("on affiche les motivation");
				System.out.println(mots[5]);
				motivation=mots[5].charAt(0);
				
				groupeTutore.add(new Tutore("tutore_", prenom, nom, moyenne, abscence, annee, motivation));
				
			}
			
			for(int i =0; i<groupeTutore.size(); i++) {
				System.out.println(groupeTutore.get(i));
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier n'existe pas.");
		}catch(IOException ioe) {
			System.out.println("Problème à la fermeture du flux.");
		}
	}
	
	
	
	public static void readAndDisplayFileTuteur(String sourceFile) {
		try {
			Scanner scan = new Scanner(new File(sourceFile));
			
			ArrayList<String> mot0 = new ArrayList<String>();
			
			int annee = 0;
			String nom;
			String prenom;
			double moyenne = 0;
			int abscence = 0;
			char motivation = 0;
			
			scan.nextLine();
			System.out.println(mot0);
			while (scan.hasNextLine()) {
				String[] mots = scan.nextLine().split(";");
				System.out.println("on affiche l'annee");
				System.out.println(mots[0]);
				annee=Integer.parseInt(mots[0]);
		
				System.out.println("on affiche les prenoms");
				System.out.println(mots[1]);
				prenom=mots[1];
				System.out.println("on affiche les noms");
				System.out.println(mots[2]);
				nom=mots[2];
				System.out.println("on affiche les moyennes");
				System.out.println(mots[3]);
				moyenne=Double.parseDouble(mots[3]);
				System.out.println("on affiche les absences");
				System.out.println(mots[4]);
				abscence=Integer.parseInt(mots[4]);
				System.out.println("on affiche les motivation");
				System.out.println(mots[5]);
				motivation=mots[5].charAt(0);
				
				groupeTuteur.add(new Tuteur("tutore_", prenom, nom, moyenne, abscence, annee, motivation));
				
			}
			
			for(int i =0; i<groupeTuteur.size(); i++) {
				System.out.println(groupeTuteur.get(i).getPrenomNom());
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier n'existe pas.");
		}catch(IOException ioe) {
			System.out.println("Problème à la fermeture du flux.");
		}
	}
	
	
	
	

}
