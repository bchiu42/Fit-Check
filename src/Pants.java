/**
Pants.java created by ben on XPS 15 in Fit Check    

Author: Ben Chiu (blchiu@wisc.edu)
Date: @date

Course CS400
Semester: Spring 2020
Lecture 001

 */

/**
 * Pants holds the inseem and waist in inches
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

	public int getInseem() {
		return inseem;
	}

	public int getWaist() {
		return waist;
	}

	
}
