/**
 * 
 */
package Aufgabenblatt01.B;

import Aufgabenblatt01.Knoten;

/**
 * @author Florian Heuer
 *
 */
public class KnotenB extends Knoten<Integer> {

	private Knoten<?> prev;
	
	private Knoten<?> next;

	public Knoten<?> getPrev() {
		return prev;
	}

	public void setPrev(Knoten<?> prev) {
		this.prev = prev;
	}

	public Knoten<?> getNext() {
		return next;
	}

	public void setNext(Knoten<?> next) {
		this.next = next;
	}
	
	
	
}
