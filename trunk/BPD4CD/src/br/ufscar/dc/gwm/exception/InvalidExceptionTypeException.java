package br.ufscar.dc.gwm.exception;

public class InvalidExceptionTypeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8816399611079389052L;

	public InvalidExceptionTypeException() {
		super();
	}

	public InvalidExceptionTypeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidExceptionTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidExceptionTypeException(String message) {
		super(message);
	}

	public InvalidExceptionTypeException(Throwable cause) {
		super(cause);
	}
}
