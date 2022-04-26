package fr.ulille.but.sae2_02.graphes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.jgrapht.alg.interfaces.MatchingAlgorithm;
import org.jgrapht.alg.matching.KuhnMunkresMinimalWeightBipartitePerfectMatching;

/** Calculer une affectation parfaite de cout minimal dans un graphe bi-partite complet.
 * Condition qui doivent être remplies par le graphe:
 * (1) les sommets du graphe sont partitionnés dans deux ensembles Partie1 et Partie2 de taille égale;
 * (2) le graphe est complet, càd pour tout couple de sommets s1 dans Partie1 et s2 dans Partie2, il existe une arête entre s1 et s2.
 * 
 * @author Iovka Boneva
 *
 * @param <S> Le type des sommets du graphe.
 */
public class CalculAffectation<S> {
	
	private MatchingAlgorithm.Matching<S, Arete<S>> matching;
	
	/** Instantiation et calcul de l'affectation. */
	public CalculAffectation(GrapheNonOrienteValue<S> graphe, List<S> partie1, List<S> partie2) {
		KuhnMunkresMinimalWeightBipartitePerfectMatching<S, Arete<S>> algo = new KuhnMunkresMinimalWeightBipartitePerfectMatching<>(graphe.getJGraph(), new HashSet<>(partie1), new HashSet<>(partie2));
		try {
			matching = algo.getMatching();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Le graphe donné n'est pas un graphe bi-partite complet de parties de tailles égales.");
		}
	}

	/** Retourne l'ensemble des arêtes qui constituent l'affectation de cout minimal. */
	public List<Arete<S>> getAffectation () {
		return new ArrayList<>(matching.getEdges());
	}
	
	/** Retourne le cout total de l'affectation de cout minimal. */
	public double getCout () {
		return matching.getWeight();
	}
}
