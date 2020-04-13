import java.util.Hashtable;

/**
CustomerTable.java created by ben on XPS 15 in Fit Check    

Author: Ben Chiu (blchiu@wisc.edu)
Date: @date

Course CS400
Semester: Spring 2020
Lecture 001

 */

/**
 * CustomerTable = TODO Describe the purpose of this user-defined type
 * @author Chiu (2020)
 *
 */
public class CustomerTable {
	private Hashtable<String, Customer> table;
	private Customer currentCustomer;
	public CustomerTable() {
		table = new Hashtable<String, Customer>();
	}
	public void insert(Customer newEntry) {
		table.put(newEntry.getCustomerID(), newEntry);
	}
	public boolean login(String ID, String password) {
		Customer temp = table.get(ID);
		if(temp == null) {
			return false;
		}
		if(temp.getPassword().equals(password)) {
			currentCustomer = temp;
			return true;
		}
		return false;
	}
	
	
	/**
	 * Checks if current User can access the ID given in parameter
	 * @param AccessID
	 * @return
	 */
	public boolean canAccess(String AccessID) {
// implementation if Customer holds Recived info		
		if(currentCustomer.getReceived().contains(AccessID)) return true;
		return false;
//		implementation if Customers hold Shared info
//		Customer temp = table.get(AccessID);
//		if(temp.getShared().contains(AccessID)) {
//			return true;
//		}
//		return false;
	}
	
	/**
	 * Shares the current User's info with another user
	 * @param receiveID
	 * @return false if other user is not found else true
	 */
	public boolean Share(String receiveID) {
		Customer temp = table.get(receiveID);
		if(temp == null) {
			return false;
		}
		temp.addReceived(currentCustomer.getCustomerID());
		currentCustomer.addShared(receiveID);
		return true;
		}

}
