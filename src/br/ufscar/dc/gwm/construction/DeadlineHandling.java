package br.ufscar.dc.gwm.construction;

import java.util.HashSet;
import java.util.Set;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.node.exception.DdlNode;
import br.ufscar.dc.gwm.node.exception.DeadlineException;
import br.ufscar.dc.gwm.node.exception.EddlNode;

public class DeadlineHandling extends CompositeConstruction<DdlNode,EddlNode> {

	private static final long serialVersionUID = -4942688051524938668L;
	
	private Graph branch;

	public DeadlineHandling(DeadlineException deadline) {
		super(null, new DdlNode(deadline), new EddlNode());
	}
	
	public DeadlineHandling(String name, DeadlineException deadline) {
		super(name, new DdlNode(deadline), new EddlNode());
	}
	
	public DeadlineHandling(String name, DdlNode startNode, EddlNode endNode) {
		super(name, startNode, endNode);
	}
	
	public DeadlineHandling() {
		super(null, new DdlNode(), new EddlNode());
	}
	
	public Graph getBranch() {
		return branch;
	}

	public void setBranch(Graph branch) {
		this.branch = branch;
	}

	public DeadlineException getDeadline() {
		return this.startNode.getDeadline();
	}
	
	@Override
	public Set<Graph> getBranches() {
		
		Set<Graph> result = new HashSet<Graph>();
		
		if (branch != null)
			result.add(branch);
		
		return result;
	}
}
