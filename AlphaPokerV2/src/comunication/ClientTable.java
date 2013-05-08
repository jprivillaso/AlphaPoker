package comunication;

import java.io.DataOutputStream;
import java.net.Socket;

public class ClientTable  {
	
	private DataOutputStream dataOutputStream;
	
	public ClientTable() {	
	}
	
	
	public void sendMessage(String IP, int port, String message){
		//System.out.println("** user send to " + IP + " port " + port  + " message " + message);
		run_s(IP, port, message);
	}
	
	public void run_s(String IP, int port, String message) {
		try{
			Socket socketclient =  new Socket(IP, port);
			dataOutputStream =  new DataOutputStream(socketclient.getOutputStream());
			dataOutputStream.flush();
			
			dataOutputStream.writeUTF(message);
			
			socketclient.close();
		}catch (Exception e) {
			//pop hash table
			System.out.println("Exception client >> " + e);
		}
		
	}
	
	
}
