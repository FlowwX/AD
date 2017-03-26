/**
 * 
 */
package Aufgabenblatt01.B;

import Aufgabenblatt01.Knoten;
import Aufgabenblatt01.Liste;
import Aufgabenblatt01.Schluessel;

/**
 * Listenimplementierung B
 * 
 * @author Florian Heuer
 *
 */
public class ListeB<T> extends Liste<T> {

	private KnotenB<T> head;
	
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
		
		KnotenB<T> neuerKnoten = new KnotenB<T>(element);
		KnotenB<T> alterKnoten = getNode(position);
		KnotenB<T> vorKnoten   = getNode(position-1);
		
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
		
		//prüfe auf invlide Eingabe für position
		if(!(position > 0 && position <= anzahlElemente )){
			throw new IndexOutOfBoundsException();
		}
		
		KnotenB<T> knoten 		= getNode(position);
		KnotenB<T> vorKnoten	= (KnotenB<T>) knoten.getPrev();
		KnotenB<T> nachKnoten	= (KnotenB<T>) knoten.getNext();
		
		try {
			vorKnoten.setNext(nachKnoten);
		} catch (Exception e) {
			
		}
		
		try {
			nachKnoten.setPrev(vorKnoten);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		anzahlElemente--;
	}

	/* (non-Javadoc)
	 * @see Aufgabenblatt01.Liste#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Schluessel schluessel) {
		int position = find(schluessel);
		if(position > 0){
			delete(position);
		}
	}

	/* (non-Javadoc)
	 * @see Aufgabenblatt01.Liste#find(int)
	 */
	@Override
	public int find(Schluessel schluessel) {
		
		KnotenB<T> knoten 	= head;
		int position 		= 0;
		
		for(int i=1; i <= anzahlElemente; i++ ){
			if( schluessel.getWert() == knoten.getSchluessel().getWert() ){
				position = i;
			}
		}
		
		return position;
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
		
		//finde ende von liste
		KnotenB<T> letzterKnoten = getNode(anzahlElemente);
		KnotenB<T> ersterKnoten  = ((ListeB) liste).getHead();
		
		
		try {
			letzterKnoten.setNext(ersterKnoten);
		} catch (NullPointerException e) {
			// leere Liste
		}
		
		try {
			ersterKnoten.setPrev(letzterKnoten);
		} catch (NullPointerException e) {
			// leere Liste
		}
		
	}
	
	/**
	 * Helfer Funktion, die den Knoten zur Position liefert.
	 * @param position
	 * @return
	 */
	private KnotenB<T> getNode(int position) {
		
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
	
	public KnotenB<T> getHead() {
		return head;
	}
	
	
	
	
	public static void main(String[] args) {
		
		Liste<Integer> liste = new ListeB<Integer>();

		liste.insert(1, 1);
		liste.insert(2, 2);
		liste.insert(3, 3);
		
		liste.insert(2, 4);
		
		liste.delete(2);
		
		System.out.println("finish!");
	}

}
