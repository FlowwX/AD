package aufgabenblatt06;

public class Fastsort extends Sort {

	private static int KEY_START = 700;
	private static int KEY_ENDE  = 800;
	
	private static int[] RESULT;
	
	
	public static void run(int[] a, int n) {
		
		//reset Zählervariablen
		VERGLEICHE 	= 0;
		TAUSCHE 	= 0;
		ZUWEISUNGEN = 0;
		
		START = System.currentTimeMillis();
		ENDE  = 0;
		
		A = a;
		N = n;
		
		RESULT = new int[n];
		filterKeys();
		
		A = RESULT;
		
		sort(0, A.length-1 );
	}
	
	private static void sort(int lo, int hi) {
		int i = lo, j = hi;
		ZUWEISUNGEN += 2;

		// Vergleichselement (pivotelement) bestimmen 
		int mid = lo + (hi - lo) / 2;
		
		//1. Verbesserung -> median of 3 nutzen
		if( A[lo] > A[mid] ){ 
			exchange(lo, mid);
		}
		
		if(A[lo]>A[hi]){
			exchange(lo, hi);
		}		  
		else if(A[hi]>A[mid]){
			exchange(hi, mid);
		}
		
		int pivot = A[hi];
		 

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
		
		ENDE = System.currentTimeMillis();
	}
	
	private static void filterKeys() {
		int schluesselGefunden = 0;
		int i = KEY_START*N;
		
		while(schluesselGefunden < N){
			if(A[i]>0){
				RESULT[schluesselGefunden] = A[i];
				schluesselGefunden++;
				ZUWEISUNGEN += 2;
			}
			VERGLEICHE += 2;
			i++;
		}
	}
	
	public static void insertionSort() {

	}
}
