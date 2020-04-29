/**
Shirt.java created by ben on XPS 15 in Fit Check    

Author: Ben Chiu (blchiu@wisc.edu)
Date: @date

Course CS400
Semester: Spring 2020
Lecture 001

 */

/**
 * Shirt = TODO Describe the purpose of this user-defined type
 * @author Chiu (2020)
 *
 */
public class Shirt implements Clothing{
	int sleeve;
        int collar;
	public enum Fit {CLASSIC, MODERN, SLIM};
	Fit fit;

        public Shirt() {
                sleeve = 0;
                collar = 0;
		fit == null;
        }

        public Shirt(int sleeve, int collar, String fit) {
                this.sleeve = sleeve;
                this.collar = collar;
		if (fit.toUpperCase.equals(Fit.CLASSIC.toString)) {
			fit = Fit.CLASSIC;
		}
		else if (fit.toUpperCase.equals(Fit.MODERN.toString)) {
                        fit = Fit.MODERN;
                }
		else if (fit.toUpperCase.equals(Fit.SLIM.toString)) {
                        fit = Fit.SLIM;
                }
        }

        private int getSleeve() {
                return this.sleeve;
        }
        private void setSleeve(int sleeve) {
                this.sleeve = sleeve;
        }
        private int getCollar() {
                return this.collar
        }
        private void setCollar(int collar) {
                this.collar = collar;
        }
	private String getFit() {
		return this.fit.toString;
	}
	private void setFit(String fit) {
		if (fit.toUpperCase.equals(Fit.CLASSIC.toString)) {
                        fit = Fit.CLASSIC;
                }
                else if (fit.toUpperCase.equals(Fit.MODERN.toString)) {
                        fit = Fit.MODERN;
                }
                else if (fit.toUpperCase.equals(Fit.SLIM.toString)) {
                        fit = Fit.SLIM;
                }

	}

}
