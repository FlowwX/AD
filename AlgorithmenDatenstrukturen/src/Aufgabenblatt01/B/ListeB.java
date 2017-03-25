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
	
	private int anzahlElemente = 0;
	
	/* (non-Javadoc)
	 * @see Aufgabenblatt01.Liste#insert(int, Aufgabenblatt01.Knoten)
	 */
	@Override
	public void insert(int position, Knoten<?> element) {
		
		//prüfe auf unkorrekte werte
		if(!(position > 0 && position <= (anzahlElemente+1))){
			throw new IndexOutOfBoundsException();
		}
		
		//erstes Element
		/*if(head==null){
			head = element;
			tail = element;
			((KnotenB)element).setNext(null);
		}*/
		
		KnotenB neuerKnoten = (KnotenB) element;
		KnotenB alterKnoten = (KnotenB) retrieve(position);
		KnotenB vorKnoten   = (KnotenB) retrieve(position-1);
		KnotenB nachKnoten  = null;

		try {
			nachKnoten = (KnotenB) alterKnoten.getNext();
		} catch (Exception e) {
			
		}
		
		if( vorKnoten == null){
			neuerKnoten.setPrev( null );
			head = neuerKnoten;
		}

		neuerKnoten.setNext( alterKnoten );
		neuerKnoten.setPrev(vorKnoten);
		
		try {
			vorKnoten.setNext(neuerKnoten);
		} catch (Exception e) {
			// TODO: handle exception
		}


		try {
			alterKnoten.setPrev(neuerKnoten);
		} catch (Exception e) {
			
		}

		anzahlElemente++;
		
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
		
		KnotenB knoten = (KnotenB) head;
		
		try {
			for(int i=1; i<position; i++){
				knoten = (KnotenB) knoten.getNext();
			}
		} catch (Exception e) {
			knoten = null;
		}

		
		return knoten;
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
		Knoten k1   = new KnotenB( 1 ); 
		Knoten k2   = new KnotenB( 2 ); 
		Knoten k3   = new KnotenB( 3 ); 
		Knoten k4   = new KnotenB( 4 ); 
		
		liste.insert(1, k1);
		liste.insert(2, k2);
		liste.insert(3, k3);
		
		liste.insert(2, k4);
		
		System.out.println("finish!");
	}

}
