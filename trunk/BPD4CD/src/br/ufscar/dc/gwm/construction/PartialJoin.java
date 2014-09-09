package br.ufscar.dc.gwm.construction;

import java.util.HashSet;
import java.util.Set;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.node.control.PartialJoinNode;

public class PartialJoin<T1 extends PartialJoinNode> extends Construction<T1> {
	
	private static final long serialVersionUID = -6793206834529773745L;

	private Set<Graph> incomingBranches = new HashSet<Graph>();
	
	private Graph outgoingBranch;
	
	public PartialJoin(T1 startNode) {
		super(null, startNode);
	}
	
	public PartialJoin(String name, T1 startNode) {
		super(name, startNode);
	}

	public void setPartialJoinNode(T1 node) {
		super.setNode(node);
	}
	
	public T1 getPartialJoinNode() {
		return super.getNode();
	}
	
	public Set<Graph> getBranches() {
		Set<Graph> result = new HashSet<Graph>(incomingBranches);
		
		if (outgoingBranch != null)
			result.add(outgoingBranch);
		
		return result;
	}
	
	public void addIncomingBranch(Graph branch) {
		if (branch != null)
			this.incomingBranches.add(branch);
	}
	
	public Graph removeIncomingBranch(int index) throws IndexOutOfBoundsException {
		Graph g = (Graph) this.incomingBranches.toArray()[index];
		this.incomingBranches.remove(g);
		return g;
	}

	public Graph getOutgoingBranch() {
		return outgoingBranch;
	}

	public void setOutgoingBranch(Graph outgoingBranch) {
		this.outgoingBranch = outgoingBranch;
	}
}
