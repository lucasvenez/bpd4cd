package br.ufscar.dc.decomposition;

import java.util.HashSet;
import java.util.Set;

import br.ufscar.dc.gwm.Graph;

public class Fragment {

	private Graph subprocess;
	
	private Set<Graph> concernsAllocatedForCloud = new HashSet<Graph>();
	
	private Set<Graph> concernsAllocatedForPremisse = new HashSet<Graph>();

	public Graph getSubprocess() {
		return subprocess;
	}

	public void setSubprocess(Graph subprocess) {
		this.subprocess = subprocess;
	}

	public Set<Graph> getConcernsAllocatedForCloud() {
		return concernsAllocatedForCloud;
	}

	public void setConcernsAllocatedForCloud(Set<Graph> concernsAllocatedForCloud) {
		this.concernsAllocatedForCloud = concernsAllocatedForCloud;
	}

	public Set<Graph> getConcernsAllocatedForPremisse() {
		return concernsAllocatedForPremisse;
	}

	public void setConcernsAllocatedForPremisse(
			Set<Graph> concernsAllocatedForPremisse) {
		this.concernsAllocatedForPremisse = concernsAllocatedForPremisse;
	}
	
}
