package Test;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import appli.Tuteur;
import appli.Tutore;
import fr.ulille.but.sae2_02.graphes.Arete;
import graphs.affectation.Assignment;
import graphs.affectation.Student;
import graphs.affectation.Tools;
import graphs.affectation.Tutor;
import graphs.affectation.Tutored;
import graphs.useAffectation.BeforeEach;

/**
 * Tests unitaire des différents scénarios imaginés.
 * 
 * @author augustin b.
 * @see Scenario
 */
public class Test {
    public Tutore 
            u1,u2,u3,u4,u5,u6,u7;
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
    public String toString() {
    	
    }

    @Test
    public void casDeBase() {
        // cas 1.A
        assignment.setPolyTutor(false);
        List<Arete<Student>> edges = assignment.getAssignment();
        List<Student> waitingList = assignment.getWaitingList();
         

        assertEquals(edges.size(), 5);
        assertEquals(waitingList.size(), 2);
        assertEquals(assignment.getCost(), 9.242, DELTA);
    }

    @Test
    public void casPolytutorat() {
        // cas 1.B
        assignment.setPolyTutor(true);
        List<Arete<Student>> edges = assignment.getAssignment();
        List<Student> waitingList = assignment.getWaitingList();
         

        assertEquals(edges.size(), 6);
        assertEquals(waitingList.size(), 1);
        assertEquals(assignment.getCost(), 11.941, DELTA);
        assertEquals(waitingList.get(0), u5);
        int olivierCount = 0;
        Pattern pattern = Pattern.compile("olivier", Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        for (Arete<Student> edge : edges) {
            matcher = pattern.matcher(edge.getExtremite2().getName());
            if(matcher.find()) {
                olivierCount++;
            }
        }
        assertEquals(olivierCount, 2);
    }

    @Test
    public void casAffectationForcee() {
        // cas 2.A
        // rappel : on force l'affectation entre Claude & Jacqueline
        assignment.setPolyTutor(false);
        List<Arete<Student>> edges = assignment.getAssignment();
        
        Arete<Student> wantedAssignment = new Arete<Student>(u1, t2);
        for (Arete<Student> edge : edges) {
            assertFalse(Tools.edgeTextEquals(edge, wantedAssignment));
        }
        
        assignment.addForcedAssignments(u1, t2);
        edges = assignment.getAssignment();
        List<Student> waitingList = assignment.getWaitingList();
        boolean isEdgeInAssignment = false;
         

        for (Arete<Student> edge : edges) {
            if(Tools.edgeTextEquals(edge, wantedAssignment)) {
                isEdgeInAssignment = true;
            }
        }
        assertTrue(isEdgeInAssignment);
        assertEquals(edges.size(), 5);
        assertEquals(waitingList, List.of(u5, u2));
        assertEquals(assignment.getCost(), 7.415, DELTA);

        assignment.removeForcedAssignment(u1);
        edges = assignment.getAssignment();
        for (Arete<Student> edge : edges) {
            assertFalse(Tools.edgeTextEquals(edge, wantedAssignment));
        }

    }

    @Test
    public void casIncompatibilite() {
        // cas 2.B
        // rappel : on veut empêcher une affectation entre 
        assignment.setPolyTutor(false);
        List<Arete<Student>> edges = assignment.getAssignment();

        Arete<Student> unwantedAssignment = new Arete<Student>(u1, t4);
        boolean isEdgeInAssignment = false;
        for (Arete<Student> edge : edges) {
            if(Tools.edgeTextEquals(edge, unwantedAssignment)) {
                isEdgeInAssignment = true;
            }
        }
        assertTrue(isEdgeInAssignment);

        assignment.addForbiddenAssignments(u1, t4);
        edges = assignment.getAssignment();
        List<Student> waitingList = assignment.getWaitingList();
         

        for (Arete<Student> edge : edges) {
            assertFalse(Tools.edgeTextEquals(edge, unwantedAssignment));
        }
        assertEquals(edges.size(), 5);
        assertEquals(waitingList, List.of(u5, u2));
        assertEquals(assignment.getCost(), 9.242, DELTA);

        assignment.removeForbiddenAssignment(u1);
        edges = assignment.getAssignment();
        isEdgeInAssignment = false;
        for (Arete<Student> edge : edges) {
            if(Tools.edgeTextEquals(edge, unwantedAssignment)) {
                isEdgeInAssignment = true;
            }
        }
        assertTrue(isEdgeInAssignment);
    }

    @Test
    public void casPonderationMoyenne() {
        // cas 3.A
        assignment.setPolyTutor(false);
        Student.setAverageWeighting(2);
        List<Arete<Student>> edges = assignment.getAssignment();
        List<Student> waitingList = assignment.getWaitingList();
 

        // assertNotEquals = poids sans pondération de la moyenne
        assertEquals(u1.getWeight(), 1.131, DELTA);
        assertNotEquals(u2.getWeight(), 1.055, DELTA);
        assertEquals(u4.getWeight(), 0.497, DELTA);
        assertNotEquals(u6.getWeight(), 0.811, DELTA);
        assertEquals(u7.getWeight(), 1.441, DELTA);

        assertEquals(t1.getWeight(), 1.467, DELTA);
        assertEquals(t2.getWeight(), 1.456, DELTA);
        assertNotEquals(t3.getWeight(), 1.141, DELTA);

        assertEquals(edges.size(), 5);
        assertEquals(waitingList, List.of(u5, u3));
        assertEquals(assignment.getCost(), 12.819, DELTA);
    }

    @Test
    public void casPonderationNiveau() {
        // cas 3.B
        assignment.setPolyTutor(false);
        Student.setLevelWeighting(2);
        List<Arete<Student>> edges = assignment.getAssignment();
        List<Student> waitingList = assignment.getWaitingList();
         

        // assertNotEquals = poids avec pondération de la moyenne uniquement
        assertNotEquals(t1.getWeight(), 1.467, DELTA);
        assertEquals(t2.getWeight(), 1.637, DELTA);
        assertNotEquals(t3.getWeight(), 1.429, DELTA);
        assertEquals(t4.getWeight(), 1.266, DELTA);
        assertEquals(t5.getWeight(), 1.428, DELTA);

        assertEquals(edges.size(), 5);
        assertEquals(waitingList, List.of(u5, u2));
        assertEquals(assignment.getCost(), 11.342, DELTA);
    }

    @Test
    public void casPonderationAbsences() {
        // cas 3.C
        assignment.setPolyTutor(false);
        Student.setAbsenceWeighting(2);
        List<Arete<Student>> edges = assignment.getAssignment();
        List<Student> waitingList = assignment.getWaitingList();
         

        // assertNotEquals = poids sans aucune pondération
        assertNotEquals(u2.getWeight(), 1.055, DELTA);
        assertEquals(u3.getWeight(), 1.306, DELTA);
        assertEquals(u4.getWeight(), 0.965, DELTA);
        assertNotEquals(u5.getWeight(), 1.696, DELTA);
        assertEquals(u6.getWeight(), 1.059, DELTA);

        assertEquals(t1.getWeight(), 1.262, DELTA);
        assertNotEquals(t3.getWeight(), 1.141, DELTA);
        assertEquals(t4.getWeight(), 1.147, DELTA);

        assertEquals(edges.size(), 5);
        assertEquals(waitingList, List.of(u5, u2));
        assertEquals(assignment.getCost(), 12.471, DELTA);
    }

    @Test
    public void casExclusionTuteur() {
        // cas 4.A
        assignment.setPolyTutor(false);
        assignment.removeStudent(t1);
        List<Arete<Student>> edges = assignment.getAssignment();
        List<Student> waitingList = assignment.getWaitingList();
         


        for (Arete<Student> edge : edges) {
            assertNotEquals(edge.getExtremite2(), t1);
        }
        assertEquals(edges.size(), 4);
        assertEquals(waitingList.size(), 3);
        assertEquals(waitingList, List.of(u5, u2, u3));
        assertEquals(assignment.getCost(), 7.178, DELTA);
    }

    @Test
    public void casExclusionTutore() {
        // cas 4.B
        assignment.setPolyTutor(false);
        assignment.removeStudent(u6);
        List<Arete<Student>> edges = assignment.getAssignment();
        List<Student> waitingList = assignment.getWaitingList();
         

        for (Arete<Student> edge : edges) {
            assertNotEquals(edge.getExtremite1(), u6);
        }
        assertEquals(edges.size(), 5);
        assertEquals(waitingList.size(), 1);
        assertEquals(waitingList, List.of(u5));
        assertEquals(assignment.getCost(), 9.457, DELTA);
    }
}
