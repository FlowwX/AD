package aufgabenblatt06_alternative;

public class Quicksort {
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

		// approximative Mitte
		int x = a[lo + (hi - lo) / 2];
		zuweisungen++;

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
		if (lo < j) {
			quicksort(lo, j);
			rekursionen++;
		}

		if (i < hi) {
			quicksort(i, hi);
			rekursionen++;
		}

	}

	private void exchange(int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
		zuweisungen += 3;
		tausche++;
	}

}
