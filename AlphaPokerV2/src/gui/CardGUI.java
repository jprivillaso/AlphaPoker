package gui;

import java.awt.Image;
import java.awt.Toolkit;

import org.havi.ui.HIcon;
import org.havi.ui.HState;

public class CardGUI  extends HIcon{

	private Image cardBack;
	private Image cardFront;
	private int posX;
	private int posY;
	private int width;
	private int heigth;
	
	public CardGUI(Image cardBack, int posX, int posY, int width, int heigth ) {
		super(cardBack, posX, posY, width, heigth);
		this.cardBack = cardBack;
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.heigth = heigth;
		this.cardFront = Toolkit.getDefaultToolkit().getImage("");
	}

	public void setCardPoker(String card){
		cardFront = Toolkit.getDefaultToolkit().getImage(card);
		this.setGraphicContent(cardFront, HState.NORMAL_STATE);
		this.repaint();
	}
	
	public void resetCard(){
		this.setGraphicContent(cardBack, HState.NORMAL_STATE);
		this.repaint();
	}

	//getters
	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeigth() {
		return heigth;
	}
	
	
}
