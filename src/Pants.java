/**
Pants.java created by ben on XPS 15 in Fit Check    

Author: Ben Chiu (blchiu@wisc.edu)
Date: @date

Course CS400
Semester: Spring 2020
Lecture 001

 */

/**
 * Pants = TODO Describe the purpose of this user-defined type
 * @author Chiu (2020)
 *
 */
public class Pants implements Clothing{
	int waist;
	int inseem;

	public Pants() {
		waist = 0;
		inseem = 0;
	}

	public Pants(int waist, int inseem) {
		this.waist = waist;
		this.inseem = inseem;
	}

	private int getWaist() {
		return this.waist;
	}
	private void setWaist(int waist) {
		this.waist = waist;
	}
	private int getInseem() {
		return this.inseem;
	}
	private void setInseem(int inseem) {
		this.inseem = inseem
	}

}
