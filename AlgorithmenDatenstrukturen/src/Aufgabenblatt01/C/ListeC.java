package Aufgabenblatt01.C;

import Aufgabenblatt01.Liste;
import Aufgabenblatt01.Schluessel;

public class ListeC<T> extends Liste<T> {
	
	private KnotenC<T> head;
	
	private KnotenC<T> tail;
	
	private int anzahlElemente = 0;
	
	private final static int NOT_FOUND = -1; 
	
	public ListeC(){
		head = new KnotenC<T>(null);
		tail = new KnotenC<T>(null);
		head.setNext(tail);
		tail.setNext(head);
	}
	
	@Override
	public void insert(int position, T element) 
			throws IndexOutOfBoundsException, IllegalArgumentException
	{
		if (anzahlElemente < (position) || 0 > position ){
			throw new IndexOutOfBoundsException(); 
		} else if (null == element){
			throw new IllegalArgumentException();
		}
		
		
		KnotenC<T> neuerKnoten = new KnotenC<T>(element);
		
		KnotenC<T> prev = getNode( position - 1);
		
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
		
		KnotenC<T> prev = getNode(position-1);
		
		prev.setNext(prev.getNext());

	}

	@Override
	public void delete(Schluessel schluessel) {
		// TODO Auto-generated method stub
		// stoppelement
		
		int pos = find();
		
		
	}

	@Override
	public int find(Schluessel schluessel) {
		
		// TODO Auto-generated method stub
		int pos = 1;
		KnotenC<T> knoten = head;
		
		Schluessel oldTailKey = tail.getSchluessel();
		tail.setSchluessel(schluessel);
		
		while()
		
		tail = new KnotenC<T>();
		while (tail.schluessel != knoten.getSchluessel()){
			knoten = knoten.getNext();
			pos++;
		}
		
		if (knoten == tail){
			pos = -1;
		}
		return pos;
	}

	@Override
	public T retrieve(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void concat(Liste<?> liste) {
		// TODO Auto-generated method stub

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
