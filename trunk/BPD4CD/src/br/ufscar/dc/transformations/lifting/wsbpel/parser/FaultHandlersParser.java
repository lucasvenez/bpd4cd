package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import javax.activation.UnsupportedDataTypeException;

import org.w3c.dom.Element;

import br.ufscar.dc.gwm.construction.ExceptionHandling;
import br.ufscar.dc.transformations.lifting.ActivityParser;

public class FaultHandlersParser extends ActivityParser<Element, ExceptionHandling> {

	private static final long serialVersionUID = 4681205305978964611L;

	public FaultHandlersParser(Element activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ExceptionHandling parse() throws UnsupportedDataTypeException {
		// TODO Auto-generated method stub
		return null;
	}

}
