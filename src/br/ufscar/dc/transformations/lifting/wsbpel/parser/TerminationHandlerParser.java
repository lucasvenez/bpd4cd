package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import javax.activation.UnsupportedDataTypeException;

import org.w3c.dom.Element;

import br.ufscar.dc.gwm.construction.FinalizationHandling;
import br.ufscar.dc.transformations.lifting.ActivityParser;

public class TerminationHandlerParser extends ActivityParser<Element, FinalizationHandling> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6339929399312141644L;

	public TerminationHandlerParser(Element activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public FinalizationHandling parse() throws UnsupportedDataTypeException {
		// TODO Auto-generated method stub
		return null;
	}

}
