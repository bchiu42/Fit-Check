import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
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
 * CustomerTable creates a hashtable of all the customers created so far
 * @author Chiu (2020)
 *
 */
public class CustomerTable {
	public Customer getCurrentCustomer() {
		return currentCustomer;
	}
	private Hashtable<String, Customer> table;
	private Customer currentCustomer;
	private ArrayList<String> IDs = new ArrayList<String>();
	private int size;
	public CustomerTable() {
		table = new Hashtable<String, Customer>();
		size = 0;
	}
	public boolean insert(Customer newEntry) {
		if(table.contains(newEntry.getCustomerID())) {
			return false;
		}
		table.put(newEntry.getCustomerID(), newEntry);
		IDs.add(newEntry.getCustomerID());
		size++;
		return true;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
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
	
	
	public Customer getCustomer(String user) {
		return table.get(user);
	}
	/**
	 * Checks if table contains a customerID
	 * @param customerID
	 * @return
	 */
	public boolean contains(String customerID) {
		if(IDs.contains(customerID)) return true;
		if(table.containsKey(customerID)) return true;
		return false;
	}
	/**
	 * Sets Current Customer
	 * @param customer
	 * @throws NoSuchObjectException if customer is not in the table
	 */
	public void setCurrentCustomer(Customer customer) throws NoSuchObjectException {
		if(!table.contains(customer)) {
			throw new NoSuchObjectException("customer being set is not in table");
		}
		currentCustomer = customer;
	}
	
	/**
	 * Replace Method that can help update in certain cases 
	 * @param oldCusID
	 * @param updatedCustomer
	 * @return
	 */
	public boolean replace(String oldCusID, Customer updatedCustomer) {
		if(table.replace(oldCusID, updatedCustomer)==null)return false;
		return true;
	}
	/**
	 * Sets CurCustomer to Null
	 */
	public void clearCurrentCustomer() {
		currentCustomer = null;
	}
	public void remove(Customer currentCustomer2) {
		table.remove(currentCustomer2.getCustomerID());
	}

}
