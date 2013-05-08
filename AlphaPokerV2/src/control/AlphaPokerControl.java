package control;


public class AlphaPokerControl {

	private static AlphaPokerControl instance = null;
	private GUIControl controlGUI;
	private GameControl controlGame;
	private DAOControl daoControl;
	private ComucationControl comucationControl;
	private SessionControl sessionControl;
	
	
	//singleton instance
	public static AlphaPokerControl getInstance(){
		if(instance == null){
			instance =  new AlphaPokerControl();
		}
		return instance;
	}
	
	//constructor default
	public AlphaPokerControl() {
		controlGUI =  new GUIControl();
		controlGame =  new GameControl();
		daoControl =  new DAOControl();
		comucationControl =  new ComucationControl();
		sessionControl =  new SessionControl();
	}
	
	public void start() {
		//initialize all game
		controlGUI.showTable();
		comucationControl.getServerTable().initServer();
	}
	
	
	//getters
	public GUIControl getControlGUI() {
		return controlGUI;
	}
	
	public GameControl getControlGame() {
		return controlGame;
	}
	
	public DAOControl getDaoControl() {
		return daoControl;
	}
	
	public ComucationControl getComucationControl() {
		return comucationControl;
	}
	
	public SessionControl getSessionControl() {
		return sessionControl;
	}
	
	
}
