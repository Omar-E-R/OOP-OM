/**
 * 
 */
package attaque;


import java.util.HashSet;
import java.util.Set;

import protagoniste.ZoneDeCombat;

/**
 * @author Omar
 *
 */
public abstract class Arme extends ForceDeCombat implements Comparable<Arme>{
	private Set<ZoneDeCombat> zonesDeCombats=new HashSet<>();
	
	public Set<ZoneDeCombat> getZonesDeCombats() {
		return zonesDeCombats;
	}
	


	public Arme(int pointDeDegat, String nom, ZoneDeCombat...zonesDeCombats) {
		super(pointDeDegat, nom);
		for(ZoneDeCombat zoneDeCombat : zonesDeCombats) {
			this.zonesDeCombats.add(zoneDeCombat);
		}
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * getPointDeDegat();
//		result = result * prime + ((getNom()== null) ? 0 : getNom().hashCode());
//		result = prime * result +  ((zonesDeCombats == null) ? 0 : zonesDeCombats.hashCode());
//		return result;
//	}
//
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arme other = (Arme) obj;
		if (zonesDeCombats == null) {
			if (other.zonesDeCombats != null)
				return false;
		} else{
			return zonesDeCombats.equals(other.zonesDeCombats);
		}
		return true;
	}
	
	@Override
	public int compareTo(Arme arme) {
		if(arme==null)
			return 1;
		if(arme.isOperationnel()==this.isOperationnel()) {
			if(isOperationnel()) {//Les deux sont operationnel
				int result = arme.getPointDeDegat() - this.getPointDeDegat();
				if(result==0) {
					return getNom().compareTo(arme.getNom());
				}else {
					return result;
				}
			}else {//les deux sont non operationnel
				return getNom().compareTo(arme.getNom());
			}
			
		}else {
			if(arme.isOperationnel()) {
				return -1;
			}else {
				return 1;
			}
		}
		
	}
	
	
	

}
