package aufgabenblatt05;

import aufgabenblatt06.Sort;

public class Quicksort extends Sort{



	public static void run(int[] a) {
		

		//reset Zählervariablen
		VERGLEICHE 	= 0;
		TAUSCHE 	= 0;
		ZUWEISUNGEN = 0;
		
		START = System.currentTimeMillis();
		ENDE  = 0;
		
		A = a;
		N = a.length;
		sort(0, N - 1);
	}
	
	private static void sort(int lo, int hi) {
		int i = lo, j = hi;
		ZUWEISUNGEN += 2;

		// Vergleichselement (pivotelement) bestimmen (wähle stur die Mitte)
		int pivot = A[lo + (hi - lo) / 2];

		ZUWEISUNGEN++;

		// Aufteilung
		while (i <= j) {
			VERGLEICHE++;

			while (A[i] < pivot) {
				i++;
				ZUWEISUNGEN++;
				VERGLEICHE++;
			}

			while (A[j] > pivot) {
				j--;
				ZUWEISUNGEN++;
				VERGLEICHE++;
			}

			if (i <= j) {
				exchange(i, j);
				i++;
				j--;
				ZUWEISUNGEN += 2;
				VERGLEICHE++;

			}
		}

		// Rekursion
		if (lo < j)
			sort(lo, j);
		if (i < hi)
			sort(i, hi);
		
		
		ENDE  = System.currentTimeMillis();
	}


}
