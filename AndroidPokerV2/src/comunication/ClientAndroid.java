package comunication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import control.ControlPokerMobile;

public class ClientAndroid {

	private static final String HOST  = "192.168.1.70";
	private static final int PORT = 5000;
	
	private DataOutputStream dataOutputStream;
	private DataInputStream dataInputStream;
	
	
	//constructor
	public ClientAndroid() { }
	
	
	public void sendMessage(String message){
		run_s(message);
	}
	
	public void run_s(String message) {
		try{
			Socket socketclient =  new Socket(ControlPokerMobile.getInstance().getPlayerControl().getPlayer().getIp(), PORT);
			dataOutputStream =  new DataOutputStream(socketclient.getOutputStream());
			dataOutputStream.flush();
			dataInputStream = new DataInputStream(socketclient.getInputStream());
			
			dataOutputStream.writeUTF(message);
			socketclient.close();
			
		}catch (Exception e) {
			System.out.println("Exception client >> " + e);
		}	
	}
	
	
}
