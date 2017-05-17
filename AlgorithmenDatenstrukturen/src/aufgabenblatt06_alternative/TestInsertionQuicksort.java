package aufgabenblatt06_alternative;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import aufgabenblatt06.SortTest;

public class TestInsertionQuicksort extends SortTest{

	private final static int K = 6;
	
	private final static int N = (int) Math.pow(10, K);
	
	private final static int KEYS_START_AT = 700 * N;
	
	private final static int KEYS_STOP_AT = 800 * N;
	
	@Test
	public void testSort() {
		int[] unsortiert = generiereFolge();
		assertFalse("Menge ist sortiert.", istSortiert(unsortiert));
		InsertionQuicksort sorter = new InsertionQuicksort();
		sorter.sort(unsortiert);
		assertTrue("Menge ist unsortiert.", istSortiert(unsortiert));
	}
	
	public static void main(String[] args) {
		
		int[] ergebnisseIQ = new int[10];
		int[] ergebnisseQS = new int[10];
		int[] ergIQlz = new int[10];
		int[] ergQSlz = new int[10];
		
		for(int i=0; i < ergebnisseIQ.length; i++){
			int[] folge = generiereFolge();
			InsertionQuicksort sorter = new InsertionQuicksort();
			
			//messe Laufzeit
			long start = System.currentTimeMillis();
			sorter.sort(folge);
			long ende = System.currentTimeMillis();
			
			ergebnisseIQ[i] = sorter.rekursionen;
			ergIQlz[i] = (int) (ende-start);
		}

		for(int i=0; i < ergebnisseQS.length; i++){
			int[] folge = generiereFolge();
			Quicksort sorter = new Quicksort();
			
			//messe Laufzeit
			long start = System.currentTimeMillis();
			sorter.sort(folge);
			long ende = System.currentTimeMillis();
			
			ergebnisseQS[i] = sorter.rekursionen;
			ergQSlz[i] = (int) (ende-start);
		}
		
		int avgIq = werteAus(ergebnisseIQ);
		int avgQs = werteAus(ergebnisseQS);
		int avgIQlz = werteAus(ergIQlz);
		int avgQSlz = werteAus(ergQSlz);
		
		System.out.println(String.format("InsertionQS: %d (%dms) QS: %d (%dms)", avgIq, avgIQlz, avgQs, avgQSlz));
		
	}
	
	public static int werteAus(int[] array ){
		int summe = 0;
		for(int i=0; i< array.length; i++){
			summe+=array[i];
		}
		return summe/array.length;
	}
	
	
	public static int[] generiereFolge(){
		int[] folge = new int[N];
		
		//erstelle Folge
		for(int i=0; i<N; i++){
			folge[i] = (int)(Math.random() * (KEYS_STOP_AT-KEYS_START_AT)) + KEYS_START_AT;
		}
		
		return folge;
	}
	

}
