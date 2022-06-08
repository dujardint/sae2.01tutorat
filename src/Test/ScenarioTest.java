package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import appli.Tuteur;
import appli.Tutore;

class ScenarioTest {

	
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
	
	@Test
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
		tutoreList=new ArrayList<Tutore>();
		
		
		tutoreList.add(u1);
		tutoreList.add(u2);
		tutoreList.add(u3);
		tutoreList.add(u4);
		tutoreList.add(u5);
		tutoreList.add(u6);
		tutoreList.add(u7);
		assertEquals(7, tutoreList.size());
		tutoreList.add(u8);
		assertEquals(8, tutoreList.size());
		tutoreList.add(u9);
		assertEquals(9, tutoreList.size());
	}

	@Test
	void testRemoveElementList() {
		tutoreList = new ArrayList<>();
		tutoreList.add(u1);
		tutoreList.add(u2);
		tutoreList.add(u3);
		tutoreList.add(u4);
		tutoreList.add(u5);
		tutoreList.add(u6);
		tutoreList.add(u7);
		tutoreList.add(u8);
		tutoreList.add(u9);
		assertEquals(9, tutoreList.size());
		tutoreList.remove(u8);
		assertEquals(8, tutoreList.size());
		tutoreList.remove(u9);
		assertEquals(7, tutoreList.size());
	}


	@Test
	void testToStringEtudiant() {
		tutoreList = new ArrayList<>();
		tutoreList.add(u1);
		tutoreList.add(u2);
		tutoreList.add(u3);
		tutoreList.clear();
		assertEquals(0, tutoreList.size());
		tutoreList.add(u1);
		assertEquals(1, tutoreList.size());

	}

	@Test
	void testGetEtudiant() {
		tutoreList = new ArrayList<>();
		u1 = new Tutore("tutore_", "Claude", "Allard", 9.8, nbAbscenceDefaut, annee, motivation);
		tutoreList.add(u1);

		assertEquals(9,8, u1.getMoyenne());
		assertEquals(1, u1.getAnnee());
		assertEquals('+', u1.getMotivation());
		assertEquals("Allard_Claude", u1.getPrenomNom());
		assertEquals(1, u1.getAbsences());
	}
		
}
