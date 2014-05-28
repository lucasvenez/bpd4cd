package br.ufscar.dc.gwm.construction;

import java.util.HashSet;
import java.util.Set;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.node.control.PartialJoinNode;

public class PartialJoin<T1 extends PartialJoinNode> extends Construction<T1> {

	public PartialJoin(String name, T1 startNode) {
		super(name, startNode);
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
