package protocol;

//interface protocol mobile-tv 

public interface InterfaceServerProtocol {
	
	public void register(String nickName, String ip, String port, String chips);
	public void play(String nickName, String typePlay, String value);
	public void logout(String nickName);
	
}
