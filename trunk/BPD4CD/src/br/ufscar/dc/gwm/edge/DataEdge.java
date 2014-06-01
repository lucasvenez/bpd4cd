package br.ufscar.dc.gwm.edge;

import java.util.HashSet;
import java.util.Set;

import br.ufscar.dc.gwm.DataItem;
import br.ufscar.dc.gwm.Edge;
import br.ufscar.dc.gwm.interfaces.IDataSet;
import br.ufscar.dc.gwm.node.control.ControlNode;

public class DataEdge extends Edge<ControlNode,ControlNode> implements IDataSet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -141718096658691770L;
	
	private Set<DataItem> data = new HashSet<DataItem>();

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
