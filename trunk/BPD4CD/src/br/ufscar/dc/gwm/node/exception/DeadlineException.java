package br.ufscar.dc.gwm.node.exception;

import br.ufscar.dc.gwm.Attribute;

public class DeadlineException extends Attribute {

	private static final long serialVersionUID = -5385990414537005261L;

	private String type;
	
	public DeadlineException(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
