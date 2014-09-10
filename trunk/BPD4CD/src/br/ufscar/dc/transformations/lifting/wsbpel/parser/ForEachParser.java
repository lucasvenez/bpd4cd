package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import javax.activation.UnsupportedDataTypeException;

import org.w3c.dom.Element;

import br.ufscar.dc.gwm.construction.Loop;
import br.ufscar.dc.transformations.lifting.ActivityParser;

public class ForEachParser extends ActivityParser<Element, Loop> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4408192154457804372L;

	public ForEachParser(Element activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Loop parse() throws UnsupportedDataTypeException {
		// TODO Auto-generated method stub
		return null;
	}

}
