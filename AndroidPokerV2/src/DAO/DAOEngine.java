package DAO;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;

import utilities.WSClient;

public class DAOEngine {

	// constructor default
	public DAOEngine() {
	}

	public static boolean connection() {
		return true;
	}

	public static boolean login(String nickName, String pin) {

		String json = "{" + "\"action\":\"login\"," + "\"nick\":\"" + nickName
				+ "\"," + "\"pin\":\"" + pin + "\"" + "}";

		String response = WSClient.invokeWebService(
				"http://alphapoker.sysney.com/control/ControlWS.php", "datas",
				json);

		try {
			org.json.JSONObject obj = new JSONObject(response);
			return Boolean.parseBoolean((String) obj.get("return"));
		} catch (Exception e) {
		}

		return false;
	}

	public static int getChips(String nickName) {
		String json = "{" + "\"action\":\"getchips\"," + "\"nick\":\""
				+ nickName + "\"}";

		System.out.println("json " + json);

		String response = WSClient.invokeWebService(
				"http://alphapoker.sysney.com/control/ControlWS.php", "datas",
				json);

		try {
			org.json.JSONObject obj = new JSONObject(response);
			return Integer.parseInt((String) obj.get("return"));
		} catch (Exception e) { }

		return 0;

	}

	public static boolean register(String nick, String pin, String face, String email) {
		try {
			String json = "{" + "\"action\":\"register\"," + "\"nick\":\"" + nick
			+ "\"," + "\"name\":\"" + nick + "\"," + "\"pin\":\"" + pin + "\"," + "\"avatar\":\"" + face
                        + "\"," + "\"email\":\"" + email + "\"" + "}";
			
			String response = WSClient.invokeWebService(
					"http://alphapoker.sysney.com/control/ControlWS.php", "datas",
					json);
			
			org.json.JSONObject obj = new JSONObject(response);
			return Boolean.parseBoolean((String) obj.get("return"));
			
			
		} catch (Exception e) { }
		return false;
		
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
