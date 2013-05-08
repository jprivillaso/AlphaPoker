package gui;


import java.awt.Color;
import java.awt.Graphics;
import org.havi.ui.HState;
import org.havi.ui.HTextLook;
import org.havi.ui.HVisible;

public class LabelLook extends HTextLook
{
	/**
	* Asigna el look de el HVisible recibido como parámetro.
	* 
	* @param g Graphics con que se pinta el botón.
	* @param visible HVisible al que se le dará el look.
	* @param state Estado del HVisible.
	*/
	public void showLook(Graphics g, HVisible visible, int state)
	{
		String msg = visible.getTextContent(HState.NORMAL_STATE);
	
		int  fontWidth = g.getFontMetrics().stringWidth(msg);
        int  offset = 2;
        int  x = (visible.getWidth() - fontWidth) / 2;;
        int  y = visible.getHeight() / 2;;

        g.setFont(visible.getFont()); 

        g.setColor(Color.black);
        g.drawString(msg, x - offset, y - offset);
        g.drawString(msg, x - offset, y);
        g.drawString(msg, x - offset, y - offset);
        g.drawString(msg, x, y - offset);
        g.drawString(msg, x, y + offset);
        g.drawString(msg, x + offset, y - offset);
        g.drawString(msg, x + offset, y);
        g.drawString(msg, x + offset, y + offset);
        
        g.setColor(Color.white);
        g.drawString(msg, x, y);
	}
}