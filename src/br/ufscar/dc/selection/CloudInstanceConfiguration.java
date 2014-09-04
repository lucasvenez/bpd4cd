package br.ufscar.dc.selection;

public class CloudInstanceConfiguration {

	private double ram;

	private int vCPUs;

	private double frequency;

	private double hardDisk;

	private double pricePerHour;

	private double priceForStoring;

	private double priceForTransfering;

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

	public double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public double getPriceForStoring() {
		return priceForStoring;
	}

	public void setPriceForStoring(double priceForStoring) {
		this.priceForStoring = priceForStoring;
	}

	public double getPriceForTransfering() {
		return priceForTransfering;
	}

	public void setPriceForTransfering(double priceForTransfering) {
		this.priceForTransfering = priceForTransfering;
	}

}
