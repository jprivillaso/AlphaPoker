package control;

public class ControlPokerMobile {

	private static ControlPokerMobile instance = null;
	private PlayerControl playerControl;
	private ComunicationControl comunicationControl;
	private GUIControl controlGUI;
	
	public static ControlPokerMobile getInstance() {
		if(instance == null){
			instance =  new ControlPokerMobile();
		}
		return instance;
	}
	
	public ControlPokerMobile() {
		playerControl =  new PlayerControl();
		comunicationControl =  new ComunicationControl();
		controlGUI =  new GUIControl();
	}

	//getters
	public PlayerControl getPlayerControl() {
		return playerControl;
	}
	
	public ComunicationControl getComunicationControl() {
		return comunicationControl;
	}
	
	public GUIControl getControlGUI() {
		return controlGUI;
	}
	
}
