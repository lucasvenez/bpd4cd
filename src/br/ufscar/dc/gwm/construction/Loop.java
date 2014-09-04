package br.ufscar.dc.gwm.construction;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.node.control.LoopNode;

public class Loop extends BranchedConstruction<LoopNode> {

	private static final long serialVersionUID = -993415847136102128L;

	private boolean conditionEvaluatedBefore = true;
	
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
		return super.getBranch();
	}
}
