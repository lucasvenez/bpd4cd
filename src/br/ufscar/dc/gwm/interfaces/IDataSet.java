package br.ufscar.dc.gwm.interfaces;

import java.util.Set;

import br.ufscar.dc.gwm.DataItem;

public interface IDataSet {

	public Set<DataItem> getData();
	
	public void addData(DataItem data);
	
	public void removeData(DataItem data);
	
	public void removeData(int index) throws IndexOutOfBoundsException;
	
	public DataItem getData(int index) throws IndexOutOfBoundsException;

	public boolean haveData();
	
	public boolean hasData(DataItem data);
}
