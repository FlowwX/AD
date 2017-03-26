package Aufgabenblatt01;

public class Schluessel {
	
	private static int nummer = 1000;
	private int wert;
	
	public Schluessel() {
		nummer++;
		wert = nummer;
	}

	public int getWert() {
		return wert;
	}
	
	
}
