package livre;




import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;
import bataille.Bataille;
import bataille.Camp;
import protagoniste.Domaine;
import protagoniste.Heros;
import protagoniste.Homme;
import protagoniste.Monstre;
import protagoniste.ZoneDeCombat;

public class AideEcrivain {
	private Bataille bataille;
	private NavigableSet<Monstre<?>> monstresDomaineSet=new TreeSet<>(new MonstreDomaineComparator());
	private NavigableSet<Monstre<?>> monstresZoneSet=new TreeSet<>(new MonstreZoneComparator());
	private NavigableSet<Monstre<?>> monstreDeFeu;
	private NavigableSet<Monstre<?>> monstreDeGlace;
	private NavigableSet<Monstre<?>> monstreTranchants;
	
	private static class MonstreDomaineComparator implements Comparator<Monstre<?>> {

		@Override
		public int compare(Monstre<?> monstre1, Monstre<?> monstre2) {
			int resultComp=monstre1.getDomaine().compareTo(monstre2.getDomaine());
			if(resultComp==0)
				return monstre1.compareTo(monstre2);
			return resultComp;
		}

	}
	private static class MonstreZoneComparator implements Comparator<Monstre<?>> {

		@Override
		public int compare(Monstre<?> monstre1, Monstre<?> monstre2) {
			int resultZone=monstre1.getZoneDeCombat().compareTo(monstre2.getZoneDeCombat());
			Integer force1=monstre1.getForceDeVie();
			Integer force2=monstre2.getForceDeVie();
			int resultForce= force1.compareTo(force2);
			
			if(resultZone==0) {
				if(resultForce==0)
					return monstre1.compareTo(monstre2);
				return resultForce;
			}
			return resultZone;
		}

	}
	public AideEcrivain(Bataille bataille) {
		this.bataille=bataille;
	}
	
	public NavigableSet<Monstre<?>> getMonstreDeFeu() {
		updateMonstresDomaine();
		return monstreDeFeu;
	}


	public NavigableSet<Monstre<?>> getMonstreDeGlace() {
		updateMonstresDomaine();
		return monstreDeGlace;
	}



	public NavigableSet<Monstre<?>> getMonstreTranchants() {
		updateMonstresDomaine();
		return monstreTranchants;
	}
	

	public String visualiserForcesHumaines() {
		List<Homme> listeTriee = new LinkedList<>();
		Camp<Homme> campHumains=bataille.getCampHumains();
		int indHeros=0;
		//campHumains est un obj d'une classe qui implemente Iterable donc on peut utiliser une foreach sur lui
		for(Homme homme : campHumains ) {
			if (  homme instanceof Heros) {
				listeTriee.add(indHeros, homme);
				indHeros++;
			}else {
				listeTriee.add(homme);//ajoute a la fin de la liste
			}
			
		}
		
		return listeTriee.toString();
		
	}
	
	private String setToString(NavigableSet<Monstre<?>> set) {
		StringBuilder strBld= new StringBuilder();
		for(Monstre<?> monstre : set) {
			strBld.append(monstre.getNom());
			strBld.append(", ");
		}
		int taille=strBld.length();
		strBld.delete(taille - 2, taille-1);
		
		return strBld.toString();
		
	}
	
	
	public String ordreNaturelMonstre() {
		NavigableSet<Monstre<?>> setMonstre=new TreeSet<>();
		for(Monstre<?> monstre : bataille.getCampMonstres()) {
			setMonstre.add(monstre);
		}
		return setToString(setMonstre);
		
		
	}
	
	public void updateMonstresDomaine() {
		
		for(Monstre<?> monstre : bataille.getCampMonstres()) {
			monstresDomaineSet.add(monstre);
		}
	}
	
	public String ordreMonstreDomaine() {
		
		updateMonstresDomaine();
		NavigableSet<Monstre<?>> monstreFeu= new TreeSet<>();
		NavigableSet<Monstre<?>> monstreGlace=new TreeSet<>();
		NavigableSet<Monstre<?>> monstreTranchant=new TreeSet<>();
		for(Monstre<?> monstre : monstresDomaineSet) {
			domaineToString(monstreFeu, monstreGlace, monstreTranchant, monstre);
		}
		
		return ("FEU:\n")+setToString(monstreFeu) + ("\nGLACE:\n") +setToString( monstreGlace) + ("\nTRANCHANT:\n") + setToString(monstreTranchant);
		
	}

	
	private void domaineToString(NavigableSet<Monstre<?>> monstreFeu, NavigableSet<Monstre<?>> monstreGlace, NavigableSet<Monstre<?>> monstreTranchant,
			Monstre<?> monstre) {
		if(monstre.getDomaine()==Domaine.FEU) {
			monstreFeu.add(monstre);
			
		}else if(monstre.getDomaine()==Domaine.GLACE) {
			monstreGlace.add(monstre);
			
		}else {
			monstreTranchant.add(monstre);
		
		}
	}
	
	public void updateMonstresZone() {
		
		for(Monstre<?> monstre : bataille.getCampMonstres()) {
			monstresZoneSet.add(monstre);
		}
	}
	
	
	public String ordreMonstreZone () {

		updateMonstresDomaine();
		
		StringBuilder monstreAer=new StringBuilder("AERIENNE:\n");
		StringBuilder monstreAqua=new StringBuilder("\nAQUATIQUE:\n");
		StringBuilder monstreTer=new StringBuilder("\nTERRESTRE:\n");
		
		for(Monstre<?> monstre : monstresDomaineSet) {
			zoneToString(monstreAer, monstreAqua, monstreTer, monstre);
		}
		
		return monstreAer.toString()+monstreAqua.toString()+monstreTer.toString();
	}
	
	
	private void zoneToString(StringBuilder monstreAer, StringBuilder monstreAqua, StringBuilder monstreTer,
			Monstre<?> monstre) {
		if(monstre.getZoneDeCombat()==ZoneDeCombat.AERIAN) {
			monstreAer.append(monstre.getNom()+" : "+monstre.getForceDeVie()+ ", ");
			
		}else if(monstre.getZoneDeCombat()==ZoneDeCombat.AQUATIQUE) {
			monstreAqua.append(monstre.getNom()+" : "+monstre.getForceDeVie()+ ", ");
			
		}else {
			monstreTer.append(monstre.getNom()+" : "+monstre.getForceDeVie()+ ", ");
			
		}
	}
	
	/*
	 * public Monstre<?> firstMonstreDomaine(Domaine domaine){ boolean found=false;
	 * Monstre<?> monstre = null; for(Iterator<Monstre<?>>
	 * iter=monstresDomaineSet.iterator(); iter.hasNext() && !found; ) {
	 * monstre=iter.next(); if(monstre.getDomaine()==domaine) found=true; }
	 * 
	 * return monstre; }
	 * 
	 * public void initMonstresDeFeu() {
	 * monstreDeFeu=monstresDomaineSet.headSet(firstMonstreDomaine(Domaine.GLACE),
	 * false);
	 * 
	 * }
	 */
	
	 public void initMonstresDeFeu() {
		 monstreDeFeu=monstresDomaineSet.headSet(monstresDomaineSet.ceiling(new Monstre<>("a", 0, null, Domaine.FEU)), true);
		 
	 }
	 
	 public void initMonstresDeGlace() {
		 monstreDeGlace=monstresDomaineSet.subSet(monstresDomaineSet.ceiling(new Monstre<>("a", 0, null, Domaine.GLACE)), true, monstresDomaineSet.ceiling(new Monstre<>("a", 0, null, Domaine.TRANCHANT)), false);
		 
	 }
	 
	 public void initMonstresTranchants() {
		 monstreTranchants=monstresDomaineSet.tailSet(monstresDomaineSet.ceiling(new Monstre<>("a", 0, null, Domaine.TRANCHANT)), true);
	 }
	
}
