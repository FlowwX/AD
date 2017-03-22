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
public abstract class Liste {
	
	/**
	 * Globaler Zaehler der alle Aktionen/Operationen zu
	 * Statistikzwecken erfasst.
	 *  
	 */
	private int statistikZaehler;
	
	/**
	 * F�gt einen Element vom Typ Knoten in die Liste ein.
	 * @param position
	 * @param element
	 */
	public abstract void insert( int position, Knoten<?> element);
	
	/**
	 * L�schung eines Elements mit �bergebener Position.
	 * @param position
	 */
	public abstract void delete( int position );
	
	/**
	 * L�schung eines Elements mit �bergebenen Schl�ssel.
	 * @param schluessel
	 */
	public abstract void delete( Integer schluessel );
	
	/**
	 * Suche nach einem Element mit zugeh�rigem Schluessel.
	 * @param schluessel
	 * @return
	 */
	public abstract int find( int schluessel );
	
	/**
	 * Liefert ein Element mit Hilfe der Position.
	 * @param position
	 * @return
	 */
	public abstract Knoten<?> retrieve( int position );
	
	/**
	 * Verbindet 2 Listen mit einander.
	 * @param liste
	 */
	public abstract void concat( Liste liste );
}
