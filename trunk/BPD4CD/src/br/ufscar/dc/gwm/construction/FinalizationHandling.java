package br.ufscar.dc.gwm.construction;

import java.util.HashSet;
import java.util.Set;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.node.exception.EfinNode;
import br.ufscar.dc.gwm.node.exception.FinNode;

public class FinalizationHandling extends CompositeConstruction<FinNode,EfinNode> {

	private Graph branch;
	
	public FinalizationHandling(String name) {
		super(name, new FinNode(), new EfinNode());
	}
	
	public FinalizationHandling(String name, FinNode startNode, EfinNode endNode) {
		super(name, startNode, endNode);
	}

	@Override
	public Set<Graph> getBranches() {
		
		Set<Graph> result = new HashSet<Graph>();
		
		if (branch != null)
			result.add(branch);
		
		return result;
	}
}
