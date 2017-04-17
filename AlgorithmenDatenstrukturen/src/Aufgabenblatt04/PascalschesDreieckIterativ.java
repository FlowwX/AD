/**
 * Das Pascalsche Dreieck iterativ
 *
 * @author Nils Egge
 * @version 1.0
 */
public class PascalschesDreieckIterativ {
	static int count = 0; // Aufwandsanalyse Counter

	/**
	 * Berechnet die Nte Zeile des Pascalschen Dreiecks.
	 */

	public static int[][] pascaliterativ(int N) {
		System.out.println("PascalschesDreieckIterativ");
		int[][] dreieck = new int[N][];
		int i;
		int j;
		for (i = 0; i < N; i++) {
			count++;
			dreieck[i] = new int[i + 1]; // Neue Zeile anlegen
			dreieck[i][0] = 1; // Ersten Wert der Zeile auf 1 setzen
			if (i == N - 1) {
				System.out.print(dreieck[i][0] + " ");

			}

			for (j = 1; j < i; j++) {
				count++;
				// Summenberechnung
				dreieck[i][j] = dreieck[i - 1][j - 1] + dreieck[i - 1][j];
				if (i == N - 1) {

					System.out.print(dreieck[i][j] + " ");

				}
			}

			dreieck[i][i] = 1; // Letzten Wert der Zeile auf 1 setzen
		}
		if (i == N) {
			System.out.println(dreieck[N - 1][N - 1]);
			System.out.println("Count:" + count);
		}
		return dreieck;
	}

	public static void main(String[] args) {
		pascaliterativ(10);
	}
}
