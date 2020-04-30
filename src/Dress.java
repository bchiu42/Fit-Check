/**
Dress.java created by ben on XPS 15 in Fit Check    

Author: Ben Chiu (blchiu@wisc.edu)
Date: @date

Course CS400
Semester: Spring 2020
Lecture 001

 */

/**
 * Dress = TODO Describe the purpose of this user-defined type
 * @author Chiu (2020)
 *
 */
public class Dress implements Clothing{
	double size;

	public Dress() {
		this.size = 0;
	}
	public Dress(double size) {
		this.size = size;
	}
	private int getSize() {
		return this.size;
	}
	private void setSize(int size) {
		this.size = Math.round(size/2) * 2;
	}
}
