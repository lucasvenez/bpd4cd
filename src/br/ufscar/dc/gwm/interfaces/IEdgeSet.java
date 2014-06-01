package br.ufscar.dc.gwm.interfaces;

import java.util.Set;

import br.ufscar.dc.gwm.Edge;
import br.ufscar.dc.gwm.Node;

public interface IEdgeSet {

	public Set<? extends Edge<? extends Node,? extends Node>> getEdges();
	
	public void addEdge(Edge<? extends Node,? extends Node> edge);
	
	public void removeEdge(Edge<? extends Node,? extends Node> edge);
	
	public void removeEdge(int index) throws IndexOutOfBoundsException;
	
	public Edge<? extends Node,? extends Node> getEdge(int index) throws IndexOutOfBoundsException;

	public boolean haveEdges();
	
	public boolean hasEdge(Edge<? extends Node,? extends Node> edge);
	
	public Set<? extends Edge<? extends Node,? extends Node>> findEdge(Node startNode, Node endNode);
}
