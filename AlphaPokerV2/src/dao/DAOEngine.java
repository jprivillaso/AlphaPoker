package dao;

import org.json.JSONObject;

import utilities.WSClient;


public class DAOEngine {

	//constructor default
	public DAOEngine() { }
	
	
	public boolean connection(){
		return true;
	}
	
	
	public static String getAvatar(String nickName){
		try{
			String json = "{" + "\"action\":\"getavatar\"," + "\"nick\":\""
			+ nickName + "\"}";


			String response = WSClient.invokeWebService(
			"http://alphapoker.sysney.com/control/ControlWS.php", "datas",
			json);
			
			org.json.JSONObject obj = new JSONObject(response);
			return ((String) obj.get("return"));

		}catch (Exception e) {	}
		
		return null;
	}
	
}
