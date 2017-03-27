/**
 * 
 */
package Aufgabenblatt01.Test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import Aufgabenblatt01.Liste;
import Aufgabenblatt01.A.ListeA;

/**
 * @author Florian Heuer
 *
 */
public class ListenTest {
	
	@Test
	public void testInsert() {
		
		Liste<Integer> l = new ListeA<Integer>();
		l.insert(1, 3);
		l.insert(2, 4);
		l.insert(3, 6);
		l.insert(4, 7);
		// Normalfall
		Assert.assertEquals(4, l.getSize());
		// Sonderfaelle
		
	}
	
	@Test
	public void testDeletePos() {
		// Normalfall
		
				// Sonderfaelle
	}
	
	@Test
	public void testDeleteSchluessel() {
		// Normalfall
		
				// Sonderfaelle
	}
	
	@Test
	public void testFind() {
		// Normalfall
		Liste<Integer> l = new ListeA<Integer>();
		l.insert(1, 3);
		l.insert(2, 4);
		l.insert(3, 6);
		l.insert(4, 7);
		
				// Sonderfaelle
	}
	
	@Test
	public void testRetrieve() {
		// Normalfall
		
				// Sonderfaelle
	}
	
	@Test
	public void testConcat() {
		// Normalfall
		
				// Sonderfaelle
	}

}
