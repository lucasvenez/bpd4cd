package br.ufscar.dc.gwm.construction;

import br.ufscar.dc.gwm.Node;

public abstract class Construction<T1> extends Node {

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
	
	public T1 getStartNode() {
        return node;
	}

	public void setStartNode(T1 node) {
        this.node = node;
	}
	
}
