package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;


import org.havi.ui.HContainer;
import org.havi.ui.HDefaultTextLayoutManager;
import org.havi.ui.HIcon;
import org.havi.ui.HInvalidLookException;
import org.havi.ui.HState;
import org.havi.ui.HText;


public class TableGUI extends HContainer {
	
	private Image background;
	private ArrayList playersGUI;
	private int positionChairs [][] = {
										{290, 90}, {500,100}, {575,240}, {500,400}, {290,410}, {90,400}, {20,240}, {90,100}
									   };
	private int numberPlayers = 8;
	private int numberCards = 5;
	private ArrayList cardsTable;
	private HIcon imgPotChips;
	private HText potGUI;
	private HText potStateGUI;
	private HText lblPotGUI;
	private HText lblPotStateGUI;
	
	public TableGUI() {
		super(0,0,720,576);
		try{
			background = Toolkit.getDefaultToolkit().getImage("images/table.png");
			init();
		}catch (Exception e) {
			System.out.println("Exception.tableGUI >> " + e);
		}
		setVisible(false);
	}
	
	
	private void init() {
		playersGUI =  new ArrayList();
		cardsTable =  new ArrayList();
		
		//paint pot
		Image imgChipsTable = Toolkit.getDefaultToolkit().getImage("images/chips.png");
		imgPotChips = new HIcon(imgChipsTable, 270, 320, 36, 26);
		lblPotGUI =  new HText("Total Pot", 310, 320, 70, 20 , new Font("Tiresias", 1, 14), Color.white, null,
				new HDefaultTextLayoutManager());
		lblPotStateGUI =  new HText("Current Bet", 310, 335, 70, 20, new Font("Tiresias", 1, 14), Color.white, null,
				new HDefaultTextLayoutManager());
		potGUI =  new HText("0", 390, 320, 40, 20, new Font("Tiresias", 1, 14), Color.white, null,
				new HDefaultTextLayoutManager());
		potStateGUI =  new HText("0", 390, 335, 40, 20, new Font("Tiresias", 1, 14), Color.white, null,
				new HDefaultTextLayoutManager());
		
		try{
			lblPotGUI.setLook(new LabelLook());
			lblPotStateGUI.setLook(new LabelLook());
			potGUI.setLook(new LabelLook());
			potStateGUI.setLook(new LabelLook());
		}catch (HInvalidLookException e) {}
		
		this.add(imgPotChips);
		this.add(potGUI);
		this.add(potStateGUI);
		this.add(lblPotGUI);
		this.add(lblPotStateGUI);
		
		
		//paint players
		for (int i = 0; i < numberPlayers; i++) {
			PlayerGUI player1 =  new PlayerGUI(i + 1, positionChairs[i][0],positionChairs[i][1] );
			playersGUI.add(player1); //add array list
			this.add(player1.getChairFold());
			this.add(player1.getChairWinner());
			this.add(player1.getChair());
			this.add(player1.getChairBack());
			this.add(player1.getFace());
			popToFront(player1.getFace());
			this.add(player1.getNickName());
			this.add(player1.getChips());
			popToFront(player1.getChips());
			this.add(player1.getAcumChips());
			this.add(player1.getiAcumChips());
			popToFront(player1.getNickName());
			popToFront(player1.getiAcumChips());
			popToFront(player1.getAcumChips());
			//hide players
			hidePlayer(i);
		}
		
		//paint cards
		Image cardBack = Toolkit.getDefaultToolkit().getImage("images/backCard.png");
		for (int i = 0; i < numberCards; i++) {
			CardGUI card = new CardGUI(cardBack, i * 75 + 170, 210, 71, 96); 
			cardsTable.add(card);
			this.add(card);
			popToFront(card);
		}
	}

	public void paint(Graphics g){
		g.drawImage(this.background, 0, 0, this);
		super.paint(g);
	}
	
	public void resetCards(){
		for (int i = 0; i < numberCards; i++) {
			((CardGUI)cardsTable.get(i)).resetCard();
		}
	}
	
	
	public void hidePlayer(int nPlayer) {
		PlayerGUI player = (PlayerGUI) getPlayerGUI(nPlayer);
		player.getChair().setVisible(false);
		player.getChairBack().setVisible(false);
		player.getChairFold().setVisible(false);
		player.getChairWinner().setVisible(false);
		player.getChips().setVisible(false);
		player.getFace().setVisible(false);
		player.getNickName().setVisible(false);
		player.getAcumChips().setVisible(false);
		player.getiAcumChips().setVisible(false);
	}
	
	
	public void showPlayer(int nPlayer) {
		PlayerGUI player = (PlayerGUI) getPlayerGUI(nPlayer);
		player.getChair().setVisible(false);
		player.getChairBack().setVisible(false);
		player.getChairWinner().setVisible(false);
		player.getChairFold().setVisible(true);
		player.getChips().setVisible(true);
		player.getFace().setVisible(true);
		player.getNickName().setVisible(true);
		player.getAcumChips().setVisible(true);
		player.getiAcumChips().setVisible(true);
	}
	
	public void createPlayerData(int nPlayer, String chips, String face, String nick ){
		PlayerGUI player = (PlayerGUI) getPlayerGUI(nPlayer);
		player.getChips().setTextContent(chips, HState.NORMAL_STATE);
		Image imgFace  = Toolkit.getDefaultToolkit().getImage("images/"+ face +".png");
		player.getFace().setGraphicContent(imgFace, HState.NORMAL_STATE);
		player.getNickName().setTextContent(nick, HState.NORMAL_STATE);
		enablePlayerTable(nPlayer, false);
		this.repaint();
	}
	
	public void enablePlayerTable(int nPlayer, boolean state){
		PlayerGUI player = (PlayerGUI) getPlayerGUI(nPlayer);
		player.getChair().setVisible(state);
		player.getChairFold().setVisible(!state);
		this.repaint();
	}
	
	public void isFocusPlayer(int nPlayer, boolean state){
		PlayerGUI player = (PlayerGUI) getPlayerGUI(nPlayer);
		player.getChairBack().setVisible(state);
		player.getChair().setVisible(!state);
		this.repaint();
	}
	
	public void setFocusWinner(int idWinner){
		final PlayerGUI player = (PlayerGUI) getPlayerGUI(idWinner);
		Runnable runnable = new Runnable() {
			public void run() {
				try{
					player.getChairWinner().setVisible(true);
					Thread.sleep(2000);
					player.getChairWinner().setVisible(false);
				}catch (Exception e) { }
			}
		};
		runnable.run();
	}
	
	public void changeChips(int nPlayer, String chips){
		PlayerGUI player = (PlayerGUI) getPlayerGUI(nPlayer);
		player.getChips().setTextContent(chips, HState.NORMAL_STATE);
		this.repaint();
	}
	
	public void changeAcumNChips(int nPlayer, String acumChips){
		PlayerGUI player = (PlayerGUI) getPlayerGUI(nPlayer);
		player.getAcumChips().setTextContent(acumChips, HState.NORMAL_STATE);
		this.repaint();
	}
	
	public void changePot(String pot){
		potGUI.setTextContent(pot, HState.NORMAL_STATE);
		this.repaint();
	}
	
	public void changeStatePot(String potState){
		potStateGUI.setTextContent(potState, HState.NORMAL_STATE);
		this.repaint();
	}
	
	//getters
	public PlayerGUI getPlayerGUI(int nPlayer) {
		return (PlayerGUI) playersGUI.get(nPlayer);
	}
	
	public CardGUI getCard(int pos){
		return (CardGUI) cardsTable.get(pos);
	}
	
}
