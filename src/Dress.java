/**
Dress.java created by ben on XPS 15 in Fit Check    

Author: Ben Chiu (blchiu@wisc.edu)
Date: @date

Course CS400
Semester: Spring 2020
Lecture 001

 */

/**
 * Dress just holds the dress size, in sizes of even numbers
 * @author Chiu (2020)
 *
 */
public class Dress implements Clothing{
	int size;

	public Dress() {
		this.size = 0;
	}
	public Dress(int size) {
		this.size = size;
	}
	public int getSize() {
		return this.size;
	}
	public void setSize(int size) {
		this.size = Math.round(size/2) * 2;
	}
}
