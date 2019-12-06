package bataille;


import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.security.SecureRandom;
import java.util.Iterator;

import protagoniste.EtreVivant;

public class Camp <Membre extends EtreVivant> implements Iterable<Membre>{

	private List<Membre> listeMembre= new LinkedList<>();
	private Random randomGenerateur = new SecureRandom();

	public void ajouter(Membre etreVivant) {
		if(!listeMembre.contains(etreVivant)) {
			listeMembre.add(etreVivant);//Comme equals est deja definie, add ne ajoute que des nouveaux membres
		}
			
		//Pas besoin de mettre une condition contains
		
	}
	
	public void eliminer(Membre etreVivant) {
		listeMembre.remove(etreVivant);
	}
	
	public String toString() {
		return listeMembre.toString();
	}
	
	public Iterator<Membre> iterator(){
		return listeMembre.iterator();
	}
	
	public int nbCombattants() {
		  return listeMembre.size();
		}

	public Membre selectionner() {
		  
		  int numeroCombattant = randomGenerateur.nextInt(listeMembre.size());
		  return listeMembre.get(numeroCombattant);
		}
}
