package control;

import Principal.PrincipalXlet;
import gui.TableGUI;

public class GUIControl {
	
	private TableGUI tableGUI;
	
	public GUIControl() {
		tableGUI =  new TableGUI();
	}
	
	
	public void showTable(){
		tableGUI.setVisible(true);
		tableGUI.requestFocus();
		PrincipalXlet.getInstance().getScene().popToFront(tableGUI);
	}
	
	public void refreshPot(int pot, int potState ){
		tableGUI.changePot(""+pot);
		tableGUI.changeStatePot(""+potState);
	}
	
	public void refreshChips(int nPlayer, int nChips, int acumChips){
		tableGUI.changeChips(nPlayer, ""+nChips);
		tableGUI.changeAcumNChips(nPlayer, ""+acumChips);
	}
	
	//getters
	public TableGUI getTableGUI() {
		return tableGUI;
	}
	
}
