package attaque;

import protagoniste.ZoneDeCombat;

public class LancePierre extends Arme{

	private static final int NOMBRE_DEGATS_LANCEPIERRE = 10;

	public LancePierre() {
		
		super(NOMBRE_DEGATS_LANCEPIERRE, "Lance-pierre", ZoneDeCombat.AERIAN, ZoneDeCombat.TERRESTRE);
		
	}
	
	

}
