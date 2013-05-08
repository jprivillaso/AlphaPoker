package control;

import protocol.ProtocolClient;
import comunication.ClientAndroid;

public class ComunicationControl {

	private ClientAndroid clientAndroid;
	private ProtocolClient protocolClient;
	
	
	public ComunicationControl() {
		
		clientAndroid = new ClientAndroid();
		protocolClient =  new ProtocolClient();
	}
	
	//start server android in thread
	/*public boolean startServer(){
		return serverAndroid.initServer();
	}*/
	
	//getters
	public ClientAndroid getClientAndroid() {
		return clientAndroid;
	}
	
	
	public ProtocolClient getProtocolClient() {
		return protocolClient;
	}

	
}
