package attaque;

import protagoniste.ZoneDeCombat;

public class Arc extends Arme{

	private int nbFlechesRestantes;
	private static final int NOMBRE_DE_DEGATS_ARC=50;
	
	public Arc(int nbFlechesRestantes) {
		super(NOMBRE_DE_DEGATS_ARC, "Arc", ZoneDeCombat.AQUATIQUE, ZoneDeCombat.AERIAN, ZoneDeCombat.TERRESTRE);
		this.nbFlechesRestantes=nbFlechesRestantes;
		
	}
	
	@Override
	public int utiliser() {
		if(nbFlechesRestantes==0) {
			setOperationnel(false);
			return 0;
		}else {
			nbFlechesRestantes--;
			return getPointDeDegat();
		}
	}
	@Override
	public boolean isOperationnel() {
		return nbFlechesRestantes!=0;
		
	}

}
