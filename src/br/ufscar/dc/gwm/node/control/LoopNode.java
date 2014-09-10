package br.ufscar.dc.gwm.node.control;

public class LoopNode extends ControlNode {

	private static final long serialVersionUID = 8573286163806988293L;

	private String condition;
	
	public LoopNode() {
		super("loop");
	}
	
	public LoopNode(String name, String condition) {
		super(name);
		this.condition = condition;		
	}
	
	public LoopNode(String condition) {
		super("loop");
		this.condition = condition;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
}
