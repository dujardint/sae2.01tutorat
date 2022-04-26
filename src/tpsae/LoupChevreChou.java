package tpsae;
import java.util.ArrayList;
import java.util.List;

public class LoupChevreChou implements ImplicitGraph {

    final int BERGER = 0;
    final int LOUP = 1;
    final int CHEVRE = 2;
    final int CHOU = 3;

    @Override
    public String type() {
        return "digraph";
    }

    @Override
    public String name() {
        return "exercice-loup-chevre-chou";
    }


    @Override
    public List<String> initialNodes () {
        List<String> l = new ArrayList<String>();
        l.add("GGGG");
        return l;
    }

    @Override
    public List<String> successors (String sommet) {    
        char bergerOld = sommet.charAt(BERGER);
        char loupOld = sommet.charAt(LOUP);
        char chevreOld = sommet.charAt(CHEVRE);
        char chouOld = sommet.charAt(CHOU);

        List<String> resultat = new ArrayList<>();
        
        // le berger traverse seul
        char bergerNew = lautreRive(bergerOld);
        if (configurationPerdante(bergerNew, loupOld, chevreOld, chouOld)) {
            resultat.add(sommet(bergerNew, loupOld, chevreOld, chouOld));    
        }

        // le berger traverse avec le loup si le loup se trouve sur la même rive
        if (loupOld == bergerOld) {
            char loupNew = lautreRive(loupOld);
            if (configurationPerdante(bergerNew, loupNew, chevreOld, chouOld)) {
                resultat.add(sommet(bergerNew, loupNew, chevreOld, chouOld));
            }
        }

        // le berger traverse avec la chèvre si la chèvre se trouve sur la même rive
        // TODO: compléter
        if (chevreOld == bergerOld ) {
            char chevreNew = lautreRive(chevreOld);
            if (configurationPerdante(bergerNew,loupOld,chevreNew,chouOld)){
                resultat.add(sommet(bergerNew,loupOld,chevreNew,chouOld));
            }
        }

        // le berger traverse avec le chou si la chou se trouve sur la même rive
        // TODO: compléter
        if (chouOld == bergerOld ) {
            char chouNew = lautreRive(chouOld);
            if (configurationPerdante(bergerNew,loupOld,chevreOld,chouNew)){
                resultat.add(sommet(bergerNew,loupOld,chevreOld,chouNew));
            }
        }

        return resultat;
    }

    private char lautreRive (char c) {
        if (c == 'G') return 'D';
        else if (c == 'D') return 'G';
        else return 'X';
    }

    private boolean configurationPerdante (char berger, char loup, char chevre, char chou) {
        return ! (loup == chevre && berger != loup ||
            chevre == chou && berger != chevre);
    }

    private String sommet (char berger, char loup, char chevre, char chou) {
            return ""+berger+loup+chevre+chou;
    }


    /** Uniquement pour tester l'implémentation. */
   public static void main (String[] args) {
        LoupChevreChou lcc = new LoupChevreChou();
        System.out.println("Doit afficher DDGD DDDD");
        afficherListe(lcc.successors("GDGD"));
    } 

    /** Utilisée pour les tests pour afficher une liste */
    private static void afficherListe (List<String> list) {
        for (String s : list) {
            System.out.print(s + " ");
        }
        System.out.println();
    }



}
