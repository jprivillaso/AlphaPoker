package modelpoker;

public class Chip {

	private int nChips; // chips that i have
	private int acumChips; // chips accumulated
	
	public Chip(int nChips) {
		this.nChips = nChips;
		this.acumChips = 0;
	}
	
	//setters
	public void setnChips(int nChips) {
		this.nChips = nChips;
	}
	
	public void resetAcumChips(){
		this.acumChips = 0;
	}
	
	public void setAcumChips(int acumChips) {
		this.acumChips += acumChips;
	}
	
	//getters
	public int getnChips() {
		return nChips;
	}
	
	public int getAcumChips() {
		return acumChips;
	}
	
}
