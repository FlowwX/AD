
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
	
	private KnotenA<?>[] arrayLst;
	
	private int anzahlElemente;
	
	private static int anzahlOperationen;
	

	//@SuppressWarnings("unchecked")
	ListeA() {
		arrayLst = (KnotenA<T>[]) new Object[arraySize];
		//arrayLst = new KnotenA<?>[arraySize];
	}
	
	
	@Override
	public void insert (int position, T element) throws IndexOutOfBoundsException {
		
		if ( (position < 0) || (position > arrayLst.length) || (element==null) ) {	//position <= 0
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
		if ( (position < 0) || (position > arrayLst.length) ) {	//position <= 0
			throw new IndexOutOfBoundsException();
		}
		KnotenA<?>[] tempArrayLst = new KnotenA<?>[arraySize];
		
		for (int i = 0; i < position; i++) {	
			tempArrayLst[i] = arrayLst[i];
			anzahlOperationen++;
		}
		for (int i = (position+1); i < arraySize; i++) {	
			tempArrayLst[i-1] = arrayLst[i];
			anzahlOperationen++;
		}
		arrayLst = tempArrayLst;
		anzahlOperationen++;
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


	@SuppressWarnings("unchecked")
	@Override
	public T retrieve(int position) throws IndexOutOfBoundsException {
		if (position < 0 || position > getSize()){
			throw new IndexOutOfBoundsException();
		}
		anzahlOperationen++;
		//??
		
		return (T) arrayLst[position];
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public void concat(Liste<?> liste) {

		
		int newConcatedSize = arrayLst.length + liste.getSize();
		anzahlOperationen++;
		
		//KnotenA<T>[] newConcatedArrayLst = (KnotenA<T>[]) new Object[newConcatedSize];
		KnotenA<?>[] newConcatedArrayLst = (KnotenA<T>[]) new Object[newConcatedSize];
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
		//KnotenA<T>[] newArrayLst = (KnotenA<T>[]) new Object[newArraySize];
		KnotenA<?>[] newArrayLst = (KnotenA<T>[]) new Object[newArraySize];
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

	public static int getAnzahlOperationen() {
		return anzahlOperationen;
	}


	public static void main (String [] args) {
		Liste<Integer> testListe = new ListeA<Integer>();
		
		testListe.insert(3, 42);
		testListe.insert(4, 42);
		testListe.insert(5, 42);
		testListe.insert(1, 52);
		testListe.insert(9, 52);
		testListe.delete(5);
		testListe.delete(1);
		testListe.delete(2);
		testListe.retrieve(3);
		System.out.println(getAnzahlOperationen());
		
	}


}


