/**
 * 
 */
package attaque;

public abstract class ForceDeCombat {

	/**
	 * @param pointDeDegat : integer
	 * @param nom : string
	 * @param operationnel: boolean
	 * 
	 */
	private int pointDeDegat;
	private String nom;
	private boolean operationnel;
	
	
	public ForceDeCombat(int pointDeDegat, String nom) {
		
		this.pointDeDegat = pointDeDegat;
		this.nom = nom;
		this.operationnel=true;
		
	}
	
	public int getPointDeDegat() {
		
		return pointDeDegat;
		
	}
	
	public String getNom() {
		
		return nom;
	}
	
	public boolean isOperationnel() {
		
		return operationnel;
	}
	
	protected void setOperationnel(boolean operationnel) {
		this.operationnel=operationnel;
	}
	
	
	
	@Override
	public String toString() {
		
		return "ForceDeCombat [nom=" + nom  + ", pointDeDegat=" + pointDeDegat + "]";
	}

	public int utiliser() {
		
		if(!operationnel) {
			return 0;
		}
		
		return pointDeDegat;
		
		
	}

	
	
	

}
