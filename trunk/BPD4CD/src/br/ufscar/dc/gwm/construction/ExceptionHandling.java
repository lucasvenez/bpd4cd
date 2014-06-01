package br.ufscar.dc.gwm.construction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.exception.InvalidBranchException;
import br.ufscar.dc.gwm.node.exception.EexpNode;
import br.ufscar.dc.gwm.node.exception.ExpNode;

public class ExceptionHandling extends CompositeConstruction<ExpNode, EexpNode> {

	private static final long serialVersionUID = -5668860947826575215L;

	private Map<Exception, Graph> branches = new HashMap<Exception, Graph>();

	public ExceptionHandling() {
		super(null, new ExpNode(), new EexpNode());
	}

	public ExceptionHandling(String name) {
		super(name, new ExpNode(), new EexpNode());
	}

	public ExceptionHandling(String name, ExpNode startNode, EexpNode endNode) {
		super(name, startNode, endNode);
	}

	public void addExceptionBranch(Exception exception, Graph branch) {
		if (exception != null && branch != null
				&& !branches.containsKey(exception)
				&& !branches.containsValue(branch)) {
			branches.put(exception, branch);
		} else {
			throw new InvalidBranchException("Invalid exception branch");
		}
	}
	
	public void addOrUpdateExceptionBranch(Exception exception, Graph branch) {
		if (exception != null && branch != null) {
			branches.put(exception, branch);
		} else {
			throw new InvalidBranchException("Invalid exception branch");
		}
	}
	
	public boolean hasException(Exception e) {
		return branches.containsKey(e);
	}
	
	public boolean hasBranch(Graph branch) {
		return this.branches.containsValue(branch);
	}

	public boolean haveBranches() {
		return !this.branches.isEmpty();
	}
	
	@Override
	public Set<Graph> getBranches() {
		return new HashSet<Graph>(branches.values());
	}
}
