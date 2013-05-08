package protocol;

public interface InterfaceClientProtocol {

	public void sendPlay(String nickName, int idCard1, int idCard2);
	public void isTurn(String nickName, String minBet, String maxBet);
	
}
