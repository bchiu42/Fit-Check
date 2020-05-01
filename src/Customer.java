import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
Customer.java created by ben on XPS 15 in Fit Check    

Author: Ben Chiu (blchiu@wisc.edu)
Date: @date

Course CS400
Semester: Spring 2020
Lecture 001

 */

/**
 * Customer = TODO Describe the purpose of this user-defined type
 * 
 * @author Chiu (2020)
 *
 */
public class Customer {
	private String name;
	private String CustomerID;
	private String Password;
	private ArrayList<String> Shared;
	private ArrayList<String> Received;
	private Shirt shirt;
	private Pants pants;
	private Dress dress;
	private Shoe shoe;

	public Customer(String name, String customerID, String password, ArrayList<String> shared,
			ArrayList<String> recieved) {
		this.name = name;
		CustomerID = customerID;
		Password = password;
		Shared = shared;
		Received = recieved;
		shirt = new Shirt();
		pants = new Pants();
		dress = new Dress();
		shoe = new Shoe();
	}

	public Customer(String name, String customerID, String password) {
		this.name = name;
		CustomerID = customerID;
		Password = password;
		Shared = new ArrayList<String>();
		Received = new ArrayList<String>();
		shirt = new Shirt();
		pants = new Pants();
		dress = new Dress();
		shoe = new Shoe();
	}

	/**
	 * Updates clothing info based on JSON file
	 * 
	 * @param jsonFilepath
	 */
	public void readFile(JSONObject jo) {
		// Get the package
		JSONArray packages = (JSONArray) jo.get("packages");
		// Get the customer's name
		JSONObject customer = (JSONObject) packages.get(0);
		// Put the customer name in a String
		String customerName = (String) customer.get("name");
		// Get the following customer's information
		JSONArray customerInformation = (JSONArray) packages.get(1);
		// Get the gender info from the customer
		JSONObject gender = (JSONObject) customerInformation.get(0);
		// Put the gender info in a String
		String customerGender = (String) gender.get("gender");
		// Get the clothes types as a JSON Array
		JSONArray clothesTypes = (JSONArray) customerInformation.get(1);
		// Deep in clothes types (Not sure about this actually. Do we just need to
		// Print out the info or store those values. Since I am not sure if we need
		// to add more based on the example JSON file, I will just print it by using
		// iterator.
		for (int i = 0; i < clothesTypes.size(); i++) {
			JSONArray clothes = (JSONArray) packages.get(i);
			Iterator iterator = clothes.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		}
		JSONArray sharedPeople = (JSONArray) customerInformation.get(2);
		JSONArray givenPeople = (JSONArray) customerInformation.get(3);
	}

	public JSONObject generateJSON() {
		JSONObject jsonObject = new JSONObject();
		JSONArray customer = new JSONArray();
		JSONObject name = new JSONObject();
		JSONObject customerID = new JSONObject();
		JSONObject password = new JSONObject();
		JSONObject information = new JSONObject();
		JSONArray informationArray = new JSONArray();
		JSONObject clothes = new JSONObject();
		JSONArray clothesArray = new JSONArray();
		JSONArray shirt = new JSONArray();
		JSONObject shirtObj = new JSONObject();
		JSONObject pantsObj = new JSONObject();
		JSONArray pants = new JSONArray();
		JSONObject dress = new JSONObject();
		JSONObject shoe = new JSONObject();
		JSONObject sharedObj = new JSONObject();
		JSONObject givenObj = new JSONObject();
		JSONArray shared = new JSONArray();
		JSONArray given = new JSONArray();
		shirt.add(this.shirt.sleeve);
		shirt.add(this.shirt.collar);
		try {
			shirt.add(this.shirt.fit);
		} catch (NullPointerException e) {
			shirt.add(0);
		}
		pants.add(this.pants.inseem);
		pants.add(this.pants.waist);
		dress.put("dress", this.dress.size);
		shoe.put("shoe", this.shoe.size);
		shirtObj.put("shirt", shirt);
		pantsObj.put("pants", pants);
		clothesArray.add(shirtObj);
		clothesArray.add(pantsObj);
		clothesArray.add(dress);
		clothesArray.add(shoe);
		clothes.put("clothes", clothesArray);
		informationArray.add(clothes);
		name.put("name", this.name);
		name.put("information", informationArray);
		name.put("customerID", this.CustomerID);
		name.put("password", this.Password);
//		customer.add(customerID);
//		customer.add(password);
//		customer.add(information);
		for (int k = 0; k < this.Shared.size(); k++) {
			shared.add(Shared.get(k));
		}
		for (int k = 0; k < this.Received.size(); k++) {
			given.add(Received.get(k));
		}
		name.put("given", given);
		name.put("shared", shared);
//		customer.add(sharedObj);
//		customer.add(givenObj);
		customer.add(name);

		jsonObject.put("packages", customer);
		try {
			FileWriter file = new FileWriter(this.CustomerID + ".JSON");
			file.write(jsonObject.toJSONString());
			file.close();
		} catch (IOException e) {
			System.out.println("failed");
		}
		return jsonObject;
	}

	public String getCustomerID() {
		return CustomerID;
	}

	public String getPassword() {
		return Password;
	}

	public ArrayList<String> getShared() {
		return Shared;
	}

	public ArrayList<String> getReceived() {
		return Received;
	}

	public Customer addReceived(String customerID2) {
		Received.add(customerID2);
		return this;
	}

	public Customer addShared(String receiveID) {
		Shared.add(receiveID);
		return this;
	}

	public void parseJSON(JSONObject jo) {
		// TODO Auto-generated method stub

	}

	public String getName() {
		return name;
	}

	public void printReceived() {
		for (String e : Received) {
			System.out.println(e);
		}
	}

	/**
	 * get analytics of this customer
	 * 
	 * @return a string contains the analytics
	 */
	public String getAnalytics() {
		String result = "";
		result += "Name: " + this.name;
		result += " ; ID: " + this.CustomerID;
		int sharedNum = this.Shared.size();
		if (sharedNum == 0 || sharedNum == 1) {
			result += " ; Shared with " + sharedNum + " other customer.";
		} else {
			result += " ; Shared with " + sharedNum + " other customers.";
		}
		return result;
	}

	/**
	 * Creates Measurements array used in profile Scene
	 * 
	 * @return
	 */
	public String[] getMeasurements() {
		String[] ret = new String[7];
		try {
			ret[0] = Integer.toString(shirt.getSleeve());
		} catch (NullPointerException e) {
			ret[0] = "-1";
		}
		try {
			ret[1] = Integer.toString(shirt.getCollar());
		} catch (NullPointerException e) {
			ret[1] = "-1";
		}
		try {
			ret[2] = shirt.getFit();
		} catch (NullPointerException e) {
			ret[2] = "-1";
		}
		try {
			ret[3] = Integer.toString(pants.waist);
		} catch (NullPointerException e) {
			ret[3] = "-1";
		}
		try {
			ret[4] = Integer.toString(pants.inseem);
		} catch (NullPointerException e) {
			ret[4] = "-1";
		}

		try {
			ret[5] = Integer.toString(dress.getSize());
		} catch (NullPointerException e) {
			ret[5] = "-1";
		}
		try {
			ret[6] = Integer.toString(shoe.size);
		} catch (NullPointerException e) {
			ret[6] = "-1";
		}

		return ret;
	}

	public Shirt getShirt() {
		return shirt;
	}

	public void setShirt(Shirt shirt) {
		this.shirt = shirt;
	}

	public Pants getPants() {
		return pants;
	}

	public void setPants(Pants pants) {
		this.pants = pants;
	}

	public Dress getDress() {
		return dress;
	}

	public void setDress(Dress dress) {
		this.dress = dress;
	}

	public Shoe getShoe() {
		return shoe;
	}

	public void setShoe(Shoe shoe) {
		this.shoe = shoe;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}

	public void setPassword(String password) {
		Password = password;
	}

	/**
	 * Updates Customer based on String array
	 * 
	 * @param measurements
	 */
	public void update(String[] measurements) {
		shirt.sleeve = Integer.parseInt(measurements[0]);
		shirt.collar = Integer.parseInt(measurements[1]);
		shirt.fit = measurements[2];
		int temp = Integer.parseInt(measurements[3]);
		System.out.println(temp);
		pants.waist = temp;
		pants.inseem = Integer.parseInt(measurements[4]);
		dress.size = Integer.parseInt(measurements[5]);
		shoe.size = Integer.parseInt(measurements[6]);
	}
}
