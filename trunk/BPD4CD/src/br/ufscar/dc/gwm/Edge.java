package br.ufscar.dc.gwm;

public abstract class Edge<T1 extends Node, T2 extends Node> extends Attribute {

	private static final long serialVersionUID = -1404978497935778404L;

	protected T1 startNode;
	
	protected T2 endNode;

	public Edge(T1 startNode, T2 endNode) {
		this.startNode = startNode;
		this.endNode = endNode;
	}
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((endNode == null) ? 0 : endNode.hashCode());
		result = prime * result
				+ ((startNode == null) ? 0 : startNode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		Edge<T1, T2> other = (Edge<T1, T2>) obj;
		if (endNode == null) {
			if (other.endNode != null)
				return false;
		} else if (!endNode.equals(other.endNode))
			return false;
		if (startNode == null) {
			if (other.startNode != null)
				return false;
		} else if (!startNode.equals(other.startNode))
			return false;
		return true;
	}
}
