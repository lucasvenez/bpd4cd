package br.ufscar.dc.gwm.construction;

import java.util.HashSet;
import java.util.Set;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.node.control.LoopNode;

public class Loop extends Construction<LoopNode> {

	private Graph branch;
	
	private boolean before = false;
	
	public Loop(String name) {
		super(name, new LoopNode());
	}
	
	public Loop(String name, LoopNode loopNode) {
		super(name, loopNode);
	}
	
	public boolean isBefore() {
		return before;
	}
	
	public void setBefore(boolean before) {
		this.before = before;
	}

	@Override
	public Set<Graph> getBranches() {
		
		Set<Graph> result = new HashSet<Graph>();
		
		if (branch != null)
			result.add(branch);
		
		return result;
	}

}
