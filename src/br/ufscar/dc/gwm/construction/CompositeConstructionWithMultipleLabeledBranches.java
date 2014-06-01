/**
 * 
 */
package br.ufscar.dc.gwm.construction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.exception.InvalidBranchException;
import br.ufscar.dc.gwm.node.control.ControlNode;

/**
 * @author Lucas Venezian Povoa
 * 
 */
public abstract class CompositeConstructionWithMultipleLabeledBranches<T1 extends ControlNode, T2 extends ControlNode, T3>
		extends CompositeConstruction<T1, T2> {

	private static final long serialVersionUID = 6290636919238955075L;

	private Map<T3,Graph> branches = new HashMap<T3,Graph>();
	
	public CompositeConstructionWithMultipleLabeledBranches(T1 startNode,
			T2 endNode) {
		super(startNode, endNode);
	}

	public CompositeConstructionWithMultipleLabeledBranches(T1 startNode,
			T2 endNode, String name) {
		super(startNode, endNode, name);
	}

	public CompositeConstructionWithMultipleLabeledBranches(String name,
			T1 startNode, T2 endNode) {
		super(name, startNode, endNode);
	}

	@Override
	public Set<Graph> getBranches() {
		return new HashSet<Graph>(branches.values());
	}
	
	public void addBranch(T3 label, Graph branch)
			throws InvalidBranchException {

		if (label == null || branch == null
				|| branches.containsKey(label)
				|| branches.containsValue(branch))
			throw new InvalidBranchException("Invalid exception branch");
		
		branches.put(label, branch);
	}
	
	public void addOrUpdateBranch(T3 label, Graph branch)
			throws InvalidBranchException {

		if (label == null || branch == null)
			throw new InvalidBranchException();

		this.branches.put(label, branch);
	}

	public Graph getBranch(int index) throws IndexOutOfBoundsException {

		if (index < 0)
			throw new IndexOutOfBoundsException();

		return (Graph) branches.values().toArray()[index];
	}

	public Graph getBranch(T3 label) {

		if (label != null)
			return branches.get(label);

		return null;
	}

	public Graph removeBranch(int index) throws IndexOutOfBoundsException {

		Graph result = (Graph) branches.values().toArray()[index];
		branches.remove(result);

		return result;
	}

	public Graph removeBranch(T3 label) {
		Graph g = null;

		if (label != null)
			g = branches.remove(label);

		return g;
	}

	public boolean hasBranch(Graph branch) {
		return branches.containsValue(branch);
	}

	public boolean haveBranches() {
		return !branches.isEmpty();
	}
}
