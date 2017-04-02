
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
	
	//private
	public KnotenA<?>[] arrayLst;
	
	private int anzahlElemente;
	
	
	//"Position" im Array f�ngt bei 1 an, wird im Array aber schon bei 0 gespeichert
	//@SuppressWarnings("unchecked")
	public ListeA() {
		//arrayLst = (KnotenA<T>[]) new Object[arraySize];
		// +1 because one dummy at pos = 0
		arrayLst = new KnotenA<?>[arraySize+1];
		// Dummy 
		arrayLst[0] = new KnotenA<T>(null, 0);
	}
	
	
	@Override
	public void insert (int position, T element)
			throws IndexOutOfBoundsException, IllegalArgumentException {
		
		// EXCEPTION 
		if ((anzahlElemente+1) < position || 1 > position ){
			throw new IndexOutOfBoundsException(); 
		} else if (null == element){
			throw new IllegalArgumentException();
		}
		
		
		if (anzahlElemente >= (arrayLst.length-1)) {
			resizeArray((arrayLst.length*2)-1);
		}
		
		
		
		// umkopieren nach hinten.
		for (int i = anzahlElemente; i >= position; i--) {
			arrayLst[i+1] = arrayLst[i];
				
			//AUFWAND INKREMENTIEREN
			statistikZaehler+=1;
				
		}
		
		arrayLst[position]  = new KnotenA<T>(element);
		anzahlElemente++;
	
		 //AUFWAND INKREMENTIEREN
		statistikZaehler+=2;	
	}

	@Override
	public void delete(int position) 
			throws IndexOutOfBoundsException {
		
		if ( (position < 1) || (position > anzahlElemente) ) {	//alternative: position < 0 und position > arrayLst.length
			throw new IndexOutOfBoundsException();
		}
		
		for (int i = position; i < anzahlElemente ; i++ ){
			arrayLst[i]= arrayLst[i+1];
		}
		
		anzahlElemente--;
	}
	
	@Override
	public void delete(Schluessel schluessel)
			throws IllegalArgumentException {
		
		if (null == schluessel){
			throw new IllegalArgumentException();
		}
		
		int pos = find(schluessel);
			
		
		if (-1 != pos) {
			delete(find(schluessel));
		} else {
//			System.out.println("Zu loeschendes Element mit Key " + schluessel.getWert() + " ist nicht in Liste enthalten, findschluessel: "+ find(schluessel));
		}			
	}

	@Override
	public int find(Schluessel schluessel) 
			throws IllegalArgumentException {
		
		if (null == schluessel){
			throw new IllegalArgumentException();
		}
		
		//find with stop element
		Schluessel old = arrayLst[0].getSchluessel();
		arrayLst[0].setSchluessel(schluessel);
		
		
		int pos = anzahlElemente;
		
		System.out.println("befor while"  + arrayLst[pos-1].getSchluessel().getWert());
		System.out.println("pos 0 "  + arrayLst[0].getSchluessel().getWert());
		
		while(!( arrayLst[pos].getSchluessel().equals(schluessel) )){
			System.out.println("insige while " + arrayLst[pos].getSchluessel().getWert());
			pos--;
		}
		
		arrayLst[0].setSchluessel(old);
		if (0 ==  pos){
			pos = -1;
		} 
		return pos;
	}


	@SuppressWarnings("unchecked")
	@Override
	public T retrieve(int position) 
			throws IndexOutOfBoundsException {
		
		if (position <= 0 || position > anzahlElemente ){ //alternative: > getSize()
			throw new IndexOutOfBoundsException();
		}
		
		//AUFWAND INKREMENTIEREN
		statistikZaehler+=1;
		
		return (T) arrayLst[position].getDaten();
	}
	

	@Override
	public void concat(Liste<T> liste) {
		
		if (liste == null ) return ;
		if ( liste.getSize() == 0) return;
		
		int anzahlElementeBeiderListen = anzahlElemente + liste.getSize();
		while (anzahlElementeBeiderListen >= (arrayLst.length-1)) {
			resizeArray(arrayLst.length*2);
		}
		int oldAnzahlElemente = anzahlElemente;
		
		for (int i = anzahlElemente+1; i <= anzahlElementeBeiderListen ; i++) {	
			// anzahlElemente incrementiert sich selbst in insert
			this.insert(i, ( liste.retrieve( (i - oldAnzahlElemente) ) ) );
		}
		
	}
	
	
	public void resizeArray(int newSize) {
		KnotenA<?>[] newArrayLst = new KnotenA<?>[newSize];
		
		for( int i = 0; i <= anzahlElemente; i++) {
			newArrayLst[i] = arrayLst[i];
			
			//AUFWAND INKREMENTIEREN
			statistikZaehler+=1;
		}
		arrayLst = newArrayLst;
		
	}
	
	public void resizeArray() {
		
		
		int newArraySize = (int) ((arrayLst.length*1.5));
		
		//AUFWAND INKREMENTIEREN
		statistikZaehler+=1;

		//KnotenA<T>[] newArrayLst = (KnotenA<T>[]) new Object[newArraySize];
		KnotenA<?>[] newArrayLst = new KnotenA<?>[newArraySize];
		
		//AUFWAND INKREMENTIEREN
		statistikZaehler+=1;
		
		for( int i = 0; i <= anzahlElemente; i++) {
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
		
		
		testListe.insert(1, 1);
		testListe.insert(2, 2);
		testListe.insert(3, 3);
		testListe.insert(4, 4);
		testListe.insert(5, 6);
		testListe.insert(6, 7);
		testListe.insert(7, 8);
		testListe.insert(8, 9);
		testListe.insert(9, 10);
		testListe.insert(10, 11);
		testListe.insert(11, 12);
		testListe.insert(12, 13);
		testListe.insert(13, 14);
		testListe.insert(14, 15);
		testListe.insert(5, 5);
		
		System.out.println(testListe.getSize());
		
		for (int i =1 ; i <= testListe.getSize(); i++){
			
			System.out.println(testListe.retrieve(i));
			if (i == testListe.getSize()) System.out.println("japp"); 
		}
		System.out.println("find: \n");
		
		System.out.println(testListe.find(new Schluessel(1008)) );
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
		testListe2.insert(4, 452);
		testListe2.insert(5, 552);
		testListe2.insert(6, 652);
		testListe2.insert(7, 752);
		testListe2.insert(8, 852);
		testListe2.insert(9, 952);
		
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


