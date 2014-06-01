package br.ufscar.dc.gwm;

import java.util.HashSet;
import java.util.Set;

public abstract class Node extends Attribute {

	private static final long serialVersionUID = -7715475042269142936L;

	protected String name;

	protected boolean onPremise;

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
}