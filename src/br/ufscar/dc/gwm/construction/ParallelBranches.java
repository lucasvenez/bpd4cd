package br.ufscar.dc.gwm.construction;

import java.util.HashSet;
import java.util.Set;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.node.control.EparNode;
import br.ufscar.dc.gwm.node.control.ParNode;

public class ParallelBranches extends CompositeConstruction<ParNode,EparNode> {

	private Set<Graph> branches = new HashSet<Graph>();
	
	public ParallelBranches(String name) {
		super(name, new ParNode(), new EparNode());
	}
	
	public ParallelBranches(String name, ParNode startNode, EparNode endNode) {
		super(name, startNode, endNode);
	}

	public void addBranch(Graph branch) {
		this.branches.add(branch);
	}
	
	public boolean removeBranch(Graph branch) {
		return this.branches.remove(branch);
	}
	
	public boolean hasBranch(Graph branch) {
		return this.branches.contains(branch);
	}
	
	@Override
	public Set<Graph> getBranches() {
		return branches;
	}
}
