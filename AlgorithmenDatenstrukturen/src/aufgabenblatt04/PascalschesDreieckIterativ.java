package aufgabenblatt04;

/**
 * Das Pascalsche Dreieck iterativ
 *
 * @author Nils Egge
 * @version 1.0
 */
public class PascalschesDreieckIterativ {

	/**
	 * Berechnet die Nte Zeile des Pascalschen Dreiecks.
	 */

	public static int[] pascaliterativ(int N) {
		int count = 0; // Aufwandsanalyse Counter
		int i;
		int j;
		int[][] dreieck = new int[N][];
		int[] pascal = new int[N];
		if (N < 1) {
			System.out.println("Count:\t" + count);
			return pascal;
		}
		pascal[0] = 1; // Ersten Wert der Zeile auf 1 setzen
		pascal[N - 1] = 1; // Letzten Wert der Zeile auf 1 setzen
		for (i = 0; i < N; i++) {
			count++;
			dreieck[i] = new int[i + 1]; // Neue Zeile anlegen
			dreieck[i][0] = 1; // Ersten Wert der Zeile auf 1 setzen

			for (j = 1; j < i; j++) {
				count++;
				// Summenberechnung
				dreieck[i][j] = dreieck[i - 1][j - 1] + dreieck[i - 1][j];
				if (i == N - 1) {
					pascal[j] = dreieck[i][j];
				}
			}
			dreieck[i][i] = 1; // Letzten Wert der Zeile auf 1 setzen
		}
		System.out.println("Count:\t" + count);

		return pascal;
	}

	public static void main(String[] args) {

		System.out.println("PascalschesDreieckIterativ");
		for (int i = 0; i <= 16; i++) {
			int exp = (int) Math.pow(2, i);
			System.out.print("N =\t" + exp + "\t");
			// System.out.print("N =\t2^" +i + "\t");
			int[] kk = pascaliterativ(exp);
			System.out.println("");
		}
//		// Ausgabe der nten Zeile auf der Konsole
//		int[] pascal = pascaliterativ(10);
//		System.out.println("___________________");
//		for (int k = 0; k < 10; k++) {
//			System.out.print(pascal[k] + " ");
//
//		}
	}
}
