package br.ufscar.dc.gwm.node.control;

public class IfNode extends ControlNode {

	private String condition;
	
	public IfNode(String condition) {
		super("if");
		this.condition = condition;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
}
