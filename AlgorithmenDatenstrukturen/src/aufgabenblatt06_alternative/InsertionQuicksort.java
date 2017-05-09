package aufgabenblatt06_alternative;

public class InsertionQuicksort {
	private int[] a;
	private int n;

	public int vergleiche;
	public int tausche;
	public int rekursionen;
	public int zuweisungen;

	public void sort(int[] a) {
		this.a = a;
		n = a.length;
		quicksort(0, n - 1);
	}

	private void quicksort(int lo, int hi) {
		int i = lo, j = hi;
		zuweisungen += 2;

		//Schwellenwert M
		int m = 50;
		//approximative Mitte
		int x = a[lo + (hi - lo) / 2];
		zuweisungen++;
		
		vergleiche++;
		if (hi - lo > m) {

			// Aufteilung
			while (i <= j) {
				vergleiche++;

				while (a[i] < x) {
					i++;
					zuweisungen++;
					vergleiche++;
				}

				while (a[j] > x) {
					j--;
					zuweisungen++;
					vergleiche++;
				}

				if (i <= j) {
					exchange(i, j);
					i++;
					j--;
					zuweisungen += 2;
					vergleiche++;

				}
			}

			// Rekursion
			if (lo < j){
				quicksort(lo, j);
				rekursionen++;
			}
				
			if (i < hi){
				quicksort(i, hi);
				rekursionen++;
			}

		}
		// insertion sort
		else {
			int t, l;
	        for (int k=lo+1; k<=hi; k++){
	        	zuweisungen++;
	        	vergleiche++;
	        	
	            l=k;
	            t=a[l];
	            zuweisungen+=2;
	            
	            while (l > 0 && a[l-1] > t){
	            	vergleiche++;
	            	zuweisungen+=2;
	                a[l]=a[l-1];
	                l--;
	            }
	            a[l]=t;
	            zuweisungen++;
	        }
		}
	}

	private void exchange(int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
		zuweisungen += 3;
		tausche++;
	}
	
	public static boolean doesExist(int[] ar, int key) {
		boolean ret = false;
		for (int i = 0; i < ar.length; i++) {
			if (ar[i] == key) {
				ret = true;
				break;
			}
		}
		return ret;
	}
	
	@Override
	public String toString() {
		return String.format("Vergleiche: %d \n Zuweisungen: %d", vergleiche, zuweisungen);
	}

	public static void main(String[] args) {

		int anz = 10;

		int[] folge = new int[anz];
		for (int i = 0; i < anz; i++) {
			int zahl = (int) ((Math.random() * anz) + 1);
			if (!doesExist(folge, zahl)) {
				folge[i] = zahl;
			} else {
				i--;
			}
		}

		InsertionQuicksort sorter = new InsertionQuicksort();
		sorter.sort(folge);

		System.out.println(sorter);
	}

}
