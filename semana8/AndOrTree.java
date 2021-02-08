
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Daniel Levandovschi fc54412
 *
 */
public class AndOrTree {

	public enum NodeType {
		AND, OR, NOT, LEAF
	}

	private static class TreeNode {

		private String id;
		private NodeType type;
		private ArrayList<TreeNode> childNodes;

		/**
		 * Recursive construction of a tree node from an identifier, a list of strings
		 * representing the formula and a list of tree nodes that can be used as child
		 * nodes
		 * 
		 * @param id          formula identifier
		 * @param f           list of strings that constitute the formula
		 * @param usableNodes list of nodes already created whose root identifiers can
		 *                    occur in f
		 * 
		 * @requires f does not contain parenthesis; it may only contain "or", "and",
		 *           "not" and identifiers; "or" has the lowest precedence and "not" the
		 *           highest.
		 */
		private TreeNode(String id, List<String> f, List<TreeNode> usableNodes) {
			if (f.size() == 1) {
				// f is the id of a member of usableNodes or it is a propositional symbol
				String name = f.get(0);
				TreeNode t = occursIn(name, usableNodes);
				if (t != null) {
					this.id = t.id;
					this.type = t.type;
					this.childNodes = t.childNodes;
				} else {
					this.id = name;
					this.type = NodeType.LEAF;
					this.childNodes = new ArrayList<>();
				}
			} else {
				this.id = id;
				this.childNodes = new ArrayList<>();
				int operatorPosition = f.indexOf("or");
				if (operatorPosition != -1) {
					// the formula has an "or"
					this.type = NodeType.OR;
					createChilds(id, f, "or", operatorPosition, 0, childNodes, usableNodes);
				} else {
					operatorPosition = f.indexOf("and");
					if (operatorPosition != -1) {
						// the formula has an "and"
						this.type = NodeType.AND;
						createChilds(id, f, "and", operatorPosition, 0, childNodes, usableNodes);
					} else {
						// it must be a not
						this.type = NodeType.NOT;
						createChilds(id, f, "not", 0, 0, childNodes, usableNodes);
					}
				}
			}
		}

		private static TreeNode occursIn(String name, List<TreeNode> childs) {
			int i = 0;
			TreeNode result = null;
			Boolean found = false;
			while (i < childs.size() && !found) {
				TreeNode t = childs.get(i);
				if (name.equals(t.id)) {
					found = true;
					result = t;
				} else {
					i++;
				}
			}
			return result;
		}

		/**
		 * Creates child nodes, of a node whose node.id is id, from a formula f.
		 * 
		 * @param id               identifier of the parent node
		 * @param f                the list of strings representing the formula
		 * @param operator         the main operator (i.e., with lowest precedence) in f
		 * @param operatorPosition the position of the first occurrence of that operator
		 *                         in f
		 * @param nodeNumber       number of the next child to be created
		 * @param childNodes       the list of childNodes of the parent node already
		 *                         created
		 * @param usableNodes      list of nodes already created whose identifiers can
		 *                         occur in f
		 */
		private static void createChilds(String id, List<String> f, String operator, int operatorPosition,
				int nodeNumber, List<TreeNode> childNodes, List<TreeNode> usableNodes) {
			String formulaId = id + "_" + Integer.toString(nodeNumber);
			if (operator.equals("not")) {
				// it is a unary operator (only one child node)
				List<String> operand = f.subList(1, 2);
				TreeNode newChild = new TreeNode(formulaId, operand, usableNodes);
				childNodes.add(newChild);
			} else {
				// two or more child nodes
				List<String> operand = f.subList(0, operatorPosition);
				TreeNode newChild = new TreeNode(formulaId, operand, usableNodes);
				childNodes.add(newChild);
				if (newChild.id.equals(formulaId)) {
					// newChild is not a LEAF node or a member of usableNodes
					nodeNumber++;
				}
				f = f.subList(operatorPosition + 1, f.size());
				int nextPosition = f.indexOf(operator);
				if (nextPosition != -1) {
					createChilds(id, f, operator, nextPosition, nodeNumber, childNodes, usableNodes);
				} else {
					childNodes.add(new TreeNode(formulaId, f, usableNodes));
				}
			}
		}

	} // end of class TreeNode

	private TreeNode root;

	private AndOrTree(TreeNode n) {
		this.root = n;
	}

	/**
	 * Creates the AndOrTree for a single named formula (that has no parenthesis)
	 * 
	 * The root id is the name of the formula. The leaves of the tree are the
	 * propositional symbols in that formula
	 * 
	 * @param f a named formula
	 */
	public AndOrTree(Formula f) {
		ArrayList<TreeNode> childs = new ArrayList<>();
		this.root = new TreeNode(f.getName(), f.getFormula(), childs);
	}

	/**
	 * Creates the AndOrTree for a formula f that has been split into a list of
	 * named subformulas, combining the tree nodes for those subformulas.
	 * 
	 * 
	 * @param subFormulas a list of named formulas representing f
	 * 
	 * @requires subFormulas is the list with each of the subformulas of f within
	 *           parenthesis, from left to right, followed by the result of
	 *           rewriting f, without parenthesis, using those named formulas.
	 * 
	 *           A subformula in the list may use the names of the previous
	 *           subformulas in the list.
	 * 
	 *           Example: [ ("f_0", ["P", "or", "Q", "or", "not", "R", "and", "T"]),
	 *           ("f_1", ["R", "and", "f_0"]), ("f_2", ["not", "f_1"]) ]
	 * 
	 */
	public AndOrTree(List<Formula> subFormulas) {
		ArrayList<TreeNode> fils = new ArrayList<>();
		for(int i = 0; i < subFormulas.size();i++) {
			fils.add(new TreeNode(subFormulas.get(i).getName(), subFormulas.get(i).getFormula(), fils));
		}
		this.root = new TreeNode(subFormulas.get(subFormulas.size()-1).getName(),subFormulas.get(subFormulas.size()-1).getFormula(), fils);
	}   



	/**
	 * The name of this tree
	 * 
	 * @return the id of the root node
	 */
	public String getId() {
		return root.id;
	}

	/**
	 * The type of this tree
	 * 
	 * @return the NodeType of the root node
	 */
	public NodeType getType() {
		switch(this.root.type) {
		case AND:
			return NodeType.AND;
		case OR:
			return NodeType.OR;
		case NOT:
			return NodeType.NOT;
		case LEAF:
			return NodeType.LEAF;
		}
		return null;
	}


	/**
	 * The list of subtrees of this tree
	 * 
	 * @return the list of the AndOrTrees that are the subtrees of the tree
	 */
	public List<AndOrTree> getSubtrees() {
		List<TreeNode> list1 = this.root.childNodes;
		List<AndOrTree> list2 = new ArrayList<AndOrTree>();
		Iterator<TreeNode> tree1 = list1.iterator();
		while(tree1.hasNext()) {
			list2.add(new AndOrTree(tree1.next()));
		}
		return list2;
	}


	/**
	 * The list with the names of the leaves of this tree
	 * 
	 * @return a list with the ids of all the leaf nodes, without repetitions
	 */
	public List<String> getLeafIds(){
		List<String> ids = new ArrayList<String>();
		getLeafIds2(this.root.childNodes, ids);
		return ids;
	}

	private void getLeafIds2(ArrayList<TreeNode> tree, List<String> ids) {
		Iterator<TreeNode> tree1 = tree.iterator();
		while(tree1.hasNext()) {
			TreeNode next = tree1.next();
			if(next.type.equals(NodeType.LEAF)) {
				if(!ids.contains(next.id)) {
					ids.add(next.id);
				}
			}
			getLeafIds2(next.childNodes, ids);
		}
	}

	/**
	 * Checks if the valuation gives values to all the propositional symbols
	 * 
	 * @param valuation a list of pairs (symbol, value)
	 * @return true if the list contains values for all the leaves of the tree,
	 *         false otherwise
	 */
	public Boolean allLeafs(List<SymbolValue> valuation) {
		ArrayList<String> ids = new ArrayList<String>();
		for(int i = 0; i < getLeafIds().size(); i++)
			for(int j = 0; j < valuation.size(); j++)
				if(getLeafIds().get(i).contains(valuation.get(j).getSymbol()))
					ids.add(valuation.get(j).getSymbol());
		return ids.size() == getLeafIds().size();
	}


	/**
	 * Evaluate this AndOrTree given values for the propositional symbols
	 * 
	 * 
	 * @param valuation a list of pairs (symbol, booleanValue)
	 * @return true if the formula represented by the tree is true, false otherwise
	 * 
	 * @requires valuation has a pair relative to each leaf of the tree
	 */
	public Boolean evaluate(List<SymbolValue> valuation) {
		return evaluate2(valuation, root);
	}

	private boolean evaluate2(List<SymbolValue> valuation, TreeNode arv) {
		boolean[] fils = new boolean[arv.childNodes.size()];
		for(int i = 0; i < arv.childNodes.size(); i++) {  //este ciclo for serve para atribuir valor true ou false
			if(arv.childNodes.get(i).type.equals(NodeType.LEAF)) {
				fils[i] = getValue(arv.childNodes.get(i).id, valuation);
			}
			else if(arv.childNodes.get(i).type.equals(NodeType.NOT)) {
				fils[i] = !evaluate2(valuation, arv.childNodes.get(i));
			}
			else {
				fils[i] = evaluate2(valuation, arv.childNodes.get(i));   
			}
		}      
		boolean cont = fils[0];
		//o cojunto de if e if else serve para atribuir o valor de acordo com o type de node(and ou or)
		if(arv.type.equals(NodeType.OR)) {
			for(int i = 1; i < fils.length; i++) cont = (cont || fils[i]);
		}
		else if(arv.type.equals(NodeType.AND)) {
			for(int i = 1; i < fils.length; i++) cont = (cont && fils[i]);
		}
		return cont;
	}


	/**
	 * Gets the value of the id in the valuation
	 * 
	 * @param id        a propositional symbol
	 * @param valuation a list of pairs (symbol, booleanValue)
	 * @return the boolean value of id or null if it is not in the valuation
	 */
	private static Boolean getValue(String id, List<SymbolValue> valuation) {
		Iterator<SymbolValue> val1 = valuation.iterator();
		while(val1.hasNext()) {
			SymbolValue next = val1.next();
			if(next.getSymbol().equals(id)) {
				return next.getValue();
			}
		}
		return null;
	}



	/**
	 * Textual representation
	 */
	public String toString() {
		String currentIndent = "";
		StringBuilder sb = new StringBuilder();
		toStringAux(currentIndent, this, sb);
		return sb.toString();
	}

	private void toStringAux(String currentIndent, AndOrTree tree, StringBuilder sb) {
		if (root != null) {
			sb.append(currentIndent + tree.getId() + ":" + tree.getType().toString() + "\n");
			if (tree.getType() != NodeType.LEAF) {
				List<AndOrTree> subtrees = tree.getSubtrees();
				for (AndOrTree subtree : subtrees) {
					toStringAux(currentIndent + "    ", subtree, sb);
				}
			}
		} else {
			sb.append("Empty tree");
		}
	}

}
