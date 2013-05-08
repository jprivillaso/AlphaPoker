package utilities;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Split {

	public static String [] split(String str, String pattern){
		ArrayList datas =  new ArrayList();
		StringTokenizer tokens = new StringTokenizer(str,pattern);
		while(tokens.hasMoreTokens()){  
			datas.add(tokens.nextToken());  
		}
		
		return castingArray(datas);
	}
	
	private static String [] castingArray(ArrayList tokens){
		String [] result =  new String [tokens.size()];
		for (int i = 0; i < tokens.size(); i++) {
			result[i] =  (String)tokens.get(i);
		}
		return result;
	}
	
}
