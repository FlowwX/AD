
package Aufgabenblatt01.A;

import Aufgabenblatt01.Liste;
import Aufgabenblatt01.Schluessel;


/**
 * @author Alexander Mendel
 *
 */
public class ListeA<T> extends Liste<T>{

	private int arraySize = 10;
	// private KnotenA<?>[] arrayLst = new KnotenA<?>[arraySize]; //Generische arrays koennen nicht erstellt werden 
	
	private KnotenA<T>[] arrayLst;
	
	private int anzahlElemente;
	
	private int anzahlOperationen;
	

	@SuppressWarnings("unchecked")
	ListeA() {
		arrayLst = (KnotenA<T>[]) new Object[arraySize];
	}
	
	
	@Override
	public void insert (int position, T element) throws IndexOutOfBoundsException {
		
		if ( (position <= 0) || (position > arrayLst.length) || (element==null) ) {
			throw new IndexOutOfBoundsException();
		}
		
		if (anzahlElemente == (arraySize)) {
			resizeArray();
		}
		
		if (arrayLst[position] == null){
			
			arrayLst[position] = new KnotenA<T>(element);
			anzahlElemente++;
			anzahlOperationen++;
			
			//Erhoeht sich "anzahlOperationen" bei "anzahlElemente++"?
			
		} else {
			for (int i = (arraySize); i > position; i--) {
				arrayLst[i] = arrayLst[i-1];
				anzahlOperationen++;
				
			}
			arrayLst[position]  = new KnotenA<T>(element);
			anzahlElemente++;
			anzahlOperationen++;
			//Erhoeht sich "anzahlOperationen" bei "anzahlElemente++"?
		}
		
	}
	


	@Override
	public void delete(int position) throws IndexOutOfBoundsException {
		if ( (position <= 0) || (position > arrayLst.length) ) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = position; i < arraySize; i++) {
			arrayLst[i] = arrayLst[i+1];
			anzahlOperationen++;
		}
		anzahlElemente--;
		//Erhoeht sich "anzahlOperationen" bei "anzahlElemente--"?
	}
	
	
	@Override
	public void delete(Schluessel schluessel) {
		
		delete(find(schluessel));
				
	}



	@Override
	public int find(Schluessel schluessel) {
				
		for (int i = 0; i < arrayLst.length; i++){
			if (arrayLst[i].getSchluessel() == schluessel) {
				return i;
			}
			anzahlOperationen++;
		}
		return -1;
	}


	@Override
	public T retrieve(int position) throws IndexOutOfBoundsException {
		if (position < 0 || position > getSize()){
			throw new IndexOutOfBoundsException();
		}
		anzahlOperationen++;
		//??
		
		return (T) arrayLst[position].getDaten();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public void concat(Liste<?> liste) {

		
		int newConcatedSize = arrayLst.length + liste.getSize();
		anzahlOperationen++;
		
		KnotenA<T>[] newConcatedArrayLst = (KnotenA<T>[]) new Object[newConcatedSize];
		anzahlOperationen++;
		
		for ( int i = 0; i < arraySize; i++) {
			newConcatedArrayLst[i] = arrayLst[i];
			anzahlOperationen++;
		}
		for (int j = arraySize; j < newConcatedSize; j++) {
			newConcatedArrayLst[j] = (KnotenA<T>) liste.retrieve(j);
			anzahlOperationen++;
		}
		
		arrayLst = newConcatedArrayLst;
		anzahlOperationen++;
		
		arraySize = newConcatedSize;
		anzahlOperationen++;
	}
	
	
	@SuppressWarnings("unchecked")
	public void resizeArray() {
		
		
		int newArraySize = (int) (arraySize*1.5);
		anzahlOperationen++;
		KnotenA<T>[] newArrayLst = (KnotenA<T>[]) new Object[newArraySize];
		anzahlOperationen++;
		
		for( int i = 0; i < arraySize; i++) {
			newArrayLst[i] = arrayLst[i];
			anzahlOperationen++;
		}
		arrayLst = newArrayLst;
		anzahlOperationen++;
		
		arraySize = newArraySize;
		anzahlOperationen++;
	}
	
	@Override
	public int getSize() {
		//erhoeht sich "anzahlOperationen"?
		return arrayLst.length;
	}

	public static void main (String [] args) {
		System.out.println();
	}


}


