package poly;

/**
 * Node implements a linked list node that contains a Term instance.
 * 
 * @author Data Structures Fall 2020
 * @author Cynthia Zhu
 */
public class Node {
	
	/**
	 * Term instance.
	 */
	Term term;
	
	/**
	 * Next node in linked list.
	 */
	Node next;
	
	/**
	 * Initializes a node that contains a term of given coefficient and degree and 
	 * a reference to the given next node.
	 * 
	 * @param coeff Coefficient of term
	 * @param degree Degree of term
	 * @param next Next node in linked list
	 */
	public Node(float coeff, int degree, Node next) {
		term = new Term(coeff, degree);
		this.next = next;
	}
	
}