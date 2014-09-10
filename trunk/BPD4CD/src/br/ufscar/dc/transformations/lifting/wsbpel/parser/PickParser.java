package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import javax.activation.UnsupportedDataTypeException;

import org.w3c.dom.Element;

import br.ufscar.dc.gwm.construction.ExclusiveExternalChoice;
import br.ufscar.dc.transformations.lifting.ActivityParser;

public class PickParser extends ActivityParser<Element, ExclusiveExternalChoice> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5552541091734944307L;

	public PickParser(Element activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ExclusiveExternalChoice parse() throws UnsupportedDataTypeException {
		// TODO Auto-generated method stub
		return null;
	}

}
