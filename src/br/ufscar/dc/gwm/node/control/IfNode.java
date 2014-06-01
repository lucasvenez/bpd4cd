package br.ufscar.dc.gwm.node.control;

public class IfNode extends ControlNode {

	private static final long serialVersionUID = 4274056283285520755L;

	private String condition;
	
	public IfNode() {
		super("if");
	}
	
	public IfNode(String condition) {
		super("if");
		this.condition = condition;
	}

	public IfNode(String name, String condition) {
		super(name);
		this.condition = condition;
	}
	
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
}
