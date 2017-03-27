
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
	

	public static int anzahlOperationen;
	
	//"Position" im Array fängt bei 1 an, wird im Array aber schon bei 0 gespeichert
	//@SuppressWarnings("unchecked")
	public ListeA() {
		//arrayLst = (KnotenA<T>[]) new Object[arraySize];
		arrayLst = new KnotenA<?>[arraySize];
	}
	
	
	@Override
	public void insert (int position, T element) throws IndexOutOfBoundsException {
		
		if ( (position <= 0) || (position > (anzahlElemente+2) ) || (element==null) ) {	//position < 0, position > arrayLst.length
			throw new IndexOutOfBoundsException();
		}
		
		
		if (anzahlElemente == (arraySize)) {
			resizeArray();
		}
		
		KnotenA<?>[] newArrayLst = new KnotenA<?>[arraySize];
		
		if (arrayLst[position-1] == null){
			
			arrayLst[position-1] = new KnotenA<T>(element);
			anzahlOperationen++; 		//Messcounter
			anzahlElemente++;
			anzahlOperationen++; 		//Messcounter
			
			
		} else {
			for (int i = 0; i < position-1; i++) {
				newArrayLst[i] = arrayLst[i];
			}
			
			for (int i = anzahlElemente; i > position; i--) {
				newArrayLst[i-1] = arrayLst[i-2];
				anzahlOperationen++;	//Messcounter
				
			}
			newArrayLst[position-1]  = new KnotenA<T>(element);
			anzahlOperationen++;		//Messcounter
			anzahlElemente++;
			anzahlOperationen++;		//Messcounter
			
			arrayLst = newArrayLst;
		}
		
	}
	


	@Override
	public void delete(int position) throws IndexOutOfBoundsException {
		if ( (position <= 0) || (position > anzahlElemente) ) {	//alternative: position < 0 und position > arrayLst.length
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
		
		//anzahlOperationen++;
		//Erhoeht sich "anzahlOperationen" bei "anzahlElemente--"?
	}
	
	
	@Override
	public void delete(Schluessel schluessel) throws IndexOutOfBoundsException {
		
		delete(find(schluessel));
				
	}



	@Override
	public int find(Schluessel schluessel) {
				
		for (int i = 0; i < anzahlElemente; i++){ //alternative: i < arrayLst.length
			if (arrayLst[i].getSchluessel().equals(schluessel) ) {
				return i+1;
			}
			anzahlOperationen++;
		}
		return -1;
	}


	@SuppressWarnings("unchecked")
	@Override
	public T retrieve(int position) throws IndexOutOfBoundsException {
		if (position <= 0 || position > getSize() ){ //alternative: > getSize()
			throw new IndexOutOfBoundsException();
		}
		anzahlOperationen++;
		//??
		
		return (T) arrayLst[position-1].getDaten();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public void concat(Liste<?> liste) {

		
		int newConcatedSize = getSize() + liste.getSize();
		anzahlOperationen++;
		
		//KnotenA<T>[] newConcatedArrayLst = (KnotenA<T>[]) new Object[newConcatedSize];
		KnotenA<?>[] newConcatedArrayLst = new KnotenA<?>[newConcatedSize];
		anzahlOperationen++;
		
		for ( int i = 0; i < getSize(); i++) {
			newConcatedArrayLst[i] = arrayLst[i];
			anzahlOperationen++;
		}
		for (int j = getSize(); j < newConcatedSize; j++) {
			int i = 1;
			//newConcatedArrayLst[j] = (KnotenA<?>) liste.retrieve(j);
			newConcatedArrayLst[j] = new KnotenA<T> ((T) liste.retrieve(i));
			i++;
			anzahlOperationen++;
			
		}
		
		arraySize = newConcatedSize;
		anzahlOperationen++;
		
		arrayLst = newConcatedArrayLst;
		anzahlOperationen++;
		
	}
	
	
	public void resizeArray() {
		
		
		int newArraySize = (int) (arraySize*1.5);
		anzahlOperationen++;
		//KnotenA<T>[] newArrayLst = (KnotenA<T>[]) new Object[newArraySize];
		KnotenA<?>[] newArrayLst = new KnotenA<?>[newArraySize];
		anzahlOperationen++;
		
		for( int i = 0; i < anzahlElemente; i++) {
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
		return anzahlElemente;
	}

	public static int getAnzahlOperationen() {
		return anzahlOperationen;
	}

	public static void main (String [] args) {
		Liste<Integer> testListe = new ListeA<Integer>();
		
		testListe.insert(1, 42);
		testListe.insert(2, 42);
		testListe.insert(3, 42);
		testListe.insert(4, 52);
		testListe.insert(5, 52);
		testListe.insert(6, 52);
		testListe.insert(7, 52);
		testListe.insert(8, 52);
		testListe.insert(9, 52);
		testListe.insert(10, 52);
		testListe.insert(11, 52);
		testListe.insert(12, 52);
		testListe.insert(13, 52);
		testListe.insert(14, 52);
		testListe.insert(5, 52);
		System.out.println(testListe.retrieve(1));
		testListe.delete(4);
		testListe.delete(1);
		testListe.delete(2);
		testListe.delete(1);
		System.out.println(testListe.retrieve(1));
		
		Liste<Integer> testListe2 = new ListeA<Integer>();
		testListe2.insert(1, 42);
		testListe2.insert(2, 42);
		testListe2.insert(3, 42);
		testListe2.insert(4, 52);
		testListe2.insert(5, 52);
		testListe2.insert(6, 52);
		testListe2.insert(7, 52);
		testListe2.insert(8, 52);
		testListe2.insert(9, 52);
		System.out.println(testListe.getSize());
		testListe.insert(1, 2);
		System.out.println(testListe2.getSize());
		System.out.println(testListe2.retrieve(1));
		System.out.println(testListe2.retrieve(2));
		System.out.println(testListe2.retrieve(3));
		System.out.println(testListe2.retrieve(4));
		System.out.println(getAnzahlOperationen());
		testListe.concat(testListe2);
		
		System.out.println(testListe.getSize());
		System.out.println(testListe2.getSize());
		testListe.delete(10);
		
		//why testListe concat no workyworky, size dos not change?
		
		System.out.println(getAnzahlOperationen());
		
	}


}


