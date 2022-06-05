package appli;

import java.util.Objects;

public abstract class Etudiant extends Utilisateur implements Comparable<Etudiant>, CharSequence{
	int annee;
	double moyenne;
	int absences;
	char motivation;

	public Etudiant(String identifiant, String prenom, String nom, double moyenne,int absences, int annee,char motivation) 
			throws IllegalArgumentException {
		super(identifiant, prenom, nom);
		if (moyenne < 0 || moyenne > 20) {
			throw new IllegalArgumentException("La moyenne doit etre comprit entre 0 et 20");
		} else {
			this.moyenne = moyenne;
		}
		if (annee < 1 || annee > 3) {
			throw new IllegalArgumentException("L'annee de l'etudiants doit etre comprit entre 1 et 3");
		} else {
			this.annee = annee;
		}if (motivation != '+' && motivation != '=' && motivation != '-') {
			throw new IllegalArgumentException("La motivation doit etre soit +,= ou -");
		} else {
			this.motivation = motivation;
		}
		this.absences = absences;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(absences, annee, motivation, moyenne);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etudiant other = (Etudiant) obj;
		return absences == other.absences && annee == other.annee && motivation == other.motivation
				&& Double.doubleToLongBits(moyenne) == Double.doubleToLongBits(other.moyenne);
	}
	
	public int compareTo(Etudiant etudiant) {
        return (int) (100*(etudiant.getMoyenne()));
    }
	
	public int commapreTo(Etudiant etudiant) {
		return (int) (100*(etudiant.getAnnee()));  
	}

	public abstract void inscription();

	public void setMoyenne(double moyenne) {
		this.moyenne = moyenne;
	}

	public double getMoyenne() {
		return this.moyenne;
	}

	public int getAnnee() {
		return this.annee;
	}

	public void setAnnee( int annee) {
		this.annee = annee;
	}
	
	public int getAbsences() {
		return absences;
	}

	public void setAbsences(int absences) {
		this.absences = absences;
	}
	public char getMotivation() {
		return motivation;
	}
	public void setMotivation(char motivation) {
		this.motivation = motivation;
	}

	@Override
	public String toString() {
		return super.toString() + "-> Etudiant [annee=" + annee + ", moyenne=" + moyenne + ", motivation=" + motivation + "]";
	}

}
