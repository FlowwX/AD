package aufgabenblatt07;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class TreeTest {
	public abstract Tree<Integer> getTree();
	
	@Test
	public void testOrders(){
		Tree<Integer> tree = getTree();
		Integer[] preOrder = {4,2,1,3,9,6,11};
		Integer[] inOrder = {1,2,3,4,6,9,11};
		Integer[] postOrder = {1,3,2,6,11,9,4};
		tree.insert(4);
		tree.insert(2);
		tree.insert(9);
		tree.insert(1);
		tree.insert(3);
		tree.insert(6);
		tree.insert(11);
		assertEquals("preOrder failed", Stream.of(preOrder).collect(Collectors.toList()), tree.preOrder());
		assertEquals("inOrder failed", Stream.of(inOrder).collect(Collectors.toList()), tree.inOrder());
		assertEquals("postOrder failed", Stream.of(postOrder).collect(Collectors.toList()), tree.postOrder());
	}
	
	@Test
	public void testInsert(){
		Tree<Integer> tree = getTree();
		assertTrue("Insertion returned false", tree.insert(8));
		assertFalse("Insertion returned true", tree.insert(8));
		assertTrue("Insertion returned false", tree.insert(5));
		assertFalse("Insertion returned true", tree.insert(5));
	}
	
	@Test
	public void testGetLeftChild(){
		Tree<Integer> tree = getTree();
		tree.insert(4);
		tree.insert(2);
		tree.insert(6);
		assertEquals("Left Child not corectly found", 2, tree.getLeftChild(4).intValue());
		assertEquals("Left Child not corectly found", null, tree.getLeftChild(2));
		assertEquals("Left Child not corectly found", null, tree.getLeftChild(5));
	}
	
	@Test
	public void testGetRightChild(){
		Tree<Integer> tree = getTree();
		tree.insert(4);
		tree.insert(2);
		tree.insert(6);
		assertEquals("Right Child not corectly found", 6, tree.getRightChild(4).intValue());
		assertEquals("Right Child not corectly found", null, tree.getRightChild(6));
		assertEquals("Right Child not corectly found", null, tree.getRightChild(5));
	}
	
	@Test
	public void testIllegalArguments(){
		Tree<Integer> tree = getTree();
		try{
			tree.insert(null);
			assertTrue("Expected Exception when inserting null", false);
		} catch (IllegalArgumentException e){
			assertTrue(true);
		}
		try{
			tree.getLeftChild(null);
			assertTrue("Expected Exception when inserting null", false);
		} catch (IllegalArgumentException e){
			assertTrue(true);
		}try{
			tree.getRightChild(null);
			assertTrue("Expected Exception when inserting null", false);
		} catch (IllegalArgumentException e){
			assertTrue(true);
		}
	}
}
