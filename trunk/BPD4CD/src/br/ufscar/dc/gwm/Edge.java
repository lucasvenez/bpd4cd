package br.ufscar.dc.gwm;

public abstract class Edge<T1 extends Node, T2 extends Node> extends Attribute {

	private static final long serialVersionUID = -1404978497935778404L;

	protected T1 startNode;
	
	protected T2 endNode;

	public Node getStartNode() {
		return startNode;
	}

	public void setStartNode(T1 startNode) {
		this.startNode = startNode;
	}

	public T2 getEndNode() {
		return endNode;
	}

	public void setEndNode(T2 endNode) {
		this.endNode = endNode;
		endNode.addIncomingEdge(this);
	}
}
