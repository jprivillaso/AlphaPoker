package modelpoker;

public class Card {

	private int value;
	private String suit;
	private String icon;
	private String frontIcon;
	private String backIcon;
	private int idCard;
	private boolean isFlippedUp;

	public Card(int idCard, int numberCard, String suit, String frontIcon, boolean isFlippedUp) {
		this.idCard = idCard;
		this.value = numberCard;
		this.suit = suit;
		this.frontIcon = frontIcon;
		this.isFlippedUp = isFlippedUp;
		this.backIcon = "images/back_card.png";
		
		if(isFlippedUp)
            icon = frontIcon;
        else icon = backIcon;
	}

	
	//change view of card
	public void flipCard() {
        if(isFlippedUp()) {
            isFlippedUp = false;
            icon = backIcon;
        } else {
            isFlippedUp = true;
            icon = frontIcon;
        }
    }
	
	// getters
	public String getIcon() {
		return icon;
	}

	public int getValue() {
		return value;
	}

	public String getSuit() {
		return suit;
	}

	public boolean isFlippedUp() {
		return isFlippedUp;
	}
	
	public int getIdCard() {
		return idCard;
	}
	
	public String getFrontIcon() {
		return frontIcon;
	}

}
