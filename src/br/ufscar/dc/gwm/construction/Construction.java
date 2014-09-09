package br.ufscar.dc.gwm.construction;

import java.util.Set;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.Node;
import br.ufscar.dc.gwm.node.control.ControlNode;

public abstract class Construction<T1 extends ControlNode> extends Node {

	private static final long serialVersionUID = 7481092845093724386L;

	protected T1 node;
	
	public Construction() {
		super(null);
	}
	
	public Construction( T1 node) {
		super(null);
		this.node = node;
	}
	
	public Construction(String name, T1 node) {
		super(name);
		this.node = node;
	}
	
	public T1 getNode() {
        return node;
	}

	public void setNode(T1 node) {
        this.node = node;
	}
	
	public abstract Set<Graph> getBranches();
	
}
