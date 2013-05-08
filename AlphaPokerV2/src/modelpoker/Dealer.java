package modelpoker;

import java.util.ArrayList;

import control.AlphaPokerControl;
import utilities.TimerPoker;

public class Dealer {
	
	private Deck deck; 
	private HandEvaluator handEvaluator;
	private TimerPoker timerPoker;
	
	public Dealer() {
		deck =  new Deck();
		handEvaluator = new HandEvaluator();
		timerPoker = new TimerPoker();
	}
	
	public void chekPlay(String nickName, String typePlay, String value) {
		String currentPlayer = AlphaPokerControl.getInstance().getControlGame().getGamePoker().getTablePoker().getNickCurrentPlayer();
		if(AlphaPokerControl.getInstance().getControlGame().getGamePoker().getTablePoker().isRound()){
			if(currentPlayer.equals(nickName)){
				setFocusCurrentPlayer(false);
				System.out.println("Entro la Jugada " + typePlay);
				if(typePlay.equals("fold")){
					AlphaPokerControl.getInstance().getControlGame().getGamePoker().getTablePoker().doFold();
				}else if(typePlay.equals("check")){
					AlphaPokerControl.getInstance().getControlGame().getGamePoker().getTablePoker().doCheck();
				}else if(typePlay.equals("raise")){
					System.out.println(value);
					AlphaPokerControl.getInstance().getControlGame().getGamePoker().getTablePoker().doRaise(value);
				}else if(typePlay.equals("call")){
					AlphaPokerControl.getInstance().getControlGame().getGamePoker().getTablePoker().doCall();
				}
			}
		}
	}
	
	
	public void checkRound() {
		//call hand evaluator etc
		ArrayList fullHand = new ArrayList();
		Card tableCards [] = AlphaPokerControl.getInstance().getControlGame().getGamePoker().getTablePoker().getCards();
		for (int i = 0; i < tableCards.length; i++) {
			fullHand.add(tableCards[i]);
		}
		
		//ArrayList handResults = new ArrayList();
		long maxHand = 0;
		int idPlayerWinner = 0;
		for (int i = 0; i < 8; i++) {
			Player player = AlphaPokerControl.getInstance().getControlGame().getGamePoker().getTablePoker().getPlayer(i);
			if(player != null){
				if(player.isPlaying()){
					fullHand.add(player.getHand().getCard1());
					fullHand.add(player.getHand().getCard2());
					long l = handEvaluator.getHandValue(fullHand);
					//handResults.add(Long.toString(l));
					if(maxHand < l){
						maxHand = l;
						idPlayerWinner = i;
					}
					fullHand.remove(fullHand.size() - 1);
					fullHand.remove(fullHand.size() - 1);
				}
			}
		}
		System.out.println("el gandor es " + idPlayerWinner + " con " + maxHand);
		chooseWinner(idPlayerWinner);
		
	}

	
	private void chooseWinner(int idPlayerWinner) {
		AlphaPokerControl.getInstance().getControlGame().getGamePoker().getTablePoker().showWinner(idPlayerWinner);
		AlphaPokerControl.getInstance().getControlGame().getGamePoker().checkStartRound();
	}

	public void setFocusCurrentPlayer(boolean state){
		int currentPlayer = AlphaPokerControl.getInstance().getControlGame().getGamePoker().getTablePoker().getCurrentPlayer();
		AlphaPokerControl.getInstance().getControlGUI().getTableGUI().isFocusPlayer(currentPlayer, state);
	}

	public Card popDeck(){
		return (Card)deck.getNextCard();
	}
	
	//getters
	public Deck getDeck() {
		return deck;
	}
	
	public HandEvaluator getHandEvaluator() {
		return handEvaluator;
	}

	public TimerPoker getTimerPoker() {
		return timerPoker;
	}

	
	
}
