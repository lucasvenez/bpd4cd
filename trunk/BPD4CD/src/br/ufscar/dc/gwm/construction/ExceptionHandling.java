package br.ufscar.dc.gwm.construction;

import br.ufscar.dc.gwm.node.exception.EexpNode;
import br.ufscar.dc.gwm.node.exception.ExpNode;

public final class ExceptionHandling extends CompositeConstructionWithMultipleLabeledBranches<ExpNode, EexpNode, Exception> {

	private static final long serialVersionUID = -7919049399138806427L;

	public ExceptionHandling() {
		super(null, new ExpNode(), new EexpNode());
	}

	public ExceptionHandling(String name) {
		super(name, new ExpNode(), new EexpNode());
	}

	public ExceptionHandling(String name, ExpNode startNode, EexpNode endNode) {
		super(name, startNode, endNode);
	}
}
