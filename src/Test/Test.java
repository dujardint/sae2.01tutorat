package Test;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import appli.Tuteur;
import appli.Tutore;
import fr.ulille.but.sae2_02.graphes.Arete;


/**
 * Tests unitaire des différents scénarios imaginés.
 * 
 * @author augustin b.
 * @see Scenario
 */
public class Test {
	public Tutore 
	u1,u2,u3,u4,u5,u6,u7,u8,u9;
	public Tuteur 
	t1,t2,t3,t4,t5;
	public List<Tutore> tutoreList;
	public List<Tuteur> tutorList;
	public static final double DELTA = 0.002;
	int nbAbscenceDefaut = 1;
	int annee = 1;
	char motivation = '+';  //+ - ou =

	int second = 2;
	int troisieme = 3;

	public void initialize() {
		u1 = new Tutore("tutore_", "Claude", "Allard", 9.8, nbAbscenceDefaut, annee, motivation);
		u2 = new Tutore("tutore_", "Madeleine", "Barre", 6.9, nbAbscenceDefaut, annee, motivation);
		u3 = new Tutore("tutore_", "Sabine", "Besnard", 12.7, nbAbscenceDefaut, annee, motivation);
		u4 = new Tutore("tutore_", "Hugues", "Bigot", 0.2, nbAbscenceDefaut, annee, motivation);
		u5 = new Tutore("tutore_", "Alexandria", "Boulay", 12.5, nbAbscenceDefaut, annee, motivation);
		u6 = new Tutore("tutore_", "Anouk", "Brun", 10.5, nbAbscenceDefaut, annee, motivation);
		u7 = new Tutore("tutore_", "Hortense", "Chauveau", 9.1,nbAbscenceDefaut, annee, motivation);
		u8 = new Tutore("tutore_", "Hortense", "Chauveau", 9.1,nbAbscenceDefaut, annee, motivation);
		u9 = new Tutore("tutore_", "Hortense", "Chauveau", 9.1,nbAbscenceDefaut, annee, motivation);

		t1 = new Tuteur("tuteur_","François","Bertin",13.3,nbAbscenceDefaut, second, motivation);
		t2 = new Tuteur("tuteur_","Joseph","Boyer",7.7,nbAbscenceDefaut, second, motivation);
		t3 = new Tuteur("tuteur_","Martin","Delmas",11.0,nbAbscenceDefaut, second, motivation);
		t4 = new Tuteur("tuteur_","daisy","Jacques",11.9,nbAbscenceDefaut, second, motivation);
		t5 = new Tuteur("tuteur_","Alex","Marchand",8.7,nbAbscenceDefaut, troisieme, motivation);




		tutoreList = new ArrayList<>();
		tutoreList.addAll(List.of(u1, u2, u3, u4, u5, u6, u7));
		tutorList = new ArrayList<>();
		tutorList.addAll(List.of(t1, t2, t3, t4, t5));
	}

	@Test
	void testAddElementList() {
		assertEquals(7, tutoreList.size());
		tutoreList.add(u8);
		assertEquals(8, tutoreList.size());
		tutoreList.add(u9);
		assertEquals(9, tutoreList.size());
	}

	@Test
	void testRemoveElementList() {
		assertEquals(9, tutoreList.size());
		tutoreList.remove(u8);
		assertEquals(8, tutoreList.size());
		tutoreList.remove(u9);
		assertEquals(7, tutoreList.size());
	}


	@Test
	void testToStringEtudiant() {
		tutoreList.clear();
		assertEquals(0, tutoreList.size());
		tutoreList.add(u1);
		assertEquals(u1.getPrenomNom() +"-> Etudiant [annee= 1, moyenne= 9.8 , motivation= + ]", tutoreList.toString());

	}

	@Test
	void testGetEtudiant() {
		assertEquals(9,8, u1.getMoyenne());
		assertEquals(1, u1.getAnnee());
		assertEquals('+', u1.getMotivation());
		assertEquals("Claude_Allard", u1.getPrenomNom());
		assertEquals(1, u1.getAbsences());
	}
		

}
