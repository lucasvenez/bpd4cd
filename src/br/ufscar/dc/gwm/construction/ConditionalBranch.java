package br.ufscar.dc.gwm.construction;

import java.util.HashSet;
import java.util.Set;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.node.control.IfNode;
import br.ufscar.dc.gwm.node.control.EifNode;

public class ConditionalBranch extends CompositeConstruction<IfNode, EifNode> {

	private static final long serialVersionUID = 64151604333883525L;

	private Graph trueBranch;
	
	private Graph falseBranch;
	
	public ConditionalBranch() {
		super(null, new IfNode(), new EifNode());
	}
	
	public ConditionalBranch(String condition) {
		super(null, new IfNode(condition), new EifNode());
	}

	public ConditionalBranch(String name, String condition) {
		super(name, new IfNode(condition), new EifNode());
	}
	
	public ConditionalBranch(String name, IfNode startNode, EifNode endNode) {
		super(name, startNode, endNode);
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

	public void addBranch(Graph branch, boolean trueBranch) {
		if (trueBranch)
			this.trueBranch = branch;
		else
			this.falseBranch = branch;
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
		return super.startNode.getCondition();
	}

	public void setCondition(String condition) {
		super.startNode.setCondition(condition);
	}
}
