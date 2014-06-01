package br.ufscar.dc.gwm.edge;

import br.ufscar.dc.gwm.Edge;
import br.ufscar.dc.gwm.node.communication.CommunicationNode;

public class CommunicationEdge extends Edge<CommunicationNode, CommunicationNode> {

	private static final long serialVersionUID = -5820323238315254390L;
	
	public String operation;

	public CommunicationEdge(CommunicationNode startNode, CommunicationNode endNode) {
		super(startNode, endNode);
	}
	
	public CommunicationEdge(CommunicationNode startNode, CommunicationNode endNode, String operation) {
		super(startNode, endNode);
		this.operation = operation;
	}
	
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
}
