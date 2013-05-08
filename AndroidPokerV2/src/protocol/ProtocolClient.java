package protocol;


import control.ControlPokerMobile;

public class ProtocolClient implements InterfaceClientProtocol{

	@Override
	public void register() {
		int port = 8080; //**************
		String request = "register";
		request += "&"+ControlPokerMobile.getInstance().getPlayerControl().getPlayer().getNickName();
		String ip = "-";
		String chips = (String) ControlPokerMobile.getInstance().getPlayerControl().getPlayer().getEnterChips();
		if(chips.equals(" ") || chips.equals("")){
			request += "&"+ip+"&"+port+"&0";
		}else{
			request += "&"+ip+"&"+port+"&"+chips;
		}
		//send client socket
		ControlPokerMobile.getInstance().getComunicationControl().getClientAndroid().sendMessage(request);
	}

	@Override
	public void sendPlay(String typePlay, String chips) {
		String request = "play";
		request += "&"+ControlPokerMobile.getInstance().getPlayerControl().getPlayer().getNickName();
		request += "&"+typePlay;
		if(chips.equals("")){
			request += "&0";
		}else{
			request += "&"+chips;
		}
		//send client socket
		ControlPokerMobile.getInstance().getComunicationControl().getClientAndroid().sendMessage(request);
	}

	@Override
	public void logout() {
		String request = "logout";
		request += "&"+ControlPokerMobile.getInstance().getPlayerControl().getPlayer().getNickName();
		//send client socket
		ControlPokerMobile.getInstance().getComunicationControl().getClientAndroid().sendMessage(request);
	}

}
