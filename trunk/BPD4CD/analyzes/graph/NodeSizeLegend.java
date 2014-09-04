package graph;

import processing.core.PApplet;

public class NodeSizeLegend extends PApplet {

private static final long serialVersionUID = 7593937316159819455L;
	
	private static final int FRAME_WIDTH = 120;
	
	private static final int FRAME_HEIGHT = 212;

	public void setup() {

		size(FRAME_WIDTH, FRAME_HEIGHT);
		
		background(254);
		
		fill(0);
		
		for (int i = 16, j = 1; i < 105; i += 8, j++) {
			
			Node n = new Node();
			n.setEnvironment(this);
			
			n.setDiameter(CostGraph.MAX_NODE_DIAMETER * i / 100f);
			n.setBorderColor(79, 119, 154);
			n.setFillColor(126, 186, 238);
			
			n.setX(20 * (j < 7 ? 1 : 3.7f));
			n.setY(30 * (j < 7 ? j * 1.15f : (j - 6) * 1.15f) - 15);

			noStroke();
			fill(242, 242, 242);
			rectMode(CENTER);
			rect(20 * (j < 7 ? 1 : 3.7f), 30 * (j < 7 ? j * 1.15f: (j - 6) * 1.15f) - 15, 25, 25);
			
			n.draw();
			
			fill(0);
			text(String.valueOf(i), 40 * (j < 7 ? 1 : 2.35f), 30 * (j < 7 ? j * 1.15f : (j - 6) * 1.15f) - 10);
		}
		
		exit();
		
		/*
		 * Exporting image as PNG
		 */
		save("legend_node_size.png");
	}
	
	public static void main(String[] args) {

		PApplet.main(new String[] { "graph.NodeSizeLegend" });

	}
}
