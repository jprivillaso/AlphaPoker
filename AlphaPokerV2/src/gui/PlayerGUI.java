package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import org.havi.ui.HDefaultTextLayoutManager;
import org.havi.ui.HIcon;
import org.havi.ui.HInvalidLookException;
import org.havi.ui.HText;


public class PlayerGUI {
	
	private int posX;
	private int posY;
	private int nPlayer;
	private HIcon chair;
	private HIcon chairBack;
	private HIcon chairFold;
	private HIcon chairWinner;
	private HIcon face;
	private HIcon iAcumChips;
	private HText nickName;
	private HText acumChips;
	private HText chips;
	
	public PlayerGUI(int nPlayer, int posX, int posY) {
		this.nPlayer = nPlayer;
		this.posX = posX; 
		this.posY = posY;
		init();
	}

	public void init() {
		//create components
		int idPlayer = ((nPlayer+1) % 4) + 1;
		
		int posXacumchips = getXChips();
		int posYacumchips = getYChips();
		int posXiAcumChips = getiXChips();
	    int posYNick = getPosYNick();
	        
		Image imgChair  = Toolkit.getDefaultToolkit().getImage("images/chair_"+idPlayer+".png");
		Image imgChairBack  = Toolkit.getDefaultToolkit().getImage("images/chair_big_"+idPlayer+".png");
		Image imgFace  = Toolkit.getDefaultToolkit().getImage("images/face_"+ nPlayer +".png");
		Image imgAcumChips = Toolkit.getDefaultToolkit().getImage("images/chips.png");
		Image imgChairFold = Toolkit.getDefaultToolkit().getImage("images/chair_fold.png");
		Image imgChairWinner = Toolkit.getDefaultToolkit().getImage("images/chair_winner.png");
		
		chair = new HIcon(imgChair, posX, posY, 110, 68);
		chairBack = new HIcon(imgChairBack, posX - 15, posY - 10, 140, 87);
		chairFold = new HIcon(imgChairFold, posX, posY, 110, 68);
		chairWinner = new HIcon(imgChairWinner, posX - 15, posY - 10, 140, 87);
		face = new HIcon(imgFace, posX + 45, posY, 61, 67);
		nickName =  new  HText("Player " + nPlayer, posX + 40, posY + posYNick, 90, 20, new Font("Tiresias", 1, 16), Color.white, null,
				new HDefaultTextLayoutManager());
		chips = new  HText("", posX + 9, posY + 25, 40, 20, new Font("Tiresias", 1, 14), Color.white, null,
				new HDefaultTextLayoutManager());
		iAcumChips = new HIcon(imgAcumChips, posX + posXiAcumChips , posY + posYacumchips - 2, 36, 26);
		acumChips = new  HText("", posX + posXacumchips , posY + posYacumchips, 50, 20, new Font("Tiresias", 1, 16), Color.white, null,
				new HDefaultTextLayoutManager());
		
		try{
			nickName.setLook(new LabelLook());
			chips.setLook(new LabelLook());
			acumChips.setLook(new LabelLook());
		}catch (HInvalidLookException e) {}
	}
	
	
	private int getXChips(){
		return 38;
	}

	private int getYChips(){
		if(nPlayer == 4 || nPlayer == 5 || nPlayer == 6){
			return - 25;
		}
		return 75;
	}
	
	private int getiXChips(){
		if(nPlayer == 6 || nPlayer == 7 || nPlayer == 8){
			return 90;
		}
		return 0;
	}
	
	private int getPosYNick(){
		if(nPlayer == 4 || nPlayer == 5 || nPlayer == 6){
			return  73;
		}
		return -25;
	}
	
	
	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getnPlayer() {
		return nPlayer;
	}

	public HIcon getChair() {
		return chair;
	}

	public HIcon getChairBack() {
		return chairBack;
	}
	
	public HIcon getFace() {
		return face;
	}

	public HText getNickName() {
		return nickName;
	}

	public HText getChips() {
		return chips;
	}
	
	public HText getAcumChips() {
		return acumChips;
	}
	
	public HIcon getiAcumChips() {
		return iAcumChips;
	}
	
	public HIcon getChairFold() {
		return chairFold;
	}
	
	public HIcon getChairWinner() {
		return chairWinner;
	}
	
}
