package br.ufscar.dc.decomposition;
import java.util.HashMap;
import java.util.Map;

import br.ufscar.dc.gwm.Edge;
import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.Node;

public class Decomposition {

	private Graph process;
	
	public Coreography performDecomposition() {
		
		Coreography result = null;
		
		result = createCoreographyAndDataFlow(
					performCluster(performFragmentation()));
		
		return result;
	}
	
	private Fragment performFragmentation() {
		
		Fragment result = null;
		
		Node node = process.getStartNode();

		boolean processLocation = node.isOnPremise();
			
		Map<String, Graph> cloudList   = new HashMap<String, Graph>();
		Map<String, Graph> premiseList = new HashMap<String, Graph>();
		
		for (node = node.getNextNode(); node != null; node = node.getNextNode()) {
			
			if (node.isOnPremise() != processLocation) {
				
				Edge<? extends Node, ? extends Node> incomingEdge = 
					node.getIncomingControlEdges().iterator().next();

				Graph concern = new Graph();
				
				concern.addNode(node);
			}
		}		
		
		return result;
		
	}
	
	private Cluster performCluster(Fragment fragment) {
		throw new UnsupportedOperationException();
	}
	
	private Coreography createCoreographyAndDataFlow(Cluster cluster) {
		throw new UnsupportedOperationException();
	}
}
