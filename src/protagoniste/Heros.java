package protagoniste;

public class Heros extends Homme {
	private static final int FORCE_DE_VIE = 100;
	public Heros(String nom) {
		super(nom);
		setForceDeVie(FORCE_DE_VIE);
	}
	@Override
	public String toString() {
		return "Heros [nom=" + getNom() + ", forceDeVie=" + getForceDeVie() + "]";
	}
	

}
