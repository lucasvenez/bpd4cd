package br.ufscar.dc.gwm.node.control;

import br.ufscar.dc.gwm.Node;
import br.ufscar.dc.gwm.construction.Construction;

public abstract class ControlNode extends Node {

	private static final long serialVersionUID = -2129856534137494359L;

	private Construction<?> parentConstruction;
	
	public ControlNode(String name) {
		super(name);
	}
	
	public void setParentConstruction(Construction<?> construction) {
		this.parentConstruction = construction; 
	}
	
	public Construction<?> getParentConstruction() {
		return parentConstruction;
	}
}
