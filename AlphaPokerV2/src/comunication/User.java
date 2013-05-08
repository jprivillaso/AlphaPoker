package comunication;

public class User {

	private String nickName;
	private String ip;
	private int port;
	
	public User(String nickName, String ip, int port) {
		this.nickName = nickName;
		this.ip = ip;
		this.port = port;
	}
	
	
	
	//getters
	public String getIp() {
		return ip;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	
	public int getPort() {
		return port;
	}
	
}
