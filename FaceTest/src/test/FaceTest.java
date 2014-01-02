package test;

import java.io.BufferedReader;

import org.json.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class FaceTest {
	
	private String key="cd8a2008408740f834121baf9ed29a5e";
	private String secret="PKchGqrVRZx1fHKJCzGIWNHW44iMYf8B";
	private String primary_url = "https://apicn.faceplusplus.com/v2";
	
	public JSONObject createGroup(String groupName){
		System.out.println("Entered createGroup");
		String group_url = primary_url;
	    String path = "/group/create?api_key="+key+"&api_secret="+secret;
	    group_url += path+"&group_name="+groupName;
	    return getApiResponse(group_url);
	}
	
	//Person creator
	public JSONObject createPerson(String groupName, String personName){
		System.out.println("Entered createPerson");
		String person_url = primary_url;
		String path = "/person/create?api_key="+key+"&api_secret="+secret;
		person_url += path+"&person_name="+personName+"&group_name="+groupName;
		return getApiResponse(person_url);
	}

	private JSONObject getApiResponse(String hit_url) {
		System.out.println("Entered getApiResponse");
		JSONObject response = new JSONObject(); 
		URL url;
		try {
	    	url = new URL(hit_url);
		    HttpsURLConnection con = (HttpsURLConnection)url.openConnection();	 
		    String responseString = streamToString(con);
		    response = new JSONObject(responseString);
	    } catch (MalformedURLException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
		    e.printStackTrace();
	    } catch (JSONException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	private String streamToString(HttpsURLConnection con){
		System.out.println("Entered streamToString");
		StringBuilder result  = new StringBuilder();
		String input;
		String responseString=null;
		if(con!=null)
		{	 
		try {		   			
		   BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		   while ((input = br.readLine()) != null)
		   {
		      result.append(input+"\n");
		   }
		   br.close();
		   responseString = result.toString();
		   
		} catch (IOException e) {
		   //e.printStackTrace();
		   return "{ error : Error}";
		}
	  }
		return responseString; 
   }
}
