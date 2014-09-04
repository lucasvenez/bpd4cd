package br.ufscar.dc.selection;

public class CostsWeight {

	private double executionCostWeight;

	private double monetaryCostWeight;

	private double privacityCostWeight;

	public CostsWeight(
			double executionCostWeight, 
			double monetaryCostWeight, 
			double privacityCostWeight) {
		
		this.setExecutionCostWeight(executionCostWeight);
		this.setMonetaryCostWeight(monetaryCostWeight);
		this.setPrivacityCostWeight(privacityCostWeight);
	}
	
	public double getExecutionCostWeight() {
		return executionCostWeight;
	}

	public void setExecutionCostWeight(double executionCostWeight) {
		this.executionCostWeight = executionCostWeight;
	}

	public double getMonetaryCostWeight() {
		return monetaryCostWeight;
	}

	public void setMonetaryCostWeight(double monetaryCostWeight) {
		this.monetaryCostWeight = monetaryCostWeight;
	}

	public double getPrivacityCostWeight() {
		return privacityCostWeight;
	}

	public void setPrivacityCostWeight(double privacityCostWeight) {
		this.privacityCostWeight = privacityCostWeight;
	}
	
}
