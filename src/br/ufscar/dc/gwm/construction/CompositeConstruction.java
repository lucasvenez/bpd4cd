package br.ufscar.dc.gwm.construction;

import java.util.Set;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.node.control.ControlNode;

public abstract class CompositeConstruction<
							T1 extends ControlNode, 
							T2 extends ControlNode> extends Construction<T1> {

	protected T2 endNode;

	
	public CompositeConstruction(String name, T1 startNode, T2 endNode) {
		super(name, startNode);
		this.endNode = endNode;
	}
	
	public abstract Set<Graph> getBranches();

	public T2 getEndNode() {
        return endNode;
	}

	public void setEndNode(T2 endNode) {
        this.endNode = endNode;
	} 	
}
