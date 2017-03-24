package Aufgabenblatt01;
/**
 * Datenhalter für den Typ Liste.
 * @author Florian Heuer
 *
 */
public abstract class Knoten<T>{
	
	/**
	 * Generische Daten
	 */
	private final T daten;
	
	/**
	 * Eindeutiger Schluessel
	 */
	private final Schluessel schluessel;
	
	public Knoten(T daten) {
		schluessel = new Schluessel();
		this.daten = daten;
	}

	public T getDaten() {
		return daten;
	}

	public Schluessel getSchluessel() {
		return schluessel;
	}
	
	
	
}
