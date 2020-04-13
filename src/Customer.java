import java.util.ArrayList;
import java.util.Hashtable;

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
		super();
		this.name = name;
		CustomerID = customerID;
		Password = password;
		Shared = shared;
		Received = recieved;
	}
	
	//Likely unnecessary, done through CustomerTable
	/**
	 * @param ID
	 * @return
	 */
//	public boolean share(String ID) {
//		
//	}
	
	/**
	 * Updates clothing info based on JSON file
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
	public void addReceived(String customerID2) {
		Received.add(customerID2);
	}
	public void addShared(String receiveID) {
		Shared.add(receiveID);	}
	
}
