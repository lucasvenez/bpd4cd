package br.ufscar.dc.decomposition;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.ufscar.dc.gwm.Edge;
import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.Node;
import br.ufscar.dc.gwm.construction.Construction;
import br.ufscar.dc.gwm.edge.ControlEdge;
import br.ufscar.dc.gwm.edge.ExceptionEdge;
import br.ufscar.dc.gwm.node.communication.GetNode;
import br.ufscar.dc.gwm.node.communication.ReqNode;

public class Decomposition {

	private Graph process;

	Map<String, Graph> cloudList   = new HashMap<String, Graph>();
	Map<String, Graph> premiseList = new HashMap<String, Graph>();
	
	int cloudOperationCount   = 0;
	int premiseOperationCount = 0;
	
	public Coreography performDecomposition() {
		
		Coreography result = null;
		
		result = createCoreographyAndDataFlow(
					performCluster(performFragmentation()));
		
		return result;
	}
	
	private Fragment performFragmentation() {
		
		fragmentation(this.process, this.process.isAllocatedForPremise());
		
		Fragment fragment = new Fragment();
		
		fragment.setSubprocess(this.process);
		fragment.setConcernsAllocatedForCloud(this.cloudList);
		fragment.setConcernsAllocatedForPremisse(this.premiseList);
		
		return fragment; 
	}
	
	@SuppressWarnings("unchecked")
	private void fragmentation(Graph graph, boolean onPremise) {
		
		Node node = graph.getStartNode();

		for (node = node.getNextNode(); node != null; node = node.getNextNode()) {
			
			Set<ExceptionEdge> outgoingExceptionEdges = new HashSet<ExceptionEdge>();
			
			if (node.isOnPremise() != onPremise) {
				
				Set<ControlEdge> incomingControlEdges = node.getIncomingControlEdges();

				Graph concern = new Graph();
				
				do {
					
					if (node instanceof Construction)
						for(Graph branch : ((Construction<? extends Node>) node).getBranches())
							fragmentation(branch, 
								((Construction<? extends Node>) node).getNode().isOnPremise());
					
					concern.addNode(node);
					
					process.removeNode(node);
					
					if (node.getNextNode().isOnPremise() != onPremise)
						process.removeEdges(node.getOutgoingControlEdges());
					
					node = node.getNextNode();
					
					outgoingExceptionEdges.addAll(node.getOutgoingExceptionEdges());
					
				} while(node != null && node.isOnPremise() != onPremise);
				
				ReqNode reqNode = new ReqNode();
				GetNode getNode = new GetNode();
				
				incomingControlEdges.add(null);
				
				ControlEdge controlEdge = new ControlEdge(reqNode, getNode);
				
				graph.addEdge(controlEdge);
				graph.addNode(reqNode);
				graph.addNode(getNode);
				
				Edge<? extends Node, ? extends Node> outgoingEdge = 
						node.getIncomingControlEdges().iterator().next();
				
				if (onPremise)
					cloudList.put(
						"cloud_operation_" + ++cloudOperationCount, concern);
				else
					premiseList.put(
						"premise_operation_" + ++premiseOperationCount, concern);
			}
		}		
	}
	
	private Cluster performCluster(Fragment fragment) {
		throw new UnsupportedOperationException();
	}
	
	private Coreography createCoreographyAndDataFlow(Cluster cluster) {
		throw new UnsupportedOperationException();
	}
}
