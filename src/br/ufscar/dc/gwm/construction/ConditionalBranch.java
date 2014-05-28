package br.ufscar.dc.gwm.construction;

import java.util.HashSet;
import java.util.Set;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.node.control.IfNode;
import br.ufscar.dc.gwm.node.control.EifNode;

public class ConditionalBranch extends CompositeConstruction<IfNode, EifNode> {

	private String condition;
	
	private Graph trueBranch;
	
	private Graph falseBranch;
	
	public ConditionalBranch(String name, IfNode startNode, EifNode endNode) {
		super(name, startNode, endNode);
	}
	
	public ConditionalBranch(String name, String condition) {
		super(name, new IfNode(condition), new EifNode());
	}
	
	public Graph getTrueBranch() {
		return trueBranch;
	}

	public void setTrueBranch(Graph trueBranch) {
		this.trueBranch = trueBranch;
	}

	public Graph getFalseBranch() {
		return falseBranch;
	}

	public void setFalseBranch(Graph falseBranch) {
		this.falseBranch = falseBranch;
	}

	@Override
	public Set<Graph> getBranches() {
		
		Set<Graph> result = new HashSet<Graph>();
		
		if (trueBranch != null)
			result.add(trueBranch);
		
		if (falseBranch != null)
			result.add(falseBranch);
		
		return result;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
}
