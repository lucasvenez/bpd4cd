package br.ufscar.dc.gwm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Graph {

	private HashMap<String,Object> attributes = new HashMap<String,Object>();

	private Set<Node> nodes = new HashSet<Node>();

	private Set<Edge> edges = new HashSet<Edge>();

	private Set<Scope> scopes = new HashSet<Scope>();

	private Set<DataItem> data = new HashSet<DataItem>();

	public HashMap<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(HashMap<String, Object> attributes) {
		this.attributes = attributes;
	}

	public Set<Node> getNodes() {
		return nodes;
	}

	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}

	public Set<Edge> getEdges() {
		return edges;
	}

	public void setEdges(Set<Edge> edges) {
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
}
