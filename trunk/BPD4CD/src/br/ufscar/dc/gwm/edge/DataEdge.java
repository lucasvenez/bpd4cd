package br.ufscar.dc.gwm.edge;

import java.util.HashSet;
import java.util.Set;

import br.ufscar.dc.gwm.DataItem;
import br.ufscar.dc.gwm.Edge;
import br.ufscar.dc.gwm.Node;
import br.ufscar.dc.gwm.interfaces.IDataSet;

public class DataEdge extends Edge<Node, Node> implements IDataSet {

	private static final long serialVersionUID = -422382861509713499L;

	private Set<DataItem> data = new HashSet<DataItem>();

	public DataEdge(Node startNode, Node endNode) {
		super(startNode, endNode);
	}

	public DataEdge(Node startNode, Node endNode, DataItem... data) {
		super(startNode, endNode);

		for (DataItem d : data)
			if (d != null)
				this.addData(d);
	}

	@Override
	public Set<DataItem> getData() {
		return this.data;
	}

	@Override
	public void addData(DataItem data) {
		if (data != null) {
			this.data.add(data);
			data.addEdge(this);
		}
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
		return this.data.contains(data);
	}
}
