package aufgabenblatt06;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import aufgabenblatt05.Quicksort;

public class FastsortTest extends SortTest {
	
	public static int N = 1000;
	
	private int[] mengeUnsortiertA;
	private int[] mengeUnsortiertB;

	public FastsortTest() {
		mengeUnsortiertA = generiereMenge(N);
		mengeUnsortiertB = Arrays.copyOf(mengeUnsortiertA, mengeUnsortiertA.length);
	}
	
	@Test
	public void test() {
		
		Quicksort.run(mengeUnsortiertA);			
		Quicksort.log();
		
		Fastsort.run(mengeUnsortiertB, N);
		Fastsort.log();
		
		assertTrue("Fehler: Menge ist nicht sortiert.", istSortiert(mengeUnsortiertA));
	}

}
