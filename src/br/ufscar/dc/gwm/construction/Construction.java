package br.ufscar.dc.gwm.construction;

import java.util.Set;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.Node;

public abstract class Construction<T1> extends Node {

	protected T1 startNode;
	
	public Construction(String name, T1 startNode) {
		super(name);
		this.startNode = startNode;
	}
	
	public T1 getStartNode() {
        return startNode;
	}

	public void setStartNode(T1 startNode) {
        this.startNode = startNode;
	}
	
	public abstract Set<Graph> getBranches();
	
}
