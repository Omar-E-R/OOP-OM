package bataille;


import attaque.Pouvoir;
import protagoniste.Homme;
import protagoniste.Monstre;

public class Bataille {
	
	private Camp<Homme> campHumains=new Camp<>();
	private Camp<Monstre<? extends Pouvoir>> campMonstres=new Camp<>();
	

	public Camp<Homme> getCampHumains() {
		return campHumains;
	}
	public Camp<Monstre<? extends Pouvoir>> getCampMonstres() {
		return campMonstres;
	}
	public void ajouter(Homme homme) {
		campHumains.ajouter(homme);
	}
	public void ajouter(Monstre<? extends Pouvoir> monstre) {
		campMonstres.ajouter(monstre);
	}
	public void eliminer(Homme homme) {
		campHumains.eliminer(homme);
	}
	public void eliminer(Monstre<? extends Pouvoir> monstre) {
		campMonstres.eliminer(monstre);
	}
	

}
