package protagoniste;

import java.util.Arrays;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import attaque.Pouvoir;
import bataille.Bataille;

public class Monstre<E extends Pouvoir> extends EtreVivant {

	private E[] attaques;
	private ZoneDeCombat zoneDeCombat;
	private Domaine domaine;
	private GestionAttaques gestionAttaques;
	
	private static Random indiceAleatoire= new Random();
	
	
	
	@SafeVarargs
	public Monstre(String nom, int forceDeVie, ZoneDeCombat zoneDeCombat, Domaine domaine, E... attaques) {
		super(nom, forceDeVie);
		this.zoneDeCombat=zoneDeCombat;
		this.domaine=domaine;
		this.attaques=attaques;
		
	}
	
	
	
	private class GestionAttaques implements Iterator<E>{
		private E[] attaquesPossibles = attaques.clone();
		private int nbAttaquePossibles = attaques.length;
		
		public boolean hasNext() {
			for(int i=0; i<nbAttaquePossibles; i++) {
				subHasNext(i);
			}
			return nbAttaquePossibles!=0;
			
		}

		private void subHasNext(int i) {
			if(!attaquesPossibles[i].isOperationnel()) {
				attaquesPossibles[i]=attaquesPossibles[nbAttaquePossibles - 1];
				nbAttaquePossibles--;
				
			}
		}
		
		public E next(){
			if(hasNext()) {
				return attaquesPossibles[indiceAleatoire.nextInt(nbAttaquePossibles)];
			}
			
			throw new NoSuchElementException("0 attacks are available");	
		}
	}
	
	
	
	


	public ZoneDeCombat getZoneDeCombat() {
		return zoneDeCombat;
	}
	
	@Override
	public String toString() {
		return "Monstre [getNom()=" + getNom() + ", attaques=" + Arrays.toString(attaques) + ", zoneDeCombat=" + zoneDeCombat + ", getForceDeVie()="
				+ getForceDeVie() + "]";
	}

	public Domaine getDomaine() {
		return domaine;
	}
	
	public void entreEnCombat() {
		for(int i=0; i<attaques.length; i++) {
			if(!attaques[i].isOperationnel()) {
				attaques[i].regenererPouvoir();
				
			}
		}
		gestionAttaques=new GestionAttaques();
		
	}
	
	public E attaque() {
		if(gestionAttaques.hasNext()) {
			return gestionAttaques.next();
		}
		return null;
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


}
