package br.ufscar.dc.gwm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.ufscar.dc.gwm.edge.ControlEdge;
import br.ufscar.dc.gwm.edge.DataEdge;
import br.ufscar.dc.gwm.interfaces.IConcern;
import br.ufscar.dc.gwm.interfaces.IDataSet;
import br.ufscar.dc.gwm.interfaces.IEdgeSet;
import br.ufscar.dc.gwm.interfaces.INamed;
import br.ufscar.dc.gwm.interfaces.INodeSet;
import br.ufscar.dc.utils.SparseMatrix;

public class Graph extends Attribute implements IConcern, INodeSet, IEdgeSet, IDataSet, INamed {

	private static final long serialVersionUID = -8480919267006875680L;

	private String name;
	
	private Node startNode;
	
	private Set<Node> nodes = new HashSet<Node>();

	private Set<Edge<? extends Node, ? extends Node>> edges = new HashSet<Edge<? extends Node, ? extends Node>>();

	private Set<Scope> scopes = new HashSet<Scope>();

	private Set<DataItem> data = new HashSet<DataItem>();

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	public Node getStartNode() {
		return this.startNode;
	}
	
	public void setStartNode(Node startNode) {
		this.startNode = startNode;
	}
	
	public Set<Node> getEndNodes() {
		return new HashSet<Node>();
	}
	
	public Set<Node> getNodes() {
		return nodes;
	}

	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}

	public Set<Edge<? extends Node, ? extends Node>> getEdges() {
		return edges;
	}

	public void setEdges(Set<Edge<? extends Node, ? extends Node>> edges) {
		this.edges = edges;
	}

	public Set<Scope> getScopes() {
		return scopes;
	}

	public void setScopes(Set<Scope> scopes) {
		this.scopes = scopes;
	}

	public Set<DataItem> getData() {
		return data;
	}

	public void setData(Set<DataItem> data) {
		this.data = data;
	}

	@Override
	public void addData(DataItem data) {
		if (data != null)
			this.data.add(data);
	}

	@Override
	public void removeData(DataItem data) {
		if (data != null)
			this.data.remove(data);
	}

	@Override
	public void removeData(int index) throws IndexOutOfBoundsException {
		if (index > -1)
			this.data.remove(this.data.toArray()[index]);
	}

	@Override
	public DataItem getData(int index) throws IndexOutOfBoundsException {
		return (DataItem) this.data.toArray()[index];
	}

	@Override
	public boolean haveData() {
		return !this.data.isEmpty();
	}

	@Override
	public boolean hasData(DataItem data) {
		if (data != null)
			return this.data.contains(data);

		return false;
	}

	@Override
	public void addNode(Node node) {
		if (node != null) {
			this.nodes.add(node);
			
			if (this.startNode == null)
				this.startNode = node;
			
			node.setParentGraph(this);
		}
	}

	@Override
	public void removeNode(Node node) {
		if (node != null)
			this.nodes.remove(node);
	}

	@Override
	public void removeNode(int index) throws IndexOutOfBoundsException {
		if (index > -1)
			this.nodes.remove(this.nodes.toArray()[index]);
	}

	@Override
	public Node getNode(int index) throws IndexOutOfBoundsException {
		return (Node) this.nodes.toArray()[index];
	}

	@Override
	public boolean haveNodes() {
		return !this.nodes.isEmpty();
	}

	@Override
	public boolean hasNode(Node node) {
		return this.nodes.contains(node);
	}

	@Override
	public void addEdge(Edge<? extends Node, ? extends Node> edge) {
		if (edge != null)
			this.edges.add(edge);
	}

	@Override
	public void removeEdge(Edge<? extends Node, ? extends Node> edge) {
		if (edge != null)
			this.edges.remove(edge);
	}

	@Override
	public void removeEdge(int index) throws IndexOutOfBoundsException {
		if (index > -1)
			this.edges.remove(this.edges.toArray()[index]);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Edge<? extends Node, ? extends Node> getEdge(int index)
			throws IndexOutOfBoundsException {
		return (Edge<? extends Node, ? extends Node>) this.edges.toArray()[index];
	}

	@Override
	public boolean haveEdges() {
		return !this.edges.isEmpty();
	}

	@Override
	public boolean hasEdge(Edge<? extends Node, ? extends Node> edge) {
		if (edge != null)
			return this.edges.contains(edge);

		return false;
	}

	@Override
	public Set<Edge<? extends Node, ? extends Node>> findEdge(Node startNode,
			Node endNode) {

		Set<Edge<? extends Node, ? extends Node>> result = new HashSet<Edge<? extends Node, ? extends Node>>();

		Iterator<Edge<? extends Node, ? extends Node>> i = this.edges
				.iterator();

		for (Edge<? extends Node, ? extends Node> e = i.next(); i.hasNext(); e = i
				.next())
			if (e.getStartNode().equals(startNode)
					&& e.getEndNode().equals(endNode))
				result.add(e);

		return result;
	}

	private Set<Edge<? extends Node, ? extends Node>> findTypedEdge(
			Node startNode, Node endNode, Class<? extends Edge<? extends Node,? extends Node>> edgeType) {

		Set<Edge<? extends Node, ? extends Node>> result = new HashSet<Edge<? extends Node, ? extends Node>>();

		Iterator<Edge<? extends Node, ? extends Node>> i = this.edges
				.iterator();

		for (Edge<? extends Node, ? extends Node> e = i.next(); i.hasNext(); e = i
				.next())
			if (e.getStartNode().equals(startNode)
					&& e.getEndNode().equals(endNode)
					&& e.getClass().isAssignableFrom(edgeType))
				result.add(e);

		return result;
	}

	public Set<Edge<? extends Node, ? extends Node>> findControlEdge(
			Node startNode, Node endNode) {
		return this.findTypedEdge(startNode, endNode, ControlEdge.class);
	}

	public Set<Edge<? extends Node, ? extends Node>> findDataEdge(
			Node startNode, Node endNode) {
		return this.findTypedEdge(startNode, endNode, DataEdge.class);
	}
	
	public Set<Edge<? extends Node, ? extends Node>> findCommunicationEdge(
			Node startNode, Node endNode) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Edge<? extends Node, ? extends Node>> findExceptionEdge(
			Node startNode, Node endNode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public long getNumberOfNodes() {
		throw new UnsupportedOperationException();
	}
	
	public long getNumberOfDataItems() {
		return this.data.size();
	}

	public Graph setDistributionLocation(SparseMatrix<Boolean> sparseMatrix) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Node> getNodesInWorkflowSequence() {
		
		List<Node> result = new ArrayList<Node>();
		
		Node n = this.startNode;
		
		
		while (n != null) {
			
			result.add(n);
			
			n = n.getNextNode();
		}
		
		return result;
	}

	public Set<String> getOutgoingControlEdges(Node node) {
		return null;
	}
}
