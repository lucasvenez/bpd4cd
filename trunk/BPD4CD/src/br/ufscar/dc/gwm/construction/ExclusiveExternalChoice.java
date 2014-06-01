package br.ufscar.dc.gwm.construction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.Operation;
import br.ufscar.dc.gwm.node.communication.XorNode;
import br.ufscar.dc.gwm.node.control.ExorNode;

public class ExclusiveExternalChoice extends CompositeConstruction<XorNode,ExorNode> {

	private static final long serialVersionUID = 5132434471288485981L;

	private Map<Operation,Graph> branches = new HashMap<Operation,Graph>();
	
	public ExclusiveExternalChoice() {
		super(null, new XorNode(), new ExorNode());
	}
	
	public ExclusiveExternalChoice(String name) {
		super(name, new XorNode(), new ExorNode());
	}
	
	public ExclusiveExternalChoice(String name, XorNode startNode,
			ExorNode endNode) {
		super(name, startNode, endNode);
	}

	public void addBranch(Operation operation, Graph branch) {
		this.branches.put(operation, branch);
	}
	
	@Override
	public Set<Graph> getBranches() {
		return new HashSet<Graph>(branches.values());
	}
}
