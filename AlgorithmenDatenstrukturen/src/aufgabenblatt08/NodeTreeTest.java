package aufgabenblatt08;

public class NodeTreeTest extends TreeTest {

	@Override
	public Tree<Integer> getTree() {		
		return new NodeTree<Integer>(null,null);
	}

}
