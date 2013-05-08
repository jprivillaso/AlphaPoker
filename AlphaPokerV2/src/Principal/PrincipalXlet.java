package Principal;

import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;

import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;

import control.AlphaPokerControl;


public class PrincipalXlet implements Xlet{
	
	private static PrincipalXlet instance = null;
	private XletContext context;
	private HScene scene;
	
	private boolean loadedPanels = false;
	
	public static PrincipalXlet getInstance(){
		return instance;
	}
	
	public void destroyXlet(boolean args) throws XletStateChangeException{
		if (scene != null) { 
			scene.setVisible(false); 
			HSceneFactory.getInstance().dispose(scene); 
		} 
		scene = null; 	
		context.notifyDestroyed();
	}

	public void initXlet(XletContext context) throws XletStateChangeException {
		this.context = context;
		scene = HSceneFactory.getInstance().getDefaultHScene();
		instance = this;
	}

	public void pauseXlet() {
		scene.setVisible(false);
		context.notifyPaused();
	}

	public void startXlet() throws XletStateChangeException {
		this.loadPanel();
		scene.setVisible(true);
		main();
	}
	
	public void exit(){
		try {
			destroyXlet(true);
		} catch (XletStateChangeException e) {}
	}
		
	private void loadPanel(){
		if(!loadedPanels){
			scene.add(AlphaPokerControl.getInstance().getControlGUI().getTableGUI());
			loadedPanels = true;
		}
	}
	
	private void main() {
		AlphaPokerControl.getInstance().start();
	}
	
	//getters
	public HScene getScene() {
		return scene;
	}

	
}