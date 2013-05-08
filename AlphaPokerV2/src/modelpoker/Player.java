package modelpoker;


public class Player {

	private String nickName;
	private Hand hand;
	private Chip chips;
	private String avatar;
	private boolean isPlaying;
	
	public Player(String namePlayer, int nChips) {
		chips =  new Chip(nChips);
		hand =  new Hand();
		this.nickName = namePlayer; 
		this.avatar =  "face.png";
		isPlaying = false;
	}
	
	
	public void setHandCards(Card card1, Card card2){
		hand = new Hand(card1, card2);
	}
	
	
	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}
	
	//getters
	public Chip getChips() {
		return chips;
	}
	
	public String getIcon() {
		return avatar;
	}
	
	public String getNamePlayer() {
		return nickName;
	}
	
	public Hand getHand() {
		return hand;
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}
	
	
}
