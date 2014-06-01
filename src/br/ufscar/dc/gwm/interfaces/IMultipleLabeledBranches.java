package br.ufscar.dc.gwm.interfaces;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.exception.InvalidBranchException;

public interface IMultipleLabeledBranches<T1> {

	public void addBranch(T1 obj, Graph branch) throws InvalidBranchException;

	public void addOrUpdateBranch(T1 obj, Graph branch) throws InvalidBranchException;
	
	public Graph getBranch(int index) throws IndexOutOfBoundsException;
	
	public Graph getBranch(T1 obj);
	
	public Graph removeBranch(int index);
	
	public Graph removeBranch(Graph branch);
	
	public boolean hasBranch();
	
	public boolean haveBranches();
}
