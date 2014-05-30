package br.ufscar.dc.gwm.edge;

import br.ufscar.dc.gwm.Edge;
import br.ufscar.dc.gwm.node.communication.CommunicationNode;

public class CommunicationEdge extends Edge<CommunicationNode, CommunicationNode> {

	public String operation;

	public CommunicationEdge() {

	}
	
	public CommunicationEdge(String operation) {
		this.operation = operation;
	}
	
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	
}
