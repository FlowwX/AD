/**
 * 
 */
package Aufgabenblatt01;

/**
 * Von diesem Typ werden die Implementierungen der Listen A,B,C
 * abgeleitet.
 * 
 * @author Florian Heuer
 *
 */
public abstract class Liste<T> {
	
	/**
	 * Globaler Zaehler der alle Aktionen/Operationen zu
	 * Statistikzwecken erfasst.
	 *  
	 */
	public long statistikZaehler;
	
	/**
	 * F�gt einen Element vom Typ Knoten in die Liste ein.
	 * @param position
	 * @param element
	 */
	public abstract void insert( int position, T element)
			throws IndexOutOfBoundsException, IllegalArgumentException;
	
	/**
	 * L�schung eines Elements mit �bergebener Position.
	 * @param position
	 */
	public abstract void delete( int position )
			throws IndexOutOfBoundsException;
	
	/**
	 * L�schung eines Elements mit �bergebenen Schl�ssel.
	 * @param schluessel
	 */
	public abstract void delete( Schluessel schluessel )
			throws IllegalArgumentException;
	
	/**
	 * Suche nach einem Element mit zugeh�rigem Schluessel.
	 * @param schluessel
	 * @return
	 */
	public abstract int find( Schluessel schluessel )
			throws IllegalArgumentException;
	
	/**
	 * Liefert ein Element mit Hilfe der Position.
	 * @param position
	 * @return
	 */
	public abstract T retrieve( int position )
			throws IndexOutOfBoundsException;
	
	/**
	 * Verbindet 2 Listen mit einander.
	 * @param liste
	 */
	public abstract void concat( Liste<T> liste );
	
	public abstract int getSize ();
	

	
	
}
