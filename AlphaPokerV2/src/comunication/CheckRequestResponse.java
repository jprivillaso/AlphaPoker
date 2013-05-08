package comunication;


import control.AlphaPokerControl;

import utilities.Split;

public class CheckRequestResponse {

	
	//processing the request to sender response
	public static void checkRequest(String request, String ipClient){
		System.out.println("request " + request);
		try{
			String [] datas = Split.split(request, "&");
			if(datas[0].equals("register")){
				AlphaPokerControl.getInstance().getComucationControl().
					getProtocolMPC().register(datas[1], ipClient, datas[3], datas[4]);
			}else if(datas[0].equals("play")){
				AlphaPokerControl.getInstance().getComucationControl().
				getProtocolMPC().play(datas[1], datas[2], datas[3]);
			}else if(datas[0].equals("logout")){
				AlphaPokerControl.getInstance().getComucationControl().
				getProtocolMPC().logout(datas[1]);
			}
			
		}catch (Exception e) { }
		
	}
	
	
	//processing the response to send another request
	public static String checkResponse(){
		return "";
	}
	
	
}
