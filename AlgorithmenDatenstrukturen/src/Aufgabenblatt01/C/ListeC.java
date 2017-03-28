package Aufgabenblatt01.C;

import Aufgabenblatt01.Liste;
import Aufgabenblatt01.Schluessel;

public class ListeC<T> extends Liste<T> {
	
	private KnotenC<T> head;
	
	private KnotenC<T> tail;
	
	private int anzahlElemente = 0;
	
	private final static int NOT_FOUND = -1; 
	
	public ListeC(){
		head = new KnotenC<T>(null, 0);
		tail = new KnotenC<T>(null, 0);
		head.setNext(tail);
		tail.setNext(head);
	}
	
	@Override
	public void insert(int position, T element) 
			throws IndexOutOfBoundsException, IllegalArgumentException
	{
		if ((anzahlElemente+1) < position || 1 > position ){
			throw new IndexOutOfBoundsException(); 
		} else if (null == element){
			throw new IllegalArgumentException();
		}
		
		
		KnotenC<T> neuerKnoten = new KnotenC<T>(element);
		
		KnotenC<T> prev = getNode(position);
		neuerKnoten.setNext(prev.getNext());
		prev.setNext(neuerKnoten);

		if (neuerKnoten.getNext() == tail){
			tail.setNext(neuerKnoten);
		}
		
		anzahlElemente++;
		return;
	}

	@Override
	public void delete(int position) 
			throws IndexOutOfBoundsException
	{
		if (anzahlElemente < position || 1 > position ){
			throw new IndexOutOfBoundsException(); 
		} 
		
		KnotenC<T> prev = getNode(position);
		
		prev.setNext(prev.getNext());
		
		anzahlElemente--;

	}

	@Override
	public void delete(Schluessel schluessel) throws IllegalArgumentException{
		
		if (null == schluessel){
			throw new IllegalArgumentException();
		}
		
		
		KnotenC<T> knoten = head.getNext();
		
		// alten schlüssel retten (optional)
		Schluessel oldTailKey = tail.getSchluessel();
		tail.setSchluessel(schluessel);
		
		while( !schluessel.equals(knoten.getNext().getSchluessel())){
			knoten = knoten.getNext();
		}
		
		// delete this
		if (knoten.getNext() != tail){
			knoten.setNext(knoten.getNext().getNext());
			// gc wird knoten.next loeschen.
			
		}
		tail.setSchluessel(oldTailKey);
		anzahlElemente--;
		return;
		//int pos = find();
		
		
	}

	@Override
	public int find(Schluessel schluessel) throws IllegalArgumentException{
		
		if (null == schluessel){
			throw new IllegalArgumentException();
		}
		
		int pos = 1;
		KnotenC<T> knoten = head.getNext();
		
		Schluessel oldTailKey = tail.getSchluessel();
		tail.setSchluessel(schluessel);
		
		
		
		while(!schluessel.equals(knoten.getSchluessel())){
			knoten = knoten.getNext();
			pos++;
		}
		
		if (knoten == tail){
			pos = -1;
		}
		tail.setSchluessel(oldTailKey);
		
		return pos;
	}

	@Override
	public T retrieve(int position) throws IndexOutOfBoundsException {
		
		if (anzahlElemente < position || 1 > position ){
			throw new IndexOutOfBoundsException(); 
		} 
		
		int currentPos = 1;
		KnotenC<T> knoten = head.getNext();
		T elem = null;
		
		while( currentPos < position){
			knoten = knoten.getNext();
			currentPos++;
		}
		
		if (knoten != tail){
			elem = knoten.getDaten();	
		}
		
		return elem;
	}

	@Override
	public void concat(Liste<T> liste) {
		
		if ( liste.getSize() == 0 || liste == null ) {
			return;
		}
		
		if (liste instanceof ListeC<?>){
			tail.getNext().getNext().setNext(
					((ListeC) liste).getNode(1)
			);
			
		} else {
			for (int pos = 1; liste.getSize() >= pos; pos++){
				insert(pos + anzahlElemente, liste.retrieve(pos) );
			}
			
		}
		anzahlElemente += liste.getSize();
		
		return;

	}
	@Override
	public int getSize() {
		return anzahlElemente;
	}
	
	private KnotenC<T> getNode(int position) {
		
		KnotenC<T> knoten = head;
		
		knoten = head;	
		for(int i=1; i<position; i++)
		{
			knoten = knoten.getNext();
		}
		
		return knoten;
	}

}
