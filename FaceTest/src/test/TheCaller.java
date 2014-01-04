package test;

import javax.swing.*;

import org.json.JSONException;
import org.json.JSONObject;

public class TheCaller {
	
	private static String welcomeMessage = "WELCOME!!\nWhat do you want to do?\n\n0.Exit\n1.Create a person\n2.Add a face to a person\n3.Add a person to Test group\n"
			+ "4.Train the test group\n5.Identify a person from Test group\n6.Delete a person";
	private static String choice;
	private static String personName;
	private static String tag;
	private static String imgPath;
	private static FaceTest faceTest =  new FaceTest();
	private static JSONObject result = new JSONObject();
	
	public TheCaller() {
		super();
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		choice = JOptionPane.showInputDialog(welcomeMessage+"\nEnter your choice");
		System.out.println(choice);
		switch(Integer.parseInt(choice)){
		
		case 1:
			personName = JOptionPane.showInputDialog("Enter name of the person");
			tag = JOptionPane.showInputDialog("Enter a tag");
			imgPath = JOptionPane.showInputDialog("Enter path of the image of the person");
			result = faceTest.personCreate(personName, tag, imgPath);
			System.out.println(result);
			break;
			
		case 2:
			personName = JOptionPane.showInputDialog("Enter name of the person");
			imgPath = JOptionPane.showInputDialog("Enter path of the image of the person");
			result = faceTest.personAddFace(personName, imgPath);
			System.out.println(result);
			break;
			
		case 3:
			personName = JOptionPane.showInputDialog("Enter person name");
			result = faceTest.groupAddPerson("Test", personName);
			System.out.println(result);
			break;
			
		case 4:
			System.out.println(faceTest.trainIdentify("Test"));
			break;
			
		case 5:
			imgPath = JOptionPane.showInputDialog("Enter path of the image of the person");
			result = faceTest.recognitionIdentify("Test", imgPath);
			try {
				System.out.println(result.getJSONArray("face").getJSONObject(0).getJSONArray("candidate").getJSONObject(0).get("person_name"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
		case 6:
			personName = JOptionPane.showInputDialog("Enter person's name");
			result = faceTest.personDelete(personName);
			System.out.println(result);
			break;
			
		case 0:
			System.exit(0);
			break;
			
		default:
			System.out.println("Wrong! Please enter again");
			break;
		}
		System.exit(0);
	}
}
