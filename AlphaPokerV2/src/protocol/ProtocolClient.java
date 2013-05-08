package protocol;

import comunication.User;

import control.AlphaPokerControl;

public class ProtocolClient implements InterfaceClientProtocol {

	public void sendPlay(String nickName, int idCard1, int idCard2) {
		String request = "play&"+idCard1+"&"+idCard2;
		User user = (User) AlphaPokerControl.getInstance().getSessionControl().getSession().getUser(nickName);
		AlphaPokerControl.getInstance().getComucationControl().getClientTable().sendMessage(user.getIp(), user.getPort(), request);
	}

	public void isTurn(String nickName, String minBet, String maxBet) {
		String request = "isTurn&"+minBet+"&"+maxBet;
		User user = (User) AlphaPokerControl.getInstance().getSessionControl().getSession().getUser(nickName);
		AlphaPokerControl.getInstance().getComucationControl().getClientTable().sendMessage(user.getIp(), user.getPort(), request);
	}
	
	
}
