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
public class ListeB<T> extends Liste<T> {

	private Knoten<T> head;
	
	private int anzahlElemente = 0;
	
	/* (non-Javadoc)
	 * @see Aufgabenblatt01.Liste#insert(int, Aufgabenblatt01.Knoten)
	 */
	@Override
	public void insert(int position, T element) {
		
		//prüfe auf unkorrekte werte
		if(!(position > 0 && position <= (anzahlElemente+1))){
			throw new IndexOutOfBoundsException();
		}
		
		KnotenB neuerKnoten = new KnotenB<T>(element);
		KnotenB alterKnoten = getNode(position);
		KnotenB vorKnoten   = getNode(position-1);
		KnotenB nachKnoten  = null;

		//versuche den alten Knoten an gegebener Position zu holen
		try {
			nachKnoten = (KnotenB) alterKnoten.getNext();
		} catch (Exception e) {
			
		}
		
		//erstes Element
		if( vorKnoten == null){
			neuerKnoten.setPrev( null );
			head = neuerKnoten;
		}
		
		//setze Referrenzen um
		neuerKnoten.setNext( alterKnoten );
		neuerKnoten.setPrev(vorKnoten);
		
		//vorheriger Knoten kann null sein
		try {
			vorKnoten.setNext(neuerKnoten);
		} catch (Exception e) {
			// TODO: handle exception
		}

		//alter Knoten kann ebenfalls null sein
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
	public T retrieve(int position) {
		return (T) getNode(position).getDaten();
	}
	
	/* (non-Javadoc)
	 * @see Aufgabenblatt01.Liste#concat(Aufgabenblatt01.Liste)
	 */
	@Override
	public void concat(Liste liste) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Helfer Funktion, die den Knoten zur Position liefert.
	 * @param position
	 * @return
	 */
	private KnotenB getNode(int position) {
		
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
	
	
	
	
	public static void main(String[] args) {
		
		Liste<Integer> liste = new ListeB<Integer>();

		liste.insert(1, 1);
		liste.insert(2, 2);
		liste.insert(3, 3);
		
		liste.insert(2, 4);
		
		System.out.println("finish!");
	}

}
