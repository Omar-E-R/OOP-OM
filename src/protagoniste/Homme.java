package protagoniste;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import attaque.Arme;
import attaque.Pouvoir;
import bataille.Bataille;

public class Homme extends EtreVivant {

	private static final int FORCE_DE_VIE = 70;
	private Map<ZoneDeCombat, List<Arme>> armes=new EnumMap<>(ZoneDeCombat.class);
	private Arme armeChoisie;
	public Homme(String nom) {
		super(nom, FORCE_DE_VIE);

	}
	
	public Arme getArmeChoisie() {
		return armeChoisie;
	}

	@Override
	public void rejointBataille(Bataille bataille) {
		super.rejointBataille(bataille);
		bataille.ajouter(this);
	}
	@Override
	public void mourir() {
		getBataille().eliminer(this);
		
	}

	@Override
	public String toString() {
		return "Homme [nom=" + getNom() + ", forceDeVie=" + getForceDeVie() + "]";
	}
	
	public void ajouterArmes(Arme...armes) {
		for(Arme arme: armes) {
			
			for(ZoneDeCombat zoneDeCombat : arme.getZonesDeCombats()) {
				if (this.armes.containsKey(zoneDeCombat)) {
					List<Arme> listeArme=this.armes.get(zoneDeCombat);
					listeArme.add(arme);	
				}else {
					List<Arme> listeArme=new ArrayList<>();
					listeArme.add(arme);
					this.armes.put(zoneDeCombat, listeArme);
					 
					
				}
			}
		}
		
	}
	
	public boolean supprimerArme(Arme arme) {
		for(Map.Entry<ZoneDeCombat, List<Arme>> entry : this.armes.entrySet() ) {
			Set<ZoneDeCombat> zonesDeCombats=arme.getZonesDeCombats();
			if (zonesDeCombats.contains(entry.getKey())) {
				List<Arme> listeArme=entry.getValue();
				return listeArme.remove(arme);	
			}
		}
		return false;
		
	}
	
	private static class KeyArme extends Arme{

		public KeyArme(int pointDeDegat) {
			super(pointDeDegat, "", ZoneDeCombat.AERIAN);
		}
		
	}
	
	public Arme choisirArme(Monstre<? extends Pouvoir> monstreACombattre) {
		ZoneDeCombat zoneDeCombatMonstre= monstreACombattre.getZoneDeCombat();
		if(armes.containsKey(zoneDeCombatMonstre)) {
			NavigableSet<Arme> armesTriees=new TreeSet<>(armes.get(zoneDeCombatMonstre));
			if(!armesTriees.isEmpty()) {
				int forceDeVieMonstre=monstreACombattre.getForceDeVie();
				NavigableSet<Arme> armesAdaptes=armesTriees.tailSet(new KeyArme(forceDeVieMonstre), true);
				if(!armesAdaptes.isEmpty()) {
					armeChoisie=armesAdaptes.first();
					return armeChoisie;
				}else {
					armeChoisie=armesTriees.last();
					return armeChoisie; 
				}
			}
		}
		return null;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
