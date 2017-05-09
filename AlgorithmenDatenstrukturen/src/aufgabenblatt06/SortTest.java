package aufgabenblatt06;

public class SortTest {

	private static int START = 700;
	private static int ENDE  = 800;
	
	public int[] generiereMenge(int n) {
		int[] menge = new int[ENDE * n + 1];
		 
		for(int i=0; i<n; i++){
			int offset = (int) (Math.random()*n*100);
			int key = START*n+offset;
			int value = (int) (Math.random()*1000)+1000;
			
			menge[key] = value;
		}
		
		return menge;
	}
	
	public boolean istSortiert(int[] menge) {
		int vorgaenger = menge[0];
		for(int i=1; i<menge.length; i++){
			if(vorgaenger>menge[i]){
				return false;
			}
			vorgaenger = menge[i];
		}
		return true;
	}

}