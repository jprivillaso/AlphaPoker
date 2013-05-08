package protocol;

import modelpoker.Player;
import comunication.User;

import control.AlphaPokerControl;
import dao.DAOEngine;

public class ProtocolServer implements InterfaceServerProtocol {

	public void register(String nickName, String ip, String port, String chips) {
		String response = "register";
		//chips = chips.substring(0, chips.length()-1);
		//check method if user is already created and check the total users 
		//it must less than 7, because if it's not created yet, the method will do it automatically
		//so, it will need one more position to do it
		boolean isCheckRound = false;
		if (!AlphaPokerControl.getInstance().getSessionControl()
				.getSession().checkUser(nickName)){
			if(AlphaPokerControl.getInstance().getSessionControl()
					.getSession().getTotalUsers() < 7 ) {
				//add user
				AlphaPokerControl
						.getInstance()
						.getSessionControl()
						.getSession()
						.addUser( new User(nickName, ip, Integer.parseInt(port) )) ;
				//add Player
				int pos = AlphaPokerControl.getInstance().getSessionControl().getSession().getPositionUser(nickName);
				AlphaPokerControl
					.getInstance()
					.getControlGame()
					.getGamePoker()
					.getTablePoker().addPlayer(new Player(nickName, Integer.parseInt(chips)), pos);
				
				isCheckRound = true;
				
				//paint Player
				String face = DAOEngine.getAvatar(nickName);
				AlphaPokerControl.getInstance().getControlGUI().getTableGUI().createPlayerData(pos, chips, face, nickName);
				AlphaPokerControl.getInstance().getControlGUI().getTableGUI().showPlayer(pos);
				
				System.out.println("HASHTABLE:  " + nickName);
				response += "&ok";
			}else{
				response += "&Try Later";
			}
		} else {
			response += "&err";
		}
		
		//send data!!
		AlphaPokerControl.getInstance().getComucationControl().getClientTable().sendMessage(ip, Integer.parseInt(port), response);
		//check Round
		if (isCheckRound) {
			AlphaPokerControl.getInstance().getControlGame().getGamePoker().checkStartRound();
		}
		
	}

	public void play(String nickName, String typePlay, String value) {
		//String response = "play";
		
		AlphaPokerControl.getInstance().getControlGame().getGamePoker()
				.getDealer().chekPlay(nickName, typePlay, value);
		
			//response += "&ok";
		
			//response += "&err";
		
		//make response
		//sendResponse(nickName, response);
	}

	
	public void logout(String nickName) {
		String response = "logout";
		User user = AlphaPokerControl.getInstance().getSessionControl().getSession().getUser(nickName);
		if(user != null){
			int pos = AlphaPokerControl.getInstance().getSessionControl().getSession().getPositionUser(nickName);
			AlphaPokerControl.getInstance().getSessionControl().getSession().removeUser(user);
			AlphaPokerControl.getInstance().getControlGame().getGamePoker().getTablePoker().removePlayer(pos);
			
			//update table poker *********
			AlphaPokerControl.getInstance().getControlGUI().getTableGUI().hidePlayer(pos);
			
			response += "&Removed succesfully";
		}else{
			response += "&err";
		}
		System.out.println(response);
	}
	
	

}
