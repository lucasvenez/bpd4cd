package graph;

import processing.core.PApplet;

public class Node {

	String label = "";
	
	int[] fillColor = {102, 166, 31};
	
	int[] borderColor = null;
	
	float borderWidth = 1;
	
	float x = PApplet.CENTER;
	
	float y = PApplet.CENTER;

	float diameter = 80;
	
	PApplet environment;
	
	public Node() {}
	
	public Node(String label) {
		this.label = label;
	}
	
	public void draw() {

		environment.fill(fillColor[0], fillColor[1], fillColor[2]);
		
		if (borderColor == null) {
			environment.noStroke();
		} else {
			environment.stroke(borderColor[0], borderColor[1], borderColor[2]);
			environment.strokeWeight(borderWidth);
		}
		
		environment.ellipse(x, y, diameter/2, diameter/2);
		
		environment.fill(0);
		
		environment.text(
			label, 
			x - (environment.textWidth(label)/2), 
			y + 3);
	}

	/**
	 * Setters and Getters
	 */
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int[] getFillColor() {
		return fillColor;
	}

	public void setFillColor(int r, int g, int b) {
		this.fillColor = new int[] {r, g, b};
	}
	
	public void setFillColor(int[] color) {
		if (color.length == 3)
			this.fillColor = color;
	}

	public int[] getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(int r, int g, int b) {
		this.borderColor = new int[] {r, g, b};
	}
	
	public void setBorderColor(int[] color) {
		if (color.length == 3)
			this.borderColor = color;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getDiameter() {
		return diameter;
	}

	public void setDiameter(float diameter) {
		this.diameter = diameter;
	}

	public PApplet getEnvironment() {
		return environment;
	}

	public void setEnvironment(PApplet environment) {
		this.environment = environment;
	}
	
	public float getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(float borderWidth) {
		this.borderWidth = borderWidth;
	}
}
