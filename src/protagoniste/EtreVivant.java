package protagoniste;


import bataille.Bataille;

public abstract class EtreVivant implements Comparable<EtreVivant>{
	private String nom;
	private int forceDeVie;
	private Bataille bataille;
	
	public EtreVivant(String nom, int forceDeVie) {
		this.nom = nom;
		this.forceDeVie = forceDeVie;
	}
	
	
	protected void setForceDeVie(int forceDeVie) {
		this.forceDeVie = forceDeVie;
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getForceDeVie() {
		return forceDeVie;
	}
	
	public Bataille getBataille() {
		return bataille;
	}
	public void setBataille(Bataille bataille) {
		this.bataille = bataille;
	}
	
	@Override
	public String toString() {
		return "EtreVivant [nom=" + nom + ", forceDeVie=" + forceDeVie + "]";
	}

	
	
	public void rejointBataille(Bataille bataille) {
		this.setBataille(bataille);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() == obj.getClass()) {
			EtreVivant etreVivant = (EtreVivant) obj;
			if (nom == null) {
				return nom==etreVivant.nom;
			}
			return nom.equals(etreVivant.nom);
		}
		return false;
	}

	public int compareTo(EtreVivant etreVivant){

		if(getClass() == etreVivant.getClass())
			return nom.compareTo(etreVivant.nom);
		return 1;
		
		
	}

	public abstract void mourir();


}
