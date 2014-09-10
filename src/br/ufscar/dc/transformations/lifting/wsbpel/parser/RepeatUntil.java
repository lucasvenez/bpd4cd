package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import javax.activation.UnsupportedDataTypeException;

import org.w3c.dom.Element;

import br.ufscar.dc.gwm.construction.Loop;
import br.ufscar.dc.transformations.lifting.ActivityParser;

public class RepeatUntil extends ActivityParser<Element, Loop> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4658703638281739744L;

	public RepeatUntil(Element activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Loop parse() throws UnsupportedDataTypeException {
		// TODO Auto-generated method stub
		return null;
	}

}
