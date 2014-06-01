package br.ufscar.dc.gwm;

import java.util.HashSet;
import java.util.Set;

import br.ufscar.dc.gwm.edge.ExceptionEdge;

public class Exception extends Attribute {

	private static final long serialVersionUID = -835184432091782603L;

	private String type;

	private Set<ExceptionEdge> exceptionEdges = new HashSet<ExceptionEdge>();

	public Exception(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void addExceptionEdge(ExceptionEdge exceptionEdge) {
		this.exceptionEdges.add(exceptionEdge);
	}
	
	public boolean removeExceptionEdge(ExceptionEdge exceptionEdge) {
		return this.exceptionEdges.remove(exceptionEdge);
	}
	
	public boolean hasExceptionEdge(ExceptionEdge exceptionEdge) {
		return this.exceptionEdges.contains(exceptionEdge);
	}
	
	public ExceptionEdge getExceptionEdge(int index) throws IndexOutOfBoundsException {
		return (ExceptionEdge) this.exceptionEdges.toArray()[index];
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exception other = (Exception) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
