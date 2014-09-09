package br.ufscar.dc.gwm;

import java.util.HashSet;
import java.util.Set;

import br.ufscar.dc.gwm.edge.CommunicationEdge;
import br.ufscar.dc.gwm.edge.ControlEdge;
import br.ufscar.dc.gwm.edge.DataEdge;
import br.ufscar.dc.gwm.edge.ExceptionEdge;
import br.ufscar.dc.gwm.interfaces.IConcern;

public abstract class Node extends Attribute implements IConcern {

	private static final long serialVersionUID = -7715475042269142936L;

	protected String name;

	protected boolean onPremise;
	
	protected boolean restricted;
	
	private Graph parentGraph;

	protected Set<Edge<? extends Node,? extends Node>> incomingEdges = 
		new HashSet<Edge<? extends Node,? extends Node>>();
	
	protected Set<Edge<? extends Node,? extends Node>> outgoingEdges = 
		new HashSet<Edge<? extends Node,? extends Node>>();
	
	protected Scope scope;
	
	public Node(String name) {
		this.name = name;
	}
	
	public Node(String name, boolean onPremise) {
		this(name);
		this.onPremise = onPremise;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOnPremise() {
		return onPremise;
	}

	public void setOnPremise(boolean onPremise) {
		this.onPremise = onPremise;
	}

	public Scope getScope() {
		return scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}
	
	public boolean isRestricted() {
		return this.restricted;
	}
	
	public void setRestricted(boolean restricted) {
		this.restricted = restricted;
	}
	
	public Node getNextNode() {
		
		Set<ControlEdge> e = getOutgoingControlEdges();
		
		if (e.isEmpty())
			return null;
		
		return e.iterator().next().getEndNode();
	}

	public Set<Edge<? extends Node,? extends Node>> getOutgoingEdges() {
		
		return this.outgoingEdges;
	}
	
	@SuppressWarnings("unchecked")
	public Set<ControlEdge> getOutgoingControlEdges() {
		
		Set<ControlEdge> result = new HashSet<ControlEdge>();
		
		for (Edge<? extends Node, ? extends Node> e : this.outgoingEdges.toArray(new Edge[0])) 
			
			if (e instanceof ControlEdge) 
				
				result.add((ControlEdge)e);
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Set<ExceptionEdge> getOutgoingExceptionEdges() {
		
		Set<ExceptionEdge> result = new HashSet<ExceptionEdge>();
		
		for (Edge<? extends Node, ? extends Node> e : this.outgoingEdges.toArray(new Edge[0])) 
			
			if (e instanceof ExceptionEdge) 
				
				result.add((ExceptionEdge)e);
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Set<CommunicationEdge> getOutgoingCommunicationEdges() {
		
		Set<CommunicationEdge> result = new HashSet<CommunicationEdge>();
		
		for (Edge<? extends Node, ? extends Node> e : this.outgoingEdges.toArray(new Edge[0])) 
			
			if (e instanceof CommunicationEdge) 
				
				result.add((CommunicationEdge)e);
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Set<DataEdge> getOutgoingDataEdges() {
		
		Set<DataEdge> result = new HashSet<DataEdge>();
		
		for (Edge<? extends Node, ? extends Node> e : this.outgoingEdges.toArray(new Edge[0])) 
			
			if (e instanceof DataEdge) 
				
				result.add((DataEdge)e);
		
		return result;
	}

	public Set<Edge<? extends Node,? extends Node>> getIncomingEdges() {
		
		return this.incomingEdges;
	}
	
	@SuppressWarnings("unchecked")
	public Set<ControlEdge> getIncomingControlEdges() {
		
		Set<ControlEdge> result = new HashSet<ControlEdge>();
		
		for (Edge<? extends Node, ? extends Node> e : this.incomingEdges.toArray(new Edge[0])) 
			
			if (e instanceof ControlEdge && !(e instanceof CommunicationEdge) && !(e instanceof ExceptionEdge)) 
				
				result.add((ControlEdge)e);
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Set<ExceptionEdge> getIncomingExceptionEdges() {
		
		Set<ExceptionEdge> result = new HashSet<ExceptionEdge>();
		
		for (Edge<? extends Node, ? extends Node> e : this.incomingEdges.toArray(new Edge[0])) 
			
			if (e instanceof ExceptionEdge) 
				
				result.add((ExceptionEdge)e);
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Set<CommunicationEdge> getIncomingCommunicationEdges() {
		
		Set<CommunicationEdge> result = new HashSet<CommunicationEdge>();
		
		for (Edge<? extends Node, ? extends Node> e : this.incomingEdges.toArray(new Edge[0])) 
			
			if (e instanceof CommunicationEdge) 
				
				result.add((CommunicationEdge)e);
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Set<DataEdge> getIncomingDataEdges() {
		
		Set<DataEdge> result = new HashSet<DataEdge>();
		
		for (Edge<? extends Node, ? extends Node> e : this.incomingEdges.toArray(new Edge[0])) 
			
			if (e instanceof DataEdge) 
				
				result.add((DataEdge)e);
		
		return result;
	}
	
	public boolean addIncomingEdge(Edge<? extends Node,? extends Node> edge) {
		return this.incomingEdges.add(edge);
	}
	
	public boolean addOutgoingEdge(Edge<? extends Node,? extends Node> edge) {
		return this.outgoingEdges.add(edge);
	}
	
	public boolean hasIncomingEdge(Edge<? extends Node,? extends Node> edge) {
		return this.incomingEdges.contains(edge);
	}
	
	public boolean hasOutgoingEdge(Edge<? extends Node,? extends Node> edge) {
		return this.outgoingEdges.contains(edge);
	}

	public Graph getParentGraph() {
		return parentGraph;
	}

	public void setParentGraph(Graph parentGraph) {
		this.parentGraph = parentGraph;
	}
}
