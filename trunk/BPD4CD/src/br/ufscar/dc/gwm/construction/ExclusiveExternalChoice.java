package br.ufscar.dc.gwm.construction;

import java.util.HashSet;
import java.util.Set;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.exception.InvalidBranchException;
import br.ufscar.dc.gwm.exception.InvalidExceptionTypeException;
import br.ufscar.dc.gwm.node.communication.XorNode;
import br.ufscar.dc.gwm.node.control.ExorNode;

public class ExclusiveExternalChoice extends CompositeConstruction<XorNode,ExorNode> {

	private Set<Graph>  branches   = new HashSet<Graph>();
	private Set<String> exceptions = new HashSet<String>();
	
	public ExclusiveExternalChoice(String name) {
		super(name, new XorNode(), new ExorNode());
	}
	
	public ExclusiveExternalChoice(String name, XorNode startNode,
			ExorNode endNode) {
		super(name, startNode, endNode);
	}

	public void addExceptionBranch(String exception, Graph branch) throws Exception {
		if (exception == null)
			throw new NullPointerException();
		
		if (branch == null)
			throw new NullPointerException();
		
		if (exceptions.contains(exception))
			throw new InvalidExceptionTypeException("Exception type already handled.");
		
		if (branches.contains(exception))
			throw new InvalidBranchException("Existing exception branch.");
		
		branches.add(branch);
		exceptions.add(exception);
	}
	
	@Override
	public Set<Graph> getBranches() {
		return branches;
	}
}
