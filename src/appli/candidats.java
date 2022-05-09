package appli;

public class candidats {

	String tuteur;
	String tutore;
	double distance;
	
	public PaireCandidats(String tuteur, String tutore, double distance) {
		this.tuteur = tuteur;
		this.tutore = tutore;
		this.distance = distance;
	}

	public String getTuteur() {
		return tuteur;
	}

	@Override
	public String toString() {
		return "PaireCandidats [tuteur=" + tuteur + ", tutore=" + tutore + ", distance=" + distance + "]";
	}

	public void setTuteur(String tuteur) {
		this.tuteur = tuteur;
	}

	public String getTutore() {
		return tutore;
	}

	public void setTutore(String tutore) {
		this.tutore = tutore;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
}
