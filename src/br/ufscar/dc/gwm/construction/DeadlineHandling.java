package br.ufscar.dc.gwm.construction;

import java.util.HashSet;
import java.util.Set;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.node.exception.DdlNode;
import br.ufscar.dc.gwm.node.exception.EddlNode;

public class DeadlineHandling extends CompositeConstruction<DdlNode,EddlNode> {

	public DeadlineHandling(String name, DdlNode startNode, EddlNode endNode) {
		super(name, startNode, endNode);
	}
	
	public DeadlineHandling(String name) {
		super(name, new DdlNode(), new EddlNode());
	}

	private Graph branch;
	
	@Override
	public Set<Graph> getBranches() {
		
		Set<Graph> result = new HashSet<Graph>();
		
		if (branch != null)
			result.add(branch);
		
		return result;
	}
}
