package bataille;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import attaque.Pouvoir;
import protagoniste.Monstre;
import protagoniste.ZoneDeCombat;
import protagoniste.ZoneDeCombatNonCompatibleException;

public class Grotte {
	private Map<Salle, List<Salle>> planGrotte=new LinkedHashMap<>();//Direct accessibility on the Set of Salle from another Salle, and orders of entry are conserved
	private Map<Salle, Bataille> batailles=new HashMap<>();//Direct accessibility on the Bataille from a Salle
	private Set<Salle> sallesExplorees=new HashSet<>();//Salle that are already explored by humans, it has no double copies
	private int numeroSalleDecisive;
	
	private Random randGen= new SecureRandom();

	public void setNumeroSalleDecisive(int numeroSalleDecisive) {
		this.numeroSalleDecisive = numeroSalleDecisive;
	}
	public void ajouterSalle(ZoneDeCombat zoneDeCombat, Monstre<? extends Pouvoir>...monstres) throws ZoneDeCombatNonCompatibleException {
		for(Monstre<? extends Pouvoir> monstre : monstres) {
			if(monstre.getZoneDeCombat()!=zoneDeCombat) {
				throw new ZoneDeCombatNonCompatibleException("La zone de combat de la salle est de type "+zoneDeCombat+", alors que celle du monstre est "+monstre.getZoneDeCombat());
			}
		}
		
		Salle salle=new Salle(planGrotte.size()+1, zoneDeCombat);
		Bataille bataille=new Bataille();
		for(Monstre<? extends Pouvoir> monstre : monstres) {
			bataille.ajouter(monstre);
		}
		planGrotte.put(salle, new ArrayList<Salle>());
		batailles.put(salle, bataille);
		
		
	}
	
	public String afficherPlanGrotte() {
		 StringBuilder affichage = new StringBuilder();
		  for (Map.Entry<Salle, List<Salle>> entry : planGrotte.entrySet()) {
		   Salle salle = entry.getKey();
		   List<Salle> acces = planGrotte.get(salle);
		   affichage.append("La " + salle + ".\nelle possede " + acces.size() + " acces : ");
		   for (Salle access : acces) {
		    affichage.append(" vers la salle " + access);
		   }
		   Bataille bataille = batailles.get(salle);
		   Camp<Monstre<? extends Pouvoir>> camp = bataille.getCampMonstres();
		   Monstre<? extends Pouvoir> monstre = camp.selectionner();
		   if (camp.nbCombattants() > 1) {
		    affichage.append("\n" + camp.nbCombattants() + " monstres de type ");
		   } else {
		    affichage.append("\nUn monstre de type ");
		   }
		   affichage.append(monstre.getNom() + " la protege.\n");
		   if (salle.getNumSalle() == numeroSalleDecisive) {
		    affichage.append("C'est dans cette salle que se trouve la pierre de sang.\n");
		   }
		   affichage.append("\n");
		  }
		  return affichage.toString();
	}
	
	private Salle trouverSalle(int numeroSalle) {
		for(Salle salle : planGrotte.keySet()) {
			if(salle.getNumSalle()==numeroSalle) {
				return salle;
			}
		}
		return null;
	}
	
	public void configurerAcces(int numSalleOrigin, int...numSallesAccessibles) {
		Salle salleOrigin=trouverSalle(numSalleOrigin);
		List<Salle> listeSalle=planGrotte.get(salleOrigin);
		
		for(int numSalle : numSallesAccessibles) {
			listeSalle.add(trouverSalle(numSalle));
		}
		
	}
	
	public boolean salleDecisive(Salle salle) {
		return salle.getNumSalle()==numeroSalleDecisive;
	}
	
	public Salle premiereSalle() {
		Set<Salle> salleSet= planGrotte.keySet();
		Iterator<Salle> iterSalleSet=salleSet.iterator();
		Salle salle= iterSalleSet.next();
		sallesExplorees.add(salle);
		return salle;
	}
	
	public Salle salleSuivante(Salle salleAQuitter) {
		List<Salle> sallesAccessibles=planGrotte.get(salleAQuitter);
		List<Salle> sallesAccessiblesCopy=new ArrayList<>(sallesAccessibles);
		
		sallesAccessibles.removeAll(sallesExplorees);
		if(sallesAccessibles.isEmpty()) {
			sallesAccessibles.addAll(sallesAccessiblesCopy);
		}
		
		Salle salleDestination=sallesAccessibles.get(randGen.nextInt(sallesAccessibles.size()));
	
		sallesExplorees.add(salleDestination);
		
		return salleDestination;
		
	}

}
