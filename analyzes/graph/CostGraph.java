package graph; 

import processing.core.PApplet;

public class CostGraph extends PApplet {

	private static final long serialVersionUID = 7593937316159819455L;
	
	private static final int FRAME_WIDTH = 400;
	
	private static final int FRAME_HEIGHT = 400;

	private static final char DELIMITER = ',';
	
	public static final int MAX_NODE_DIAMETER = 40;

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
	
	public static double[] getMaxValues(String[] lines) {
		
		double[] result = new double[] {
			0.000000000000000000000000001,
			0.000000000000000000000000001, 
			0.000000000000000000000000001, 
			0.000000000000000000000000001};
		
		for (String line : lines) {
			
			double[] tmp = new double[4];
			
			for (int i = 0; i < 4; i++) {
				
				tmp[i] = Double.parseDouble(split(line, DELIMITER)[i]);
				
				if (tmp[i] > result[i])
					result[i] = tmp[i];
			}
		}
		
		return result;
	}

	public void setup() {

		size(FRAME_WIDTH, FRAME_HEIGHT);

		Node center = new Node();

		center.setEnvironment(this);

		center.setBorderWidth(2);
		center.setFillColor(198, 219, 239);
		center.setBorderColor(159, 173, 185);

		center.setDiameter(20);
		center.setX(FRAME_WIDTH / 2);
		center.setY(FRAME_WIDTH / 2);

		String[] lines = loadStrings("/home/lucas/workspace/BPD4CD/analyzes/data/selection_costs.csv");

		int nol = lines.length / 3;

		double[] maxValues = getMaxValues(lines);

		for (int k = 1; k < 4; k++) {
			
			background(254);
		
			int i = 0;

			Node[] nodes = new Node[nol];
		
			for (String line : lines) {
	
				double[] columns = new double[6];
	
				for (int j = 0; j < columns.length; j++) {
					try {
						if (j != 4) {
							columns[j] = Double.parseDouble((split(line, DELIMITER))[j]);
						} else { 
							columns[j] = Integer.parseInt((split(line, DELIMITER))[j], 2);
						}
					} catch(Exception e) {
						columns[j] = 0.0;
					}
				}
				
				if (columns[5] == k) {
	
					Node n = new Node();
	
					n.setEnvironment(this);
	
					n.setDiameter((float) (MAX_NODE_DIAMETER * (columns[0] / maxValues[0])));
					
					n.setFillColor(NODE_FILL_COLOR[(int)columns[2]]);
					n.setBorderColor(NODE_BORDER_COLOR[(int)columns[2]]);
	
					float radius = (float) (180 * (columns[3] / maxValues[3]));
	
					float angle  = TWO_PI / nol;
					
					n.setX((float) ((radius * sin(angle * i) + FRAME_WIDTH / 2)));
					n.setY((float) ((radius * cos(angle * i) + FRAME_HEIGHT / 2)));
	
					nodes[i] = n;
					
					Edge e = new Edge(center, n);
					e.setEnvironment(this);
					e.setEdgeColor(100 * (1 - columns[1] / maxValues[1]));
					
					e.draw();
					
					i++;
				}
	
			}
	
			/*
			 * Drawing nodes 
			 */
			for(Node n : nodes)
				n.draw();
			
			center.draw();
			
			exit();
			
			/*
			 * Exporting image as PNG
			 */
			save("cloud_instance_" + k + ".png");
		}
	}

	public static void main(String[] args) {

		PApplet.main(new String[] { "graph.CostGraph" });

	}
}
