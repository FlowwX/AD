package aufgabenblatt08;

public class ArrayTreeTest extends TreeTest {
	@Override
	public Tree<Integer> getTree() {
		return new ArrayTree<Integer>(20);
	}

}
