package br.ufscar.dc.selection;

public class PremisseServerConfiguration {
	
	private double ram;

	private int vCPUs;

	private double frequency;

	private double hardDisk;
	
	private double bandwidth;
	
	public PremisseServerConfiguration() {
		
	}
	
	public PremisseServerConfiguration(double ram, int vCPUs, double frequency,
			double hardDisk, double bandwidth) {
		super();
		this.ram = ram;
		this.vCPUs = vCPUs;
		this.frequency = frequency;
		this.hardDisk = hardDisk;
		this.bandwidth = bandwidth;
	}

	public double getRam() {
		return ram;
	}

	public void setRam(double ram) {
		this.ram = ram;
	}

	public int getvCPUs() {
		return vCPUs;
	}

	public void setvCPUs(int vCPUs) {
		this.vCPUs = vCPUs;
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	public double getHardDisk() {
		return hardDisk;
	}

	public void setHardDisk(double hardDisk) {
		this.hardDisk = hardDisk;
	}

	public double getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(double bandwidth) {
		this.bandwidth = bandwidth;
	}
}
