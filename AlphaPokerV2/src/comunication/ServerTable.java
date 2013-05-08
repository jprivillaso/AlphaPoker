package comunication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTable implements Runnable {

	private static final int port = 5000;
	private boolean isRunning;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	
	
	public ServerTable() {
		isRunning = false;
	}
	
	public void initServer(){
		isRunning = true;
		Thread t = new Thread(this);
		t.start();
	}
	
	public void run() {
		try{
			System.out.println("Server Running....");
			ServerSocket serverSocket = new ServerSocket(port);
			while(isRunning){
				Socket socketClient = serverSocket.accept();
				dataOutputStream = new DataOutputStream(socketClient.getOutputStream());
				dataOutputStream.flush();
				dataInputStream =  new DataInputStream(socketClient.getInputStream());
				
				String ipClient = socketClient.getInetAddress().getHostAddress();
				
				String request =  dataInputStream.readUTF();
				CheckRequestResponse.checkRequest(request, ipClient);
				
				socketClient.close();
			}
			
		}catch (Exception e) {
			
		}
	}
	
	
	public void stopServer(){
		isRunning = false;
	}
	
}
