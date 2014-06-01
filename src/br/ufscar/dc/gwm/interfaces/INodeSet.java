package br.ufscar.dc.gwm.interfaces;

import java.util.Set;

import br.ufscar.dc.gwm.Node;

public interface INodeSet {

	public Set<Node> getNodes();
	
	public void addNode(Node node);
	
	public void removeNode(Node node);
	
	public void removeNode(int index) throws IndexOutOfBoundsException;
	
	public Node getNode(int index) throws IndexOutOfBoundsException;

	public boolean haveNodes();
	
	public boolean hasNode(Node node);
}
