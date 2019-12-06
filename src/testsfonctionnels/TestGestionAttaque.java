package testsfonctionnels;

import attaque.BouleDeFeu;
import attaque.Eclair;
import attaque.Feu;
import attaque.Lave;
import protagoniste.Domaine;
import protagoniste.Monstre;
import protagoniste.ZoneDeCombat;

public class TestGestionAttaque {

	public static void main(String[] args) {
		
		
		
		Monstre<Feu> dragotenebre=new Monstre<>("Dragotenebre", 200, ZoneDeCombat.AERIAN, Domaine.FEU, new Lave(10), new Eclair(10), new BouleDeFeu(5));
		
		dragotenebre.entreEnCombat();
		
		System.out.println(dragotenebre.toString());
		
		System.out.println(dragotenebre.attaque());
		
		System.out.println(dragotenebre.attaque());
		
		System.out.println(dragotenebre.attaque());
		
	}

}
