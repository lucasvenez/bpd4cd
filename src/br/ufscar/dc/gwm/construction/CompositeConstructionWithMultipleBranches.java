/**
 * 
 */
package br.ufscar.dc.gwm.construction;

import java.util.HashSet;
import java.util.Set;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.exception.InvalidBranchException;
import br.ufscar.dc.gwm.node.control.ControlNode;

/**
 * @author Lucas Venezian Povoa
 * 
 */
public abstract class CompositeConstructionWithMultipleBranches<T1 extends ControlNode, T2 extends ControlNode>
		extends CompositeConstruction<T1, T2> {

	private static final long serialVersionUID = 6290636919238955075L;

	private Set<Graph> branches = new HashSet<Graph>();

	public CompositeConstructionWithMultipleBranches(T1 startNode, T2 endNode) {
		super(startNode, endNode);
	}

	public CompositeConstructionWithMultipleBranches(T1 startNode, T2 endNode,
			String name) {
		super(startNode, endNode, name);
	}

	public CompositeConstructionWithMultipleBranches(String name, T1 startNode,
			T2 endNode) {
		super(name, startNode, endNode);
	}

	@Override
	public Set<Graph> getBranches() {
		return branches;
	}

	public void addBranch(Graph branch) throws InvalidBranchException {

		if (branch == null || branches.contains(branch))
			throw new InvalidBranchException("Invalid exception branch");

		branches.add(branch);
	}

	public void addOrUpdateBranch(Graph branch)
			throws InvalidBranchException {

		if (branch == null)
			throw new InvalidBranchException();

		this.branches.add(branch);
	}

	public Graph getBranch(int index) throws IndexOutOfBoundsException {

		if (index < 0)
			throw new IndexOutOfBoundsException();

		return (Graph) branches.toArray()[index];
	}

	public Graph removeBranch(int index) throws IndexOutOfBoundsException {

		Graph result = (Graph) branches.toArray()[index];
		branches.remove(result);

		return result;
	}

	public Graph removeBranch(Graph branch) {

		if (branch != null) {
			branches.remove(branch);
			return branch;
		}

		return null;
	}

	public boolean hasBranch(Graph branch) {
		return branches.contains(branch);
	}

	public boolean haveBranches() {
		return !branches.isEmpty();
	}
}

