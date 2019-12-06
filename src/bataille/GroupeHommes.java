package bataille;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;

import attaque.Arme;
import attaque.Pouvoir;
import protagoniste.Homme;
import protagoniste.Monstre;

public class GroupeHommes {
	NavigableSet<Homme> groupe=new TreeSet<>();
	
	public void ajouterHommes(Homme...hommes) {
		Collections.addAll(groupe, hommes);
		
		
	}
	
	private static class ComparateurHommes implements Comparator<Homme>{
		@Override
		public int compare(Homme h1, Homme h2) {
			int result = h2.getForceDeVie() - h1.getForceDeVie();//Plus fort < Moins fort
			if(result==0) {
				String h1Nom=h1.getNom();
				return h1Nom.compareTo(h2.getNom());
			}
			return result;
		}
		
	}
	
	private static class ComparateurArmes implements Comparator<Arme>{
		Monstre<? extends Pouvoir> monstre;
		public ComparateurArmes(Monstre<? extends Pouvoir> monstre) {
			this.monstre=monstre;
			
		}
		@Override
		public int compare(Arme arme1, Arme arme2) {
			Integer damage1=(Integer)arme1.getPointDeDegat();
			Integer damage2=(Integer)arme2.getPointDeDegat();
			
			if(!damage1.equals(damage2)) {
				NavigableMap<Integer, Arme> classementForce =new TreeMap<>();
				classementForce.put(damage1, arme1);
				classementForce.put(damage2, arme2);
				Integer damage=classementForce.floorKey(monstre.getForceDeVie());
				if(damage==null) {
					damage=classementForce.firstKey();
				}
				if(damage.equals(damage2)) {
					return 1;
				}
				return -1;
			}
			String arme1Nom=arme1.getNom();
			return arme1Nom.compareTo(arme2.getNom());//ordre naturel
		}
	}

	
	public List<Homme> choixParticipants(Bataille bataille) {
		Camp<Monstre<? extends Pouvoir>> campMonstres=bataille.getCampMonstres();
		Monstre<? extends Pouvoir> monstre=campMonstres.selectionner();
		NavigableMap<Arme, NavigableSet<Homme>> hommesArmes=new TreeMap<>(new ComparateurArmes(monstre));
		
		for(Homme homme : groupe) {
			Arme arme=homme.choisirArme(monstre);
			if(arme!=null) {
				if(hommesArmes.containsKey(arme)) {
					NavigableSet<Homme> hommeSet = hommesArmes.get(arme);
					hommeSet.add(homme);
				}else {
					NavigableSet<Homme> hommeSet=new TreeSet<>(new ComparateurHommes());
					hommeSet.add(homme);
					hommesArmes.put(arme, hommeSet);
				}
			}	
		}
		return choixMeilleursHommes(bataille, hommesArmes);
	}


	private List<Homme> choixMeilleursHommes(Bataille bataille, NavigableMap<Arme, NavigableSet<Homme>> hommesArmes) {
		List<Homme> listeHommeParticipants=new ArrayList<>();
		Arme arme=hommesArmes.firstKey();
		int taille=0;
		do {
			NavigableMap<Arme, NavigableSet<Homme>> entry=hommesArmes.tailMap(arme, true);
			NavigableSet<Homme> hommeSet= entry.get(arme);
			for(Iterator<Homme> iter=hommeSet.iterator(); iter.hasNext() && taille<3; taille++) {
				Homme homme=iter.next();
				listeHommeParticipants.add(homme);
				bataille.ajouter(homme);
			}
			arme=hommesArmes.higherKey(arme);
		}while(listeHommeParticipants.size()<3 && arme!=null);
		return listeHommeParticipants;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
