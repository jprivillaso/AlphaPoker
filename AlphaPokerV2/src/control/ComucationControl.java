package control;


import protocol.ProtocolClient;
import protocol.ProtocolServer;
import comunication.ClientTable;
import comunication.ServerTable;

public class ComucationControl {
	
	private ServerTable serverTable;
	private ProtocolServer protocolMPC;
	private ProtocolClient protocolClient;
	
	private ClientTable clientTable;
	
	public ComucationControl() {
		//server
		serverTable = new ServerTable();
		protocolMPC =  new ProtocolServer();
		
		//client
		clientTable = new ClientTable();
		protocolClient =  new ProtocolClient();
	}
	
	//getters
	public ProtocolServer getProtocolMPC() {
		return protocolMPC;
	}
	
	public ServerTable getServerTable() {
		return serverTable;
	}
	
	public ClientTable getClientTable() {
		return clientTable;
	}
	
	public ProtocolClient getProtocolClient() {
		return protocolClient;
	}
	
}
