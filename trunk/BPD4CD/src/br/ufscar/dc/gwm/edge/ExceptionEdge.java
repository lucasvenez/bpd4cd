package br.ufscar.dc.gwm.edge;

import br.ufscar.dc.gwm.Edge;
import br.ufscar.dc.gwm.Exception;
import br.ufscar.dc.gwm.Node;
import br.ufscar.dc.gwm.node.exception.ExceptionNode;

public class ExceptionEdge extends Edge<Node,ExceptionNode> {

	private static final long serialVersionUID = 6240011160791657364L;

	private Exception exception;
	
	public ExceptionEdge(Node startNode, ExceptionNode endNode) {
		super(startNode, endNode);
	}
	
	public ExceptionEdge(Node startNode, ExceptionNode endNode, Exception exception) {
		super(startNode, endNode);
		this.exception = exception;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
}
