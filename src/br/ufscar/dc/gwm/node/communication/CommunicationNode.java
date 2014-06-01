package br.ufscar.dc.gwm.node.communication;

import br.ufscar.dc.gwm.node.control.ControlNode;

public abstract class CommunicationNode extends ControlNode {

	private static final long serialVersionUID = 6863031966159559671L;

	public CommunicationNode(String name) {
		super(name);
	}
}
