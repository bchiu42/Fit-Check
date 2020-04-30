import java.util.ArrayList;
import java.util.Hashtable;

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
	private Coat coat;

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

	// Likely unnecessary, done through CustomerTable
	/**
	 * @param ID
	 * @return
	 */
//	public boolean share(String ID) {
//		
//	}

	/**
	 * Updates clothing info based on JSON file
	 * 
	 * @param jsonFilepath
	 */
	public void readFile(String jsonFilepath) {

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

	/**
	 * Updates Customer based on String array
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
