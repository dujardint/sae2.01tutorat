package fr.ulille.but.sae2_02.donnees;

import java.util.Arrays;

public class DonneesPourTester {
	
	/** Contien des données de 57 étudiants fictifs comprenant un prénom, un nom, une note, et une année d'études parmi 1, 2 ou 3. */ 
    public static String[][] studentData = new String[][]{
    	{"Claude","Allard","9.8","1"}, 
    	{"Madeleine","Barre","6.9","1"}, 
    	{"Sabine","Besnard","12.7","1"}, 
    	{"Hugues","Bigot","0.2","1"}, 
    	{"Lucas","Bouchet","17.3","1"}, 
    	{"Alexandria","Boulay","12.5","1"}, 
    	{"Anouk","Brun","10.5","1"}, 
    	{"Hortense","Chauveau","9.1","1"}, 
    	{"David","Colin","7.0","1"}, 
    	{"Amélie","Dijoux","9.7","1"}, 
    	{"Martin","Dijoux","10.1","1"}, 
    	{"Thomas","Evrard","8.9","1"}, 
    	{"Guy","Goncalves","0.5","1"}, 
    	{"Émile","Guerin","4.8","1"}, 
    	{"Éléonore","Guillou","13.1","1"}, 
    	{"Guillaume","Hamel","5.5","1"}, 
    	{"Valérie","Jacob","4.7","1"}, 
    	{"Odette","Lacroix","15.7","1"}, 
    	{"Jacques","Marchand","7.1","1"}, 
    	{"Honoré","Martel","11.7","1"}, 
    	{"Thierry","Moreno","1.4","1"}, 
    	{"Paulette","Muller","7.5","1"}, 
    	{"Camille","Pascal","12.8","1"}, 
    	{"Théodore","Payet","2.1","1"}, 
    	{"Céline","Perrier","6.4","1"}, 
    	{"Édith","Pichon","8.8","1"}, 
    	{"Agnès","Renard","13.2","1"}, 
    	{"Marine","Roux","9.1","1"}, 
    	{"Aurore","Schmitt","9.9","1"}, 
    	{"François","Bertin","13.3","2"}, 
    	{"Joseph","Boyer","7.7","2"}, 
    	{"Martin","Delmas","11.0","2"}, 
    	{"Maurice","Fernandez","5.7","2"}, 
    	{"Thérèse","Gay","11.5","2"}, 
    	{"daisy","Jacques","11.9","2"}, 
    	{"Georges","Lefebvre","10.4","2"}, 
    	{"Sabine","Leleu","15.8","2"}, 
    	{"William","Letellier","11.8","2"}, 
    	{"Gabriel","Marin","12.3","2"}, 
    	{"Nathalie","Marion","12.1","2"}, 
    	{"Christophe","Millet","14.2","2"}, 
    	{"Henri","Moreno","10.8","2"}, 
    	{"Vincent","Muller","9.3","2"}, 
    	{"Jacqueline","Pons","13.2","2"}, 
    	{"Pénélope","Renault","13.2","2"}, 
    	{"Nicolas","Roche","13.1","2"}, 
    	{"Juliette","Traore","9.8","2"}, 
    	{"Sophie","Vallee","15.8","2"}, 
    	{"Édouard","Auger","13.9","3"}, 
    	{"Olivier","Gallet","11.3","3"}, 
    	{"Inès","Gautier","9.3","3"},
    	{"Franck","Hebert","11.9","3"}, 
    	{"Daniel","Lefebvre","12.9","3"}, 
    	{"Charles","Letellier","12.7","3"}, 
    	{"Alex","Marchand","8.7","3"}, 
    	{"Josette","Nicolas","12.5","3"}, 
    	{"Paul","Sanchez","12.0","3"}};

    public static void main(String[] args) {
        for(int i=0; i<studentData.length; i++) {
            System.out.println(Arrays.deepToString(studentData[i]));
        }
    }
}
