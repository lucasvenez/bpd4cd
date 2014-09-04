package br.ufscar.dc.decomposition;

import br.ufscar.dc.gwm.Graph;

public class Cluster {

	private boolean sourceSubprocessOnPremise;
	
	private Graph sourceSubprocesses;
	
	private Graph firstSubprocess;
	
	private Graph secondSubprocess;
	
	public boolean isSourceSubprocessOnPremise() {
		return this.sourceSubprocessOnPremise;
	}
}
