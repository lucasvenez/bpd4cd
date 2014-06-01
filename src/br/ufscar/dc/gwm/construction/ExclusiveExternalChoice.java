package br.ufscar.dc.gwm.construction;

import br.ufscar.dc.gwm.Operation;
import br.ufscar.dc.gwm.node.communication.XorNode;
import br.ufscar.dc.gwm.node.control.ExorNode;

public class ExclusiveExternalChoice
		extends CompositeConstructionWithMultipleLabeledBranches<XorNode, ExorNode, Operation> {

	private static final long serialVersionUID = 5132434471288485981L;

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
}
