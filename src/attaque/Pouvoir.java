package attaque;

public abstract class Pouvoir extends ForceDeCombat{
	
	private int nbUtilisationPouvoir;
	private final int nbUtilisationPouvoirInitial;
	
	public Pouvoir(int pointDeDegat, String nom, int nbUtilisationPouvoir) {
		
		super(pointDeDegat, nom);
		
		this.nbUtilisationPouvoir = nbUtilisationPouvoir;
		this.nbUtilisationPouvoirInitial=nbUtilisationPouvoir;
		
	}
	
	public void regenererPouvoir() {
		
		setOperationnel(true);
		nbUtilisationPouvoir=nbUtilisationPouvoirInitial;
		
	}
	
	@Override
	public int utiliser() {
		
		if(nbUtilisationPouvoir==0) {
			setOperationnel(false);
			return 0;
			
			
		}else {
			nbUtilisationPouvoir--;
			return getPointDeDegat();
		
		}
		
		
	}
	
	
	
	
	

}
