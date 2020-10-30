package poly;

/**
 * Term implements a term of a polynomial.
 * 
 * @author Data Structures Fall 2020
 * @author Cynthia Zhu
 */
public class Term {
	
	/**
	 * Coefficient of term.
	 */
	public float coeff;
	
	/**
	 * Degree of term.
	 */
	public int degree;
	
	/**
	 * Initializes a term of given coefficient and degree.
	 * 
	 * @param coeff Coefficient of term
	 * @param degree Degree of term
	 */
	public Term(float coeff, int degree) {
		this.coeff = coeff;
		this.degree = degree;
	}
	
	/**
	 * Returns a string representation of a term.
	 * 
	 * @return String representation of term
	 */
	public String toString() {
		if (degree == 0) {
			return coeff + "";
		} else if (degree == 1) {
			return coeff + "x";
		} else {
			return coeff + "x^" + degree;
		}
	}
	
}