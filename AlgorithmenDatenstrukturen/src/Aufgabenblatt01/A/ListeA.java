package Aufgabenblatt01.A;

import Aufgabenblatt01.Knoten;
import Aufgabenblatt01.Liste;


/**
 * @author Alexander Mendel
 *
 */
public class ListeA extends Liste{

	private int arraySize = 10;
	private Knoten<?>[] arrayLst = new Knoten<?>[arraySize];
	
	private int anzahlElemente;
	
	@Override
	public void insert (int position, Knoten<?> element) {
		
		if ( (position <= 0) || (position > arrayLst.length) || (element==null) ) {
			throw new IndexOutOfBoundsException();
		}
		
		if (anzahlElemente == (arraySize)) {
			resizeArray();
		}
		
		if (arrayLst[position] == null){
			arrayLst[position] = element;
			anzahlElemente++;
		} else {
			for (int i = (anzahlElemente); i > position; i--) {
				arrayLst[i] = arrayLst[i-1];
			}
			arrayLst[position]  = element;
			anzahlElemente++;
		}
		
	}
	


	@Override
	public void delete(int position) {
		if ( (position <= 0) || (position > arrayLst.length) ) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = position; i < anzahlElemente; i++) {
			arrayLst[i] = arrayLst[i+1];
		}
		anzahlElemente--;
		
	}

	@Override
	public void delete(Schluessel schluessel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int find(Schluessel schluessel) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Knoten<?> retrieve(int position) {
		return arrayLst[position];
	}

	@Override
	public void concat(Liste liste) {
		// TODO Auto-generated method stub
		
	}
	
	public void resizeArray() {
		
		int newArraySize = (int) (arraySize*1.5);
		Knoten<?>[] newArrayLst = new Knoten<?>[newArraySize];
		
		for( int i = 0; i < arraySize; i++) {
			newArrayLst[i] = arrayLst[i];
		}
		arrayLst = newArrayLst;
		arraySize = newArraySize;
		
	}
	public static void main (String [] args) {
		System.out.println();
	}
}


