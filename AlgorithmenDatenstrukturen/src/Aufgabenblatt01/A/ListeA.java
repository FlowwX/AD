
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
	
	
	//"Position" im Array f�ngt bei 1 an, wird im Array aber schon bei 0 gespeichert
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
		
		
		if (arrayLst[position-1] == null){
			
			arrayLst[position-1] = new KnotenA<T>(element);

			anzahlElemente++;
			
			//AUFWAND INKREMENTIEREN
			statistikZaehler+=2;
			
			
		} else {
			for (int i = anzahlElemente; i > position; i--) {
				arrayLst[i-1] = arrayLst[i-2];
				
				//AUFWAND INKREMENTIEREN
				statistikZaehler+=1;
				
			}
			arrayLst[position-1]  = new KnotenA<T>(element);
		
			anzahlElemente++;
	
			//AUFWAND INKREMENTIEREN
			statistikZaehler+=2;
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
			
			//AUFWAND INKREMENTIEREN
			statistikZaehler+=1;
		}
		for (int i = (position+1); i < arraySize; i++) {	
			tempArrayLst[i-1] = arrayLst[i];
			
			//AUFWAND INKREMENTIEREN
			statistikZaehler+=1;
		}
		arrayLst = tempArrayLst;
		
		//AUFWAND INKREMENTIEREN
		statistikZaehler+=1;
		
		
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
			
			//AUFWAND INKREMENTIEREN
			statistikZaehler+=1;
		}
		return -1;
	}


	@SuppressWarnings("unchecked")
	@Override
	public T retrieve(int position) throws IndexOutOfBoundsException {
		if (position <= 0 || position > getSize() ){ //alternative: > getSize()
			throw new IndexOutOfBoundsException();
		}
		
		//AUFWAND INKREMENTIEREN
		statistikZaehler+=1;
		//??
		
		return (T) arrayLst[position-1].getDaten();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public void concat(Liste<T> liste) {

		int aktuelleAnzahlElemente = this.anzahlElemente;
		int j = 1;
		for (int i = aktuelleAnzahlElemente; i < (aktuelleAnzahlElemente + liste.getSize()); i++) {	
			this.insert(i, ( (T) liste.retrieve(j) ) );
			j++;
			
			//AUFWAND INKREMENTIEREN
			statistikZaehler+=1;
		}
	}
	
	
	public void resizeArray() {
		
		
		int newArraySize = (int) (arraySize*1.5);
		
		//AUFWAND INKREMENTIEREN
		statistikZaehler+=1;

		//KnotenA<T>[] newArrayLst = (KnotenA<T>[]) new Object[newArraySize];
		KnotenA<?>[] newArrayLst = new KnotenA<?>[newArraySize];
		
		//AUFWAND INKREMENTIEREN
		statistikZaehler+=1;
		
		for( int i = 0; i < anzahlElemente; i++) {
			newArrayLst[i] = arrayLst[i];
			
			//AUFWAND INKREMENTIEREN
			statistikZaehler+=1;
		}
		arrayLst = newArrayLst;
		
		//AUFWAND INKREMENTIEREN
		statistikZaehler+=1;
		
		arraySize = newArraySize;
		
		//AUFWAND INKREMENTIEREN
		statistikZaehler+=1;
	}
	
	@Override
	public int getSize() {
		//erhoeht sich "anzahlOperationen"?
		return anzahlElemente;
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
		
		Liste<Integer> testListe3 = new ListeA<Integer>();
		testListe3.insert(1, 32);
		testListe3.insert(2, 33);
		testListe3.insert(3, 34);
		testListe3.insert(4, 35);
		testListe3.insert(5, 36);
		testListe3.insert(6, 37);
		testListe3.insert(7, 38);
		testListe3.insert(8, 39);
		testListe3.insert(9, 40);
		
		System.out.println(testListe.getSize());
		testListe.insert(1, 2);
		System.out.println(testListe2.getSize());
		System.out.println(testListe2.retrieve(1));
		System.out.println(testListe2.retrieve(2));
		System.out.println(testListe2.retrieve(3));
		System.out.println(testListe2.retrieve(4));
		
		testListe.concat(testListe2);
		testListe.concat(testListe3);
		
		System.out.println("Liste1 size nach concat: " + testListe.getSize());
		System.out.println(testListe2.getSize());
		testListe.delete(10);
		
		//why testListe concat no workyworky, size dos not change?
		
		
		
	}


}


