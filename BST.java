
public class BST {
	
	private class Node{
		private int value;
		private Node left;
		private Node right;
		
		public Node(int x){
			value = x;
			left = right = null;
		}
	}
	
	private Node root;
	private int nItems;
	private int treeIndex;
	private String [] aTree = new String[32];
	
	public BST(){
		root = null;
		for(int i = 1; i <= 31; i++){
			aTree[i] = " . ";
		}
	}
	
	
	
	public void printTree(){
		for(int i = 1; i <= 31; i++){
			aTree[i] = " . ";
		}
		for(int i = 1; i <= 31; i++){
			if(aTree[i] == null)
				aTree[i] = " / ";
		}
		buildArray(root, 1);
		System.out.println("                                                         " + aTree[1]
				+ "\n                                                          |\n"
				+ "                           " + aTree[2] + " ---------------------------^--------------------------- " + aTree[3]
				+ "\n                            |                                                           |\n"
				+ "            " + aTree[4] + " ------------^------------ " + aTree[5] + "                           " + aTree[6] + " ------------^------------ " + aTree[7]
				+ "\n             |                             |                             |                             |\n"
				+ "     " + aTree[8] + " ----^---- "+ aTree[9] + "             " + aTree[10] + " ----^---- " + aTree[11] + "             " + aTree[12] + " ----^---- " + aTree[13] + "             " + aTree[14] + " ----^---- " + aTree[15]
				+ "\n     |              |              |              |              |              |              |              |\n"
				+ aTree[16] + " -^- " + aTree[17] + "    " + aTree[18] + " -^- " + aTree[19] + "    " + aTree[20] + " -^- " + aTree[21] + "    " + aTree[22] + " -^- " + aTree[23] + "    " + aTree[24] + " -^- " + aTree[25] + "    " + aTree[26] + " -^- " + aTree[27] + "    " + aTree[28] + " -^- " + aTree[29] + "    " + aTree[30] + " -^- " + aTree[31] + "\n");
	}
	public void buildArray(Node p, int index){
		if(p == null)
			return;
		if(index > 31)
			return;
		String int2string = Integer.toString(p.value);
		aTree [index] = int2string;
		buildArray(p.left, index * 2);
		buildArray(p.right, index * 2 +1);
	}
	
	
	
	/* 1. create array and store item in array (sorter)
	 * 2. delete tree
	 * 3. add middle (from array) to new tree
	 * 	add middle of left half to tree
	 *  add middle of right half to tree
	 */
	public void balance(){
		sortArray();
		root = null;
		toBST();
	}
	public void sortArray(){
		treeIndex = 0;
		Traversal(root);
	}
	private void Traversal(Node n){
		if(n == null){
			return;
		}
		else{
			Traversal(n.left);
			String intString= String.valueOf(n.value);
			treeIndex++;
			aTree[treeIndex] = intString;
			Traversal(n.right);
		}
	}
	public void toBST(){
		int [] balance = new int[nItems+1];
		nItems = 0;
		int counter = 1;
		String sAdd;
		int iAdd;
		for(int i = 1; i < balance.length; i++){
			sAdd = aTree[i];
			iAdd = Integer.parseInt(sAdd);
			balance[counter] = iAdd;
			counter++;
		}
		balanceRecursive(1, balance.length, balance);
	}
	private void balanceRecursive(int start, int end, int [] balance){
		if(start == end)
			return;
		int middle = (start + end) / 2;
		add(balance[middle]);
		balanceRecursive(middle+1, end, balance);
		balanceRecursive(start, middle, balance);
	}
	
	
	public void display(){
		LNRTraversal(root);
	}
	private void LNRTraversal(Node n){
		if(n == null){
			return;
		}
		else{
			LNRTraversal(n.left);
			System.out.print(n.value + ", ");
			LNRTraversal(n.right);
		}
	}
	
	public int treeHeight(Node temp){
		if(temp == null)
			return 0;
		else
			return 1 + Math.max(treeHeight(temp.left), treeHeight(temp.right));
	}
	public void printStatistics(){
		if(root == null){
			System.out.println("Empty List"
					+ "\nTree Height: 0" 
					+ "\nTree Root: none"
					+ "\nTree Count: 0");
		}
		else{
			display();
			System.out.println("\nTree Height: " + treeHeight(root)
					+ "\nTree Root: " + root.value
					+ "\nTree Count: " + nItems);
		}
	}
	
	
	
	public void add(int x){
		Node temp = new Node(x);
		nItems++;
		if(root == null)
			root = temp;
		else
			insertInOrder(root, temp);
	}
	private void insertInOrder(Node p, Node n){
		if(n.value < p.value){
			if(p.left == null)
				p.left = n;
			else
				insertInOrder(p.left, n);
		}
		else if(p.right == null)
			p.right = n;
		else
			insertInOrder(p.right, n);
	}
	
	
	
	public void delete(int remove){
		Node parent = root;
		Node current = root;
		boolean isLeftChild = false;
		while(current.value != remove){
			parent = current;
			if(current.value > remove){
				isLeftChild = true;
				current = current.left;
			}
			else{
				isLeftChild = false;
				current = current.right;
			}
			if(current == null){
				System.out.println("The number " + remove + " was not found in the BST tree.");
				return;
			}
		}
		//Case 1: Node has no children
		if(current.left == null && current.right == null){
			if(current == root)
				root = null;
			if(isLeftChild == true)
				parent.left = null;
			else
				parent.right = null;
		}
		//Case 2: Node only has one child
		else if(current.right == null){
			if(current == root)
				root = current.left;
			else if(isLeftChild)
				parent.left = current.left;
			else
				parent.right = current.left;
		}
		else if(current.left == null){
			if(current == root)
				root = current.right;
			else if(isLeftChild)
				parent.left = current.right;
			else
				parent.right = current.right;
		}
		//Case 3: Node had two children
		else if(current.left != null && current.right != null){
			Node successor = getSuccessor(current);
			if(current == root)
				root = successor;
			else if(isLeftChild)
				parent.left = successor;
			else
				parent.right = successor;
			successor.left = current.left;
		}
		nItems--;
		System.out.println(remove + " was deleted from the tree.");
	}
	//method finds the Node that replaces the deleted Node
	public Node getSuccessor(Node deleteNode){
		Node successor = null;
		Node successorParent = null;
		Node current = deleteNode.right;
		while(current != null){
			successorParent = successor;
			successor = current;
			current = current.left;
		}
		if(successor != deleteNode.right){
			successorParent.left = successor.right;
			successor.right = deleteNode.right;
		}
		return successor;
	}
	
	
	
	public void find(int search){
		boolean dFound = false;
		int level = 0;
		Node current = root;
		while(current != null && dFound == false){
			if(current.value == search){
				level++;
				dFound = true;
			}
			else if(current.value > search){
				level++;
				current = current.left;
			}
			else{
				level++;
				current = current.right;
			}
		}
		if(dFound == true)
			System.out.println("The number " + search + " was found on level " + level);
		else
			System.out.println("The number " + search + " was not found in the BST tree.");
	}
	
	
	
	public void newTree(){
		for(int i = 1; i <= 31; i++){
			aTree[i] = " . ";
		}
		root = null;
		nItems = 0;
	}
	
}
