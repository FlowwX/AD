package aufgabenblatt08;

public class TreeNode<T extends Comparable<T>> implements Comparable<TreeNode<T>> {
	private T value;
	private int sum;
	
	public TreeNode(T value){
		this.value = value;
		this.sum = -1;
	}

	@Override
	public int compareTo(TreeNode<T> other) {
		return value.compareTo(other.value);
	}

}
