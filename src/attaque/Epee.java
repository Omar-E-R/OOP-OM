package attaque;

import protagoniste.ZoneDeCombat;

public class Epee extends Arme{

	private static final int NOMBRE_DEGATS_EPEE = 80;
	@SuppressWarnings("unused")
	private String nomEpee;
	
	public Epee(String nomEpee) {
		
		super(NOMBRE_DEGATS_EPEE, nomEpee, ZoneDeCombat.AQUATIQUE, ZoneDeCombat.TERRESTRE);
		this.nomEpee=nomEpee;
		

	}
	

}
