package graph;

import processing.core.PApplet;

public class EdgeColorLegend extends PApplet {

private static final long serialVersionUID = 7593937316159819455L;
	
	private static final int FRAME_WIDTH = 65;
	
	private static final int FRAME_HEIGHT = 212;

	public void setup() {

		size(FRAME_WIDTH, FRAME_HEIGHT);
	}
	
	public void draw() {
		
		background(254);
		
		strokeWeight(1);
		
		int j = 0, k = 10;
		
		final int PADDING = 5; 
		
		fill(0);
		
		for (float i = 0; i <= 100; i+= .5, j++) {
			
			stroke(i);
			strokeWeight(1f);
			line(10, j + PADDING, 35, j + PADDING);
			
			if (j % 20 == 0) {
				text(String.valueOf(k--), 40, j + PADDING + 5);
				
				stroke(254);
				strokeWeight(0.6f);
				line(10, j + PADDING, 15, j + PADDING);
				line(35, j + PADDING, 30, j + PADDING);
			}
		}
		
		exit();
		
		/*
		 * Exporting image as PNG
		 */
		save("legend_edge_color.png");
	}

	public static void main(String[] args) {

		PApplet.main(new String[] { "graph.EdgeColorLegend" });

	}
}
