package attaque;

public abstract class TranchantInfini extends Tranchant {

	public TranchantInfini(int pointDeDegat, String nom) {
		super(pointDeDegat, nom, 1);
		
	}
	
@Override
	public int utiliser(){
		return this.getPointDeDegat();
	}
}