package br.ufscar.dc.decomposition;

import br.ufscar.dc.gwm.Graph;

public class Cluster {

	private boolean sourceSubprocessOnPremise;
	
	private Graph sourceSubprocess;
	
	private Graph firstSubprocess;
	
	private Graph secondSubprocess;
	
	public boolean isSourceSubprocessOnPremise() {
		return this.sourceSubprocessOnPremise;
	}

	public Graph getSourceSubprocess() {
		return sourceSubprocess;
	}

	public void setSourceSubprocess(Graph sourceSubprocess) {
		this.sourceSubprocess = sourceSubprocess;
	}

	public Graph getFirstSubprocess() {
		return firstSubprocess;
	}

	public void setFirstSubprocess(Graph firstSubprocess) {
		this.firstSubprocess = firstSubprocess;
	}

	public Graph getSecondSubprocess() {
		return secondSubprocess;
	}

	public void setSecondSubprocess(Graph secondSubprocess) {
		this.secondSubprocess = secondSubprocess;
	}

	public void setSourceSubprocessOnPremise(boolean sourceSubprocessOnPremise) {
		this.sourceSubprocessOnPremise = sourceSubprocessOnPremise;
	}
}
