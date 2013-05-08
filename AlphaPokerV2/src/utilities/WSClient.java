package utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WSClient {

	/**
	 * Invokes the webService specified in the parameter.
	 * 
	 * @param urlString
	 *            The URL of the webService.
	 * @return The response from the server after accessing the service.
	 */
	public static String invokeWebService(String urlString, String varName, String data) {

		System.setProperty("http.keepAlive", "false");

		URL url;

		try {
			url = new URL(urlString+"?"+varName+"="+data);
		} catch (MalformedURLException e) {

			e.printStackTrace();
			return null;
		}

		StringBuffer sb = new StringBuffer();

		try {
			// open connection
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			connection.setRequestProperty("User-agent", "app");
			connection.setRequestProperty("From", "app");

			// This fixes the connection reset issue retrieving data from the
			// .NET Rest Service
			connection.setRequestProperty("accept", "text/plain, text/xml");

			// read response content
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));

			String tmp = null;

			while ((tmp = reader.readLine()) != null) {
				sb.append(tmp);
			}

			reader.close();

			connection.disconnect();

			// Close the connection
			connection.disconnect();
		} catch (Exception e) {
			System.out.println("Exception >> " + e );
		}

		return sb.toString();
	}
}
