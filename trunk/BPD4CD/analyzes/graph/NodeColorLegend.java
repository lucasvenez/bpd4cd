package graph; 

import processing.core.PApplet;

public class NodeColorLegend extends PApplet {

	private static final long serialVersionUID = 7593937316159819455L;
	
	private static final int FRAME_WIDTH = 100;
	
	private static final int FRAME_HEIGHT = 212;

	private static final int NODE_DIAMETER = 20;
	
	private static final float X = 18;
			
	private static final float Y = NODE_DIAMETER * .88f;

	private static final int[][] NODE_BORDER_COLOR = new int[][] {
		{ 79, 119, 154}, { 83, 113, 145}, { 87, 108, 137}, { 91, 103, 129},
		{ 95,  98, 121}, { 99,  92, 113}, {104,  87, 105}, {108,  82,  97},
		{112,  77,  89}, {116,  71,  81}, {120,  66,  73}, {125,  61,  65},
		{129,  56,  57}, {133,  50,  49}, {137,  45,  41}, {141,  40,  33},
		{146,  35,  25}
	};
	
	private static final int[][] NODE_FILL_COLOR = new int[][] { 
		{126, 186, 238}, {134, 174, 223}, {142, 163, 208}, {150, 152, 194},
		{158, 140, 179}, {166, 129, 165}, {174, 118, 150}, {182, 106, 136},
		{190,  95, 121}, {198,  84, 106}, {206,  72,  92}, {214,  61,  77},
		{222,  50,  63}, {230,  38,  48}, {238,  27,  34}, {246,  16,  19},
		{255,   5,   5}
	};

	public void setup() {

		size(FRAME_WIDTH, FRAME_HEIGHT);
		
		background(254);
			
		/*
		 * Node color legend
		 */
		for (int i = 0; i < NODE_FILL_COLOR.length; i++) {
			
			Node n = new Node();
			
			n.setEnvironment(this);
			
			rectMode(CENTER);
			noStroke();
			fill(242, 242, 242);
			rect(X * (i < 9 ? 1 : 3.5f), Y * (i < 9 ? i : i - 9) * 1.35f + 10, NODE_DIAMETER * .9f, NODE_DIAMETER * .9f);
			
			n.setBorderColor(NODE_BORDER_COLOR[i]);
			n.setFillColor(NODE_FILL_COLOR[i]);
			n.setDiameter(NODE_DIAMETER);
			n.setBorderWidth(2);
			
			n.setX(X * (i < 9 ? 1 : 3.5f));
			n.setY(Y * (i < 9 ? i : i - 9) * 1.35f + 10);
			
			n.draw();

			text(String.valueOf(i), (X - 4) * (i < 9 ? 1 : 4.1f) + NODE_DIAMETER * 1.1f, Y * (i < 9 ? i : i - 9) * 1.35f + 15);
		}
		
		exit();
		save("legend_node_color.png");
	}
	
	public static void main(String[] args) {

		PApplet.main(new String[] { "graph.NodeColorLegend" });

	}
}
