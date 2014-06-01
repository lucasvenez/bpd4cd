package br.ufscar.dc.gwm;

import java.util.HashSet;
import java.util.Set;

import br.ufscar.dc.gwm.interfaces.IDataSet;
import br.ufscar.dc.gwm.interfaces.INamed;
import br.ufscar.dc.gwm.interfaces.INodeSet;

public class Scope extends Attribute implements INodeSet, IDataSet, INamed {

	private static final long serialVersionUID = -8417963492468725844L;

	private String name;
	
	private Set<Node> nodes = new HashSet<Node>();

	private Set<DataItem> data = new HashSet<DataItem>();
	
	public Scope(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Set<Node> getNodes() {
		return this.nodes;
	}
	
	@Override
	public void addNode(Node node) {
		if (node != null && node.getScope() == null) {
			nodes.add(node);
			node.setScope(this);
		}
	}
	
	@Override
	public void removeNode(Node node) {
		this.nodes.remove(node);
		node.setScope(null);
	}
	
	@Override
	public void removeNode(int index) throws IndexOutOfBoundsException {
		Node n = (Node) this.nodes.toArray()[index];
		
		this.nodes.remove(n);
		n.setScope(null);
	}
	
	@Override
	public Node getNode(int index) throws IndexOutOfBoundsException {
		return (Node) this.nodes.toArray()[index];
	}

	@Override
	public boolean haveNodes() {
		return this.nodes.isEmpty();
	}
	
	@Override
	public boolean hasNode(Node node) {
		return this.nodes.contains(node);
	}

	@Override
	public Set<DataItem> getData() {
		return this.data;
	}

	@Override
	public void addData(DataItem data) {
		if (data != null && data.getScope() == null) {
			this.data.add(data);
			data.setScope(this);
		}
	}

	@Override
	public void removeData(DataItem data) {
		if (data != null) {
			this.data.remove(data);
			data.setScope(null);			
		}
	}

	@Override
	public void removeData(int index) throws IndexOutOfBoundsException {
		if (index > -1) {
			DataItem d = (DataItem) this.data.toArray()[index];
			this.data.remove(d);
			d.setScope(null);
		}
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
		return this.data.contains(data);
	}
}
