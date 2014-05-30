package br.ufscar.dc.gwm.exception;

public class InvalidBranchException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9189932894706877016L;

	public InvalidBranchException() {
		super();
	}

	public InvalidBranchException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidBranchException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidBranchException(String message) {
		super(message);
	}

	public InvalidBranchException(Throwable cause) {
		super(cause);
	}
}
