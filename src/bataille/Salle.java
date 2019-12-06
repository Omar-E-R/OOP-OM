package bataille;



import protagoniste.ZoneDeCombat;

public class Salle{

	private int numSalle;
	private ZoneDeCombat zoneDeCombat;
	
	public Salle(int numSalle, ZoneDeCombat zoneDeCombat) {
		this.numSalle=numSalle;
		this.zoneDeCombat=zoneDeCombat;
	}

	public int getNumSalle() {
		return numSalle;
	}

	public ZoneDeCombat getZoneDeCombat() {
		return zoneDeCombat;
	}

	@Override
	public String toString() {
		return "Salle n°" + numSalle + " de type combat " + zoneDeCombat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numSalle;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Salle other = (Salle) obj;
		return numSalle == other.numSalle;

	}



	
}
