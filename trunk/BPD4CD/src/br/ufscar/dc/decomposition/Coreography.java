package br.ufscar.dc.decomposition;

import java.util.HashSet;
import java.util.Set;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.edge.CommunicationEdge;
import br.ufscar.dc.gwm.edge.DataEdge;

public class Coreography {

	private Set<CommunicationEdge> communicationEdges = new HashSet<CommunicationEdge>();
	
	private Set<DataEdge> dataEdges = new HashSet<DataEdge>();
	
	private Graph mainProcess;
	
	private Set<Graph> subprocesses = new HashSet<Graph>();
	
	public Set<Graph> getSubprocesses() {
		return this.subprocesses;
	}
	
	public void addSubprocess(Graph subprocess) {
		this.subprocesses.add(subprocess);
		
		if (subprocesses.isEmpty())
			this.setMainProcess(subprocess);
	}
	
	public Set<CommunicationEdge> getCommunicationEdges() {
		return this.communicationEdges;
	}
	
	public void addCommunicationEdge(CommunicationEdge edge) {
		this.communicationEdges.add(edge);
	}
	
	public Set<DataEdge> getDataEdges() {
		return this.dataEdges;
	}
	
	public void addDataEdge(DataEdge edge) {
		this.dataEdges.add(edge);
	}

	public Graph getMainProcess() {
		return mainProcess;
	}

	public void setMainProcess(Graph mainProcess) {
		this.mainProcess = mainProcess;
	}
}
