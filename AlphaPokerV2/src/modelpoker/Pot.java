package modelpoker;

public class Pot {

	private int pot;
	private int statePot;
	
	public Pot() {
		pot = 0;
		statePot = 0;
	}
	
	//only when the game begin
	public void resetPot() {
		pot = 0;
		statePot = 0;
	}
	
	public void addPot(int pot){
		this.pot += pot;  
	}
	
	public void setStatePot(int statePot) {
		this.statePot = statePot;
	}
	
	
	//getters
	public int getPot() {
		return pot;
	}
	
	public int getStatePot() {
		return statePot;
	}
	
}
