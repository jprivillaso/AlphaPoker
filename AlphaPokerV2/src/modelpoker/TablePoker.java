package modelpoker;


import control.AlphaPokerControl;

public class TablePoker {

	private Pot pot;
	private Card [] cards;
	private Player [] players;
	private boolean isRound;
	private int currentRound;
	private int currentPlayer;
	private int blindBet = 500;
	
	public TablePoker() {
		pot =  new Pot();
		cards = new Card [5];
		players =  new Player [8];
		isRound = false;
		currentPlayer = 0;
		currentRound = 0;
	}
	
	//begin the round
	public void initRound(){
		isRound = true;
		getPot().resetPot();
		currentRound = 0;
		
		AlphaPokerControl
			.getInstance()
			.getControlGame()
			.getGamePoker()
			.getDealer()
			.getDeck().restartDeek();
		
		currentPlayer = 0;
		activePlayers(); //active current players
		AlphaPokerControl.getInstance().getControlGUI().getTableGUI().resetCards();
		shuffleCardsToPlayers();
		
		launchRound();
	}
	
	
	
	private void launchRound() {
		if(currentRound <= 3){
			if(currentRound == 0){
				roundOne();
			}
			if(currentRound == 1){
				roundTwo();
			} 
			if (currentRound == 2){
				roundThree();
			}
			if(currentRound == 3){
				finalRound();
			}
		}else{
			finishRound();
		}
		
	}
	
	public void finishRound(){
		/** finish general round  */
		isRound = false;
		//call dealer
		AlphaPokerControl.getInstance().getControlGame().getGamePoker().getDealer().checkRound();
		System.out.println("****** FINISHED ROUND **** ");	
	}
	
	/** round 1 */
	private void roundOne(){
		//paint the table with cards 
		paintCardsInTable(0);
		paintCardsInTable(1);
		paintCardsInTable(2);
		blindBet(); //do blind bet
		currentRound ++;
		currentPlayer = 0; //****
		//launchRound();
	}

	/** round 2 */
	private void roundTwo(){
		if(currentPlayer < 8){
			if(players[currentPlayer] != null && players[currentPlayer].isPlaying()){
				setCurrentPlayer();
				return;
			}else{
				nextCurrentPlayer();
				launchRound();
			}
		}else{
			paintCardsInTable(3);
			currentRound ++;
			currentPlayer = 0; //initialization again current player
			//launchRound(); //launch round 3
		}
	}
		
	/** round 3 */
	private void roundThree(){
		if(currentPlayer < 8){
			if(players[currentPlayer] != null && players[currentPlayer].isPlaying()){
				setCurrentPlayer();
				return;
			}else{
				nextCurrentPlayer();
				launchRound();
			}
		}else{
			paintCardsInTable(4);
			currentRound ++;
			currentPlayer = 0; //initialization again current player
			//finalRound 
		}
	}
	
	/** final round */
	private void finalRound() {
		if (currentPlayer < 8) {
			if (players[currentPlayer] != null
					&& players[currentPlayer].isPlaying()) {
				setCurrentPlayer();
				return;
			} else {
				nextCurrentPlayer();
				launchRound();
			}
		} else {
			currentRound++;
			currentPlayer = 0; // initialization again current player
			finishRound();
		}
	}
	
	private void setCurrentPlayer() {
		int minBetPlayer = pot.getStatePot() - players[currentPlayer].getChips().getAcumChips();
		int maxBetPlayer = players[currentPlayer].getChips().getnChips();
		AlphaPokerControl.getInstance().getComucationControl().
				getProtocolClient().isTurn(players[currentPlayer].getNamePlayer(), ""+minBetPlayer , ""+maxBetPlayer);
		//paint focus
		AlphaPokerControl.getInstance().getControlGame().getGamePoker().getDealer().setFocusCurrentPlayer(true);
		
	}

	private void blindBet() {
		for (int i = 0; i < players.length; i++){
			if(players[i] != null){
				if(players[i].isPlaying()){
					players[i].getChips().setnChips((players[i].getChips().getnChips() - blindBet));
					players[i].getChips().setAcumChips(blindBet);
					pot.addPot(blindBet);
					pot.setStatePot(blindBet);
					
					//update GUI
					AlphaPokerControl.getInstance().getControlGUI().refreshPot(pot.getPot(), pot.getStatePot());
					updatePlayerChips(i);
					
				}
			}
		}
	}

	public void paintCardsInTable(int posCard){
		Card card = AlphaPokerControl.getInstance().getControlGame().getGamePoker().getDealer().getDeck().getNextCard();
		cards[posCard] = card;
		AlphaPokerControl.getInstance().getControlGUI().getTableGUI().getCard(posCard).setCardPoker(card.getFrontIcon());
	}

	private void shuffleCardsToPlayers() {
		for (int i = 0; i < players.length; i++){
			if(players[i] != null){
				if(players[i].isPlaying()){
					Card card1 = AlphaPokerControl.getInstance().getControlGame().getGamePoker().getDealer().getDeck().getNextCard();
					Card card2 = AlphaPokerControl.getInstance().getControlGame().getGamePoker().getDealer().getDeck().getNextCard();
					players[i].setHandCards(card1, card2);
					//send message to android
					AlphaPokerControl.getInstance().getComucationControl().getProtocolClient().sendPlay(players[i].getNamePlayer(), card1.getIdCard(), card2.getIdCard());
				}
			}
		}
	}

	//this method set the active players that are present in the array
	private void activePlayers() {
		for (int i = 0; i < players.length; i++){
			if(players[i] != null){
				players[i].setPlaying(true);
				AlphaPokerControl.getInstance().getControlGUI().getTableGUI().enablePlayerTable(i, true);
			}
		}
	}
	
//	public void lostTurn() {
//		System.out.println("perdi turno " + currentPlayer);
//		AlphaPokerControl.getInstance().getControlGame().getGamePoker().getDealer().setFocusCurrentPlayer(false);
//		AlphaPokerControl.getInstance().getControlGame().getGamePoker().getDealer().restartTimer(15);
//		players[currentPlayer].setPlaying(false);
//		AlphaPokerControl.getInstance().getControlGUI().getTableGUI().enablePlayerTable(currentPlayer, false);
//		currentPlayer ++;
//		launchRound();
//	}
	

	//users
	public void addPlayer(Player player, int pos){
		players[pos] = player;
	}
	
	public void removePlayer(int pos){
		players[pos] = null;
	}
	
	
	//Cards
	public void putCard(Card card, int pos){
		cards[pos] = card;
	}
	
	public void resetCards(){
		for (int i = 0; i < cards.length; i++) {
			cards[i] = null;
		}
	}
	
	//play players
	public void doCheck() {
		nextCurrentPlayer();
		launchRound();
	}

	public void doRaise(String value) {
		//implements
		int increment = 0;
		try{
			increment = Integer.parseInt(value);
		}catch (Exception e) {
			System.out.println("Exception in cash value in raise");
		}
		pot.setStatePot( ( (pot.getStatePot() + increment) ));
		pot.addPot(increment);
		
		players[currentPlayer].getChips().setnChips(players[currentPlayer].getChips().getnChips() - increment);
		players[currentPlayer].getChips().setAcumChips(increment);
		
		//update GUI
		AlphaPokerControl.getInstance().getControlGUI().refreshPot(pot.getPot(), pot.getStatePot());
		updatePlayerChips(currentPlayer);
		
		nextCurrentPlayer();
		launchRound();
	}

	public void doCall() {
		//implements
		int betPlayer = pot.getStatePot() - players[currentPlayer].getChips().getAcumChips();
		players[currentPlayer].getChips().setnChips(players[currentPlayer].getChips().getnChips() - betPlayer);
		players[currentPlayer].getChips().setAcumChips(betPlayer);
		//pot
		pot.addPot(betPlayer);
		//non increment in the state pot
		//update GUI
		AlphaPokerControl.getInstance().getControlGUI().refreshPot(pot.getPot(), pot.getStatePot());
		updatePlayerChips(currentPlayer);
		
		nextCurrentPlayer();
		launchRound();
	}

	public void doFold() {
		players[currentPlayer].setPlaying(false);
		AlphaPokerControl.getInstance().getControlGUI().getTableGUI().enablePlayerTable(currentPlayer, false);
		players[currentPlayer].getChips().resetAcumChips();
		updatePlayerChips(currentPlayer);
		if(!checkMinimumPlayers()){
			//exception case
			setChipsPlayerWinDefault();
		}else{
			nextCurrentPlayer();
			launchRound();
		}
	}
	
	
	private void setChipsPlayerWinDefault() {
		for (int i = 0; i < players.length; i++){
			if(players[i] != null){
				if(players[i].isPlaying()){
					players[i].getChips().setnChips(players[i].getChips().getnChips() + pot.getPot());
					players[i].getChips().resetAcumChips();
					pot.resetPot();
					//update data
					AlphaPokerControl.getInstance().getControlGUI().refreshPot(pot.getPot(), pot.getStatePot());
					updatePlayerChips(i);
					break;
				}
			}
		}
	}

	private boolean checkMinimumPlayers() {
		int numberActivePlayers = 0;
		for (int i = 0; i < players.length; i++){
			if(players[i] != null){
				if(players[i].isPlaying()){
					numberActivePlayers++;
				}
			}
		}
		if(numberActivePlayers >= 2){
			return true;
		}
		return false;
	}

	public void showWinner(int idWinner) {
		for (int i = 0; i < players.length; i++){
			if(players[i] != null){
				players[i].getChips().resetAcumChips();
				//update player GUI
				updatePlayerChips(i);
			}
		}
		int totalPot = pot.getPot();
		pot.resetPot();
		players[idWinner].getChips().setnChips(players[idWinner].getChips().getnChips() + totalPot);
		setFocusWinner(idWinner);
		//update GUI pot
		AlphaPokerControl.getInstance().getControlGUI().refreshPot(pot.getPot(), pot.getStatePot());
		updatePlayerChips(idWinner);
	}

	private void nextCurrentPlayer(){
		currentPlayer ++;
	}
	
	//update GUI's
	private void updatePlayerChips(int nPlayer){
		AlphaPokerControl.getInstance().getControlGUI().refreshChips(nPlayer, players[nPlayer].getChips().getnChips(), players[nPlayer].getChips().getAcumChips());
	}
	
	private void setFocusWinner(int idWinner) {
		AlphaPokerControl.getInstance().getControlGUI().getTableGUI().setFocusWinner(idWinner);
	}
	
	//getters
	public Card[] getCards() {
		return cards;
	}
	
	public Pot getPot() {
		return pot;
	}
	
	public Player getPlayer(int pos) {
		return players[pos];
	}
	
	public boolean isRound() {
		return isRound;
	}
	
	public int getTotalPlayer(){
		int totalPlayers = 0;
		for (int i = 0; i < players.length; i++) {
			if(players[i] != null){
				totalPlayers ++;
			}
		}
		return totalPlayers;
	}

	
	public int getCurrentPlayer() {
		return currentPlayer;
	}
	
	public String getNickCurrentPlayer(){
		return players[currentPlayer].getNamePlayer();
	}

}
