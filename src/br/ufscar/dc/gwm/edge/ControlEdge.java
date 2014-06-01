package br.ufscar.dc.gwm.edge;

import br.ufscar.dc.gwm.Edge;
import br.ufscar.dc.gwm.Node;

public class ControlEdge extends Edge<Node,Node> {

	private static final long serialVersionUID = -2423893887583341612L;
	
	public ControlEdge(Node startNode, Node endNode) {
		super(startNode, endNode);
	}
}
