package br.ufscar.dc.gwm.node.exception;

public class DdlNode extends ExceptionNode {

	private static final long serialVersionUID = -9186531807129909438L;
	
	private DeadlineException deadline;
	
	public DdlNode() {
		super("ddl");
	}

	public DdlNode(DeadlineException deadline) {
		super("ddl");
		this.deadline = deadline;
	}

	public DeadlineException getDeadline() {
		return deadline;
	}

	public void setDeadline(DeadlineException deadline) {
		this.deadline = deadline;
	}
}
