package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * Polynomial adds, multiplies, and evaluates polynomials using linked lists.
 * 
 * @author Data Structures Fall 2020
 * @author Cynthia Zhu
 */
public class Polynomial {
	
	/**
	 * Reads from input a polynomial in the following format:
	 * <pre>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * 
	 * @param sc Scanner that reads polynomial
	 * @throws IOException If input error is encountered
	 * @return Polynomial that is read from input (head of linked list)
	 */
	public static Node read(Scanner sc) throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials.
	 * 
	 * @param poly1 First polynomial (head of linked list)
	 * @param poly2 Second polynomial (head of linked list)
	 * @return Sum of first polynomial and second polynomial (head of new linked list)
	 */
	public static Node add(Node poly1, Node poly2) {
		
		Node sum = null;
		Node head = sum;
		
		if (poly1 != null && poly2 == null) {
			//creates deep copy of first polynomial linked list
			for (Node current = poly1; current != null; current = current.next) {
				if (sum == null) {
					sum = new Node(current.term.coeff, current.term.degree, null);
					head = sum;
				} else {
					sum.next = new Node(current.term.coeff, current.term.degree, null);
					sum = sum.next;
				}
			}
		}
		
		if (poly1 == null && poly2 != null) {
			//creates deep copy of second polynomial linked list
			for (Node current = poly2; current != null; current = current.next) {
				if (sum == null) {
					sum = new Node(current.term.coeff, current.term.degree, null);
					head = sum;
				} else {
					sum.next = new Node(current.term.coeff, current.term.degree, null);
					sum = sum.next;
				}
			}
		}
		
		if (poly1 != null && poly2 != null) {
			//creates deep copy of first polynomial linked list
			for (Node current = poly1; current != null; current = current.next) {
				if (sum == null) {
					sum = new Node(current.term.coeff, current.term.degree, null);
					head = sum;
				} else {
					sum.next = new Node(current.term.coeff, current.term.degree, null);
					sum = sum.next;
				}
			}
			
			//adds second polynomial to deep copy of first polynomial
			for (Node current2 = poly2; current2 != null; current2 = current2.next) {
				for (Node currentSum = head; currentSum != null; currentSum = currentSum.next) {
					//inserts current term of second polynomial before first term of first polynomial
					if (current2.term.degree < head.term.degree) {
						Node temp = new Node(current2.term.coeff, current2.term.degree, head);
						head = temp;
						break;
					}
					
					//combines like terms
					if (current2.term.degree == currentSum.term.degree) {
						currentSum.term.coeff = currentSum.term.coeff + current2.term.coeff;
						break;
					}
					
					//inserts current term of second polynomial after last term of first polynomial
					if (currentSum.next == null) {
						Node temp = new Node(current2.term.coeff, current2.term.degree, null);
						currentSum.next = temp;
						break;
					}
					
					//inserts current term of second polynomial after current term of first polynomial
					if ((current2.term.degree > currentSum.term.degree) && 
						(current2.term.degree < currentSum.next.term.degree)) {
						Node temp = new Node(current2.term.coeff, current2.term.degree, currentSum.next);
						currentSum.next = temp;
						break;
					}
				}
			}
			
			//removes terms with zero coefficients
			Node toHead = new Node(0.0f, 0, head);
			Node current = toHead;
			while (current.next != null) {
				if (current.next.term.coeff == 0.0f) {
					current.next = current.next.next;
				} else {
					current = current.next;
				}
			}
			head = toHead.next;
		}
		
		return head;
		
	}
	
	/**
	 * Returns the product of two polynomials.
	 * 
	 * @param poly1 First polynomial (head of linked list)
	 * @param poly2 Second polynomial (head of linked list)
	 * @return Product of first polynomial and second polynomial (head of new linked list)
	 */
	public static Node multiply(Node poly1, Node poly2) {
		
		Node product1 = null;
		Node head1 = product1;
		Node head2 = null;
		Node head3 = null;
		
		if (poly1 != null && poly2 != null) {
			//multiplies first polynomial and second polynomial
			for (Node current1 = poly1; current1 != null; current1 = current1.next) {
				for (Node current2 = poly2; current2 != null; current2 = current2.next) {
					if (product1 == null) {
						product1 = new Node(current1.term.coeff * current2.term.coeff, 
											current1.term.degree + current2.term.degree, 
											null);
						head1 = product1;
					} else {
						product1.next = new Node(current1.term.coeff * current2.term.coeff, 
												 current1.term.degree + current2.term.degree, 
												 null);
						product1 = product1.next;
					}
				}
			}
			
			//creates new linked list that contains first term of product polynomial
			head2 = new Node(head1.term.coeff, head1.term.degree, null);
			//removes first term from product polynomial
			head1 = head1.next;
			
			//combines like terms of product polynomial
			head3 = add(head2, head1);
		}
		
		return head3;
		
	}
	
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (head of linked list)
	 * @param x Value
	 * @return Value of polynomial at x
	 */
	public static float evaluate(Node poly, float x) {
		
		float value = 0.0f;
		
		if (poly != null) {
			for (Node current = poly; current != null; current = current.next) {
				value = value + (current.term.coeff * (float)(Math.pow(x, current.term.degree)));
			}
		}
		
		return value;
		
	}
	
	/**
	 * Returns a string representation of a polynomial.
	 * 
	 * @param poly Polynomial (head of linked list)
	 * @return String representation of polynomial
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		}
		
		String polyString = "";
		for (Node current = poly; current != null; current = current.next) {
			polyString = current.term.toString() + " + " + polyString;
		}
		
		return polyString;
	}
	
}