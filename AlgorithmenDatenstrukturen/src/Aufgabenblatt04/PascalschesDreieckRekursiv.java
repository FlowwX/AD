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
		int[] pascal = new int[N];
		for (int k = 0; k < N; k++) {
			count++;
			pascal[k] = pascalRekursiv(N - 1, k);
//			System.out.print(pascal[k] + " ");
		}
		System.out.println("Count:\t" + count);
		count = 0;
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
//		System.out.println("PascalschesDreieckRekursiv");
//		for (int i = 0; i <= 4; i++) {
//			int exp = (int) Math.pow(2, i);
////			System.out.print("N =\t" + exp + "\t");
//			System.out.print("N =\t2^" +i + "\t");
//			getLinePascals(exp);
//		}
		
		System.out.println("PascalschesDreieckRekursiv");
		for (int i = 0; i <= 16; i++) {
			int exp = i;
//			System.out.print("N =\t" + exp + "\t");
			System.out.print("N =\t2^" +i + "\t");
			getLinePascals(exp);
		}
	}
}
