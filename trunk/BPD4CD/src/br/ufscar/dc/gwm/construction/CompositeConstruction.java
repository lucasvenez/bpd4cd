package br.ufscar.dc.gwm.construction;

import java.util.Set;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.node.control.ControlNode;

public abstract class CompositeConstruction<T1 extends ControlNode, T2 extends ControlNode>
		extends Construction<T1> {

	private static final long serialVersionUID = -5269284483257221432L;

	protected T2 endNode;
	
	public CompositeConstruction(T1 startNode, T2 endNode) {
		super(null, startNode);
		this.endNode = endNode;
	}

	public CompositeConstruction(T1 startNode, T2 endNode, String name) {
		super(name, startNode);
		this.endNode = endNode;
	}

	public CompositeConstruction(String name, T1 startNode, T2 endNode) {
		this(startNode, endNode, name);
	}

	public abstract Set<Graph> getBranches();

	public T1 getStartNode() {
		return super.node;
	}
	
	public void setStartNode(T1 startNode) {
		this.node = startNode;
	}
	
	public T2 getEndNode() {
		return endNode;
	}

	public void setEndNode(T2 endNode) {
		this.endNode = endNode;
	}
}
