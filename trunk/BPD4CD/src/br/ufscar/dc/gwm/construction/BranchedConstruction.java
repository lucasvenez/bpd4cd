package br.ufscar.dc.gwm.construction;

import br.ufscar.dc.gwm.Graph;

public class BranchedConstruction<T1> extends Construction<T1> {

	private static final long serialVersionUID = -5195530527547480501L;

	protected Graph branch;

	public BranchedConstruction() {
		super();
	}

	public BranchedConstruction(String name, T1 startNode) {
		super(name, startNode);
	}

	public BranchedConstruction(T1 startNode) {
		super(startNode);
	}

	public Graph getBranch() {
		
		return this.branch;
	}
}
