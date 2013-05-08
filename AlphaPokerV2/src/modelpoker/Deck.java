package modelpoker;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	private ArrayList deck;
	private int idNextCard;
	
	
	public Deck() {
		deck =  new ArrayList(52);
		idNextCard = 0;
		shuffle();
	}
	
	public void shuffle(){
		idNextCard = 0;
		int id = 1;
        String[] suits = {"Club", "Spade", "Heart", "Diamond"};
        String icon = null;
        for(int i = 14; i > 1; i--) {
            for(int j = 0; j < suits.length; j ++) {
            	String suit = suits[j];
                icon = "images/card_" + id + ".png";
                deck.add(new Card(id, i, suit, icon, false));
                id++;
            }
        }
        shuffleDeck();
	}
	
	
	public void restartDeek(){
		idNextCard = 0;
		shuffle();
		for(int i = 0; i < 52; i++){
            if(((Card)deck.get(i)).isFlippedUp()){
                ((Card)deck.get(i)).flipCard();
            }
        }
		shuffleDeck();
	}
	
	private void shuffleDeck(){
		for(int i = 0; i < 100; i++) {
            Collections.shuffle(deck);
        }
	}
	
	public Card getNextCard(){
		Card cardTemp =  (Card) deck.get(idNextCard);
		idNextCard ++;
		return cardTemp;
	}
	
	//getters
	public int getIdNextCard() {
		return idNextCard;
	}
	
}
