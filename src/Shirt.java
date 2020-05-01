/**
Shirt.java created by ben on XPS 15 in Fit Check    

Author: Ben Chiu (blchiu@wisc.edu)
Date: @date

Course CS400
Semester: Spring 2020
Lecture 001

 */

/**
 * Shirt holds the size of sleeve and collar and the desired fit of a shirt
 * 
 * @author Chiu (2020)
 *
 */
public class Shirt implements Clothing {
	int sleeve;
	int collar;
	String fit;
			
	public enum Fit {		//Enum for the different fits
		CLASSIC("CLASSIC"), MODERN("MODERN"), SLIM("SLIM");

		private final String text;

		Fit(final String text) {
			this.text = text;
		}

		public String toString() {
			return text;
		}

	};

	/**
	 *No arg constructor
	 */
	public Shirt() {
		sleeve = 0;
		collar = 0;
		fit = null;
	}
	
	/**
	 * Constructor with all values specified
	 */
	public Shirt(int sleeve, int collar, String fit) {
		this.sleeve = sleeve;
		this.collar = collar;
		if (fit.toUpperCase().equals("CLASSIC")) {
			this.fit = Fit.CLASSIC.toString();
		} else if (fit.toUpperCase().equals("MODERN")) {
			this.fit = Fit.MODERN.toString();
		} else if (fit.toUpperCase().equals("SLIM")) {
			this.fit = Fit.SLIM.toString();
		}
	}

	/**
	 * Gets this sleeve length
	 */
	public int getSleeve() {
		return this.sleeve;
	}

	/**
	 * Sets this sleeve length
	 *
	 * sleeve - the length of this sleeve
	 */
	public void setSleeve(int sleeve) {
		this.sleeve = sleeve;
	}

	/**
	 * Gets this collar length
	 */
	public int getCollar() {
		return collar;
	}

	/**
	 * Sets this collar length
	 *
	 * collar - the length to be set
	 */
	public void setCollar(int collar) {
		this.collar = collar;
	}

	/**
	 * Gets this fit
	 */
	public String getFit() {
		return this.fit.toString();
	}

	/**
	 * Sets this fit
	 */
	public void setFit(String fit) {
		if (fit.toUpperCase().equals(Fit.CLASSIC.toString())) {
			this.fit = Fit.CLASSIC.toString();
		} else if (fit.toUpperCase().equals(Fit.MODERN.toString())) {
			this.fit = Fit.MODERN.toString();
		} else if (fit.toUpperCase().equals(Fit.SLIM.toString())) {
			this.fit = Fit.SLIM.toString();
		}

	}

}
