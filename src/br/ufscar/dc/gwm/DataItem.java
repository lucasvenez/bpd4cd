package br.ufscar.dc.gwm;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import br.ufscar.dc.gwm.edge.DataEdge;
import br.ufscar.dc.gwm.interfaces.IEdgeSet;

/**
 * @author Lucas Venezian Povoa
 */
public class DataItem extends Attribute implements IEdgeSet {

	private static final long serialVersionUID = -5583218434370583667L;

	private String name;

	private String type;

	private Scope scope;

	private Set<DataEdge> edges = new HashSet<DataEdge>();
	
	public DataItem() {
		
	}
	
	public DataItem(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Scope getScope() {
		return scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataItem other = (DataItem) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public Set<? extends Edge<? extends Node, ? extends Node>> getEdges() {
		return edges;
	}

	public void setEdges(Set<DataEdge> edges) {
		this.edges = edges;
	}

	@Override
	public void addEdge(Edge<? extends Node, ? extends Node> edge) {
		if (edge != null)
			this.edges.add((DataEdge) edge);
	}

	@Override
	public void removeEdge(Edge<? extends Node, ? extends Node> edge) {

		if (edge != null && edges.contains(edge)) {

			Iterator<DataEdge> i = edges.iterator();

			for (DataEdge e = i.next(); i.hasNext(); e = i.next()) {

				if (e.equals(edge)) {

					e.removeData(this);
					this.edges.remove(edge);
					break;
				}
			}
		}
	}

	@Override
	public void removeEdge(int index) throws IndexOutOfBoundsException {

		if (index > -1) {
			DataEdge e = (DataEdge) this.edges.toArray()[index];
			e.removeData(this);
			this.edges.remove(e);
		}
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
		return this.edges.contains(edges);
	}

	@Override
	public Set<Edge<? extends Node, ? extends Node>> findEdge(Node startNode,
			Node endNode) {

		Set<Edge<? extends Node, ? extends Node>> result = new HashSet<Edge<? extends Node, ? extends Node>>();

		Iterator<? extends Edge<? extends Node, ? extends Node>> i = this.edges
				.iterator();

		for (Edge<? extends Node, ? extends Node> e = i.next(); i.hasNext(); e = i
				.next())
			if (e.getStartNode().equals(startNode)
					&& e.getEndNode().equals(endNode))
				result.add(e);

		return result;
	}
}
