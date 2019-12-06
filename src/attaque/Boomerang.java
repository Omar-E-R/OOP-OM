package attaque;

import protagoniste.ZoneDeCombat;

public class Boomerang extends Arme{

	private static final int NOMBRE_DEGATS_BOOMERANG = 20;

	public Boomerang() {
		super(NOMBRE_DEGATS_BOOMERANG, "Boomerang", ZoneDeCombat.AERIAN, ZoneDeCombat.TERRESTRE);
	}

}
