package br.ufscar.dc.gwm.node.exception;

import br.ufscar.dc.gwm.Exception;

public class DeadlineException extends Exception {

	private static final long serialVersionUID = -5385990414537005261L;

	public DeadlineException(String type) {
		super(type);
	}

}
