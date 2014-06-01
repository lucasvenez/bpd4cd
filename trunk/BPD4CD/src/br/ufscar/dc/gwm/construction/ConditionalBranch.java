package br.ufscar.dc.gwm.construction;

import br.ufscar.dc.gwm.node.control.EifNode;
import br.ufscar.dc.gwm.node.control.IfNode;

public class ConditionalBranch
		extends CompositeConstructionWithMultipleLabeledBranches<IfNode, EifNode, Boolean> {

	private static final long serialVersionUID = 64151604333883525L;

	public ConditionalBranch() {
		super(null, new IfNode(), new EifNode());
	}

	public ConditionalBranch(String condition) {
		super(null, new IfNode(condition), new EifNode());
	}

	public ConditionalBranch(String name, String condition) {
		super(name, new IfNode(condition), new EifNode());
	}

	public ConditionalBranch(String name, IfNode startNode, EifNode endNode) {
		super(name, startNode, endNode);
	}

	public String getCondition() {
		return super.startNode.getCondition();
	}

	public void setCondition(String condition) {
		super.startNode.setCondition(condition);
	}
}
