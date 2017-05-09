package aufgabenblatt06;

public abstract class Sort {

	protected static int[] A;
	protected static int N;
	
	public static long VERGLEICHE;
	public static long TAUSCHE;
	public static long ZUWEISUNGEN;
	
	public static long START;
	public static long ENDE;
	
	protected static void exchange(int i, int j) {
		int t = A[i];
		A[i] = A[j];
		A[j] = t;
		ZUWEISUNGEN += 3;
		TAUSCHE++;
	}
	
	protected static void log() {
		System.out.println(
			"Dauer: \t" + (ENDE - START)  	+ "\n" +
			"Vergleiche: \t" + VERGLEICHE 	+ "\n" +
			"Tausche: \t" + TAUSCHE 	  	+ "\n" +
			"Zuweisungen: \t" + ZUWEISUNGEN + "\n" 
		);
	}

}