package br.ufscar.dc.decomposition;

import java.util.HashMap;
import java.util.Map;

import br.ufscar.dc.gwm.Graph;

public class Fragment {

	private Graph subprocess;
	
	private Map<String, Graph> concernsAllocatedForCloud = new HashMap<String, Graph>();
	
	private Map<String, Graph> concernsAllocatedForPremisse = new HashMap<String, Graph>();

	public Graph getSubprocess() {
		return subprocess;
	}

	public void setSubprocess(Graph subprocess) {
		this.subprocess = subprocess;
	}

	public Map<String, Graph> getConcernsAllocatedForCloud() {
		return concernsAllocatedForCloud;
	}

	public void setConcernsAllocatedForCloud(Map<String, Graph> concernsAllocatedForCloud) {
		this.concernsAllocatedForCloud = concernsAllocatedForCloud;
	}

	public Map<String, Graph> getConcernsAllocatedForPremisse() {
		return concernsAllocatedForPremisse;
	}

	public void setConcernsAllocatedForPremisse(
			Map<String, Graph> concernsAllocatedForPremisse) {
		this.concernsAllocatedForPremisse = concernsAllocatedForPremisse;
	}
	
}
