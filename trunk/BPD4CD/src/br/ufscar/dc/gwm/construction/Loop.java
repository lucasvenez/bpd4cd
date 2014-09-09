package br.ufscar.dc.gwm.construction;

import java.util.HashSet;
import java.util.Set;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.node.control.LoopNode;

public class Loop extends Construction<LoopNode> {

	private static final long serialVersionUID = -993415847136102128L;

	private boolean conditionEvaluatedBefore = true;
	
	private Graph iterativeBranch;
	
	public Loop(String name) {
		super(name, new LoopNode());
	}
	
	public Loop(String name, LoopNode loopNode) {
		super(name, loopNode);
	}
	
	public boolean isConditionEvaluatedBefore() {
		return conditionEvaluatedBefore;
	}
	
	public void setConditionEvaluatedBefore(boolean before) {
		this.conditionEvaluatedBefore = before;
	}

	public LoopNode getLoopNode() {
		return super.node;
	}
	
	public Graph getIterativeBranch() {
		return this.iterativeBranch;
	}

	@Override
	public Set<Graph> getBranches() {
		
		Set<Graph> branches = new HashSet<Graph>();
		
		branches.add(this.iterativeBranch);
		
		return branches;
	}
}
