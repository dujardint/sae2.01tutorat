package fr.ulille.but.sae2_02.graphes;

/** Arête d'un graphe non orienté.
 * Une arête a deux extrémités. 
 * @param S le type des sommets du graphe
 * @author Iovka Boneva
 *
 */
public class Arete<S> {
	
	private S extremite1;
	private S extremite2;
	
	public Arete(S extremite1, S extremite2) {
		this.extremite1 = extremite1;
		this.extremite2 = extremite2;
	}

	public S getExtremite1() {
		return extremite1;
	}
	
	public S getExtremite2() {
		return extremite2;
	}
	
	@Override
	public String toString() {
		return String.format("Arete(%s, %s)", getExtremite1(), getExtremite2());
	}
}
