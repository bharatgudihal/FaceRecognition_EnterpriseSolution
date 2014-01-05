package test;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;

import org.json.*;

import java.io.File;

public class FaceTest {
	
	private String key="cd8a2008408740f834121baf9ed29a5e";
	private String secret="PKchGqrVRZx1fHKJCzGIWNHW44iMYf8B";
	private HttpRequests httpRequests = null;
	//Constructor
	public FaceTest() {
		super();
		this.httpRequests = new HttpRequests(key,secret,true,false);
	}
	
	//Detect a face
	public JSONObject detectionDetect(String imgPath){
		System.out.println("Entered detectionDetect");
		JSONObject detectionResult = null;
		try {
			detectionResult = httpRequests.detectionDetect(new PostParameters().setImg(new File(imgPath)));
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Exited detectionDetect");
		return detectionResult;
	}
	
	//Create a person
	public JSONObject personCreate(String personName, String tag, String imgPath){
		System.out.println("Entered personCreate");
		JSONObject detectionResult = detectionDetect(imgPath);
		JSONObject personCreateResult = new JSONObject();
		try {
			personCreateResult = httpRequests.personCreate(new PostParameters().setPersonName(personName).setTag(tag));
			String faceId = detectionResult.getJSONArray("face").getJSONObject(0).getString("face_id");
			httpRequests.personAddFace(new PostParameters().setPersonName(personName).setFaceId(faceId));
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Exited personCreate");
		return personCreateResult;
	}
	
	//Add person to group
	public JSONObject groupAddPerson(String groupName, String personName){
		System.out.println("Entered groupAddPerson");
		JSONObject groupAddPerson =  new JSONObject();
		try {
			groupAddPerson = httpRequests.groupAddPerson(new PostParameters().setGroupName(groupName).setPersonName(personName));
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Exited groupAddPerson");
		return groupAddPerson;
	}
	
	public JSONObject trainIdentify(String groupName){
		System.out.println("Entered trainIdentify");
		JSONObject trainIdentifyResult = new JSONObject();
		try {
			JSONObject syncResult = httpRequests.trainIdentify(new PostParameters().setGroupName(groupName));
			trainIdentifyResult = httpRequests.getSessionSync(syncResult.getString("session_id"));
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Exited trainIdentify");
		return trainIdentifyResult;
	}
	
	public JSONObject recognitionIdentify(String groupName, String imgPath){
		System.out.println("Entered recognitionIdentify");
		JSONObject recognitionIdentifyResult = new JSONObject();
		try {
			recognitionIdentifyResult = httpRequests.recognitionIdentify(new PostParameters().setGroupName(groupName).setImg(new File(imgPath)));
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Exited recognitionIdentify");
		return recognitionIdentifyResult;
	}
	
	public JSONObject personDelete(String personName){
		System.out.println("Entered personDelete");
		JSONObject personDeleteResult = new JSONObject();
		try {
			personDeleteResult = httpRequests.personDelete(new PostParameters().setPersonName(personName));
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Exited personDelete");
		return personDeleteResult;
	}
	
	public JSONObject personAddFace(String personName, String imgPath){
		System.out.println("Entered personAddFace");
		JSONObject detectionResult = detectionDetect(imgPath);
		JSONObject personAddFaceResult = new JSONObject();
		try {
			String faceId = detectionResult.getJSONArray("face").getJSONObject(0).getString("face_id");
			personAddFaceResult = httpRequests.personAddFace(new PostParameters().setPersonName(personName).setFaceId(faceId));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Exited personAddFace");
		return personAddFaceResult;
	}
}
