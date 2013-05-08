package control;

import android.test.IsolatedContext;
import TVPC.poker.Play;


public class GUIControl {

	private Play play;
	
	public GUIControl() {
		play = new Play() ;
	}
	
	public void showGame(){
		//Waiting.getInstance().pasar();
	}

	public void showCards(String card1, String card2) {
		play.paintCards("card_9", "card_9");
	}
	
	//getters
	public Play getPlay() {
		return play;
	}
	
	
}
