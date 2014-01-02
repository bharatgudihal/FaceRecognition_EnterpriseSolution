package test;

import org.json.JSONException;
import org.json.JSONObject;

public class TheCaller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FaceTest tester = new FaceTest();
		try {
			JSONObject groupObject = tester.createGroup("TestGroup");
			if(groupObject.has("error")){
				System.out.println(groupObject.get("error"));
			}
			else
			System.out.println(groupObject);
			JSONObject personObject = tester.createPerson("TestGroup", "TestPerson");
			if(personObject.has("error")){
				System.out.println(personObject.get("error"));
			}
			else
			System.out.println(personObject);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
