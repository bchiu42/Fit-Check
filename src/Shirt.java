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
	public enum Fit {CLASSIC("CLASSIC"), MODERN("MODERN"), SLIM("SLIM");
		
	private final String text;
		
		Fit(final String text){
			this.text = text;
		}
	
		public String toString() {
			return text;
		}
	
	};
	Fit fit;

        public Shirt() {
                sleeve = 0;
                collar = 0;
		fit = null;
        }

        public Shirt(int sleeve, int collar, String fit) {
                this.sleeve = sleeve;
                this.collar = collar;
		if (fit.toUpperCase().equals(Fit.CLASSIC.toString())) {
			fit = Fit.CLASSIC.toString();
		}
		else if (fit.toUpperCase().equals(Fit.MODERN.toString())) {
                        fit = Fit.MODERN.toString();
                }
		else if (fit.toUpperCase().equals(Fit.SLIM.toString())) {
                        fit = Fit.SLIM.toString();
                }
        }

        public int getSleeve() {
                return this.sleeve;
        }
        public void setSleeve(int sleeve) {
                this.sleeve = sleeve;
        }
        public int getCollar() {
                return collar;
        }
        public void setCollar(int collar) {
                this.collar = collar;
        }
        public String getFit() {
		return this.fit.toString();
	}
        public void setFit(String fit) {
		if (fit.toUpperCase().equals(Fit.CLASSIC.toString())) {
                        fit = Fit.CLASSIC.toString();
                }
                else if (fit.toUpperCase().equals(Fit.MODERN.toString())) {
                        fit = Fit.MODERN.toString();
                }
                else if (fit.toUpperCase().equals(Fit.SLIM.toString())) {
                        fit = Fit.SLIM.toString();
                }

	}

}
