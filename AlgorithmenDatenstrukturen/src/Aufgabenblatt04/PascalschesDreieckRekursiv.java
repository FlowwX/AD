/**
 * Das Pascalsche Dreieck rekursiv
 *
 * @author Nils Egge
 * @version 1.0
 */
public class PascalschesDreieckRekursiv {
	static int count = 0; // Aufwandsanalyse Counter

	/**
	 * 
	 * @param N
	 *            Zeile wird berchnet
	 * @return Nte Zeile wird zurück gegeben
	 */
	public static int[] getLinePascals(int N) {
		System.out.println("PascalschesDreieckRekursiv");
		int[] pascal = new int[N];
		for (int k = 0; k < N; k++) {
			count++;
			pascal[k] = pascalRekursiv(N - 1, k);
			System.out.print(pascal[k] + " ");
		}
		System.out.println("\nCount: " + count);
		return pascal;
	}

	/**
	 * 
	 * @param N
	 *            Element in Zeile N an Position k wird berechnet
	 * @param k
	 *            Element in Zeile N an Position k wird berechnet
	 * @return Element in Zeile N an Position k wird zurückgegeben
	 */
	public static int pascalRekursiv(int N, int k) {
		if (k == 0 || k == N) {
			return 1;
		}
		count++;
		return pascalRekursiv(N - 1, k) + pascalRekursiv(N - 1, k - 1);
	}

	public static void main(String[] args) {
		getLinePascals(10);
	}
}
