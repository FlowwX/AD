package Aufgabenblatt01.C;

import Aufgabenblatt01.Liste;
import Aufgabenblatt01.Schluessel;

public class ListeC<T> extends Liste<T> {
	
	private KnotenC<T> head;
	
	private int anzahlElemente = 0;
	
	public ListeC(){
		
	}
	
	@Override
	public void insert(int position, T element) 
			throws IndexOutOfBoundsException, IllegalArgumentException
	{
		if (anzahlElemente < (position - 1) || 0 > position ){
			throw new IndexOutOfBoundsException(); 
		} else if (null == element){
			throw new IllegalArgumentException();
		}
		
		KnotenC<T> prev = getNode( position - 1 );
		KnotenC<T> neuerKnoten = new KnotenC<T>(element);
		
		neuerKnoten.setNext(prev.getNext());
		prev.setNext(neuerKnoten);
		
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
