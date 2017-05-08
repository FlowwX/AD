package aufgabenblatt05;

import static org.junit.Assert.*;

import org.junit.Test;

import aufgabenblatt06.SortTest;

public class QuicksortTest extends SortTest{

	private int[] mengeUnsortiert = {1,3,4,10,6,2,8,9,5,7,7};
	
	
	@Test
	public void test() {
		Quicksort.run(mengeUnsortiert);		
		assertTrue("Fehler: Menge ist nicht sortiert.", istSortiert(mengeUnsortiert));
	}
	
}
