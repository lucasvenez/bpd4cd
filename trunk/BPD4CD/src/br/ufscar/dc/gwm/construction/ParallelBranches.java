package br.ufscar.dc.gwm.construction;

import java.util.HashSet;
import java.util.Set;

import br.ufscar.dc.gwm.node.control.ParNode;
import br.ufscar.dc.gwm.node.control.EparNode;
import br.ufscar.dc.gwm.node.control.PartialJoinNode;

public class ParallelBranches extends CompositeConstructionWithMultipleBranches<ParNode,EparNode> {

	private static final long serialVersionUID = 994685295521246109L;

	private Set<PartialJoin<? extends PartialJoinNode>> partialJoin = 
			new HashSet<PartialJoin<? extends PartialJoinNode>>();
	
	public ParallelBranches() {
		super(null, new ParNode(), new EparNode());
	}
	
	public ParallelBranches(String name) {
		super(name, new ParNode(), new EparNode());
	}
	
	public ParallelBranches(String name, ParNode startNode, EparNode endNode) {
		super(name, startNode, endNode);
	}
	
	public void addPartialJoin(PartialJoin<? extends PartialJoinNode> join) {
		this.partialJoin.add(join);
	}
	
	public PartialJoin<? extends PartialJoinNode> removePartialJoin(int index) {
		
		@SuppressWarnings("unchecked")
		PartialJoin<? extends PartialJoinNode> p = 
				(PartialJoin<? extends PartialJoinNode>) this.partialJoin.toArray()[index];
		
		this.partialJoin.remove(p);
		
		return p;
	}
	
	public boolean hasPartialJoin(PartialJoin<? extends PartialJoinNode> partialJoin) {
		return this.partialJoin.contains(partialJoin);
	}
	
	public boolean havePartialJoins() {
		return !this.partialJoin.isEmpty();
	}
}
