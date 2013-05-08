package protocol;

public interface InterfaceClientProtocol {
	
	public void register();
	public void sendPlay(String typePlay, String chips);
	public void logout();
	
}
