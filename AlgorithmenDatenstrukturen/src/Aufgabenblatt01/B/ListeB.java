/**
 * 
 */
package Aufgabenblatt01.B;

import Aufgabenblatt01.Knoten;
import Aufgabenblatt01.Liste;
import Aufgabenblatt01.Schluessel;

/**
 * @author Florian Heuer
 *
 */
public class ListeB extends Liste {

	private Knoten<?> head;
	private Knoten<?> tail;
	
	private int anzahlElemente;
	
	/* (non-Javadoc)
	 * @see Aufgabenblatt01.Liste#insert(int, Aufgabenblatt01.Knoten)
	 */
	@Override
	public void insert(int position, Knoten<?> element) {
		
		//prüfe auf unkorrekte werte
		if(!(position > 0 && position < anzahlElemente+1)){
			throw new IndexOutOfBoundsException();
		}
		
		//erstes Element
		if(head==null){
			head = element;
			tail = element;
			((KnotenB)element).setNext(null);
		}
		
		
		
	}

	/* (non-Javadoc)
	 * @see Aufgabenblatt01.Liste#delete(int)
	 */
	@Override
	public void delete(int position) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see Aufgabenblatt01.Liste#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Schluessel schluessel) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see Aufgabenblatt01.Liste#find(int)
	 */
	@Override
	public int find(Schluessel schluessel) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see Aufgabenblatt01.Liste#retrieve(int)
	 */
	@Override
	public Knoten<?> retrieve(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Aufgabenblatt01.Liste#concat(Aufgabenblatt01.Liste)
	 */
	@Override
	public void concat(Liste liste) {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) {
		
		Liste liste = new ListeB();
		Knoten k1   = new KnotenB( 8 ); 
		
		liste.insert(0, k1);
		
	}

}
