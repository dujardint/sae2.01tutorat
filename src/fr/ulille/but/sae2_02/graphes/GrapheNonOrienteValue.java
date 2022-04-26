package fr.ulille.but.sae2_02.graphes;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.graph.DefaultUndirectedWeightedGraph;

/** Représenter un graphe non orienté valué.
 * Le graphe est construit en ajoutant des sommets, puis des arêtes.
 * Ne permet pas de supprimer des arêtes ou des sommets.
 * 
 * @author Iovka Boneva
 *
 * @param <S> Le type des sommets du graphe.
 */
public class GrapheNonOrienteValue<S> {

	private DefaultUndirectedWeightedGraph<S, Arete<S>> graph = new DefaultUndirectedWeightedGraph<S,Arete<S>>((Class<? extends Arete<S>>) Arete.class);

	protected DefaultUndirectedWeightedGraph<S, Arete<S>> getJGraph () {
		return graph;
	}
	
	/** Ajoute un sommet au graphe.
	 * N'a pas d'effet si un même sommet est ajouté une deuxième fois.
	 * 
	 * @param sommet
	 */
	public void ajouterSommet (S sommet) {
		graph.addVertex(sommet);
	}
	
	/** L'ensemble des sommets du graphe. */
	public List<S> sommets () {
		return new ArrayList<>(graph.vertexSet()); 
	}

	/** Vérifier si le graphe contient un sommet. */
	public boolean contientSommet(S sommet) {
		return graph.containsVertex(sommet);
	}

	/** Ajoute une arête ayant le poids spécifié.
	 * Les sommets extrémités de l'arête doivent être ajoutés au préalable.
	 * @see #ajouterSommet(Object)
	 * 
	 * @param extremite1 Un sommet extrémité de l'arête.
	 * @param extremite2 L'autre sommet extrémité de l'arête.
	 * @param poids Le poids de l'arête.
	 * @throws IllegalArgumentException Si un des sommets extrémités n'est pas ajouté au graphe au préalable.
	 * @throws IllegalArgumentException Si une arête entre ces sommets existe déjà dans le graphe.
	 */
	public void ajouterArete(S extremite1, S extremite2, double poids) {
		if (! graph.containsVertex(extremite1) || ! graph.containsVertex(extremite2))
			throw new IllegalArgumentException("Sommet inexistant. Ajouter les sommets extrémités avant d'ajouter une arête.");
		if (graph.getEdge(extremite1, extremite2) != null)
			throw new IllegalArgumentException("Le graphe contient déjà une telle arête.");
		Arete<S> edge = new Arete<>(extremite1, extremite2);
		graph.addEdge(extremite1, extremite2, edge);
		graph.setEdgeWeight(edge, poids);
	}
	
	/** L'ensemble des arêtes du graphe. */
	public List<Arete<S>> aretes () {
		return new ArrayList<>(graph.edgeSet());
	}
	
	/** Vérifier si le graphe contient une arête. */
	public boolean contientArete(S extremite1, S extremite2) {
		return graph.containsEdge(extremite1, extremite2);
	}
	
	/** Retourne l'arête du graphe entre les deux extrémités.
	 * L'arête retournée peut avoir ses extrémités inversées par rapport aux paramètres de la méthode. 
	 * @param extremite1
	 * @param extremite2
	 * @throws IllegalArgumentException si une telle arête n'existe pas dans le graphe.
	 */
	public Arete<S> getArete(S extremite1, S extremite2) {
		Arete<S> a = graph.getEdge(extremite1, extremite2);
		if (a == null)
			throw new IllegalArgumentException("Le graphe ne contient pas d'arête entre ces sommets.");
		return a;
	}
	
	/** Le poids de l'arête entre deux sommets.
	 * @see #contientArete(Object, Object)
	 * 
	 * @param extremite1 L'une des extrémités de l'arête.
	 * @param extremite2 L'autre extrémité de l'arête.
	 * @return Le poids de l'arête
	 * @throws IllegalArgumentException si aucune arête entre ces deux sommets.
	 */
	public double getPoids (S extremite1, S extremite2) {
		Arete<S> edge = graph.getEdge(extremite1, extremite2);
		if (edge == null) throw new IllegalArgumentException("Le graphe ne contient pas de telle arête.");
		else return graph.getEdgeWeight(edge);
	}
	
	@Override
	public String toString () {
		String sommets = graph.vertexSet().toString();
		sommets = "{" + sommets.substring(1, sommets.length()-1) + "}";
		
		StringBuilder saretes = new StringBuilder();
		for (Arete<S> a : aretes()) {
			saretes.append(String.format("(%s, %s):%f, ", a.getExtremite1(), a.getExtremite2(), getPoids(a.getExtremite1(), a.getExtremite2())));
		}
		if (saretes.length() >= 2)
			saretes.delete(saretes.length()-2, saretes.length());
		
		return String.format("GrapheOrienteNonValue[ Sommets: %s, Arêtes et poids: %s ]", sommets, saretes.toString()); 
	}
	
}
