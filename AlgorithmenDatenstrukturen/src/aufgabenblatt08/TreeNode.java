package aufgabenblatt08;

public class TreeNode<T extends Comparable<T>> implements Comparable<TreeNode<T>> {
	private T value;
	public int sum;
	
	public TreeNode(T value){
		this.value = value;
		this.sum = 0;
	}

	@Override
	public int compareTo(TreeNode<T> other) {
		return value.compareTo(other.value);
	}
	
	public T getValue(){
		return value;
	}

	public int getSum(){
		return sum;
	}
	
	@Override
	public String toString(){
		return "(" + value.toString() + ",s" + Integer.toString(sum) + ")";
	}
	
	@Override
	public boolean equals(Object other){
		if(other == null) return false;
		if(this == other) return true;
		if(!(other instanceof TreeNode<?>)) return false;
		TreeNode<?> otherTN = (TreeNode<?>)other;
		return otherTN.value.equals(this.value);
	}
}
