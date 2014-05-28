package br.ufscar.dc.gwm.construction;

import java.util.HashSet;
import java.util.Set;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.node.exception.EexpNode;
import br.ufscar.dc.gwm.node.exception.ExpNode;

public class ExceptionHandling extends CompositeConstruction<ExpNode,EexpNode> {

	public ExceptionHandling(String name) {
		super(name, new ExpNode(), new EexpNode());
	}
	
	public ExceptionHandling(String name, ExpNode startNode, EexpNode endNode) {
		super(name, startNode, endNode);
	}

	private Set<Graph> branches = new HashSet<Graph>();
	
	private Set<Exception> exceptions = new HashSet<Exception>();

	public void addExceptionBranch(Exception exception, Graph branch) {
		if (exception != null && branch != null) {
			exceptions.add(exception);
			branches.add(branch);
		} else {
			throw new NullPointerException("Invalid exception or branch");
		}
	}

	@Override
	public Set<Graph> getBranches() {
		return branches;
	}

}
