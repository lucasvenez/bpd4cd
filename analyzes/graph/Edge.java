package graph;

import processing.core.PApplet;

public class Edge {

	Node from;

	Node to;

	double edgeColor = 50;
	
	float edgeWidth = 0.35f; 
	
	PApplet environment;

	Edge(Node from, Node to) {
	
		this.from = from;
		
		this.to = to;
		
	}

	public void draw() {
		
		environment.stroke((float)edgeColor, 30f);
		
		environment.strokeWeight(edgeWidth);
		
		environment.line(from.getX(), from.getY(), to.getX(), to.getY());
	}

	public Node getFrom() {
		return from;
	}

	public void setFrom(Node from) {
		this.from = from;
	}

	public Node getTo() {
		return to;
	}

	public void setTo(Node to) {
		this.to = to;
	}

	public double getEdgeColor() {
		return edgeColor;
	}

	public void setEdgeColor(double edgeColor) {
		this.edgeColor = edgeColor;
	}
	
	public float getEdgeWidth() {
		return edgeWidth;
	}

	public void setEdgeWidth(float edgeWidth) {
		this.edgeWidth = edgeWidth;
	}

	public PApplet getEnvironment() {
		return environment;
	}

	public void setEnvironment(PApplet environment) {
		this.environment = environment;
	}
	
}
